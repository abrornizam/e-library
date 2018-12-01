package com.library.client.model;

/**
 * @author ANIZAM
 *
 */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="idtransaction")
	private String idtransaction;

	public String getIdtransaction() {
		return idtransaction;
	}

	public void setIdtransaction(String idtransaction) {
		this.idtransaction = idtransaction;
	}	
	
	@ManyToOne
	@JoinColumn(name="idbook", nullable=false)
	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	@ManyToOne
	@JoinColumn(name="idmember", nullable=false)
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name="total")
	private int total;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	@Column(name="borrow_date", nullable=false)
	private Date borrow_date;
		
	public Date getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}

	@Column(name="return_date", nullable=true)
	private Date return_date;

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	
	@Column(name="trx_status")
	private String trx_status;
	
	public String getTrx_status() {
		return trx_status;
	}

	public void setTrx_status(String trx_status) {
		this.trx_status = trx_status;
	}

	@Column(name="status")
	private Boolean status;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
