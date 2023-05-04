package com.se.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.se.entity.CustomTTSP;
import com.se.entity.HinhPhu;
import com.se.entity.Loai;
import com.se.entity.NhaSanXuat;
import com.se.entity.SanPham;
import com.se.service.QuanLySanPhamService;

@Controller
@RequestMapping("/quanlysanpham")
public class QuanLySanPhamController {

	@Autowired
	private QuanLySanPhamService quanLySanPhamService;

	@GetMapping("/")
	public String showPage(Model theModel) {
		List<SanPham> listSP = quanLySanPhamService.getListSanPham();

		theModel.addAttribute("listSP", listSP);

		Map<SanPham, CustomTTSP> mapSP = new HashMap<SanPham, CustomTTSP>();
		for (SanPham sanPham : listSP) {

			DecimalFormat df = new DecimalFormat("#,000");
			String giamGiaInt = (String.valueOf(sanPham.getGiamGia()*100).replace(".0", ""));
		
			String giaGoc = df.format(sanPham.getGia());
			String giaDaGiam = df.format(sanPham.getGia() * (1 - sanPham.getGiamGia()));

			CustomTTSP custom = new CustomTTSP(giamGiaInt, giaGoc, giaDaGiam);

			mapSP.put(sanPham, custom);

		}
		theModel.addAttribute("mapSP", mapSP);

		return "productmanagement";
	}

	@GetMapping("/productadd")
	public String showPageAdd(Model theModel) {
		List<NhaSanXuat> listNSX = quanLySanPhamService.getListNhaSanXuat();
		List<Loai> listLoai = quanLySanPhamService.getListLoai();
		int idSanPhamCuoi = quanLySanPhamService.getidSanPhamCuoi();

		theModel.addAttribute("listNSX", listNSX);
		theModel.addAttribute("listLoai", listLoai);
		theModel.addAttribute("idCuoi", idSanPhamCuoi + 1);

		return "productadd";
	}

	@PostMapping("/luuSanPham")
	public String luuSanPham(@RequestParam("idCuoi") int idCuoi, @RequestParam("giaSP") double giaSP,
			@RequestParam("tenSP") String tenSP, @RequestParam("nhaSanXuat") int idNhaSanXuat,
			@RequestParam("giamGia") double giamGia, @RequestParam("loai") int idLoai,
			@RequestParam("moTa") String moTa, @RequestParam("hinhChinh") MultipartFile hinhChinh,
			@RequestParam("listHinhPhu") ArrayList<MultipartFile> listHinhPhu) throws Exception {

		SanPham sanPham = new SanPham(idCuoi, tenSP, giaSP, giamGia / 100, moTa, 0);
		sanPham.setNhaSanXuat(new NhaSanXuat(idNhaSanXuat));
		sanPham.setLoai(new Loai(idLoai));
		sanPham.setNgaySua(new Date());
		sanPham.setNgayTao(new Date());

		sanPham.setHinhChinh(hinhChinh.getOriginalFilename());
		ArrayList<HinhPhu> listTemp = new ArrayList<HinhPhu>();
		for (MultipartFile hinhPhu : listHinhPhu) {
			listTemp.add(new HinhPhu(0, hinhPhu.getOriginalFilename(), null));

		}
		sanPham.setListHinhPhu(listTemp);
		quanLySanPhamService.saveSanPham(sanPham, hinhChinh, listHinhPhu);

		return "redirect:/quanlysanpham/productadd";
	}

	@GetMapping("/productupdate")
	public String showPageUpdate(Model theModel, @RequestParam("idSPUpdate") int idSP) {
		SanPham sanPham = quanLySanPhamService.getSanPham(idSP);
		
		theModel.addAttribute("SPupdate", sanPham);
		
		String giamGia = (String.valueOf(sanPham.getGiamGia()*100).replace(".0", ""));
		DecimalFormat df = new DecimalFormat("###");
		String giaGoc = df.format(sanPham.getGia());
		
		
		theModel.addAttribute("giamGia", giamGia);
		theModel.addAttribute("giaGoc", giaGoc);

		

		List<NhaSanXuat> listNSX = quanLySanPhamService.getListNhaSanXuat();
		List<Loai> listLoai = quanLySanPhamService.getListLoai();

		theModel.addAttribute("listNSX", listNSX);
		theModel.addAttribute("listLoai", listLoai);

		theModel.addAttribute("idNSX", sanPham.getNhaSanXuat().getId());
		theModel.addAttribute("idLoai", sanPham.getLoai().getId());

		return "productupdate";
	}

	@GetMapping("/xoaSanPham")
	public String xoaSanPham(@RequestParam("idCanXoa") int idCanXoa) {
		quanLySanPhamService.deleteSanPham(idCanXoa);
		return "redirect:/quanlysanpham/";
	}

	@GetMapping("/timKiem")
	public String timKiem(@RequestParam("idTimKiem") String idTimKiem, Model theModel) {
		if (idTimKiem.trim().equals(""))
			return "redirect:/quanlysanpham/";
		
		List<SanPham> list = quanLySanPhamService.getListSanPham();
		List<SanPham> listTemp =new ArrayList<SanPham>();
		for (SanPham sp : list) {
			if (idTimKiem.trim().matches("\\d*")) {
				if(sp.getId()== Integer.parseInt(idTimKiem)) {
					listTemp.add(sp);
				}
				
			}
			else {
				if(sp.getTen().trim().toLowerCase().contains(idTimKiem.trim().toLowerCase())) {
					listTemp.add(sp);
				}
			}
		}
		
		theModel.addAttribute("listSP", listTemp);

		Map<SanPham, CustomTTSP> mapSP = new HashMap<SanPham, CustomTTSP>();
		for (SanPham sanPham : listTemp) {

			DecimalFormat df = new DecimalFormat("#,000");
			String giamGiaInt = (String.valueOf(sanPham.getGiamGia()*100).replace(".0", ""));
			String giaGoc = df.format(sanPham.getGia());
			String giaDaGiam = df.format(sanPham.getGia() * (1 - sanPham.getGiamGia()));

			CustomTTSP custom = new CustomTTSP(giamGiaInt, giaGoc, giaDaGiam);

			mapSP.put(sanPham, custom);

		}
		theModel.addAttribute("mapSP", mapSP);

		return "productmanagement";
	}

	@PostMapping("/suaSanPham")
	public String suaSanPham(@RequestParam("idSPCanUpdate") String idSP, @RequestParam("giaSP") String giaSP,
			@RequestParam("tenSP") String tenSP, @RequestParam("nhaSanXuat") String idNhaSanXuat,
			@RequestParam("giamGia") String giamGia, @RequestParam("loai") String idLoai,
			@RequestParam("moTa") String moTa) throws Exception {

		SanPham sanPham = new SanPham(Integer.parseInt(idSP), tenSP, Double.parseDouble(giaSP),
				(Double.parseDouble(giamGia) / 100), moTa, 0);
		sanPham.setNhaSanXuat(new NhaSanXuat(Integer.parseInt(idNhaSanXuat)));
		sanPham.setLoai(new Loai(Integer.parseInt(idLoai)));
		sanPham.setNgaySua(new Date());

		quanLySanPhamService.updateSanPham(sanPham);

		return "redirect:/quanlysanpham/";
	}

}
