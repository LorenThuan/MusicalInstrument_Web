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
@Table(name = "Danh_Gia")
public class DanhGia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "binh_luan",columnDefinition = "nvarchar(MAX)",nullable = false)
	private String binhLuan;
	
	@ManyToOne
	@JoinColumn(name = "tai_khoan_id",nullable = false)
	private TaiKhoan taiKhoan;
	
	@ManyToOne
	@JoinColumn(name = "san_pham_id",nullable = false)
	private SanPham sanPham;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBinhLuan() {
		return binhLuan;
	}

	public void setBinhLuan(String binhLuan) {
		this.binhLuan = binhLuan;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public DanhGia(int id, String binhLuan, TaiKhoan taiKhoan, SanPham sanPham) {
		super();
		this.id = id;
		this.binhLuan = binhLuan;
		this.taiKhoan = taiKhoan;
		this.sanPham = sanPham;
	}

	public DanhGia() {
		super();
	}

	@Override
	public String toString() {
		return "DanhGia [id=" + id + ", binhLuan=" + binhLuan + ", taiKhoan=" + taiKhoan + ", sanPham=" + sanPham + "]";
	}
	
	
}
