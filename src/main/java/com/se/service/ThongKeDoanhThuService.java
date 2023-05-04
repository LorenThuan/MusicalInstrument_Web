package com.se.service;

import java.util.List;
import java.util.Map;


import com.se.entity.ChiTietHoaDon;
import com.se.entity.HoaDon;
import com.se.entity.SanPham;

public interface ThongKeDoanhThuService{
//	public List<HoaDon> getAllCTHD();
	
	public List<HoaDon> getJoinInfomation();
	public List<HoaDon> findById(int idHD);
	public double tongTien();
	public List<HoaDon> layThongTinHoaDonTheoMY(int month, int year);
	public List<HoaDon> layThongTinHoaDonTheoDMY(int day, int month, int year);
	
}
