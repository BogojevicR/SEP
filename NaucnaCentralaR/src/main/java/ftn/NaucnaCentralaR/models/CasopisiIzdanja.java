package ftn.NaucnaCentralaR.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class CasopisiIzdanja {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String naziv;
	@Column
	private String issn;
	@Column
	private double cena;
	@ManyToMany
	private List<Rad> radovi=new ArrayList<Rad>();
	@OneToOne
	private InformacijeZaPlacanje informacijeZaPlacanje;
	
	
	public CasopisiIzdanja() {
		super();
	}
	
	public CasopisiIzdanja(Casopisi casopis) {
		super();
		this.naziv=casopis.getNaziv();
		this.issn=casopis.getIssn();
		this.cena=casopis.getCena();
		dodajRadove(casopis);
		this.informacijeZaPlacanje=casopis.getInformacijeZaPlacanje();
	}
	
	private void dodajRadove(Casopisi casopis) {
		for(Rad r:casopis.getRadovi()) {
			dodajRad(r);
		}
		
	}

	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public List<Rad> getRadovi() {
		return radovi;
	}

	public void setRadovi(List<Rad> radovi) {
		this.radovi = radovi;
	}

	public InformacijeZaPlacanje getInformacijeZaPlacanje() {
		return informacijeZaPlacanje;
	}

	public void setInformacijeZaPlacanje(InformacijeZaPlacanje informacijeZaPlacanje) {
		this.informacijeZaPlacanje = informacijeZaPlacanje;
	}
	
	public void dodajRad(Rad r) {
		this.radovi.add(r);
	}

	public void generateISSN() {
		this.issn+=UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4)+"-"+UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);;
	}
}
