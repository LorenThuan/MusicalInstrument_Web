package com.se.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.se.entity.ChiTietHoaDon;
import com.se.service.QuanLySanPhamService;

@Controller
@RequestMapping("/")
public class DoiMatKhauAdminController {
	@GetMapping("/changepass-admin")
	public String showPage(Model theModel) {
		return "changepass-admin";
	}
}
