package edu.java.spring.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import edu.java.spring.model.Khoa;
import edu.java.spring.model.KhoaMapper;

public class KhoaDAO {
	private Log log = LogFactory.getLog(KhoaDAO.class);
	private DataSource dataSource;
	private JdbcTemplate templateObject;
	private String insertSQL;
	
	@Autowired
	@Qualifier("khoaMapper")
	KhoaMapper mapper;
	
	public List<Khoa> selectKhoaByName(String tenkhoa){
		mapper = new KhoaMapper();
		String sql = "SELECT * FROM Khoa WHERE tenkhoa like ?";
		List<Khoa> khoas = templateObject.query(sql, new Object[]{'%'+tenkhoa+'%'},mapper);
		return khoas;
	}
	
	public void deleteKhoa(String makhoa){
		
		System.out.println("DAO.deleteKhoa() invoked!!\n");
		String sql = "DELETE FROM Khoa WHERE makhoa = ?";
		
		templateObject.update(sql, makhoa);
	}
	
	public void updateKhoa(Khoa khoa){
		
		System.out.println("DAO.updateKhoa() invoked!!");
		String sql = "UPDATE Khoa "
				+ "SET tenkhoa=?, malanhdaokhoa = ? WHERE makhoa=?";
		templateObject.update(sql,khoa.getTenkhoa(),khoa.getMalanhdaokhoa(),khoa.getMakhoa());
		log.info("Updated RECORD with makhoa = "+khoa.getMakhoa());
		return;
	}
	
	public Khoa loadKhoa(String makhoa){
		mapper = new KhoaMapper();
		String sql = "SELECT * from Khoa WHERE makhoa = ?";
		return templateObject.queryForObject(sql,new Object[]{makhoa},mapper);
	}
	
	public List<Khoa> listKhoa(){
		mapper = new KhoaMapper();
		String sql = "SELECT * FROM Khoa";
		List<Khoa> danhSach = templateObject.query(sql,mapper);
		return danhSach;			
	}
	
	public void insert(final Khoa khoa){
		
		PreparedStatementCreator kreator =
				new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement
							(Connection konn) throws SQLException {
						PreparedStatement stmt = 
								konn.prepareStatement(insertSQL);
						stmt.setString(1, khoa.getMakhoa());
						stmt.setString(2,khoa.getTenkhoa());
						stmt.setString(3, khoa.getMalanhdaokhoa());
//						stmt.setString(3, khoa.getTen());
//						stmt.setInt(4, khoa.getTuoi());
//						stmt.setString(5, khoa.getDiachi());
//						stmt.setString(6, khoa.getQuequan());
//						stmt.setString(7, khoa.getChucvu());
						return stmt;
					}
				};
				
		templateObject.update(kreator);
		log.info("\n Created RECORD name = "+khoa.getTenkhoa());
	}
	
	public void shutdown(){
		try{
			dataSource.getConnection().close();
		}catch(SQLException exc){
			log.error(exc);
		}
		
		try{
			log.info("\n\n shutdown Database!\n\n");
			DriverManager.getConnection("jdbc:sqlserver:;shutdown=true");
		}catch(SQLException exc){
			log.error(exc);
		}
	}	
	
	public void createTableKhoa(String tableName,String createTableSQL) throws SQLException{	
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getTables(null, null, tableName.toUpperCase(), null);
		
		if(rs.next()){
			//log.info("\n\nTable "+tableName+" already exist!\n\n");
			return;
		}
		
		templateObject.execute(createTableSQL);
	}
	
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		templateObject = new JdbcTemplate(dataSource);
	}
	public String getInsertSQL() {
		return insertSQL;
	}
	public void setInsertSQL(String insertSQL) {
		this.insertSQL = insertSQL;
	}
}
