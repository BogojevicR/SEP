package ftn.NaucnaCentralaR.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Rad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String naslov;
	@Column
	private String kljucniPojmovi;
	@Column
	private String apstrakt;
	@Column
	private String naucnaOblast;
	@Column
	private String pdf;
	public Rad() {
		super();
	}
	public Rad(Rad r) {
		this.naslov=r.getNaslov();
		this.kljucniPojmovi=r.getKljucniPojmovi();
		this.apstrakt=r.getApstrakt();
		this.naucnaOblast=r.getNaucnaOblast();
		this.pdf=r.getPdf();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	
	public String getApstrakt() {
		return apstrakt;
	}
	public void setApstrakt(String apstrakt) {
		this.apstrakt = apstrakt;
	}
	
	public String getKljucniPojmovi() {
		return kljucniPojmovi;
	}
	public void setKljucniPojmovi(String kljucniPojmovi) {
		this.kljucniPojmovi = kljucniPojmovi;
	}
	public String getNaucnaOblast() {
		return naucnaOblast;
	}
	public void setNaucnaOblast(String naucnaOblast) {
		this.naucnaOblast = naucnaOblast;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	
	
}
