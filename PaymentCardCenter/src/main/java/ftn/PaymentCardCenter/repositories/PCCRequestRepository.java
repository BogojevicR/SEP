package ftn.PaymentCardCenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.PaymentCardCenter.models.PCCRequest;



public interface PCCRequestRepository extends JpaRepository<PCCRequest,String> {

}
