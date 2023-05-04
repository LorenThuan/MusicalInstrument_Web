package com.se.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.se.entity.ChiTietHoaDon;
import com.se.entity.HoaDon;
import com.se.entity.SanPham;
import com.se.entity.TaiKhoan;

@Repository
public class GioHangDAOImpl implements GioHangDAO {

	private EntityManager entityManager;

	@Autowired
	public GioHangDAOImpl(EntityManager theEntityManager) {

		entityManager = theEntityManager;
	}

	@Override
	public List<ChiTietHoaDon> getCTHoaDon() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ChiTietHoaDon> theQuery = currentSession.createQuery("from ChiTietHoaDon", ChiTietHoaDon.class);
		List<ChiTietHoaDon> listCT = theQuery.getResultList();

		return listCT;
	}

	@Override
	public List<ChiTietHoaDon> getTTCTHoaDon() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ChiTietHoaDon> theQuery = currentSession.createQuery("from ChiTietHoaDon", ChiTietHoaDon.class);
		List<ChiTietHoaDon> cthd = theQuery.getResultList();

		return cthd;
	}

	@Override
	public void saveVaoGioHang(String idSPMua, int soLuongMua, String tenTKMuaHang) {
		Session currentSession = entityManager.unwrap(Session.class);
		// Lấy thông tin sản phẩm được mua
		SanPham sp = currentSession.get(SanPham.class, Integer.parseInt(idSPMua));
		double giaSP = sp.getGia() * (1 - sp.getGiamGia());
		HoaDon hd = new HoaDon();

		// Lấy thông tin tài khoản đang login
		Query<TaiKhoan> theQuery2 = currentSession.createQuery("From TaiKhoan", TaiKhoan.class);
		List<TaiKhoan> listTK = theQuery2.getResultList();
		TaiKhoan tk = new TaiKhoan();
		for (TaiKhoan taiKhoan : listTK) {
			if (taiKhoan.getTenTaiKhoan().toLowerCase().equals(tenTKMuaHang.toLowerCase()))
				tk = taiKhoan;
		}

		hd.setTaiKhoan(tk);
		hd.setNgayDat(new Date());
		hd.setTrangThai("Đang trong giỏ hàng");

		// Kiểm tra tài khoản này có hóa đơn chưa
		Query<HoaDon> theQuery = currentSession.createQuery("from HoaDon", HoaDon.class);
		List<HoaDon> listHD = theQuery.getResultList();
		int flag = 0;
		HoaDon hdTemp = new HoaDon();
		for (HoaDon hoaDon : listHD) {
			if (hoaDon.getTaiKhoan().getId() == tk.getId()
					&& hoaDon.getTrangThai().equalsIgnoreCase("Đang trong giỏ hàng")) {
				hdTemp = hoaDon;
				flag = 1;
				break;
			}
		}
		// Nếu có thì cập nhật lại hóa đơn và chi tiết hóa đơn
		if (flag == 1) {
			hdTemp.setNgayDat(new Date());
			hdTemp.setTongTien(hdTemp.getTongTien() + (giaSP * soLuongMua));
			currentSession.update(hdTemp);

			// Kiểm tra chi tiết hóa đơn đã có sản phẩm này hay chưa
			Query<ChiTietHoaDon> queryCTHD = currentSession.createQuery("from ChiTietHoaDon", ChiTietHoaDon.class);
			List<ChiTietHoaDon> listCTHD = queryCTHD.getResultList();
			ChiTietHoaDon cthdTemp = new ChiTietHoaDon();
			int flag2 = 0;
			for (ChiTietHoaDon cthd : listCTHD) {
				if (cthd.getHoaDon().getId() == hdTemp.getId() && cthd.getSanPham().getId() == sp.getId()) {
					cthdTemp = cthd;
					cthdTemp.setSoLuong(cthd.getSoLuong() + soLuongMua);
					cthdTemp.setGia((cthd.getGia() + giaSP) / 2); // cập nhật lại thông tin
					cthdTemp.setTongTien(cthd.getTongTien() + giaSP * soLuongMua); // vào biến cthdTemp
					flag2 = 1;
				}
			}
			// Nếu có thì cập nhật lại chi tiết hóa đơn
			if (flag2 == 1) {
				currentSession.update(cthdTemp);
			}
			// Nếu chưa thì tạo mới chi tiết hóa đơn
			else {
				ChiTietHoaDon cthd = new ChiTietHoaDon(0, giaSP, soLuongMua, giaSP * soLuongMua, hdTemp, sp);
				currentSession.save(cthd);
			}
		}
		// Nếu chưa thì tạo mới hóa đơn và chi tiết hóa đơn
		else {
			hd.setId(0);
			hd.setTongTien(giaSP * soLuongMua);
			int idHD = (int) currentSession.save(hd);
			hd = currentSession.get(HoaDon.class, idHD);
			ChiTietHoaDon cthd = new ChiTietHoaDon(0, giaSP, soLuongMua, giaSP * soLuongMua, hd, sp);
			currentSession.save(cthd);
		}
	}

	/**
	 * Lấy thông tin cho trang Delivering
	 */
	@Override
	public List<ChiTietHoaDon> getDonHangDangGiao() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ChiTietHoaDon> theQuery = currentSession.createQuery("from ChiTietHoaDon", ChiTietHoaDon.class);
		List<ChiTietHoaDon> listDG = theQuery.getResultList();

		return listDG;
	}

	/**
	 * Lấy thông tin khách hàng để thanh toán cho trang Payment
	 */
	@Override
	public List<HoaDon> getTTKhachHang() {

		// Ham lay user hien tai
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Session currentSession = entityManager.unwrap(Session.class);
//		Query<ChiTietHoaDon> theQuery = currentSession.createQuery("from ChiTietHoaDon" , ChiTietHoaDon.class);
//		List<ChiTietHoaDon> listDG = theQuery.getResultList();

		String hql = " from  HoaDon p inner join p.taiKhoan where p.taiKhoan.tenTaiKhoan like '" + username + "' ";

		Query query = currentSession.createQuery(hql);

		List<Object[]> listResult = query.list();
		List<HoaDon> listHD = new ArrayList<HoaDon>();
		for (Object[] aRow : listResult) {
			HoaDon hd = (HoaDon) aRow[0];
			TaiKhoan tk = (TaiKhoan) aRow[1];
			listHD.add(hd);
		}

		return listHD;
	}

	/**
	 * Lay thong tin hoa don cho Payment
	 */
