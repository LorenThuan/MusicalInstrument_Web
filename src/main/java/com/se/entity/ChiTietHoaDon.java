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
@Table(name = "Chi_Tiet_Hoa_Don")
public class ChiTietHoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "gia",columnDefinition = "money",nullable = false)
	private double gia;
	
	@Column(name = "so_luong",columnDefinition = "int",nullable = false)
	private int soLuong;
	
	@Column(name = "tong_tien",columnDefinition = "money",nullable = false)
	private double tongTien;
	
	@ManyToOne
	@JoinColumn(name = "hoa_don_id",nullable = false)
	private HoaDon hoaDon;
	
	@ManyToOne
	@JoinColumn(name = "san_pham_id",nullable = false)
	private SanPham sanPham;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public ChiTietHoaDon(int id, double gia, int soLuong, double tongTien, HoaDon hoaDon, SanPham sanPham) {
		super();
		this.id = id;
		this.gia = gia;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
	}

	public ChiTietHoaDon() {
		super();
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [id=" + id + ", gia=" + gia + ", soLuong=" + soLuong + ", tongTien=" + tongTien
				+ ", hoaDon=" + hoaDon + ", sanPham=" + sanPham + "]";
	}
	
	
}
