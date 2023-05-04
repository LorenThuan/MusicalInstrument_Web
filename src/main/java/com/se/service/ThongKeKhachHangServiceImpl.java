package com.se.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.dao.ThongKeKhachHangDAO;
import com.se.dao.TinhTongSoKhachHangDAO;
import com.se.entity.TaiKhoan;

@Service
public class ThongKeKhachHangServiceImpl implements ThongKeKhachHangService{
	@Autowired
	private ThongKeKhachHangDAO thongKeKhachHangDAO;
	
	@Autowired
	private TinhTongSoKhachHangDAO tinhTongSoKhachHangDAO;

	@Override
	@Transactional
	public Map<TaiKhoan, Double> quanLyThongTinKhachHang() {
		return thongKeKhachHangDAO.quanLyThongTinKhachHang();
	}

	@Override
	@Transactional
	public int tongKhachHang() {
		return tinhTongSoKhachHangDAO.tongKhachHang();
	}

	@Override
	@Transactional
	public Map<TaiKhoan, Double> timKhachHangTheoTen(String tenKH) {
		return thongKeKhachHangDAO.timKhachHangTheoTen(tenKH);
	}

	@Override
	@Transactional
	public Map<TaiKhoan, Double> timKhachHangLauNam(int soNam) {
		return thongKeKhachHangDAO.timKhachHangLauNam(soNam);
	}

	@Override
	@Transactional
	public Map<TaiKhoan, Double> timKhachHangMoi(int soNam) {
		return thongKeKhachHangDAO.timKhachHangMoi(soNam);
	}

	
	

	

	
	

}
