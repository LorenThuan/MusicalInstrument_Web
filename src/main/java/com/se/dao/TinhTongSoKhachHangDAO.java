package com.se.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.se.entity.TaiKhoan;

@Repository
public interface TinhTongSoKhachHangDAO extends JpaRepository<TaiKhoan, Integer>{
	@Query(value = "SELECT count(tk.id) FROM Tai_Khoan tk", nativeQuery = true)
	public int tongKhachHang();

}
