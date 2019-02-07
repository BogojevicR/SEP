package ftn.NaucnaCentralaR.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pretplata {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Casopisi casopis;
	@Column
	private Date datumIsticanja;
	public Pretplata() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Casopisi getCasopis() {
		return casopis;
	}
	public void setCasopis(Casopisi casopis) {
		this.casopis = casopis;
	}
	public Date getDatumIsticanja() {
		return datumIsticanja;
	}
	public void setDatumIsticanja(Date datumIsticanja) {
		this.datumIsticanja = datumIsticanja;
	}
	@Override
	public String toString() {
		return "Pretplata [id=" + id + ", casopis=" + casopis + ", datumIsticanja=" + datumIsticanja + "]";
	}
	
	
}
