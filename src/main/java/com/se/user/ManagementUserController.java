package com.se.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se.entity.TaiKhoan;

@Controller
public class ManagementUserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/accountmanagement")
	public String index(Model model) {
		model.addAttribute("users", userRepository.findByAllUserActive());
		return "webapp/admin/account/accountmanagement";
	}

	@GetMapping("/accountmanagement/create")
	public String create(Model model) {
		model.addAttribute("users", new TaiKhoan());
		return "webapp/auth/registration";
	}

	@GetMapping("/accountmanagement/search")
	public String search(@RequestParam("s") String s, Model model) {
		if (s.equals("")) {
			return "redirect:/accountmanagement";
		}
		model.addAttribute("users", userService.search(s));
		return "webapp/admin/account/accountmanagement";
	}

	
	@GetMapping("/accountmanagement/{id}/delete")
	public String deleteUser(@PathVariable int id, Model model) {
		TaiKhoan user = userRepository.findById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setDaXoa(0);
			    userRepository.save(user);
	    return "redirect:/accountmanagement?successdelete";
	}
	
	
	@GetMapping("/accountmanagement/{id}/ressetpass")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		TaiKhoan user = userRepository.findById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
				user.setMatKhau(passwordEncoder.encode("123456"));
			    userRepository.save(user);
	    return "redirect:/accountmanagement?succes";
	}

}