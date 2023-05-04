package com.se.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.dao.ThongKeDoanhThuDAO;
import com.se.dao.TinhTongTienDoanhThuDAO;

import com.se.entity.HoaDon;


@Service
public class ThongKeDoanhThuServiceImpl implements ThongKeDoanhThuService{
	@Autowired
	private ThongKeDoanhThuDAO thongKeDoanhThuDAO;
	
	@Autowired
	private TinhTongTienDoanhThuDAO tinhTongTienDoanhThuDAO;
//	@Override
//	@Transactional
//	public List<HoaDon> getAllCTHD() {
//		return thongKeDoanhThuDAO.getAllCTHD();
//	}

	

	@Override
	@Transactional
	public double tongTien() {
		return tinhTongTienDoanhThuDAO.tongTien();
	}



	@Override
	@Transactional
	public List<HoaDon> getJoinInfomation() {
		return thongKeDoanhThuDAO.getJoinInfomation();
	}



	@Override
	@Transactional
	public List<HoaDon> findById(int idHD) {
		return thongKeDoanhThuDAO.findById(idHD);
	}



	@Override
	@Transactional
	public List<HoaDon> layThongTinHoaDonTheoDMY(int day, int month, int year) {
		return thongKeDoanhThuDAO.layThongTinHoaDonTheoDMY(day, month, year);
	}



	@Override
	@Transactional
	public List<HoaDon> layThongTinHoaDonTheoMY(int month, int year) {
		return thongKeDoanhThuDAO.layThongTinHoaDonTheoMY(month, year);
	}



	


	
	
}
