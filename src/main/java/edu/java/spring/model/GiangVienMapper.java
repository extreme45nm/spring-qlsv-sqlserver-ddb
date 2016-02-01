package edu.java.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GiangVienMapper implements RowMapper<GiangVien>{

	@Override
	public GiangVien mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		GiangVien gv = new GiangVien();
		gv.setMagv(rs.getInt("magv"));
		gv.setMakhoa(rs.getString("makhoa"));
		gv.setTengv(rs.getString("tengv"));
		gv.setTdhv(rs.getString("tdhv"));
		gv.setNs(rs.getDate("ns"));
		gv.setCapham(rs.getString("capham"));
	
		return gv;
	}

}
