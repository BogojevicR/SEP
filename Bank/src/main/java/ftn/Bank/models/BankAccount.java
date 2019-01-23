package ftn.Bank.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class BankAccount implements Serializable {
	@Id
	@Column
	private String pan;
	@Column
	private String securityCode;
	@Column
	private String cardHolderName;
	@Column
	private Date expirationDate;
	@Column
	private double availableFunds;
	@Column
	private double reservedFunds;

	public BankAccount() {
		super();
	}

	public BankAccount(String pan, String securityCode, String cardHolderName, Date expirationDate,
			double availableFunds, double reservedFunds) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.expirationDate = expirationDate;
		this.availableFunds = availableFunds;
		this.reservedFunds = reservedFunds;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public double getAvailableFunds() {
		return availableFunds;
	}

	public void setAvailableFunds(double availableFunds) {
		this.availableFunds = availableFunds;
	}

	public double getReservedFunds() {
		return reservedFunds;
	}

	public void setReservedFunds(double reservedFunds) {
		this.reservedFunds = reservedFunds;
	}
	
	public void addFunds(double funds) {
		this.availableFunds+=funds;
	}
	public void removeFunds(double funds) {
		this.availableFunds-=funds;
	}
	
	public void addReservedFunds(double funds) {
		this.reservedFunds+=funds;
	}
	public void removeReservedFunds(double funds) {
		this.reservedFunds-=funds;
	}

	@Override
	public String toString() {
		return "BankAccount [pan=" + pan + ", securityCode=" + securityCode + ", cardHolderName=" + cardHolderName
				+ ", expirationDate=" + expirationDate + ", availableFunds=" + availableFunds + ", reservedFunds="
				+ reservedFunds + "]";
	}
	
	

}
