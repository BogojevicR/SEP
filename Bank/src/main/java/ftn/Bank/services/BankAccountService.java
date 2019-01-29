package ftn.Bank.services;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.Bank.models.Bank;
import ftn.Bank.models.BankAccount;
import ftn.Bank.models.MerchantAccount;
import ftn.Bank.repositories.BankAccountRepository;
import ftn.Bank.repositories.BankRepository;
import ftn.Bank.repositories.MerchantAccountRepository;

@Service
public class BankAccountService {
	@Autowired
	public BankAccountRepository bankAccountRep;
	@Autowired 
	public MerchantAccountRepository merchantAccountRep;
	
	@Autowired
	public BankRepository bankRep;
	
	public BankAccount save(BankAccount bankAccount) throws ParseException {
		
		bankAccount.generatePan();

		if(bankAccountRep.findBypan(bankAccount.getPan())==null) {
			bankAccountRep.save(bankAccount);
			return bankAccount;
		}
		return null;
	}
	
public MerchantAccount saveMerchant(MerchantAccount merchantAccount) throws ParseException {
		
	merchantAccount.getClientAccount().generatePan();
	merchantAccount.generateMerchantAccount();

	
	if(merchantAccountRep.findOne(merchantAccount.getMerchantId())==null) {
		
		if(bankAccountRep.findBypan(merchantAccount.getClientAccount().getPan())==null) {
			bankAccountRep.save(merchantAccount.getClientAccount());
			merchantAccountRep.save(merchantAccount);
			return merchantAccount;
		}else {
			return null;
		}
	}
	return null;
	}
	
public BankAccount login(String pan,String secret) throws NullPointerException {
		BankAccount ba=bankAccountRep.findBypan(pan);
		try {
			if(ba.getSecurityCode().equals(secret)) {
				return ba;
			}
		} catch (NullPointerException npe) {
	
		}
		return null;
		
			 
	}

public MerchantAccount merchantlogin(String merchantId,String password) throws NullPointerException {

	MerchantAccount ma=merchantAccountRep.findBymerchantId(merchantId);
	try {
		if(ma.getMerchantPassword().equals(password)) {
			return ma;
		}
	} catch (NullPointerException npe) {

	}
	return null;
	
		 
}
	
	public boolean reserveFunds(String pan ,double ammount) {
		BankAccount bankAccount=bankAccountRep.findBypan(pan);
		if(bankAccount==null)
			return false;
		if(bankAccount.getAvailableFunds()-ammount>=0) {
			bankAccount.removeFunds(ammount);
			bankAccount.addReservedFunds(ammount);
			bankAccountRep.save(bankAccount);
			return true;
		}
		return false;
	}
	
	public boolean transferFunds(String buyerpan, String sellerpan, double ammount) {
		BankAccount buyer=bankAccountRep.findBypan(buyerpan);
		BankAccount seller=bankAccountRep.findBypan(sellerpan);
		if(buyer==null || seller==null) {
			return false;
		}
		if(buyer.getReservedFunds()<ammount) {
			return false;
		}else {
			buyer.removeReservedFunds(ammount);
			seller.addFunds(ammount);
			bankAccountRep.save(buyer);
			bankAccountRep.save(seller);
			return true;
		}
		
	}
	
	public Bank getBank(String port) {
		return bankRep.findByport(port);
	}

}
