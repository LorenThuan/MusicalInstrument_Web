package com.se.dao;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.se.entity.ChiTietHoaDon;
import com.se.entity.DanhGia;
import com.se.entity.HinhPhu;
import com.se.entity.HoaDon;
import com.se.entity.Loai;
import com.se.entity.NhaSanXuat;
import com.se.entity.SanPham;
import com.se.entity.TaiKhoan;

@Repository
public class QuanLySanPhamDAOImpl implements QuanLySanPhamDAO {

	private EntityManager entityManager;

	@Autowired
	public QuanLySanPhamDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<SanPham> getListSanPham() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<SanPham> theQuery = currentSession.createQuery("From SanPham where daXoa=0", SanPham.class);
		List<SanPham> listSanPham = theQuery.getResultList();

		return listSanPham;

	}

	@Override
	public void saveSanPham(SanPham sanPham, MultipartFile hinhChinh, ArrayList<MultipartFile> listHinhPhu)
			throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);

		sanPham.setId(0);
		sanPham.setLoai(currentSession.get(Loai.class, sanPham.getLoai().getId()));
		sanPham.setNhaSanXuat(currentSession.get(NhaSanXuat.class, sanPham.getNhaSanXuat().getId()));

		Path path = Paths.get("src/main/resources/static/upload/");
		try {
			InputStream inputStream = hinhChinh.getInputStream();
			Files.copy(inputStream, path.resolve(hinhChinh.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

			for (MultipartFile hinhPhu : listHinhPhu) {
				inputStream = hinhPhu.getInputStream();
				Files.copy(inputStream, path.resolve(hinhPhu.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
		}

		int idSP = (int) currentSession.save(sanPham);

		for (HinhPhu hinhPhu : sanPham.getListHinhPhu()) {
			hinhPhu.setSanPham(currentSession.get(SanPham.class, idSP));
			currentSession.save(hinhPhu);
		}

	}

	@Override
	public SanPham getSanPham(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		SanPham sanPham = currentSession.get(SanPham.class, theId);
		return sanPham;
	}

	@Override
	public void deleteSanPham(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		SanPham sanPham = currentSession.get(SanPham.class, theId);
		sanPham.setDaXoa(1);
		currentSession.saveOrUpdate(sanPham);
	}

	@Override
	public List<NhaSanXuat> getListNhaSanXuat() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<NhaSanXuat> theQuery = currentSession.createQuery("From NhaSanXuat", NhaSanXuat.class);
		List<NhaSanXuat> listNSX = theQuery.getResultList();

		return listNSX;
	}

	@Override
	public List<Loai> getListLoai() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Loai> theQuery = currentSession.createQuery("From Loai", Loai.class);
		List<Loai> listLoai = theQuery.getResultList();

		return listLoai;
	}

	@Override
	public int getidSanPhamCuoi() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("from SanPham order by id DESC");
		query.setMaxResults(1);
		SanPham sanPhamCuoi = (SanPham) query.uniqueResult();
		if (sanPhamCuoi != null)
			return sanPhamCuoi.getId();
		return 0;
	}

	@Override
	public List<SanPham> getListSanPham(String ma) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<SanPham> theQuery = currentSession.createQuery("From SanPham where daXoa=0 and id=" + ma, SanPham.class);
		List<SanPham> listSanPham = theQuery.getResultList();

		return listSanPham;
	}

	@Override
	public void updateSanPham(SanPham sanPham) {
		Session currentSession = entityManager.unwrap(Session.class);
		SanPham temp = currentSession.get(SanPham.class, sanPham.getId());

		temp.setTen(sanPham.getTen());
		temp.setGia(sanPham.getGia());
		temp.setGiamGia(sanPham.getGiamGia());
		temp.setMoTa(sanPham.getMoTa());
		temp.setNgaySua(sanPham.getNgaySua());
		temp.setLoai(currentSession.get(Loai.class, sanPham.getLoai().getId()));
		temp.setNhaSanXuat(currentSession.get(NhaSanXuat.class, sanPham.getNhaSanXuat().getId()));

		currentSession.saveOrUpdate(temp);
	}

	@Override
	public List<SanPham> getListSanPhamBanNhieu(int soLuong) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<SanPham> theQuery = currentSession.createQuery("From SanPham where daXoa=0", SanPham.class)
				.setMaxResults(soLuong);
		List<SanPham> listSanPham = theQuery.getResultList();

		return listSanPham;
	}

	@Override
	public List<DanhGia> getDanhGiaSP(int idSP) {

		Session currentSession = entityManager.unwrap(Session.class);
		Query<DanhGia> theQuery = currentSession.createQuery("From DanhGia where sanPham.id=" + idSP, DanhGia.class);
		List<DanhGia> listDanhGia = theQuery.getResultList();
		return listDanhGia;
	}

	@Override
	public void saveDanhGia(DanhGia danhGia) {
		Session currentSession = entityManager.unwrap(Session.class);

		
		//lấy tài khoản từ username và sản phẩm từ id
		SanPham sp = currentSession.get(SanPham.class, danhGia.getSanPham().getId());
		Query<TaiKhoan> theQuery = currentSession.createQuery("From TaiKhoan", TaiKhoan.class);
		List<TaiKhoan> listTK = theQuery.getResultList();
		TaiKhoan tk = new TaiKhoan();
		for (TaiKhoan taiKhoan : listTK) {
			if (taiKhoan.getTenTaiKhoan().toLowerCase().equals(danhGia.getTaiKhoan().getTenTaiKhoan().toLowerCase()))
				tk = taiKhoan;
		}
		
		danhGia.setSanPham(sp);
		danhGia.setTaiKhoan(tk);
		
		//Kiểm tra khách hàng đã đánh giá sản phẩm chưa
		Query<DanhGia> theQuery2 = currentSession.createQuery("From DanhGia", DanhGia.class);
		List<DanhGia> listDG = theQuery2.getResultList();
		int flag=0;
		DanhGia danhGiaTemp=new DanhGia();
		for (DanhGia temp : listDG) {
			if (temp.getTaiKhoan().getTenTaiKhoan().toLowerCase()
					.equals(danhGia.getTaiKhoan().getTenTaiKhoan().toLowerCase())
					&& temp.getSanPham().getId() == danhGia.getSanPham().getId()) {
				danhGiaTemp=temp;
				flag=1;
				break;
			}
		}
		//Nếu đã đánh giá thì cập nhật lại đánh giá
		if(flag==1) {
			danhGiaTemp.setBinhLuan(danhGia.getBinhLuan());
		}
		//Nếu chưa đánh giá thì thêm mới
		else {
			danhGia.setId(0);
			currentSession.save(danhGia);
		}
	}

	@Override
	public List<SanPham> getListSanPhamTheoTen(String tenSP) {

		Session currentSession = entityManager.unwrap(Session.class);
		Query<SanPham> theQuery = currentSession.createQuery("From SanPham where ten like '%" + tenSP + "%'",
				SanPham.class);

		List<SanPham> listSanPham = theQuery.getResultList();

		return listSanPham;
	}

	@Override
	public void saveMuaHang(String idSPMua, String soLuongMua, String loaiMuaHang) {

	}

}
