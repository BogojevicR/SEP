package ftn.Bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.Bank.models.PaymentRequest;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest,Long> {

}
