package ftn.Bank.controllers;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.Bank.models.Bank;
import ftn.Bank.models.MerchantAccount;
import ftn.Bank.models.PCCRequest;
import ftn.Bank.models.PCCResponse;
import ftn.Bank.models.PaymentModel;
import ftn.Bank.models.PaymentRequest;
import ftn.Bank.models.Transaction;
import ftn.Bank.models.TransactionMessage;
import ftn.Bank.models.TransferRequest;
import ftn.Bank.repositories.BankAccountRepository;
import ftn.Bank.repositories.MerchantAccountRepository;
import ftn.Bank.repositories.PaymentRepository;
import ftn.Bank.repositories.PaymentRequestRepository;
import ftn.Bank.services.BankAccountService;
import ftn.Bank.services.BankService;

@RestController
@RequestMapping("/api/bank")
public class BankController {
	
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private BankService bankService;
	
	@Autowired
	private BankAccountRepository bankAccountRep;
	@Autowired
	private MerchantAccountRepository merchantAccountRep;
	@Autowired
	private PaymentRequestRepository paymentRequestRep;
	@Autowired
	private PaymentRepository paymentRep;
	
	
	
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
		MerchantAccount merchant=merchantAccountRep.findBymerchantId(paymentRequest.getMerchantId());
		
		p.setPaymentUrl("http://localhost:"+merchant.getClientAccount().getPortNumber()+"/#/paymentInput");
		paymentRequestRep.save(paymentRequest);
		paymentRep.save(p);
		return p;
	}
	
	
	@RequestMapping(value="getPayment/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public PaymentModel login(@PathVariable Long id) {

		return paymentRep.findOne(id);	
	}
	
	@RequestMapping(value = "/finalizeTransfer",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionMessage  finalizeTransfer(@RequestBody TransferRequest request) throws NullPointerException,  IOException{
		TransactionMessage transaction=bankService.finalizeTransfer(request);
		return transaction;
		
	}
	
	
	@CrossOrigin(origins = "http://localhost:8085")
	@RequestMapping(value="/receivePCCRequest",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String  receivePCCRequest(@RequestBody PCCRequest pccRequest) throws JsonGenerationException, JsonMappingException, IOException {

		String responseString=bankService.receivePCCRequest(pccRequest);
		return responseString;
	}
	
	@CrossOrigin(origins = "http://localhost:8085")
	@RequestMapping(value="/finalizePCCTransfer",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String  finalizePCCTransfer(@RequestBody PCCResponse pccResponse) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Finishing PCCPayment!");
		Transaction transaction=bankService.finalizePCCTransfer(pccResponse);
		
		String jsonInString = new ObjectMapper().writeValueAsString(transaction);
		return jsonInString;
		
		
	}
	

}
