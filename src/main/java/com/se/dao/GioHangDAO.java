package com.se.dao;

import java.util.List;

import com.se.entity.ChiTietHoaDon;
import com.se.entity.HoaDon;

public interface GioHangDAO {
	 
	public List<ChiTietHoaDon> getCTHoaDon();
	
	public List<ChiTietHoaDon> getTTCTHoaDon();
	
	

	
	public void saveVaoGioHang(String idSPMua,int soLuongMua,String tenTKMuaHang);
	
public List<ChiTietHoaDon> getDonHangDaGiao();
	
	public List<ChiTietHoaDon> getDonHangDangGiao();
	
	public List<HoaDon> getTTKhachHang();
	
	/*
	 * Payment
	 */
	public List<ChiTietHoaDon> getTTHoaDon();
}
