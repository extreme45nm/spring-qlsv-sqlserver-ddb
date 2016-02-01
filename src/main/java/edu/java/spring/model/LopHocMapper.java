package edu.java.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LopHocMapper implements RowMapper<LopHoc>{

	@Override
	public LopHoc mapRow(ResultSet rel, int rowNum) throws SQLException {
		 
		LopHoc lopHoc = new LopHoc();
		
		lopHoc.setMalop(rel.getInt("malop"));
		lopHoc.setMack(rel.getInt("mack"));
		lopHoc.setGvcn(rel.getString("gvcn"));
		lopHoc.setMaloptruong(rel.getInt("maloptruong"));
		return lopHoc;
	}
	
}
