package ftn.Bank.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class MerchantAccount implements Serializable {
	@Id
	private String merchantId;
	@Column
	private String merchantPassword;
	@Column
	private String username;

	@OneToOne
	private BankAccount clientAccount;

	public MerchantAccount() {
		super();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BankAccount getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(BankAccount clientAccount) {
		this.clientAccount = clientAccount;
	}

	@Override
	public String toString() {
		return "MerchantAccount [merchantId=" + merchantId + ", merchantPassword=" + merchantPassword + ", username="
				+ username + ", clientAccount=" + clientAccount + "]";
	}
	
	
	public void generateMerchantAccount() {
		
		this.merchantPassword=UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);
		this.merchantId=UUID.randomUUID().toString();
	}
	
}
