package edu.java.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MonHocMapper implements RowMapper<MonHoc>{

	@Override
	public MonHoc mapRow(ResultSet rel, int rowNum) throws SQLException {
		 
		MonHoc mh = new MonHoc();
		mh.setMamon(rel.getInt("mamon"));
		mh.setTenmon(rel.getString("tenmon"));
		mh.setSotc(rel.getInt("sotc"));
		mh.setGiangvienchinh(rel.getString("giangvienchinh"));
		
		return mh;
	}
	
}
