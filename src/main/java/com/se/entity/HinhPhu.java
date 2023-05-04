package com.se.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Hinh_Phu")
public class HinhPhu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "hinh",columnDefinition = "nvarchar(MAX)",nullable = false)
	private String hinh;

	@ManyToOne
	@JoinColumn(name = "san_pham_id",nullable = false)
	private SanPham sanPham;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHinh() {
		return hinh;
	}

	public void setHinh(String hinh) {
		this.hinh = hinh;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public HinhPhu(int id, String hinh, SanPham sanPham) {
		super();
		this.id = id;
		this.hinh = hinh;
		this.sanPham = sanPham;
	}

	public HinhPhu() {
		super();
	}

	public HinhPhu(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "HinhPhu [id=" + id + ", hinh=" + hinh + ", sanPham=" + sanPham + "]";
	}
	
	
}
