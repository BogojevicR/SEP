package ftn.Bank.models;

import java.io.Serializable;

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
	
}
