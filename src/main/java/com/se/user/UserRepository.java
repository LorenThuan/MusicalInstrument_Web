package com.se.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.se.entity.TaiKhoan;

@Repository
public interface UserRepository extends JpaRepository<TaiKhoan, String>{
	
	@Query("SELECT u FROM TaiKhoan u WHERE u.tenTaiKhoan = ?1")
	List<TaiKhoan> findByTenTaiKhoan(String tenTaiKhoan);
	
	@Query("SELECT u FROM TaiKhoan u WHERE u.daXoa=1")
	List<TaiKhoan> findByAllUserActive();
	
	@Modifying
	@Query("update TaiKhoan u set u.daXoa = 0 where u.id  = ?1")
	void deletreAccount( int id);

	Optional<TaiKhoan> findById(int id);
	
	boolean existsByTenTaiKhoan(String tentaikhoan);


}
