package com.se.service;

import java.util.List;
import java.util.Map;

import com.se.entity.HoaDon;
import com.se.entity.SanPham;
import com.se.entity.TaiKhoan;

public interface ThongKeKhachHangService {
	public Map<TaiKhoan, Double> quanLyThongTinKhachHang();
	public int tongKhachHang();
	public Map<TaiKhoan, Double> timKhachHangTheoTen(String tenKH);
	public Map<TaiKhoan, Double> timKhachHangLauNam(int soNam);
	public Map<TaiKhoan, Double> timKhachHangMoi(int soNam);
}
