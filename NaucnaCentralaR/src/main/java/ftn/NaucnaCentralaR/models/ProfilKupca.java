package ftn.NaucnaCentralaR.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ProfilKupca {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToMany
	private List<CasopisiIzdanja> kupljeni_casopisi=new ArrayList<CasopisiIzdanja>();
	@ManyToMany
	private List<Pretplata> aktivne_pretplate=new ArrayList<Pretplata>();
	public ProfilKupca() {
		super();
	}
	public ProfilKupca(List<CasopisiIzdanja> kupljeni_casopisi, List<Pretplata> aktivne_pretplate) {
		super();
		this.kupljeni_casopisi = kupljeni_casopisi;
		this.aktivne_pretplate = aktivne_pretplate;
	}
	public List<CasopisiIzdanja> getKupljeni_casopisi() {
		return kupljeni_casopisi;
	}
	public void setKupljeni_casopisi(List<CasopisiIzdanja> kupljeni_casopisi) {
		this.kupljeni_casopisi = kupljeni_casopisi;
	}
	public List<Pretplata> getAktivne_pretplate() {
		return aktivne_pretplate;
	}
	public void setAktivne_pretplate(List<Pretplata> aktivne_pretplate) {
		this.aktivne_pretplate = aktivne_pretplate;
	}
	public void kupiCasopis(CasopisiIzdanja c) {
		this.kupljeni_casopisi.add(c);
		
	}
	public void pretplatiSe(Pretplata p) {
		this.aktivne_pretplate.add(p);
		
	}
	public Pretplata proveriPretplatu(Long id2) {
		for(Pretplata p:this.aktivne_pretplate) {
			if(p.getCasopis().getId()==id2) {
				Date date= new Date();
				date.setMonth(date.getMonth() + 1);
				p.setDatumIsticanja(date);
				return p;
			}
		}
		return null;
		
	}
	public List<Pretplata> proveriSvePretplate() {
		List<Pretplata> provera=new ArrayList<Pretplata>();
		if(this.aktivne_pretplate.size()!=0) {
			for(int i=0; i<this.aktivne_pretplate.size(); i++) {
				if(this.aktivne_pretplate.get(i).getDatumIsticanja().compareTo(new Date())<0) {
					provera.add(this.aktivne_pretplate.get(i));
					this.aktivne_pretplate.remove(this.aktivne_pretplate.get(i));
				}
			}
		}
		
		return provera;
	}
	
	
}
