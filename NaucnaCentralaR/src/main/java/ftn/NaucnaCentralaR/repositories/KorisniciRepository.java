package ftn.NaucnaCentralaR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import ftn.NaucnaCentralaR.models.Korisnici;

public interface KorisniciRepository extends JpaRepository<Korisnici, String> {
	public Korisnici findByemail(String email);
}
