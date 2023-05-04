package com.se.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.se.entity.DanhGia;
import com.se.entity.Loai;
import com.se.entity.NhaSanXuat;
import com.se.entity.SanPham;

public interface QuanLySanPhamService {

	public List<SanPham> getListSanPham();

	public void saveSanPham(SanPham sanPham, MultipartFile hinhChinh, ArrayList<MultipartFile> listHinhPhu)
			throws Exception;

	public SanPham getSanPham(int theId);

	public void deleteSanPham(int theId);

	public void updateSanPham(SanPham sanPham);

	public List<NhaSanXuat> getListNhaSanXuat();

	public List<Loai> getListLoai();

	public int getidSanPhamCuoi();

	public List<SanPham> getListSanPham(String ma);

	public List<SanPham> getListSanPhamBanNhieu(int idSP);
	
	public List<DanhGia> getDanhGiaSP(int idSp);
	
	public void saveDanhGia(DanhGia danhGia);
	
	public List<SanPham> getListSanPhamTheoTen(String tenSP);
	
	public void saveMuaHang(String idSPMua, String soLuongMua, String loaiMuaHang);
}
