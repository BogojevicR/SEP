package ftn.KoncentratorPlacanja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.KoncentratorPlacanja.models.PaymentRequest;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest,Long> {

}
