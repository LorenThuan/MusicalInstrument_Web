package com.se.dao;


import java.util.List;
import java.util.Map;
import com.se.entity.HoaDon;
import com.se.entity.SanPham;


public interface ThongKeDoanhThuDAO{
	public List<HoaDon> getJoinInfomation();
	public List<HoaDon> findById(int idHD);
	public List<HoaDon> layThongTinHoaDonTheoMY(int month, int year);
	public List<HoaDon> layThongTinHoaDonTheoDMY(int day, int month, int year);
}
