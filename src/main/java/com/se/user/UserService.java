package com.se.user;

import java.util.List;
import java.util.Optional;

import com.se.entity.TaiKhoan;

public interface UserService {
	List<TaiKhoan> findAll();
	
	List<TaiKhoan> search(String q);

}
