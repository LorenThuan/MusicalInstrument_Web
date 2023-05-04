package com.se.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Loai")
public class Loai {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "ten",columnDefinition = "nvarchar(200)",nullable = false)
	private String ten;
	
	@OneToMany(mappedBy = "loai")
	private List<SanPham> sanPham;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public List<SanPham> getSanPham() {
		return sanPham;
	}

	public void setSanPham(List<SanPham> sanPham) {
		this.sanPham = sanPham;
	}

	public Loai(int id) {
		super();
		this.id = id;
	}

	public Loai(int id, String ten, List<SanPham> sanPham) {
		super();
		this.id = id;
		this.ten = ten;
		this.sanPham = sanPham;
	}

	public Loai() {
		super();
	}

	@Override
	public String toString() {
		return "Loai [id=" + id + ", ten=" + ten + ", sanPham=" + sanPham + "]";
	}
	
	
	
}
