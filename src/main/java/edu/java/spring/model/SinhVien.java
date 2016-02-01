package edu.java.spring.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class SinhVien {
	
	
	private int masv;
	private int chuyenkhoa;
	@NotBlank
	private String hodem;
	@NotBlank
	private String ten;
	@Range(min=1,max=150)
	private int tuoi;
	private String diachi;
	private String quequan;
	private String chucvu;
	private int lop;
	

	public SinhVien(){}
	
	public int getMasv() {
		return masv;
	}
	public void setMasv(int masv) {
		this.masv = masv;
	}
	public int getChuyenkhoa() {
		return chuyenkhoa;
	}
	public void setChuyenkhoa(int chuyenkhoa) {
		this.chuyenkhoa = chuyenkhoa;
	}
	public String getHodem() {
		return hodem;
	}
	public void setHodem(String hodem) {
		this.hodem = hodem;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getQuequan() {
		return quequan;
	}
	public void setQuequan(String quequan) {
		this.quequan = quequan;
	}
	public String getChucvu() {
		return chucvu;
	}
	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}
	public int getLop() {
		return lop;
	}
	public void setLop(int lop) {
		this.lop = lop;
	}
	
}
