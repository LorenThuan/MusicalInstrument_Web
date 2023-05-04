package com.se.entity;

public class CustomTTSP {
	private String giamGiaInt;
	private String giaGoc;
	private String giaDaGiam;

	public String getGiamGiaInt() {
		return giamGiaInt;
	}

	public void setGiamGiaInt(String giamGiaInt) {
		this.giamGiaInt = giamGiaInt;
	}

	public String getGiaGoc() {
		return giaGoc;
	}

	public void setGiaGoc(String giaGoc) {
		this.giaGoc = giaGoc;
	}

	public String getGiaDaGiam() {
		return giaDaGiam;
	}

	public void setGiaDaGiam(String giaDaGiam) {
		this.giaDaGiam = giaDaGiam;
	}

	public CustomTTSP(String giamGiaInt, String giaGoc, String giaDaGiam) {
		super();
		this.giamGiaInt = giamGiaInt;
		this.giaGoc = giaGoc;
		this.giaDaGiam = giaDaGiam;
	}
}
