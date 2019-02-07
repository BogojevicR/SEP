package ftn.NaucnaCentralaR.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.NaucnaCentralaR.models.Korisnici;
import ftn.NaucnaCentralaR.services.KorisniciService;

@RestController
@RequestMapping("/api/centrala")
public class KorisniciController {

	@Autowired
	public KorisniciService korisniciService;
	
	@RequestMapping(value="create", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Korisnici createBankAccount(@RequestBody Korisnici korisnik) throws ParseException {
		return korisniciService.save(korisnik);	
	}
	
	@RequestMapping(value="login/{email}/{password}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Korisnici login(@PathVariable String email,@PathVariable String password) {
		return korisniciService.login(email,password);	
	}
	
}
