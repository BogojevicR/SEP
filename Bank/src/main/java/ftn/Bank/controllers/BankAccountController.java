package ftn.Bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.Bank.models.Bank;
import ftn.Bank.models.BankAccount;
import ftn.Bank.services.BankAccountService;

@RestController
@RequestMapping("/api/bankAccount")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;
	
	
	@RequestMapping(value="create", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean createBankAccount(@RequestBody BankAccount bankAccount) {
		return bankAccountService.save(bankAccount);	
	}
	
	@RequestMapping(value="login/{pan}/{secret}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public BankAccount login(@PathVariable String pan,@PathVariable String secret) {
		return bankAccountService.login(pan,secret);	
	}
	
	@RequestMapping(value="reserve/{pan}/{ammount}",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean reserveFunds(@PathVariable String pan,@PathVariable double ammount) {
		return bankAccountService.reserveFunds(pan,ammount);
	}
	
	@RequestMapping(value="transfer/{buyerPan}/{sellerPan}/{ammount}",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean reserveFunds(@PathVariable String buyerPan,@PathVariable String sellerPan,@PathVariable double ammount) {
		return bankAccountService.transferFunds(buyerPan,sellerPan,ammount);
	}
	
	@RequestMapping(value="bank/{port}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Bank getBank(@PathVariable String port) {
		return bankAccountService.getBank(port);
	}

}
