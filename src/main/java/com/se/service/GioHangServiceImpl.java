package com.se.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.dao.GioHangDAO;
import com.se.entity.ChiTietHoaDon;
import com.se.entity.HoaDon;

@Service
public class GioHangServiceImpl implements GioHangService {

	@Autowired
	private GioHangDAO gioHangDAO;

	@Override
	@Transactional
	public List<ChiTietHoaDon> getCTHoaDon() {

		return gioHangDAO.getCTHoaDon();
	}

	@Override
	@Transactional
	public List<ChiTietHoaDon> getTTCTHoaDon() {

		return gioHangDAO.getTTCTHoaDon();
	}

	@Override
	@Transactional
	public void saveVaoGioHang(String idSPMua, int soLuongMua, String tenTKMuaHang) {
		gioHangDAO.saveVaoGioHang(idSPMua, soLuongMua, tenTKMuaHang);

	}

	/**
	 * Lấy thông tin các đơn hàng đã giao cho trang Delivered
	 */
	@Override
	@Transactional
	public List<ChiTietHoaDon> getDonHangDaGiao() {
		// TODO Auto-generated method stub
		return gioHangDAO.getDonHangDaGiao();
	}

	/**
	 * Lấy thông tin các đơn hàng đang giao cho trang Delivering
	 */
	@Override
	@Transactional
	public List<ChiTietHoaDon> getDonHangDangGiao() {
		// TODO Auto-generated method stub
		return gioHangDAO.getDonHangDangGiao();
	}

	/**
	 * Lấy thông tin khách hàng để thanh toán
	 */
	@Override
	@Transactional
	public List<HoaDon> getTTKhachHang() {
		// TODO Auto-generated method stub
		return gioHangDAO.getTTKhachHang();
	}

	/**
	 * lay thong tin hoa don Payment
	 */
	@Override
	@Transactional
	public List<ChiTietHoaDon> getTTHoaDon() {
		// TODO Auto-generated method stub
		return gioHangDAO.getTTHoaDon();
	}

}
