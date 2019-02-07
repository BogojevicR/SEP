package ftn.NaucnaCentralaR.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.aspectj.weaver.patterns.PerThisOrTargetPointcutVisitor;

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
	public boolean proveriPretplatu(Long id2) {
		for(Pretplata p:this.aktivne_pretplate) {
			if(p.getCasopis().getId()==id2) {
				return true;
			}
		}
		return false;
		
	}
	
	
}
