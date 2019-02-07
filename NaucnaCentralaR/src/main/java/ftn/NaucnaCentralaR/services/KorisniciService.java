package ftn.NaucnaCentralaR.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.NaucnaCentralaR.models.Korisnici;
import ftn.NaucnaCentralaR.models.ProfilKupca;
import ftn.NaucnaCentralaR.repositories.KorisniciRepository;
import ftn.NaucnaCentralaR.repositories.ProfilKupcaRepository;
@Service
public class KorisniciService {

	@Autowired
	public KorisniciRepository korisniciRep;
	@Autowired
	public ProfilKupcaRepository profilRep;
	
	
	
	public Korisnici save(Korisnici korisnik) {
		if(korisniciRep.findByemail(korisnik.getEmail())==null) {
			korisnik.setProfilKupca(new ProfilKupca());
			profilRep.save(korisnik.getProfulKupca());
			korisniciRep.save(korisnik);
			return korisnik;
		}
		return null;
	}

	public Korisnici login(String email, String password) {
		Korisnici korisnik=korisniciRep.findByemail(email);
		if(korisnik.getLozinka().equals(password)) {
			return korisnik;
		}
		return null;
	}

}
