package edu.java.spring.model;

import java.sql.Date;

public class GiangVien {
	
	private int magv;
	private String makhoa;
	private String tengv;
	private String tdhv;
	private Date ns;
	private String capham;
	
	public GiangVien(){}
	
	public int getMagv() {
		return magv;
	}
	public void setMagv(int magv) {
		this.magv = magv;
	}
	public String getMakhoa() {
		return makhoa;
	}
	public void setMakhoa(String makhoa) {
		this.makhoa = makhoa;
	}
	public String getTengv() {
		return tengv;
	}
	public void setTengv(String tengv) {
		this.tengv = tengv;
	}
	public String getTdhv() {
		return tdhv;
	}
	public void setTdhv(String tdhv) {
		this.tdhv = tdhv;
	}
	public Date getNs() {
		return ns;
	}
	public void setNs(Date ns) {
		this.ns = ns;
		
	}
	public String getCapham() {
		return capham;
	}
	public void setCapham(String capham) {
		this.capham = capham;
	}
}
