package ftn.NaucnaCentralaR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.NaucnaCentralaR.models.Casopisi;
import ftn.NaucnaCentralaR.models.Korisnici;

public interface CasopisiRepository extends JpaRepository<Casopisi, Long> {
	public Casopisi findByid(Long id);

}
