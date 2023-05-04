package com.se.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se.entity.CustomTTSP;
import com.se.entity.SanPham;
import com.se.service.QuanLySanPhamService;

@Controller
public class TrangChuController {

	@Autowired
	private QuanLySanPhamService quanLySanPhamService;

	@GetMapping("/")
	public String showPageIndex(Model theModel) {

		int index = 0;
		List<SanPham> listSP = quanLySanPhamService.getListSanPham();
		List<SanPham> listSPResult = new ArrayList<SanPham>();
		while(index <= 11) {
			index++;
			listSPResult.add(listSP.get(index));
		}
		Map<SanPham, CustomTTSP> mapSP = new HashMap<SanPham, CustomTTSP>();
		putDataMap(listSPResult, mapSP);
		theModel.addAttribute("mapSP", mapSP);

		List<SanPham> listBanChay = quanLySanPhamService.getListSanPhamBanNhieu(3);
		Map<SanPham, CustomTTSP> mapSPBanChay = new HashMap<SanPham, CustomTTSP>();
		putDataMap(listBanChay, mapSPBanChay);
		theModel.addAttribute("sanPhamBanChay", mapSPBanChay);

		List<SanPham> listPhoBien = quanLySanPhamService.getListSanPhamBanNhieu(6);
		Map<SanPham, CustomTTSP> mapSPPhoBien = new HashMap<SanPham, CustomTTSP>();
		putDataMap(listPhoBien, mapSPPhoBien);
		theModel.addAttribute("sanPhamPhoBien", mapSPPhoBien);

		System.out.println(getTenTaiKhoanLogin());
		
		if(getTenTaiKhoanLogin().equals("anonymousUser"))
			return "index";
		return "index-logined";
	}

	@GetMapping("/timSanPham")
	public String showPageListProduct(@RequestParam("tenSPCanTim") String tenSP, Model theModel) {
		
		List<SanPham> listTemp = quanLySanPhamService.getListSanPham();
		List<SanPham> listSP = new ArrayList<SanPham>();
		for (SanPham sanPham : listTemp)
			if (sanPham.getTen().toLowerCase().contains(tenSP.toLowerCase()))
				listSP.add(sanPham);

		Map<SanPham, CustomTTSP> mapSP = new HashMap<SanPham, CustomTTSP>();
		putDataMap(listSP, mapSP);
		theModel.addAttribute("mapSP", mapSP);
		String tbTim = "";
		if (listSP.isEmpty())
			tbTim = "Không tìm thấy";
		theModel.addAttribute("tbTim", tbTim);
		
		if(getTenTaiKhoanLogin().equals("anonymousUser"))
			return "listproduct";
		return "listproduct-logined";
		
	}

	private void putDataMap(List<SanPham> listSP, Map<SanPham, CustomTTSP> mapSP) {
		for (SanPham sanPham : listSP) {

			DecimalFormat df = new DecimalFormat("#,000");
			String giamGiaInt = (String.valueOf(sanPham.getGiamGia()*100).replace(".0", ""));
			String giaGoc = df.format(sanPham.getGia());
			String giaDaGiam = df.format(sanPham.getGia() * (1 - sanPham.getGiamGia() ));

			CustomTTSP custom = new CustomTTSP(giamGiaInt, giaGoc, giaDaGiam);

			mapSP.put(sanPham, custom);
		}
	}
	
	public String getTenTaiKhoanLogin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String tenTKMuaHang = "";
		if (principal instanceof UserDetails) {
			tenTKMuaHang = ((UserDetails) principal).getUsername();
		} else {
			tenTKMuaHang = principal.toString();
		}
		return tenTKMuaHang;
		
	}

}
