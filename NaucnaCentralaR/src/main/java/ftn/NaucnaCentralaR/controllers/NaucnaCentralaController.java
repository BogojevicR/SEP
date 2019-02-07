package ftn.NaucnaCentralaR.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.NaucnaCentralaR.models.Casopisi;
import ftn.NaucnaCentralaR.models.Rad;
import ftn.NaucnaCentralaR.services.NaucnaCentralaService;

@RestController
@RequestMapping("/api/nc")
public class NaucnaCentralaController {

	@Autowired
	private NaucnaCentralaService naucnaCentralaService;
	
	@RequestMapping(value="/casopis/getAll",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Casopisi> getAllCasopisi(){
		return naucnaCentralaService.getAllCasopisi();
	}
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping(value="/casopis/getOne/{casopisId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Casopisi getCasopis(@PathVariable Long casopisId) throws ParseException {
		return naucnaCentralaService.getCasopis(casopisId);
	}
	
	@RequestMapping(value="/casopis/dodajRad", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Rad dodajRadUCasopis(@RequestBody Rad rad) throws ParseException {
		return naucnaCentralaService.dodajRadUCasopis(rad);
	}
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping(value="/casopis/kupiCasopis/{email}/{casopisId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Casopisi kupiCasopis(@PathVariable String email,@PathVariable Long casopisId) throws ParseException {
		return naucnaCentralaService.kupiCasopis(email,casopisId);
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping(value="/casopis/pretplatiSe/{email}/{casopisId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Casopisi pretplatiSe(@PathVariable String email,@PathVariable Long casopisId) throws ParseException {
		return naucnaCentralaService.pretplatiSe(email,casopisId);
	}
}
