package ftn.Bank.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bank implements Serializable{
	@Id
	@Column
	private String port;
	@Column
	private String name;
	public Bank() {
		super();
	}
	public Bank(String port, String name) {
		super();
		this.port = port;
		this.name = name;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Bank [port=" + port + ", name=" + name + "]";
	}
	
	

}
