package edu.java.spring.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class MonHoc {
	
	
	private int mamon;
	@NotBlank
	private String tenmon;
	@Range(min=1,max=10)
	private int sotc;
	private String giangvienchinh;
	
	public MonHoc(){}
	
	public int getMamon() {
		return mamon;
	}
	public void setMamon(int mamon) {
		this.mamon = mamon;
	}
	public String getTenmon() {
		return tenmon;
	}
	public void setTenmon(String tenmon) {
		this.tenmon = tenmon;
	}
	public int getSotc() {
		return sotc;
	}
	public void setSotc(int sotc) {
		this.sotc = sotc;
	}
	public String getGiangvienchinh() {
		return giangvienchinh;
	}
	public void setGiangvienchinh(String giangvien) {
		this.giangvienchinh = giangvien;
	}
}
