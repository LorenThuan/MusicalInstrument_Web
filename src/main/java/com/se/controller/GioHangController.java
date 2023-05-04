package com.se.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.se.entity.ChiTietHoaDon;
import com.se.entity.HoaDon;
import com.se.entity.TaiKhoan;
import com.se.service.GioHangService;

@Controller
@RequestMapping("/giohang")
public class GioHangController {
	
	@Autowired
	private GioHangService gioHangService;
	
	/**
	 * Trang thông tin về các sản phẩm mà khách hàng đã chọn mua
	 * @param theModel
	 * @return
	 */
	@GetMapping("/cart")
	public String showPage(Model theModel) {
		List<ChiTietHoaDon> listCT = gioHangService.getCTHoaDon();
		List<ChiTietHoaDon> listCTResult = new ArrayList<ChiTietHoaDon>();
		for(int i = 0; i < listCT.size(); ++i) {
			if(listCT.get(i).getHoaDon().getTrangThai().equals("Đang trong giỏ hàng")) {
				listCTResult.add(listCT.get(i));
			}
		}
		
		double tongThanhToan = 0;
		
		for(int i = 0; i < listCTResult.size(); i++) {
			tongThanhToan = (listCTResult.get(i).getGia()*listCTResult.get(i).getSoLuong()) + tongThanhToan;
		}
		
		theModel.addAttribute("tongTT", tongThanhToan);
		
		theModel.addAttribute("carts",listCTResult);
		return "cart";
	}
	
	/**
	 * Trang thanh toán xác nhận thông tin của khách hàng cho trang Payment
	 * @param theModel
	 * @return
	 */
	@GetMapping("/payment")
	public String showPagePayment(Model theModel) {		
		
		List<HoaDon> listHD = gioHangService.getTTKhachHang();
		
		
		
//		if(listHD.size() > 0) {
//			for(int i = 0; i < listHD.size(); i++)
//				if(listHD.size() > 1) {
//					i--;
//				}
//		}
		List<ChiTietHoaDon> listCTHD = gioHangService.getTTHoaDon();
		List<ChiTietHoaDon> listNew = new ArrayList<ChiTietHoaDon>();
		for(int i = 0; i < listCTHD.size(); i++) {
			listNew.add(listCTHD.get(0));
		}
		
		
		///
		double tongThanhToan = 0;
		
		for(int i = 0; i < listCTHD.size(); i++) {
			tongThanhToan = (listCTHD.get(i).getGia()*listCTHD.get(i).getSoLuong()) + tongThanhToan;
		}
		
		theModel.addAttribute("tongTT", tongThanhToan);
		
		theModel.addAttribute("chitietHD", listCTHD);
		theModel.addAttribute("hoadon", listHD);
		
		
		return "payment";
	}
	
	/**
	 * Lấy thông tin của những đơn hàng đang giao cho trang Delivering
	 * @param theModel
	 * @return
	 */
	@GetMapping("/delivering") 
	public String showPageDelivering(Model theModel) {
		
		List<ChiTietHoaDon> listDHDG = gioHangService.getDonHangDangGiao();
		List<ChiTietHoaDon> listDHResult = new ArrayList<ChiTietHoaDon>();
		for(int i = 0; i < listDHDG.size(); ++i) {
			if(listDHDG.get(i).getHoaDon().getTrangThai().equals("Đang giao hàng")) {
				listDHResult.add(listDHDG.get(i));
			}
		}
		
		theModel.addAttribute("listDH", listDHResult);
		
		return "delivering";
	}
	
	/**
	 * Lấy thông tin những đơn hàng đã giao của khách hàng cho trang Delivered
	 * @param theModel
	 * @return
	 */
	@GetMapping("/delivered")
	public String showPageDelivered(Model theModel) {
		
		List<ChiTietHoaDon> listDHDG = gioHangService.getDonHangDaGiao();
		List<ChiTietHoaDon> listDHResult = new ArrayList<ChiTietHoaDon>();
		for(int i = 0; i < listDHDG.size(); ++i) {
			if(listDHDG.get(i).getHoaDon().getTrangThai().equals("Đã giao")) {
				listDHResult.add(listDHDG.get(i));
			}
		}
		
		theModel.addAttribute("listDH", listDHResult);
		
		return "delivered";
	}
	
	
	/**
	 * Lấy thông tin những sản phẩm đã được khách hàng xác nhận mua cho trang Completed
	 * @param theModel
	 * @return
	 */
	@GetMapping("/completed")
	public String showPageComplete(Model theModel) {
		
		List<ChiTietHoaDon> listCT = gioHangService.getCTHoaDon();
		List<ChiTietHoaDon> listCTResult = gioHangService.getCTHoaDon();
//		for(int i = 0; i < listCT.size(); ++i) {
//			if(listCT.get(i).getHoaDon().getTrangThai().equals("Đang giao hàng")) {
//				listCTResult.add(listCT.get(i));
//			}
//		}
		
		String s1 = "Đang giao hàng";
		for(int i = 0; i < listCT.size(); ++i) {
			if(listCT.get(i).getHoaDon().getTrangThai() == s1) {
				
			} else {
				listCT.remove(i);
			}
		}
		
		
		double tongThanhToan = 0;
		
		for(int i = 0; i < listCT.size(); i++) {
			tongThanhToan = (listCT.get(i).getGia()*listCT.get(i).getSoLuong()) + tongThanhToan;
		}
		
		theModel.addAttribute("tongTT", tongThanhToan);
		
		theModel.addAttribute("chitietN",listCT);
//		
		return "completed";
	}
	
}
