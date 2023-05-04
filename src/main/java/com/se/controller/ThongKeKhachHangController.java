package com.se.controller;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se.entity.HoaDon;
import com.se.entity.SanPham;
import com.se.entity.TaiKhoan;
import com.se.service.ThongKeKhachHangService;

@Controller
@RequestMapping("/thongkekhachhang")
public class ThongKeKhachHangController {
	@Autowired
	private ThongKeKhachHangService thongKeKhachHangService;
	
	@GetMapping("/")
	public String showPage() {
		return "productmanagement";
	}
	
	@GetMapping("/thongke")
	public String thongKeKhachHang(Model theModel) {
		Map<TaiKhoan, Double> listKhachhang = thongKeKhachHangService.quanLyThongTinKhachHang();
		int tongKH = listKhachhang.size();
		theModel.addAttribute("listKhachhang", listKhachhang);
		theModel.addAttribute("tongKH", tongKH);
		return "customer";
	}
	
	@GetMapping("/timTheoTen")
	public String timKiemTheoId(@Param("tenKH") String tenKH, Model theModel) {
		Map<TaiKhoan, Double> listTheoTen = thongKeKhachHangService.timKhachHangTheoTen(tenKH);
		int tongKH = thongKeKhachHangService.tongKhachHang();
		theModel.addAttribute("tenKH", tenKH);
		theModel.addAttribute("listKhachhang", listTheoTen);
		theModel.addAttribute("tongKH", tongKH);
		return "customer2";
	}
	
	@GetMapping("/timTheoKH")
	public String timKiemKHLauNam(@RequestParam("soNam") int soNam, Model theModel) {
		if(soNam == 5) {
			Map<TaiKhoan, Double> listTheoKHLauNam = thongKeKhachHangService.timKhachHangLauNam(soNam);
			theModel.addAttribute("listKhachhang", listTheoKHLauNam);
		}
		else if(soNam == 0) {
			Map<TaiKhoan, Double> listTheoKHMoi = thongKeKhachHangService.timKhachHangMoi(soNam);
			theModel.addAttribute("listKhachhang", listTheoKHMoi);
		}
//		theModel.addAttribute("soNam", soNam);
		return "customer2";
	}
	

	
}
