package com.library.demo.model;

/**
 * @author ANIZAM
 *
 */

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pengarang")
public class Pengarang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy = "pengarang")
	private List<Buku> listBuku = new ArrayList<>();

	public List<Buku> getListBuku() {
		return listBuku;
	}

	public void setListBuku(List<Buku> listBuku) {
		this.listBuku = listBuku;
	}
	
	@Column(name="nama_pengarang")
	private String nama_pengarang;

	public String getNama_pengarang() {
		return nama_pengarang;
	}

	public void setNama_pengarang(String nama_pengarang) {
		this.nama_pengarang = nama_pengarang;
	}	
	
}
