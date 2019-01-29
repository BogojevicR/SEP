package ftn.Bank.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import ftn.Bank.models.Bank;
import ftn.Bank.models.BankAccount;
import ftn.Bank.models.PaymentModel;
import ftn.Bank.models.PaymentRequest;
import ftn.Bank.models.Transaction;
import ftn.Bank.models.TransferRequest;
import ftn.Bank.repositories.BankAccountRepository;
import ftn.Bank.repositories.MerchantAccountRepository;
import ftn.Bank.repositories.PaymentRepository;
import ftn.Bank.repositories.PaymentRequestRepository;
import ftn.Bank.repositories.TransactionRepository;
import ftn.Bank.services.BankAccountService;

@RestController
@RequestMapping("/api/bank")
public class BankController {
	
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private BankAccountRepository bankAccountRep;
	@Autowired
	private MerchantAccountRepository merchantAccountRep;
	@Autowired
	private PaymentRequestRepository paymentRequestRep;
	@Autowired
	private PaymentRepository paymentRep;
	@Autowired
	private TransactionRepository transactionRep;
	
	@RequestMapping(value="/{port}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Bank getBank(@PathVariable String port) {
		return bankAccountService.getBank(port);
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping(value="createPayment",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public PaymentModel createPayment(@RequestBody PaymentRequest paymentRequest) {
		//TODO: PROVERI ISPRAVNOST ZAHTEVA;
		//TODO: PODESI HOST DA GADJA BANKU GDE SE PLACA ZAHTEV
		Date date =new Date();
		paymentRequest.setMerchantTimestamp(date);
		PaymentModel p=new PaymentModel();
		p.setPaymentRequest(paymentRequest);
		p.setPaymentUrl("http://localhost:8082/#/paymentInput");
		paymentRequestRep.save(paymentRequest);
		paymentRep.save(p);
		return p;
	}
	
	
	@RequestMapping(value="getPayment/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public PaymentModel login(@PathVariable Long id) {
		System.out.println(id);
		return paymentRep.findOne(id);	
	}
	
	@RequestMapping(value = "/finalizeTransfer",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction  finalizeTransfer(@RequestBody TransferRequest request){
		Transaction transaction=new Transaction();
		BankAccount buyer=bankAccountRep.findBypan(request.getBuyer().getPan());
		BankAccount seller=merchantAccountRep.findBymerchantId(request.getPayment().getPaymentRequest().getMerchantId()).getClientAccount();
		if(buyer.checkInfo(request.getBuyer())){
			System.out.println("Izvrsena transakcija!");
			buyer.removeFunds(request.getPayment().getPaymentRequest().getAmount());
			seller.addFunds(request.getPayment().getPaymentRequest().getAmount());
			bankAccountRep.save(seller);
			bankAccountRep.save(buyer);
			transaction.setAcquirerTimestamp(new Date());
			transaction.setMerchantOrderId(request.getPayment().getPaymentRequest().getMerchantOrderId());
			transaction.setPaymentId(request.getPayment().getPaymentId());
			transactionRep.save(transaction);
			return transaction;
		}else {
			System.out.println("Izvrsena nije transakcija!");
			return null;
		}
		
		
		
	}

}
