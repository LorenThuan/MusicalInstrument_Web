package com.se.user;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.se.entity.TaiKhoan;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository repository;

	public UserRegistrationController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("users")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "webapp/auth/registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute TaiKhoan taikhoan, HttpSession httpSession) {
		if (repository.existsByTenTaiKhoan(taikhoan.getTenTaiKhoan())) {
			return "redirect:/registration?fails";
		} else {
			taikhoan.setRole("USER");
			Date date = new Date();
			taikhoan.setNgayTao(date);
			taikhoan.setMatKhau(passwordEncoder.encode(taikhoan.getMatKhau()));
			taikhoan.setDaXoa(1);
			repository.save(taikhoan);
			return "redirect:/registration?success";
		}
	}
}
