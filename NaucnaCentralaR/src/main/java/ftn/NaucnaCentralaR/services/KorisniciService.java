package ftn.NaucnaCentralaR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.NaucnaCentralaR.models.Korisnici;
import ftn.NaucnaCentralaR.models.Pretplata;
import ftn.NaucnaCentralaR.models.ProfilKupca;
import ftn.NaucnaCentralaR.repositories.KorisniciRepository;
import ftn.NaucnaCentralaR.repositories.PretplataRepository;
import ftn.NaucnaCentralaR.repositories.ProfilKupcaRepository;
@Service
public class KorisniciService {

	@Autowired
	public KorisniciRepository korisniciRep;
	@Autowired
	public ProfilKupcaRepository profilRep;
	@Autowired
	public PretplataRepository pretplataRep;
	
	
	
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
			List<Pretplata>zaBrisanje=korisnik.getProfulKupca().proveriSvePretplate();
			if(zaBrisanje.size()>0) {
				for(Pretplata p:zaBrisanje) {
					pretplataRep.delete(p);
				}
				profilRep.save(korisnik.getProfulKupca());
			}
			
			return korisnik;
		}
		return null;
	}

}
