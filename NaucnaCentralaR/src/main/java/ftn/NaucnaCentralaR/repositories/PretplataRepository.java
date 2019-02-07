package ftn.NaucnaCentralaR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.NaucnaCentralaR.models.Casopisi;
import ftn.NaucnaCentralaR.models.Pretplata;

public interface PretplataRepository extends JpaRepository<Pretplata, Long> {
	public Pretplata findBycasopisId(Long id);
}
