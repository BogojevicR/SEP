package ftn.NaucnaCentralaR.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InformacijeZaPlacanje {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	@Column
	private String paypalUsername;
	@Column
	private String paypalPassword;
	@Column
	private String bitcoinToken;
	@Column
	private String merchantId;
	@Column
	private String merchantPassword;
	public InformacijeZaPlacanje() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaypalUsername() {
		return paypalUsername;
	}
	public void setPaypalUsername(String paypalUsername) {
		this.paypalUsername = paypalUsername;
	}
	public String getPaypalPassword() {
		return paypalPassword;
	}
	public void setPaypalPassword(String paypalPassword) {
		this.paypalPassword = paypalPassword;
	}
	public String getBitcoinToken() {
		return bitcoinToken;
	}
	public void setBitcoinToken(String bitcoinToken) {
		this.bitcoinToken = bitcoinToken;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantPassword() {
		return merchantPassword;
	}
	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}
	

}
