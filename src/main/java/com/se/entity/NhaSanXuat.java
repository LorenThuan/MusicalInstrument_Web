package com.se.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Nha_San_Xuat")
public class NhaSanXuat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "ten",columnDefinition = "nvarchar(200)",nullable = false)
	private String ten;
	
	@Column(name = "dia_chi",columnDefinition = "nvarchar(max)",nullable = false)
	private String diaChi;
	
	@Column(name = "email",columnDefinition = "nvarchar(200)",nullable = false)
	private String email;
	
	@Column(name = "so_dien_thoai",columnDefinition = "nvarchar(50)",nullable = false)
	private String soDienThoai;
	
	@Column(name = "ngay_hop_tac",columnDefinition = "date",nullable = false)
	private Date ngayHopTac;
	
	@OneToMany(mappedBy = "loai")
	private List<SanPham> sanPham;

	public NhaSanXuat(int id) {
		super();
		this.id = id;
	}

	public NhaSanXuat(int id, String ten, String diaChi, String email, String soDienThoai, Date ngayHopTac,
			List<SanPham> sanPham) {
		super();
		this.id = id;
		this.ten = ten;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.ngayHopTac = ngayHopTac;
		this.sanPham = sanPham;
	}

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

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public Date getNgayHopTac() {
		return ngayHopTac;
	}

	public void setNgayHopTac(Date ngayHopTac) {
		this.ngayHopTac = ngayHopTac;
	}

	public List<SanPham> getSanPham() {
		return sanPham;
	}

	public void setSanPham(List<SanPham> sanPham) {
		this.sanPham = sanPham;
	}

	public NhaSanXuat() {
		super();
	}

	@Override
	public String toString() {
		return "NhaSanXuat [id=" + id + ", ten=" + ten + ", diaChi=" + diaChi + ", email=" + email + ", soDienThoai="
				+ soDienThoai + ", ngayHopTac=" + ngayHopTac + ", sanPham=" + sanPham + "]";
	}
	
	
	
}
