package com.se.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Don_Vi_Van_Chuyen")
public class DonViVanChuyen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "ten_don_vi",columnDefinition = "nvarchar(500)",nullable = false)
	private String tenDonVi;
	
	@Column(name = "gia",columnDefinition = "money",nullable = false)
	private double gia;
	
	@OneToMany(mappedBy = "taiKhoan")
	private List<HoaDon> hoaDon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenDonVi() {
		return tenDonVi;
	}

	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public List<HoaDon> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(List<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}

	public DonViVanChuyen(int id, String tenDonVi, double gia, List<HoaDon> hoaDon) {
		super();
		this.id = id;
		this.tenDonVi = tenDonVi;
		this.gia = gia;
		this.hoaDon = hoaDon;
	}

	public DonViVanChuyen() {
		super();
	}

	@Override
	public String toString() {
		return "DonViVanChuyen [id=" + id + ", tenDonVi=" + tenDonVi + ", gia=" + gia + ", hoaDon=" + hoaDon + "]";
	}
	
	
	
}
