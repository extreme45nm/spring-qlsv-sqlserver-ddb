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

import edu.java.spring.model.SinhVien;
import edu.java.spring.model.SinhVienMapper;


public class SinhVienDAO {
	
	private Log log = LogFactory.getLog(SinhVienDAO.class);
	private DataSource dataSource;
	private JdbcTemplate templateObject;
	private String insertSQL;
	
	@Autowired
	@Qualifier("sinhVienMapper")
	SinhVienMapper mapper;
	
	public List<SinhVien> selectSinhVienByName(String ten){
		mapper = new SinhVienMapper();
		String sql = "SELECT * FROM SinhVien WHERE ten like ?";
		List<SinhVien> students = templateObject.query(sql, new Object[]{'%'+ten+'%'},mapper);
		return students;
	}
	
	public void deleteSinhVien(int masv){
		
		System.out.println("DAO.deleteSinhVien() invoked!!\n");
		String sql = "DELETE FROM SinhVien WHERE masv = ?";
		
		templateObject.update(sql, masv);
	}
	
	public void updateSinhVien(SinhVien sv){
		
		System.out.println("DAO.updateSinhVien() invoked!!");
		String sql = "UPDATE SinhVien "
				+ "SET chuyenkhoa=?,hodem=?,ten=?,tuoi=?,diachi=?,quequan=?,chucvu=? WHERE masv=?";
		templateObject.update(sql,sv.getChuyenkhoa(),sv.getHodem(),sv.getTen(),
					sv.getTuoi(),sv.getDiachi(),sv.getQuequan(),sv.getChucvu(),sv.getMasv());
		log.info("Updated RECORD with ID = "+sv.getMasv());
		return;
	}
	
	public SinhVien loadSinhVien(int masv){
		mapper = new SinhVienMapper();
		String sql = "SELECT * from SinhVien WHERE masv = ?";
		return templateObject.queryForObject(sql,new Object[]{masv},mapper);
	}
	
	public List<SinhVien> listSinhVien(){
		mapper = new SinhVienMapper();
		String sql = "SELECT * FROM SinhVien";
		List<SinhVien> danhSach = templateObject.query(sql,mapper);
		return danhSach;			
	}
	
	public void insert(final SinhVien sv){
		
		PreparedStatementCreator kreator =
				new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement
							(Connection konn) throws SQLException {
						PreparedStatement stmt = 
								konn.prepareStatement(insertSQL);
						
						stmt.setInt(1,sv.getChuyenkhoa());
						stmt.setString(2, sv.getHodem());
						stmt.setString(3, sv.getTen());
						stmt.setInt(4, sv.getTuoi());
						stmt.setString(5, sv.getDiachi());
						stmt.setString(6, sv.getQuequan());
						stmt.setString(7, sv.getChucvu());
						return stmt;
					}
				};
				
		templateObject.update(kreator);
		log.info("\n Created RECORD name = "+sv.getTen());
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
	
	public void createTableSinhVien(String tableName,String createTableSQL) throws SQLException{	
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
