package ftn.NaucnaCentralaR.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown=true)
public class Korisnici implements Serializable {
	
	public enum Uloga {KUPAC,AUTOR}
	@Id
	@Column
	private String email;
	@Column
	private String ime;
	@Column
	private String prezime;
	@Column
	private String grad;
	@Column
	private String drzava;
	@Column
	private String lozinka;
	@Column
	private Uloga uloga;
	@OneToOne
	private ProfilKupca profilKupca;


	
	
	public Korisnici() {
		super();
	}


	public Korisnici(String email, String ime, String prezime, String grad, String drzava, String lozinka, Uloga uloga,
			ProfilKupca profilKupca) {
		super();
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.drzava = drzava;
		this.lozinka = lozinka;
		this.uloga = uloga;
		this.profilKupca = profilKupca;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}



	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	
	

	public ProfilKupca getProfulKupca() {
		return profilKupca;
	}
	public void setProfilKupca(ProfilKupca profilKupca) {
		this.profilKupca = profilKupca;
	}
	@Override
	public String toString() {
		return "Korisnik [email=" + email + ", ime=" + ime + ", prezime=" + prezime + ", uloga=" + uloga + ", grad="
				+ grad + ", drzava=" + drzava + ", lozinka=" + lozinka + "]";
	}


	
	
	

}
