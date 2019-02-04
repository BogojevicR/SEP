package ftn.KoncentratorPlacanja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.KoncentratorPlacanja.models.BitcoinRequest;
import ftn.KoncentratorPlacanja.models.PaymentRequest;
import ftn.KoncentratorPlacanja.services.KPService;

@RestController
@RequestMapping("/api/KP")
public class KoncentratorPlacanjaController {
	@Autowired
	public KPService kpService;
	
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping(value="createPayment",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String createPayment(@RequestBody BitcoinRequest bitcoinRequest) {

		return "url";
	}
	

	@RequestMapping(value="getPaymentRequest/{merchantId}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public PaymentRequest getPaymentRequest(@PathVariable Long merchantId) {
		PaymentRequest pr=kpService.getPaymentRequest(merchantId);
		PaymentRequest response=new PaymentRequest();
		response.setAmount(pr.getAmount());
		response.setMerchantId(pr.getMerchantId());
		response.setMerchantOrderId(pr.getMerchantOrderId());
		return response;
	}

	
}
