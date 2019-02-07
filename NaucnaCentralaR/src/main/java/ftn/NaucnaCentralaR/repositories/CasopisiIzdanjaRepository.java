package ftn.NaucnaCentralaR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.NaucnaCentralaR.models.Casopisi;
import ftn.NaucnaCentralaR.models.CasopisiIzdanja;
import ftn.NaucnaCentralaR.models.Korisnici;

public interface CasopisiIzdanjaRepository extends JpaRepository<CasopisiIzdanja, Long> {
	public CasopisiIzdanja findByid(Long id);

}
