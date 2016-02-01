package edu.java.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ChuyenKhoaMapper implements RowMapper<ChuyenKhoa>{

	@Override
	public ChuyenKhoa mapRow(ResultSet rel, int rowNum) throws SQLException {
		 ChuyenKhoa ck = new ChuyenKhoa();
		 
		 ck.setMack(rel.getInt("mack"));
		 ck.setTenck(rel.getString("tenck"));
		 ck.setLanhdaock(rel.getString("lanhdaock"));
		 
		 return ck;
	}
	
}
