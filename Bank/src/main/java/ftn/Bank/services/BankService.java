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

			new Requests().makePostRequest("http://localhost:8085/api/pcc/finalizePayment", jsonInString);
			return null;
			
		}
		
		
	}
	
	
	public void finalizePCCPayment(PCCRequest pccRequest) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("USAO U FINALIZE PCC!");
		PaymentRequest pr =paymentRequestRep.getOne(pccRequest.getMerchantOrderId());
		BankAccount buyer=bankAccountRep.getOne(pccRequest.getBuyersPan());
		if(!buyer.addReservedFunds(pr.getAmount())) {
			//odbaci
		}
		PCCResponse pccResponse=new PCCResponse();
		pccResponse.setAcquirerOrderId(pccRequest.getAcquirerOrderId());
		pccResponse.setAcquirerTimestamp(pccRequest.getAcquirerTimestamp());
		pccResponse.setBuyersPan(pccRequest.getBuyersPan());
		pccResponse.setMerchantOrderId(pccRequest.getMerchantOrderId());
		pccResponse.setIssuerTimestamp(new Date());
		pccResponseRep.save(pccResponse);
		System.out.println("DOSAO OVDE");
		String jsonInString = new ObjectMapper().writeValueAsString(pccResponse);
		System.out.println("DOSAO OVDE2");
		new Requests().makePostRequest("http://localhost:8085/api/pcc/finalizePCCPayment", jsonInString);
		//TODO URADI TACKU 6.
	}
}
