package ftn.Bank.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
	public boolean removeFunds(double funds) {
		if(this.availableFunds>=funds) {
			this.availableFunds-=funds;
			return true;
		}
		return false;
		
	}
	
	public boolean addReservedFunds(double funds) {
		if(this.availableFunds>=funds) {
			this.availableFunds-=funds;
			this.reservedFunds+=funds;
			return true;
		}
		return false;
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
	
	public void generatePan() throws ParseException {
		this.pan+=" "+UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);
		this.pan+=" "+UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);
		this.pan+=" "+UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);
		
		
		Date date =new Date();
		
		date.setYear(date.getYear()+10);
		DateFormat formatter = new SimpleDateFormat("MM/yyyy");
		Date todayWithZeroTime = formatter.parse(formatter.format(date));
		this.setExpirationDate(todayWithZeroTime);

	}
	
	public boolean checkInfo(BankAccount ba) {
		//TODO : MOZDA IMA PROBLEM SA CHECK EXPIRE DATE
/*/		System.out.println(ba.getCardHolderName());
		System.out.println(ba.getCardHolderName().equals(this.cardHolderName));
		System.out.println(ba.getSecurityCode().equals(this.securityCode));
		System.out.println(ba.getExpirationDate());
		System.out.println(this.expirationDate);
		System.out.println(ba.getExpirationDate().compareTo(this.expirationDate));
		System.out.println(ba.getExpirationDate().compareTo(this.expirationDate) == 1);
		*/
		if(ba.getCardHolderName().equals(this.cardHolderName) && ba.getSecurityCode().equals(this.securityCode) && ba.getExpirationDate().compareTo(this.expirationDate) == 1 ) {

			
			return true;
		}
		return false;
		
	}
	
	public String getPortNumber() {
		String[] splited = this.pan.split("\\s+");
		return splited[0];
	}
	
	

}
