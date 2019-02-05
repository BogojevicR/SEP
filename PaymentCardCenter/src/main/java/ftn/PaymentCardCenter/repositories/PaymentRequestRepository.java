package ftn.PaymentCardCenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.PaymentCardCenter.models.PaymentRequest;



public interface PaymentRequestRepository extends JpaRepository<PaymentRequest,Long> {

}
