package ftn.PaymentCardCenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.PaymentCardCenter.models.MerchantAccount;



public interface MerchantAccountRepository extends JpaRepository<MerchantAccount,String> {

	public MerchantAccount findBymerchantId(String merchantId);
}
