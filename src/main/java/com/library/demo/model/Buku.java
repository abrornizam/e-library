package com.library.demo.model;

/**
 * @author ANIZAM
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="buku")
public class Buku {
	
	@Id
	@GenericGenerator(name = "sequence_kdbuku", strategy = "com.abror.demo.config.BukuGenerator")
	@GeneratedValue(generator = "sequence_kdbuku")
	@Column(name="kdbuku")
	private String kdbuku;
	
	public String getKdbuku() {
		return kdbuku;
	}

	public void setKdbuku(String kdbuku) {
		this.kdbuku = kdbuku;
	}

	@Column(name="judul")
	private String judul;
	
	public String getJudul() {
		return judul;
	}
	
	public void setJudul(String judul) {
		this.judul = judul;
	}
	
	@Column(name="deskripsi")
	private String deskripsi;
		
	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	@Column(name="tahun")
	private String tahun;
	public String getTahun() {
		return tahun;
	}
	
	public void setTahun(String tahun) {
		this.tahun = tahun;
	}
	
	@Column(name="jumlah")
	private int jumlah;
	
	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	@ManyToOne
	@JoinColumn(name="id_pengarang", nullable=false)
	private Pengarang pengarang;

	public Pengarang getPengarang() {
		return pengarang;
	}

	public void setPengarang(Pengarang pengarang) {
		this.pengarang = pengarang;
	}
	
	@Column(name="status")
	private Boolean status;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}	
		
//	@OneToMany(mappedBy = "buku")
//	private List<Peminjaman> listPeminjaman = new ArrayList<>();
//
//	public List<Peminjaman> getListPeminjaman() {
//		return listPeminjaman;
//	}
//
//	public void setListPeminjaman(List<Peminjaman> listPeminjaman) {
//		this.listPeminjaman = listPeminjaman;
//	}	

}
