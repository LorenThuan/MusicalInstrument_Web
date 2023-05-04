package com.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.se.entity.DanhGia;
import com.se.entity.Loai;
import com.se.entity.NhaSanXuat;
import com.se.entity.SanPham;

public interface QuanLySanPhamDAO {

	public List<SanPham> getListSanPham();

	public List<SanPham> getListSanPham(String ma);

	public void saveSanPham(SanPham sanPham, MultipartFile hinhChinh, ArrayList<MultipartFile> listHinhPhu)
			throws Exception;

	public SanPham getSanPham(int theId);

	public void deleteSanPham(int theId);

	public List<NhaSanXuat> getListNhaSanXuat();

	public List<Loai> getListLoai();

	public int getidSanPhamCuoi();

	public void updateSanPham(SanPham sanPham);

	public List<SanPham> getListSanPhamBanNhieu(int soLuong);

	public List<DanhGia> getDanhGiaSP(int idSP);

	public void saveDanhGia(DanhGia danhGia);

	public List<SanPham> getListSanPhamTheoTen(String tenSP);
	
	public void saveMuaHang(String idSPMua, String soLuongMua, String loaiMuaHang);

}
