package com.se.dao;

import java.util.List;
import java.util.Map;

import com.se.entity.HoaDon;
import com.se.entity.TaiKhoan;

public interface ThongKeKhachHangDAO {
	public Map<TaiKhoan, Double> quanLyThongTinKhachHang();
	public Map<TaiKhoan, Double> timKhachHangTheoTen(String tenKH);
	public Map<TaiKhoan, Double> timKhachHangLauNam(int soNam);
	public Map<TaiKhoan, Double> timKhachHangMoi(int soNam);
}
