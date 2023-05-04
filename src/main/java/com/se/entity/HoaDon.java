package com.se.entity;

import java.util.Date;
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
@Table(name = "Hoa_Don")
public class HoaDon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "ngay_dat",columnDefinition = "datetime",nullable = false)
	private Date ngayDat;
	
	@Column(name = "ghi_chu",columnDefinition = "nvarchar(MAX)",nullable = true)
	private String ghiChu;
	
	@Column(name = "trang_thai",columnDefinition = "nvarchar(200)",nullable = false)
	private String trangThai;
	
	@Column(name = "tong_tien",columnDefinition = "money",nullable = false)
	private double tongTien;
	
	@Column(name = "dia_chi",columnDefinition = "nvarchar(MAX)",nullable = true)
	private String diaChi;
	
	
	
	@ManyToOne
	@JoinColumn(name = "tai_khoan_id",nullable = false)
	private TaiKhoan taiKhoan;
	
	@ManyToOne
	@JoinColumn(name = "don_vi_van_chuyen_id",nullable = true)
	private DonViVanChuyen donViVanChuyen;
	
	@OneToMany(mappedBy = "hoaDon")
	private List<ChiTietHoaDon> chiTietHoaDon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public DonViVanChuyen getDonViVanChuyen() {
		return donViVanChuyen;
	}

	public void setDonViVanChuyen(DonViVanChuyen donViVanChuyen) {
		this.donViVanChuyen = donViVanChuyen;
	}

	public List<ChiTietHoaDon> getChiTietHoaDon() {
		return chiTietHoaDon;
	}

	public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
		this.chiTietHoaDon = chiTietHoaDon;
	}

	public HoaDon(int id, Date ngayDat, String ghiChu, String trangThai, double tongTien, TaiKhoan taiKhoan,
			DonViVanChuyen donViVanChuyen, List<ChiTietHoaDon> chiTietHoaDon) {
		super();
		this.id = id;
		this.ngayDat = ngayDat;
		this.ghiChu = ghiChu;
		this.trangThai = trangThai;
		this.tongTien = tongTien;
		this.taiKhoan = taiKhoan;
		this.donViVanChuyen = donViVanChuyen;
		this.chiTietHoaDon = chiTietHoaDon;
	}

	public HoaDon() {
		super();
	}

	@Override
	public String toString() {
		return "HoaDon [id=" + id + ", ngayDat=" + ngayDat + ", ghiChu=" + ghiChu + ", trangThai=" + trangThai
				+ ", tongTien=" + tongTien + ", taiKhoan=" + taiKhoan + ", donViVanChuyen=" + donViVanChuyen
				+ ", chiTietHoaDon=" + chiTietHoaDon + "]";
	}
	
	
	
	
}
