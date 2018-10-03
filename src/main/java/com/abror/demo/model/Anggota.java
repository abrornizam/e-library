package com.abror.demo.model;

/**
 * @author ANIZAM
 *
 */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="anggota")
public class Anggota {
	
	@Id
	@GenericGenerator(name = "sequence", strategy = "com.abror.demo.config.AnggotaGenerator")
	@GeneratedValue(generator = "sequence")
	@Column(name="idanggota")
	private String idanggota;
	
	@Column(name="nama_anggota")
	private String nama_anggota;
	
	@Column(name="alamat")
	private String alamat;
	
	@Column(name="no_telp")
	private String no_telp;

	@Column(name="email")
	private String email;
	
	@Column(name="status")
	private Boolean status;
	
	@Column(name="tgl_join")
	private Date tgl_join;

	public String getIdanggota() {
		return idanggota;
	}

	public void setIdanggota(String idanggota) {
		this.idanggota = idanggota;
	}

	public String getNama_anggota() {
		return nama_anggota;
	}

	public void setNama_anggota(String nama_anggota) {
		this.nama_anggota = nama_anggota;
	}
	
	public String getNo_telp() {
		return no_telp;
	}

	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}
	
	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getTgl_join() {
		return tgl_join;
	}

	public void setTgl_join(Date tgl_join) {
		this.tgl_join = tgl_join;
	}
	
}
