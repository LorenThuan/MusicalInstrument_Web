package com.se.controller;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se.entity.CustomTTSP;
import com.se.entity.DanhGia;
import com.se.entity.SanPham;
import com.se.entity.TaiKhoan;
import com.se.service.GioHangService;
import com.se.service.QuanLySanPhamService;

@Controller
@RequestMapping("/item")
public class ChiTietSanPhamController {

	@Autowired
	private QuanLySanPhamService quanLySanPhamService;

	@Autowired
	private GioHangService gioHangService;

	private int idSPChon;

	@GetMapping("/")
	public String showPageItem(@RequestParam("idSPDuocChon") int idSPDuocChon, Model theModel) {

		idSPChon = idSPDuocChon;
		SanPham sanPham = quanLySanPhamService.getSanPham(idSPDuocChon);

		theModel.addAttribute("SPDuocChon", sanPham);

		DecimalFormat df = new DecimalFormat("#,000");
		String giaDaGiam = df.format(sanPham.getGia() * (1 - sanPham.getGiamGia()));
		CustomTTSP ttCustom = new CustomTTSP("", df.format(sanPham.getGia() * 100), giaDaGiam);

		theModel.addAttribute("ttSPCustom", ttCustom);

		List<DanhGia> danhGiaSP = quanLySanPhamService.getDanhGiaSP(idSPDuocChon);
		theModel.addAttribute("danhGiaSP", danhGiaSP);

		String tenTKMuaHang = getTenTaiKhoanLogin();
		String binhLuanCuaKH = "";
		for (DanhGia danhGia : danhGiaSP) {
			if (danhGia.getTaiKhoan().getTenTaiKhoan().toLowerCase().equals(tenTKMuaHang.toLowerCase())) {
				binhLuanCuaKH = danhGia.getBinhLuan();
			}
		}

		theModel.addAttribute("danhGiaCuaKH", binhLuanCuaKH);

		int soLuongDanhGia = 0;
		if (danhGiaSP.isEmpty())
			soLuongDanhGia = 0;
		else
			soLuongDanhGia = danhGiaSP.size();

		theModel.addAttribute("soLuongDanhGia", soLuongDanhGia);

		if (getTenTaiKhoanLogin().equals("anonymousUser"))
			return "item";
		return "item-logined";
	}

	@GetMapping("/danhGiaSP")
	public String danhGiaSP(@RequestParam("idSPDuocChon") int idSPDanhGia, @RequestParam("binhLuan") String binhLuan) {

		String tenTKMuaHang = getTenTaiKhoanLogin();

		TaiKhoan tk = new TaiKhoan();
		tk.setTenTaiKhoan(tenTKMuaHang);

		SanPham sp = new SanPham();
		sp.setId(idSPDanhGia);

		DanhGia danhGia = new DanhGia(0, binhLuan, tk, sp);

		quanLySanPhamService.saveDanhGia(danhGia);

		return "redirect:/item/chiTiet";
	}

	@GetMapping("/chiTiet")
	public String showPageChiTiet(Model theModel) {

		SanPham sanPham = quanLySanPhamService.getSanPham(idSPChon);
		theModel.addAttribute("SPDuocChon", sanPham);

		DecimalFormat df = new DecimalFormat("#,000");
		String giaDaGiam = df.format(sanPham.getGia() * (1 - sanPham.getGiamGia()));
		CustomTTSP ttCustom = new CustomTTSP("", df.format(sanPham.getGia()), giaDaGiam);

		theModel.addAttribute("ttSPCustom", ttCustom);

		List<DanhGia> danhGiaSP = quanLySanPhamService.getDanhGiaSP(idSPChon);
		theModel.addAttribute("danhGiaSP", danhGiaSP);

		int soLuongDanhGia = 0;
		if (danhGiaSP.isEmpty())
			soLuongDanhGia = 0;
		else
			soLuongDanhGia = danhGiaSP.size();

		theModel.addAttribute("soLuongDanhGia", soLuongDanhGia);
		return "item-logined";
	}

	@GetMapping("/muaSP")
	public String muaSP(Model theModel, @RequestParam("muaHang") String loaiMuaHang,
			@RequestParam("soLuongMua") int soLuongMua, @RequestParam("idSPMua") String idSPMua) {

		String tenTKMuaHang = getTenTaiKhoanLogin();

		if (loaiMuaHang.equals("muaNgay")) {
			gioHangService.saveVaoGioHang(idSPMua, soLuongMua, tenTKMuaHang);
			return "redirect:/giohang/cart";
		} else {
			gioHangService.saveVaoGioHang(idSPMua, soLuongMua, tenTKMuaHang);
			return "redirect:/item/chiTiet";
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
