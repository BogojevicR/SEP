package ftn.Bank.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PCCResponse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long issuerOrderId;
	@Column
	private Date issuerTimestamp;
	@Column
	private String buyersPan;
	@Column
	private long merchantOrderId;
	@Column
	private long  acquirerOrderId;
	@Column
	private Date acquirerTimestamp;

	
	public PCCResponse() {
		super();
	}
	public long getAcquirerOrderId() {
		return acquirerOrderId;
	}
	public void setAcquirerOrderId(long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}
	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}
	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}
	public String getBuyersPan() {
		return buyersPan;
	}
	public void setBuyersPan(String buyersPan) {
		this.buyersPan = buyersPan;
	}
	public long getMerchantOrderId() {
		return merchantOrderId;
	}
	public void setMerchantOrderId(long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	public long getIssuerOrderId() {
		return issuerOrderId;
	}
	public void setIssuerOrderId(long issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}
	public Date getIssuerTimestamp() {
		return issuerTimestamp;
	}
	public void setIssuerTimestamp(Date issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}
	
}
