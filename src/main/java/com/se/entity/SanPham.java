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
@Table(name = "San_Pham")
public class SanPham {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ten",columnDefinition = "nvarchar(500)",nullable = false)
	private String ten;

	@Column(name = "gia",columnDefinition = "money",nullable = false)
	private double gia;
	
	@Column(name = "giam_gia",columnDefinition = "money",nullable = false)
	private double giamGia;
	
	@Column(name = "hinh_chinh",columnDefinition = "nvarchar(MAX)",nullable = false)
	private String hinhChinh;
	
	@Column(name = "mo_ta",columnDefinition = "nvarchar(MAX)",nullable = false)
	private String moTa;
	
	@Column(name = "ngay_tao",columnDefinition = "datetime",nullable = false)
	private Date ngayTao;
	
	@Column(name = "ngay_sua",columnDefinition = "datetime",nullable = true)
	private Date ngaySua;
	
	@Column(name = "da_xoa",columnDefinition = "int",nullable = false)
	private int daXoa; 
	
	@OneToMany(mappedBy = "sanPham")
	private List<DanhGia> danhGia;
	
	@OneToMany(mappedBy = "sanPham")
	private List<ChiTietHoaDon> chiTietHoaDon;
	
	@ManyToOne
	@JoinColumn(name = "loai_id",nullable = false)
	private Loai loai;
	
	@ManyToOne
	@JoinColumn(name = "nha_san_xuat_id",nullable = false)
	private NhaSanXuat nhaSanXuat;
	
	@OneToMany(mappedBy = "sanPham")
	private List<HinhPhu> listHinhPhu;

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

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public double getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(double giamGia) {
		this.giamGia = giamGia;
	}

	public String getHinhChinh() {
		return hinhChinh;
	}

	public void setHinhChinh(String hinhChinh) {
		this.hinhChinh = hinhChinh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	

	public List<HinhPhu> getListHinhPhu() {
		return listHinhPhu;
	}

	public void setListHinhPhu(List<HinhPhu> listHinhPhu) {
		this.listHinhPhu = listHinhPhu;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Date getNgaySua() {
		return ngaySua;
	}

	public void setNgaySua(Date ngaySua) {
		this.ngaySua = ngaySua;
	}

	public int getDaXoa() {
		return daXoa;
	}

	public void setDaXoa(int daXoa) {
		this.daXoa = daXoa;
	}

	public List<DanhGia> getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(List<DanhGia> danhGia) {
		this.danhGia = danhGia;
	}

	public List<ChiTietHoaDon> getChiTietHoaDon() {
		return chiTietHoaDon;
	}

	public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
		this.chiTietHoaDon = chiTietHoaDon;
	}

	public Loai getLoai() {
		return loai;
	}

	public void setLoai(Loai loai) {
		this.loai = loai;
	}

	public NhaSanXuat getNhaSanXuat() {
		return nhaSanXuat;
	}

	public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}


	
	
	

	public SanPham(int id, String ten, double gia, double giamGia, String hinhChinh, String moTa, Date ngayTao,
			Date ngaySua, int daXoa, List<DanhGia> danhGia, List<ChiTietHoaDon> chiTietHoaDon, Loai loai,
			NhaSanXuat nhaSanXuat, List<HinhPhu> listHinhPhu) {
		super();
		this.id = id;
		this.ten = ten;
		this.gia = gia;
		this.giamGia = giamGia;
		this.hinhChinh = hinhChinh;
		this.moTa = moTa;
		this.ngayTao = ngayTao;
		this.ngaySua = ngaySua;
		this.daXoa = daXoa;
		this.danhGia = danhGia;
		this.chiTietHoaDon = chiTietHoaDon;
		this.loai = loai;
		this.nhaSanXuat = nhaSanXuat;
		this.listHinhPhu = listHinhPhu;
	}

	public SanPham(int id, String ten, double gia, double giamGia, String moTa, int daXoa) {
		super();
		this.id = id;
		this.ten = ten;
		this.gia = gia;
		this.giamGia = giamGia;
		this.moTa = moTa;
		this.daXoa = daXoa;
	}

	public SanPham() {
		super();
	}

	@Override
	public String toString() {
		return "SanPham [id=" + id + ", ten=" + ten + ", gia=" + gia + ", giamGia=" + giamGia + ", hinhChinh="
				+ hinhChinh + ", moTa=" + moTa +", ngayTao=" + ngayTao + ", ngaySua="
				+ ngaySua + ", daXoa=" + daXoa + ", danhGia=" + danhGia + ", chiTietHoaDon=" + chiTietHoaDon + ", loai="
				+ loai + ", nhaSanXuat=" + nhaSanXuat + "]";
	}
	
	
	
}
