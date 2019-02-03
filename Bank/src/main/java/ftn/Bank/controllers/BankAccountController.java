package ftn.Bank.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.Bank.models.BankAccount;
import ftn.Bank.models.MerchantAccount;
import ftn.Bank.services.BankAccountService;

@RestController
@RequestMapping("/api/bankAccount")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;
	
	
	@RequestMapping(value="create", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) throws ParseException {
		return bankAccountService.save(bankAccount);	
	}
	
	@RequestMapping(value="createMerchant", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public MerchantAccount createBankAccount(@RequestBody MerchantAccount merchantAccount) throws ParseException {
		return bankAccountService.saveMerchant(merchantAccount);	
	}
	
	@RequestMapping(value="login/{pan}/{secret}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public BankAccount login(@PathVariable String pan,@PathVariable String secret) {
		return bankAccountService.login(pan,secret);	
	}
	
	@RequestMapping(value="merchantlogin/{merchantId}/{password}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public MerchantAccount merchantlogin(@PathVariable String merchantId ,@PathVariable String password) {
		return bankAccountService.merchantlogin(merchantId,password);	
	}
	
	@RequestMapping(value="reserve/{pan}/{ammount}",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean reserveFunds(@PathVariable String pan,@PathVariable double ammount) {
		return bankAccountService.reserveFunds(pan,ammount);
	}
	
	@RequestMapping(value="transfer/{buyerPan}/{sellerPan}/{ammount}",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean reserveFunds(@PathVariable String buyerPan,@PathVariable String sellerPan,@PathVariable double ammount) {
		return bankAccountService.transferFunds(buyerPan,sellerPan,ammount);
	}
	
	
	
	

}
