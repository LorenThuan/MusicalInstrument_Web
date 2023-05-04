package com.se.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.se.entity.TaiKhoan;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TaiKhoan tk = (TaiKhoan) userRepository.findByTenTaiKhoan(username);
		if (tk == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUseDetail(tk);
	}

	@Override
	public List<TaiKhoan> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<TaiKhoan> search(String q) {
		return userRepository.findByTenTaiKhoan(q);
	}

}