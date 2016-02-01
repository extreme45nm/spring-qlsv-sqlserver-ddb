package edu.java.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

//import edu.java.spring.model.SinhVienMonHoc;

public class SinhVienMonHocMapper implements RowMapper<SinhVienMonHoc>{

	@Override
	public SinhVienMonHoc mapRow(ResultSet result, int rowNum) throws SQLException {
		SinhVienMonHoc svmh = new SinhVienMonHoc();
		svmh.setMasv(result.getInt("masv"));
		svmh.setMamon(result.getInt("mamon"));
//		svmh.setSotc(result.getInt("sotc"));
		svmh.setDiem(result.getDouble("diem"));
		
		return svmh;
	}
	
	
}
