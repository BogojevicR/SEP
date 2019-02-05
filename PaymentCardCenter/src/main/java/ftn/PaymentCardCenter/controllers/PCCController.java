package ftn.PaymentCardCenter.controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.PaymentCardCenter.models.MerchantAccount;
import ftn.PaymentCardCenter.models.PCCRequest;
import ftn.PaymentCardCenter.models.PCCResponse;
import ftn.PaymentCardCenter.requests.Requests;
import ftn.PaymentCardCenter.services.PCCService;

@RestController
@RequestMapping("/api/pcc")
public class PCCController {
	
	@Autowired
	public PCCService pccService;
	
	
	@CrossOrigin(origins = "http://localhost:8083")
	@RequestMapping(value="makePCCRequest",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String makePCCRequest(@RequestBody PCCRequest pccRequest) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("PCC prosledjuje zahtev banci prodavca!");
		
		pccService.savePayment(pccRequest);
	
		String jsonInString = new ObjectMapper().writeValueAsString(pccRequest);
		String[] splited = pccRequest.getBuyersPan().split("\\s+");
		
		String responseString=new Requests().makePostRequest("http://localhost:"+splited[0]+"/api/bank/receivePCCRequest", jsonInString);
		return responseString;
	}
	
	@CrossOrigin(origins = "http://localhost:8083")
	@RequestMapping(value="/receivePCCResponse",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String receivePCCResponse(@RequestBody PCCResponse pccResponse) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("PCC dobija odgovor od banke kupca!");
		
		MerchantAccount ma=pccService.getMerchantAccount(pccResponse);
		String jsonInString = new ObjectMapper().writeValueAsString(pccResponse);
		String repsonseString=new Requests().makePostRequest("http://localhost:"+ma.getClientAccount().getPortNumber()+"/api/bank/finalizePCCTransfer", jsonInString);
		
		return repsonseString;
	}

}
