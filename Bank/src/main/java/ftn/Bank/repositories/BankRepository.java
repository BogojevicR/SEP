package ftn.Bank.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.Bank.models.Bank;

public interface BankRepository extends JpaRepository<Bank,String>{
	
	public Bank findByport(String port);
	
}

