package com.se.user;

import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserRegistrationDto {

	@Column(name = "ten_tai_khoan",columnDefinition = "varchar(200)",nullable = false,unique = true)
	private String tenTaiKhoan;
	
	private String matKhau;
	
	private Date ngayTao;
	
	private String email;
	
	private String soDienThoai;
	
	private String ngaySinh;
	
	private String gioiTinh;
	
	private String hoTen;
	
	private int daXoa;

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

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public int getDaXoa() {
		return daXoa;
	}

	public void setDaXoa(int daXoa) {
		this.daXoa = daXoa;
	}

	public UserRegistrationDto(String tenTaiKhoan, String matKhau, Date ngayTao, String email, String soDienThoai,
			String ngaySinh, String gioiTinh, String hoTen, int daXoa) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.ngayTao = ngayTao;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.hoTen = hoTen;
		this.daXoa = daXoa;
	}

	public UserRegistrationDto() {
		super();
	}
	
	
}
