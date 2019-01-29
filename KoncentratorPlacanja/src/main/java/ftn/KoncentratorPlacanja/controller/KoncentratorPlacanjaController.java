package ftn.KoncentratorPlacanja.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.KoncentratorPlacanja.models.BitcoinRequest;

@RestController
@RequestMapping("/api/KP")
public class KoncentratorPlacanjaController {
	
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping(value="createPayment",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String createPayment(@RequestBody BitcoinRequest bitcoinRequest) {

		
		
		
		return "url";
	}

}
