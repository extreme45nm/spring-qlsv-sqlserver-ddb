package edu.java.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class KhoaMapper implements RowMapper<Khoa>{

	@Override
	public Khoa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Khoa khoa = new Khoa();
		
		khoa.setMakhoa(rs.getString("makhoa"));
		khoa.setTenkhoa(rs.getString("tenkhoa"));
		khoa.setMalanhdaokhoa(rs.getString("malanhdaokhoa"));
		
		return khoa;
	}
	
	
}
