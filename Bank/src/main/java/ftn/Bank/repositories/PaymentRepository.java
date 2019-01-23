package ftn.Bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.Bank.models.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
