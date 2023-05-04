package com.se.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.se.entity.ChiTietHoaDon;
import com.se.entity.HoaDon;
import com.se.entity.SanPham;

@Repository
public class ThongKeDoanhThuImpl implements ThongKeDoanhThuDAO{

	private EntityManager entityManager;
	@Autowired
	public ThongKeDoanhThuImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}
	@Override
	public List<HoaDon> getJoinInfomation() {
		List<HoaDon> hoaDons = new ArrayList<HoaDon>();
		Session currentSession = entityManager.unwrap(Session.class);
		List<Object[]> object = currentSession.createNativeQuery("SELECT hd.id, tk.id as idTk\r\n"
				+ "from Hoa_Don hd inner join Tai_Khoan tk on hd.tai_khoan_id=tk.id group by hd.id, tk.id order by hd.id asc").getResultList();
		for(Object[] o : object) {
			Integer idHd = (Integer) o[0];
			HoaDon hoaDon = currentSession.get(HoaDon.class, idHd);
			hoaDons.add(hoaDon);
		}
		return hoaDons;
	}
	@Override
	public List<HoaDon> findById(int idHD) {
		List<HoaDon> hoaDons = new ArrayList<HoaDon>();
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Object[]> theQuery  = currentSession.createNativeQuery(" SELECT hd.id, tk.id as idTk\r\n"
				+ "						 from	Hoa_Don hd inner join Tai_Khoan tk on hd.tai_khoan_id=tk.id \r\n"
				+ "							where hd.id =:idHD\r\n"
				+ "						group by hd.id, tk.id");
		theQuery.setParameter("idHD", idHD);
		List<Object[]> object  = theQuery.getResultList();
		for(Object[] o : object) {
			Integer idHd = (Integer) o[0];
			HoaDon hoaDon = currentSession.get(HoaDon.class, idHd);
			hoaDons.add(hoaDon);
		}
		return hoaDons;
	}
	@Override
	public List<HoaDon> layThongTinHoaDonTheoMY(int month, int year) {
		List<HoaDon> hoaDons = new ArrayList<HoaDon>();
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Object[]> theQuery  = currentSession.createNativeQuery("SELECT hd.id, tk.id as idTk  from	Hoa_Don hd inner join Tai_Khoan tk on hd.tai_khoan_id=tk.id\r\n"
				+ "							where YEAR(GETDATE()) = :year and MONTH(GETDATE()) > :month\r\n"
				+ "												group by hd.id, tk.id");
		theQuery.setParameter("month", month);
		theQuery.setParameter("year", year);
		List<Object[]> object  = theQuery.getResultList();
		for(Object[] o : object) {
			Integer idHd = (Integer) o[0];
			HoaDon hoaDon = currentSession.get(HoaDon.class, idHd);
			hoaDons.add(hoaDon);
		}
		return hoaDons;
	}
	@Override
	public List<HoaDon> layThongTinHoaDonTheoDMY(int day, int month, int year) {
		List<HoaDon> hoaDons = new ArrayList<HoaDon>();
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Object[]> theQuery  = currentSession.createNativeQuery("SELECT hd.id, tk.id as idTk  from	Hoa_Don hd inner join Tai_Khoan tk on hd.tai_khoan_id=tk.id\r\n"
				+ "							where YEAR(GETDATE()) = :year and MONTH(GETDATE()) = :month and DAY(GETDATE()) >= :day \r\n"
				+ "												group by hd.id, tk.id");
		theQuery.setParameter("day", day);
		theQuery.setParameter("month", month);
		theQuery.setParameter("year", year);
		List<Object[]> object  = theQuery.getResultList();
		for(Object[] o : object) {
			Integer idHd = (Integer) o[0];
			HoaDon hoaDon = currentSession.get(HoaDon.class, idHd);
			hoaDons.add(hoaDon);
		}
		return hoaDons;
	}
	
}
