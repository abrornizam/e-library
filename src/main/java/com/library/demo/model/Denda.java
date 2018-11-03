/**
 * 
 */
package com.library.demo.model;

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
@Table(name="denda")
public class Denda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(name = "kddenda")
	private String kddenda;

	@ManyToOne
	@JoinColumn(name = "kd_peminjaman", nullable = false)
	private Peminjaman peminjaman;

	@Column(name = "nominal")
	private int nominal;

	@Column(name = "status")
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getkddenda() {
		return kddenda;
	}

	public void setkddenda(String kddenda) {
		this.kddenda = kddenda;
	}

	public Peminjaman getPeminjaman() {
		return peminjaman;
	}

	public void setPeminjaman(Peminjaman peminjaman) {
		this.peminjaman = peminjaman;
	}

	public int getNominal() {
		return nominal;
	}

	public void setNominal(int nominal) {
		this.nominal = nominal;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