//	@Override
//	public List<ChiTietHoaDon> getTTHoaDon() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		String username = "";
//		if (principal instanceof UserDetails) {
//			username = ((UserDetails) principal).getUsername();
//		} else {
//			username = principal.toString();
//		}
//		Session currentSession = entityManager.unwrap(Session.class);
//		
//		String hql = " from ChiTietHoaDon p inner join p.hoaDon hd inner join hd.taiKhoan where hd.taiKhoan.tenTaiKhoan like '"+username+"' ";
//		
//		Query query = currentSession.createQuery(hql);
//		
//		List<Object[]> listResult = query.list();
//		List<ChiTietHoaDon> listHD = new ArrayList<ChiTietHoaDon>();
//		for (Object[] aRow : listResult) {
//			ChiTietHoaDon cthd = (ChiTietHoaDon) aRow[0];
//			HoaDon hd = (HoaDon) aRow[1];
//		    TaiKhoan tk = (TaiKhoan) aRow[2];
//		    listHD.add(cthd);
//		}
//		
//		
//		return listHD;
//	}

	@Override
	public List<ChiTietHoaDon> getTTHoaDon() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ChiTietHoaDon> theQuery = currentSession.createQuery("from ChiTietHoaDon", ChiTietHoaDon.class);
		List<ChiTietHoaDon> listHD = theQuery.getResultList();

		return listHD;
	}

	@Override
	public List<ChiTietHoaDon> getDonHangDaGiao() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ChiTietHoaDon> theQuery = currentSession.createQuery("from ChiTietHoaDon", ChiTietHoaDon.class);
		List<ChiTietHoaDon> listDG = theQuery.getResultList();

		return listDG;
	}

}
