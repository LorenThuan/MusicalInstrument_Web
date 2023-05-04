package com.se.entity;

import java.io.Serializable;
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
@Table(name = "Tai_Khoan")
public class TaiKhoan implements Serializable, Comparable<TaiKhoan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ten_tai_khoan", columnDefinition = "varchar(200)", nullable = false, unique = true)
	private String tenTaiKhoan;

	@Column(name = "mat_khau", columnDefinition = "varchar(200)", nullable = false)
	private String matKhau;

	@Column(name = "ngay_tao", columnDefinition = "datetime", nullable = false)
	private Date ngayTao;

	@Column(name = "email", columnDefinition = "nvarchar(200)", nullable = false)
	private String email;

	@Column(name = "so_dien_thoai", columnDefinition = "nvarchar(50)", nullable = false)
	private String soDienThoai;

	@Column(name = "ngay_sinh", columnDefinition = "nvarchar(50)", nullable = false)
	private String ngaySinh;

	@Column(name = "gioi_tinh", columnDefinition = "nvarchar(10)", nullable = false)
	private String gioiTinh;

	@Column(name = "da_xoa", columnDefinition = "int", nullable = false)
	private int daXoa;

	@Column(name = "role", columnDefinition = "nvarchar(50)", nullable = false)
	private String role;

	@Column(name = "ho_ten", columnDefinition = "nvarchar(MAX)", nullable = false)
	private String hoTen;

	@OneToMany(mappedBy = "taiKhoan")
	private List<DanhGia> danhGia;

	@OneToMany(mappedBy = "taiKhoan")
	private List<HoaDon> hoaDon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
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

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
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

	public List<HoaDon> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(List<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}

	public TaiKhoan() {
		super();
	}

	
	
	

	
	public TaiKhoan(int id, String tenTaiKhoan, String matKhau, Date ngayTao, String email, String soDienThoai,
			String ngaySinh, String gioiTinh, int daXoa, String role, String hoTen, List<DanhGia> danhGia,
			List<HoaDon> hoaDon) {
		super();
		this.id = id;
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.ngayTao = ngayTao;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.daXoa = daXoa;
		this.role = role;
		this.hoTen = hoTen;
		this.danhGia = danhGia;
		this.hoaDon = hoaDon;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	@Override
	public String toString() {
		return "TaiKhoan [id=" + id + ", tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + ", ngayTao=" + ngayTao
				+ ", email=" + email + ", soDienThoai=" + soDienThoai + ", ngaySinh=" + ngaySinh + ", gioiTinh="
				+ gioiTinh + ", daXoa=" + daXoa + ", role=" + role + ", hoTen=" + hoTen + ", danhGia=" + danhGia
				+ ", hoaDon=" + hoaDon + "]";
	}

	@Override
	public int compareTo(TaiKhoan o) {
		int diff = this.id - o.id;
		// Note: Two equal employee Id will return 0
		return diff;
	}

}
