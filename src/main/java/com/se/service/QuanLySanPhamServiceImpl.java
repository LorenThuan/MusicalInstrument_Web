package com.se.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.se.dao.QuanLySanPhamDAO;
import com.se.entity.DanhGia;
import com.se.entity.Loai;
import com.se.entity.NhaSanXuat;
import com.se.entity.SanPham;

@Service
public class QuanLySanPhamServiceImpl implements QuanLySanPhamService {

	@Autowired
	private QuanLySanPhamDAO quanLySanPhamDAO;

	@Override
	@Transactional
	public List<SanPham> getListSanPham() {
		return quanLySanPhamDAO.getListSanPham();
	}
	
	@Override
	@Transactional
	public void saveSanPham(SanPham sanPham, MultipartFile hinhChinh, ArrayList<MultipartFile> listHinhPhu)
			throws Exception {
		quanLySanPhamDAO.saveSanPham(sanPham,hinhChinh,listHinhPhu);
		
	}
	
	

	@Override
	@Transactional
	public SanPham getSanPham(int theId) {
		return quanLySanPhamDAO.getSanPham(theId);
	}

	@Override
	@Transactional
	public void deleteSanPham(int theId) {
		quanLySanPhamDAO.deleteSanPham(theId);

	}

	@Override
	@Transactional
	public List<NhaSanXuat> getListNhaSanXuat() {
		
		return quanLySanPhamDAO.getListNhaSanXuat();
	}

	@Override
	@Transactional
	public List<Loai> getListLoai() {
		return quanLySanPhamDAO.getListLoai();
	}

	@Override
	@Transactional
	public int getidSanPhamCuoi() {
		return quanLySanPhamDAO.getidSanPhamCuoi();
	}

	@Override
	@Transactional
	public List<SanPham> getListSanPham(String ma) {
		return quanLySanPhamDAO.getListSanPham(ma);
	}

	@Override
	@Transactional
	public void updateSanPham(SanPham sanPham) {
		quanLySanPhamDAO.updateSanPham(sanPham);
		
	}

	@Override
	@Transactional
	public List<SanPham> getListSanPhamBanNhieu(int soLuong) {
		return quanLySanPhamDAO.getListSanPhamBanNhieu(soLuong);
	}

	@Override
	@Transactional
	public List<DanhGia> getDanhGiaSP(int idSP) {
		return quanLySanPhamDAO.getDanhGiaSP(idSP);
	}

	@Override
	@Transactional
	public void saveDanhGia(DanhGia danhGia) {
		quanLySanPhamDAO.saveDanhGia(danhGia);
	}

	@Override
	@Transactional
	public List<SanPham> getListSanPhamTheoTen(String tenSP) {
		
		return quanLySanPhamDAO.getListSanPhamTheoTen(tenSP);
	}

	@Override
	@Transactional
	public void saveMuaHang(String idSPMua, String soLuongMua, String loaiMuaHang) {
		quanLySanPhamDAO.saveMuaHang(idSPMua, soLuongMua, loaiMuaHang);
		
	}





	

}
