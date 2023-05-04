package com.se.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.se.entity.HoaDon;

@Repository
public interface TinhTongTienDoanhThuDAO extends JpaRepository<HoaDon, Integer>{
	@Query(value = "SELECT sum(hd.tong_tien) from Hoa_Don hd where hd.trang_thai not like N'%Đang chờ xác nhận%' and hd.trang_thai not like N'%Đang giao hàng%'", nativeQuery = true)
	public double tongTien();
}
