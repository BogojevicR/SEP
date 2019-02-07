package ftn.NaucnaCentralaR.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.NaucnaCentralaR.models.Casopisi;
import ftn.NaucnaCentralaR.models.CasopisiIzdanja;
import ftn.NaucnaCentralaR.models.Korisnici;
import ftn.NaucnaCentralaR.models.Pretplata;
import ftn.NaucnaCentralaR.models.Rad;
import ftn.NaucnaCentralaR.repositories.CasopisiIzdanjaRepository;
import ftn.NaucnaCentralaR.repositories.CasopisiRepository;
import ftn.NaucnaCentralaR.repositories.KorisniciRepository;
import ftn.NaucnaCentralaR.repositories.PretplataRepository;
import ftn.NaucnaCentralaR.repositories.ProfilKupcaRepository;
import ftn.NaucnaCentralaR.repositories.RadRepository;

@Service
public class NaucnaCentralaService {
	
	@Autowired
	public CasopisiRepository casopisiRep;
	@Autowired
	public CasopisiIzdanjaRepository casopisiIzdanjaRep;
	@Autowired
	public RadRepository radRep;
	@Autowired
	public KorisniciRepository korisniciRep;
	@Autowired
	public ProfilKupcaRepository profilKupcaRep;
	@Autowired
	public PretplataRepository pretplataRep;
	
	public List<Casopisi> getAllCasopisi() {
		
		return casopisiRep.findAll();
	}

	public Rad dodajRadUCasopis(Rad rad) {
		Rad r=new Rad(rad);
		Casopisi c=casopisiRep.findByid(rad.getId());
		c.dodajRad(r);
		radRep.save(r);
		casopisiRep.save(c);
		
		return rad;
	}


	public Casopisi kupiCasopis(String email, Long casopisId) {
		Korisnici k=korisniciRep.findByemail(email);

		Casopisi c=casopisiRep.findByid(casopisId);
		CasopisiIzdanja izdanje=new CasopisiIzdanja(c);
		
		k.getProfulKupca().kupiCasopis(izdanje);
		casopisiIzdanjaRep.save(izdanje);
		profilKupcaRep.save(k.getProfulKupca());
		return c;
	}
	
	public Casopisi pretplatiSe(String email, Long casopisId) {
		Korisnici k=korisniciRep.findByemail(email);

		Casopisi c=casopisiRep.findByid(casopisId);
		if(k.getProfulKupca().proveriPretplatu(c.getId())!=null) {

			for(Pretplata p:k.getProfulKupca().getAktivne_pretplate()) {
				if(p.getCasopis().getId()==casopisId) {

					pretplataRep.save(p);
					return p.getCasopis();
				}
			}
		}
		
		Pretplata p=new Pretplata();
		Date date= new Date();
		date.setMonth(date.getMonth() + 1);
		p.setDatumIsticanja(date);
		p.setCasopis(c);
		pretplataRep.save(p);
		
		k.getProfulKupca().pretplatiSe(p);
		profilKupcaRep.save(k.getProfulKupca());
		return c;
	}

	public Casopisi getCasopis(Long casopisId) {
		
		return casopisiRep.findByid(casopisId);
	}

}
