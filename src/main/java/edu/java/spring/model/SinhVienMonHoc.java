package edu.java.spring.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class SinhVienMonHoc {
	
	
	private int mamon;
	
	private int masv;
//	@Range(min=0,max=10)
	private double diem;
	public int getMamon() {
		return mamon;
	}
	public void setMamon(int mamon) {
		this.mamon = mamon;
	}
	public int getMasv() {
		return masv;
	}
	public void setMasv(int masv) {
		this.masv = masv;
	}
	public double getDiem() {
		return diem;
	}
	public void setDiem(double diem) {
		this.diem = diem;
	}
	
}
