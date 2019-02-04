package ftn.Bank.services;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.Bank.models.BankAccount;
import ftn.Bank.models.PCCRequest;
import ftn.Bank.models.PCCResponse;
import ftn.Bank.models.PaymentRequest;
import ftn.Bank.models.Transaction;
import ftn.Bank.models.TransferRequest;
import ftn.Bank.repositories.BankAccountRepository;
import ftn.Bank.repositories.BankRepository;
import ftn.Bank.repositories.MerchantAccountRepository;
import ftn.Bank.repositories.PCCResponseRepository;
import ftn.Bank.repositories.PaymentRepository;
import ftn.Bank.repositories.PaymentRequestRepository;
import ftn.Bank.repositories.TransactionRepository;
import ftn.Bank.requests.Requests;

@Service
public class BankService {
	@Autowired
	public BankAccountRepository bankAccountRep;
	@Autowired 
	public MerchantAccountRepository merchantAccountRep;
	@Autowired
	public TransactionRepository transactionRep;
	@Autowired
	public BankRepository bankRep;
	@Autowired
	private PCCResponseRepository pccResponseRep;
	@Autowired
	private PaymentRequestRepository paymentRequestRep;
	@Autowired
	private PaymentRepository paymentRep;

	public  Transaction finalizeTransfer(TransferRequest request) throws JsonGenerationException, JsonMappingException, IOException  {
		Transaction transaction=new Transaction();
		BankAccount buyer=bankAccountRep.findBypan(request.getBuyer().getPan());
		BankAccount seller=merchantAccountRep.findBymerchantId(request.getPayment().getPaymentRequest().getMerchantId()).getClientAccount();
		
		if(!buyer.checkInfo(request.getBuyer())){
			System.out.println("Transackija nije izvrsena! Nema podataka o datoj kartici!");
			return null;
		}
		
		// ACCOUNT ARE IN SAME BANK
		if(buyer.getPortNumber().equals(seller.getPortNumber())) {
			if(!buyer.removeFunds(request.getPayment().getPaymentRequest().getAmount())) {
				System.out.println("Transackija nije izvrsena! Nema dovoljno novca na racunu!");
				return null;
			}
			System.out.println("Transackija je uspesno izvrsena!!");
			seller.addFunds(request.getPayment().getPaymentRequest().getAmount());
			bankAccountRep.save(seller);
			bankAccountRep.save(buyer);
			transaction.setAcquirerTimestamp(new Date());
			transaction.setMerchantOrderId(request.getPayment().getPaymentRequest().getMerchantOrderId());
			transaction.setPaymentId(request.getPayment().getPaymentId());
			transactionRep.save(transaction);
			return transaction;
			
		}else { 
			System.out.println("Banke su na razlicitim portovima!");
			PCCRequest pcc=new PCCRequest();
			pcc.setAcquirerTimestamp(new Date());
			pcc.setBuyersPan(buyer.getPan());
			pcc.setMerchantOrderId(request.getPayment().getPaymentRequest().getMerchantOrderId());
			String jsonInString = new ObjectMapper().writeValueAsString(pcc);

			String trans=new Requests().makePostRequest("http://localhost:8085/api/pcc/makePCCRequest", jsonInString);

			
			if(trans.equals("")) {
				return null;
			}else {
				Transaction t=new ObjectMapper().readValue(trans, Transaction.class);
				System.out.println(t.toString());
				return t;
			}
			
			
		}
		
		
	}
	
	
	public String receivePCCRequest(PCCRequest pccRequest) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Banka kupca prima Request za naplatu!");
		
		PaymentRequest pr =paymentRequestRep.getOne(pccRequest.getMerchantOrderId());
		BankAccount buyer=bankAccountRep.getOne(pccRequest.getBuyersPan());
		if(!buyer.addReservedFunds(pr.getAmount())) {
			System.out.println("Nema dovoljno novca na racunu!");
			return null;
		}
		bankAccountRep.save(buyer);
		PCCResponse pccResponse=new PCCResponse().makeFromPCCRequest(pccRequest);
		pccResponseRep.save(pccResponse);

		String jsonInString = new ObjectMapper().writeValueAsString(pccResponse);
	
		String responseString=new Requests().makePostRequest("http://localhost:8085/api/pcc/receivePCCResponse", jsonInString);
		return responseString;
	}


	public Transaction finalizePCCTransfer(PCCResponse pccResponse) {
		PaymentRequest pr=paymentRequestRep.getOne(pccResponse.getMerchantOrderId());
		BankAccount seller=merchantAccountRep.getOne(pr.getMerchantId()).getClientAccount();
		BankAccount buyer=bankAccountRep.getOne(pccResponse.getBuyersPan());
		seller.addFunds(pr.getAmount());
		buyer.removeReservedFunds(pr.getAmount());
		bankAccountRep.save(buyer);
		bankAccountRep.save(seller);
		
		Transaction transaction=new Transaction();
		transaction.setAcquirerOrderId(pccResponse.getAcquirerOrderId());
		transaction.setAcquirerTimestamp(pccResponse.getIssuerTimestamp());
		transaction.setMerchantOrderId(pccResponse.getMerchantOrderId());	
		transaction.setPaymentId(paymentRep.findBypaymentRequestMerchantOrderId(pccResponse.getMerchantOrderId()).getPaymentId());
		transactionRep.save(transaction);

		
		return transaction;
		
	}
}
