package ftn.Bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.Bank.models.Payment;
import ftn.Bank.models.PaymentRequest;
import ftn.Bank.services.BankAccountService;

@RestController
@RequestMapping("/api/bank")
public class BankController {
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping(value="createPayment",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Payment createPayment(@RequestBody PaymentRequest paymentRequest) {
		//TODO: PROVERI ISPRAVNOST ZAHTEVA;
		//TODO: PODESI HOST DA GADJA BANKU GDE SE PLACA ZAHTEV
		
		Payment p=new Payment();
		p.setPayment_request(paymentRequest);
		p.setPayment_url("http://localhost:8082/#/paymentInput");
		
		return p;
	}

}
