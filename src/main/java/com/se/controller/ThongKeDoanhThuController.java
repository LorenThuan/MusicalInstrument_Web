package com.se.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import com.se.service.ThongKeDoanhThuService;
@Controller
@RequestMapping("/thongkedoanhthu")
public class ThongKeDoanhThuController {
	@Autowired
	private ThongKeDoanhThuService thongKeDoanhThuService;
	
	@GetMapping("/thongke")
	public String thongKeDoanhThu(Model theModel) {
		List<HoaDon> listDoanhthu = thongKeDoanhThuService.getJoinInfomation();
		Double tongTien = thongKeDoanhThuService.tongTien();
		theModel.addAttribute("tongtien", tongTien);
		theModel.addAttribute("listDoanhthu", listDoanhthu);
		return "turnover";
	}
	
	@GetMapping("/")
	public String showPage() {
		
		return "productmanagement";
	}
	
	@GetMapping("/timTheoId")
	public String timKiemTheoId(@RequestParam("idHD") int idHD, Model theModel) {
		List<HoaDon> listTheoId = thongKeDoanhThuService.findById(idHD);
		Double tongTien = thongKeDoanhThuService.tongTien();
		theModel.addAttribute("tongtien", tongTien);
		theModel.addAttribute("idHD", idHD);
		theModel.addAttribute("listDoanhthu", listTheoId);
		return "turnover2";
	}
	
	@GetMapping("/timTheoDMY")
	public String timKiemTheoDMY(@RequestParam("day") int day,@RequestParam("month") int month,@RequestParam("year") int year, Model theModel) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int yearNow  = localDate.getYear();
		int monthNow = localDate.getMonthValue();
		int dayNow   = localDate.getDayOfMonth();
		if(yearNow == year && monthNow > month) {
			List<HoaDon> listTheoMY = thongKeDoanhThuService.layThongTinHoaDonTheoMY(month, year);
			theModel.addAttribute("listDoanhthu", listTheoMY);
		}
		else if(yearNow == year && monthNow == month && dayNow >= day) {
			List<HoaDon> listTheoDMY = thongKeDoanhThuService.layThongTinHoaDonTheoDMY(day, month, year);
			theModel.addAttribute("listDoanhthu", listTheoDMY);
		}
		theModel.addAttribute("day", day);
		theModel.addAttribute("month", month);
		theModel.addAttribute("year", year);
		
		return "turnover2";
	}
}
