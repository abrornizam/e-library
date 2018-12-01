/**
 * 
 */
package com.library.client.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ANIZAM
 *
 */

@Entity
@Table(name="charge")
public class Charge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(name = "idcharge")
	private String idcharge;

	@ManyToOne
	@JoinColumn(name = "idtransaction", nullable = false)
	private Transaction transaction;

	@Column(name = "nomine")
	private int nomine;

	@Column(name = "status")
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getidcharge() {
		return idcharge;
	}

	public void setidcharge(String idcharge) {
		this.idcharge = idcharge;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public int getNomine() {
		return nomine;
	}

	public void setNomine(int nomine) {
		this.nomine = nomine;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
