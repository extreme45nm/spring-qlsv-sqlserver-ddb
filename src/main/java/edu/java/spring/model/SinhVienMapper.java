package edu.java.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SinhVienMapper implements RowMapper<SinhVien> {

	@Override
	public SinhVien mapRow(ResultSet rel, int rowNum) throws SQLException {
		
		SinhVien sv = new SinhVien();
		 sv.setMasv(rel.getInt("masv"));
		 sv.setChuyenkhoa(rel.getInt("chuyenkhoa"));
		 sv.setHodem(rel.getString("hodem"));
		 sv.setTen(rel.getString("ten"));
		 sv.setTuoi(rel.getInt("tuoi"));
		 sv.setDiachi(rel.getString("diachi"));
		 sv.setQuequan(rel.getString("quequan"));
		 sv.setChucvu(rel.getString("chucvu"));
		return sv;
	}

}
