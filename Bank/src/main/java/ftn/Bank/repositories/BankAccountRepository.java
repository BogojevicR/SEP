package ftn.Bank.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ftn.Bank.models.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount,String>{
	
	public BankAccount findBypan(String pan);
	
}

