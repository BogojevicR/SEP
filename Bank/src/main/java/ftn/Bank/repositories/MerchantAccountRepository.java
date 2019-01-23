package ftn.Bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.Bank.models.MerchantAccount;

public interface MerchantAccountRepository extends JpaRepository<MerchantAccount,String> {

}
