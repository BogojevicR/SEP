package ftn.Bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.Bank.models.PaymentRequest;
import ftn.Bank.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
