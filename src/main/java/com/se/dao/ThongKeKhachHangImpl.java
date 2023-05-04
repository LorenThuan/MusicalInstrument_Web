package com.se.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.se.entity.HoaDon;
import com.se.entity.TaiKhoan;

@Repository
public class ThongKeKhachHangImpl implements ThongKeKhachHangDAO{
	private EntityManager entityManager;
	
	
	@Autowired
	public ThongKeKhachHangImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}



	@Override
	public Map<TaiKhoan, Double> quanLyThongTinKhachHang() {
		Map<TaiKhoan, Double> map = new TreeMap<TaiKhoan, Double>();
		Double tinhTien = 0.0;
		Session currentSession = entityManager.unwrap(Session.class);
		List<Object[]> objects = currentSession.createNativeQuery("SELECT Tai_Khoan.id,ten_tai_khoan, ngay_tao, email, so_dien_thoai, ngay_sinh, gioi_tinh, sum(tong_tien) \r\n"
				+ "FROM            Hoa_Don INNER JOIN\r\n"
				+ "                         Tai_Khoan ON Hoa_Don.tai_khoan_id = Tai_Khoan.id\r\n"
				+ "			where [trang_thai] not like N'%Đang chờ xác nhận%' and trang_thai not like N'%Đang giao hàng%' and role not like N'ADMIN' \r\n"
				+ "						 group by  Tai_Khoan.id,ten_tai_khoan, ngay_tao, email, so_dien_thoai, ngay_sinh, gioi_tinh").getResultList();
		for(Object[] o : objects) {
			Integer idTK = (Integer) o[0];
			TaiKhoan taiKhoan = currentSession.get(TaiKhoan.class, idTK);
			BigDecimal tongTien = (BigDecimal) o[7];
			if(tongTien == null) {
				tinhTien = 0.0;
			}
			tinhTien = tongTien.doubleValue();
			map.put(taiKhoan, tinhTien);
		}
		return map;
	}



	@Override
	public Map<TaiKhoan, Double> timKhachHangTheoTen(String tenKH) {
		Map<TaiKhoan, Double> map = new TreeMap<TaiKhoan, Double>();
		Double tinhTien = 0.0;
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createNativeQuery("SELECT Tai_Khoan.id,ten_tai_khoan, ngay_tao, email, so_dien_thoai, ngay_sinh, gioi_tinh, sum(tong_tien) \r\n"
				+ "FROM Hoa_Don INNER JOIN\r\n"
				+ "                         Tai_Khoan ON Hoa_Don.tai_khoan_id = Tai_Khoan.id\r\n"
				+ "			where trang_thai not like N'%Đang chờ xác nhận%' and trang_thai not like N'%Đang giao hàng%' and role not like N'ADMIN' and Tai_Khoan.ten_tai_khoan like  '%" + tenKH +"%'  \r\n"
				+ "						 group by  Tai_Khoan.id,ten_tai_khoan, ngay_tao, email, so_dien_thoai, ngay_sinh, gioi_tinh");
		List<Object> listValue = theQuery.getResultList();
		for(Object object : listValue) {
			Object[] o =  (Object[]) object;
			Integer idTK = (Integer) o[0];
			TaiKhoan taiKhoan = currentSession.get(TaiKhoan.class, idTK);
			BigDecimal tongTien = (BigDecimal) o[7];
			if(tongTien == null) {
				tinhTien = 0.0;
			}
			tinhTien = tongTien.doubleValue();
			map.put(taiKhoan, tinhTien);
		}
		return map;
	}



	@Override
	public Map<TaiKhoan, Double> timKhachHangLauNam(int soNam) {
		Map<TaiKhoan, Double> map = new TreeMap<TaiKhoan, Double>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Double tinhTien = 0.0;
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Object[]> theQuery = currentSession.createNativeQuery("SELECT Tai_Khoan.id,ten_tai_khoan, ngay_tao, email, so_dien_thoai, ngay_sinh, gioi_tinh, sum(tong_tien) \r\n"
				+ "FROM Hoa_Don INNER JOIN\r\n"
				+ "                         Tai_Khoan ON Hoa_Don.tai_khoan_id = Tai_Khoan.id\r\n"
				+ "			where trang_thai not like N'%Đang chờ xác nhận%' and trang_thai not like N'%Đang giao hàng%' and role not like N'ADMIN' and YEAR(ngay_tao) <= YEAR(GETDATE()) - :soNam \r\n"
				+ "						 group by  Tai_Khoan.id,ten_tai_khoan, ngay_tao, email, so_dien_thoai, ngay_sinh, gioi_tinh");
		List<Object[]> objects = theQuery.setParameter("soNam", soNam).getResultList();
		for(Object[] o : objects) {
			Integer idTK = (Integer) o[0];
			TaiKhoan taiKhoan = currentSession.get(TaiKhoan.class, idTK);
			BigDecimal tongTien = (BigDecimal) o[7];
			if(tongTien == null) {
				tinhTien = 0.0;
			}
			tinhTien = tongTien.doubleValue();
			map.put(taiKhoan, tinhTien);
		}
		return map;
	}



	@Override
	public Map<TaiKhoan, Double> timKhachHangMoi(int soNam) {
		Map<TaiKhoan, Double> map = new TreeMap<TaiKhoan, Double>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Double tinhTien = 0.0;
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Object[]> theQuery = currentSession.createNativeQuery("SELECT Tai_Khoan.id,ten_tai_khoan, ngay_tao, email, so_dien_thoai, ngay_sinh, gioi_tinh, sum(tong_tien) \r\n"
				+ "FROM Hoa_Don INNER JOIN\r\n"
				+ "                         Tai_Khoan ON Hoa_Don.tai_khoan_id = Tai_Khoan.id\r\n"
				+ "			where trang_thai not like N'%Đang chờ xác nhận%' and trang_thai not like N'%Đang giao hàng%' and role not like N'ADMIN' and YEAR(ngay_tao) = YEAR(GETDATE()) + :soNam\r\n"
				+ "						 group by  Tai_Khoan.id,ten_tai_khoan, ngay_tao, email, so_dien_thoai, ngay_sinh, gioi_tinh");
		List<Object[]> objects = theQuery.setParameter("soNam", soNam).getResultList();
		for(Object[] o : objects) {
			Integer idTK = (Integer) o[0];
			TaiKhoan taiKhoan = currentSession.get(TaiKhoan.class, idTK);
			BigDecimal tongTien = (BigDecimal) o[7];
			if(tongTien == null) {
				tinhTien = 0.0;
			}
			tinhTien = tongTien.doubleValue();
			map.put(taiKhoan, tinhTien);
		}
		return map;
	}






}
