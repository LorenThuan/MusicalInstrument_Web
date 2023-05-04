package com.se.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.se.entity.TaiKhoan;

public class CustomUseDetail implements UserDetails {

	private TaiKhoan taiKhoan;
	


	public CustomUseDetail(TaiKhoan taiKhoan) {
		super();
		this.taiKhoan = taiKhoan;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return taiKhoan.getMatKhau();
	}

	@Override
	public String getUsername() {
		return taiKhoan.getTenTaiKhoan();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
