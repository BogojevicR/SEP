package ftn.Bank.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class PaymentRequest implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String merchantId;
	@Column
	private String merchantPassword;
	@Column
	private int merchantOrderId;
	@Column
	private Date merchantTimestamp;
	@Column
	private double amount;
	@Column
	private String successUrl;
	@Column
	private String failedUrl;
	@Column
	private String errorUrl;

	public PaymentRequest() {
		super();
	}

	public PaymentRequest(long id,String merchantId, String merchantPassword, int merchantOrderId, Date merchantTimestamp,
			double amount, String successUrl, String failedUrl, String errorUrl) {
		super();
		this.id=id;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.amount = amount;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(int merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	@Override
	public String toString() {
		return "Transaction [merchantId=" + merchantId + ", merchantPassword=" + merchantPassword + ", merchantOrderId="
				+ merchantOrderId + ", merchantTimestamp=" + merchantTimestamp + ", amount=" + amount + ", successUrl="
				+ successUrl + ", failedUrl=" + failedUrl + ", errorUrl=" + errorUrl + "]";
	}
	
	
}
