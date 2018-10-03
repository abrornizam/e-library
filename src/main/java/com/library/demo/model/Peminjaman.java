package com.library.demo.model;

/**
 * @author ANIZAM
 *
 */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="peminjaman")
public class Peminjaman {

	@Id
	@GenericGenerator(name = "sequence", strategy = "com.abror.demo.config.PeminjamanGenerator")
	@GeneratedValue(generator = "sequence")
	@Column(name="kdpeminjaman")
	private String kdpeminjaman;

	public String getKdpeminjaman() {
		return kdpeminjaman;
	}

	public void setKdpeminjaman(String kdpeminjaman) {
		this.kdpeminjaman = kdpeminjaman;
	}	
	
	@ManyToOne
	@JoinColumn(name="kd_buku", nullable=false)
	private Buku buku;

	public Buku getBuku() {
		return buku;
	}

	public void setBuku(Buku buku) {
		this.buku = buku;
	}
	
	@ManyToOne
	@JoinColumn(name="id_peminjam", nullable=false)
	private Anggota anggota;

	public Anggota getAnggota() {
		return anggota;
	}

	public void setAnggota(Anggota anggota) {
		this.anggota = anggota;
	}

	@Column(name="jumlah")
	private int jumlah;
	
	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}
	
	@Column(name="tgl_pinjam", nullable=false)
	private Date tgl_pinjam;
		
	public Date getTgl_pinjam() {
		return tgl_pinjam;
	}

	public void setTgl_pinjam(Date tgl_pinjam) {
		this.tgl_pinjam = tgl_pinjam;
	}

	@Column(name="tgl_kembali", nullable=true)
	private Date tgl_kembali;

	public Date getTgl_kembali() {
		return tgl_kembali;
	}

	public void setTgl_kembali(Date tgl_kembali) {
		this.tgl_kembali = tgl_kembali;
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
