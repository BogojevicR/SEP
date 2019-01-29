package ftn.Bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.Bank.models.PaymentModel;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel,Long> {

}
