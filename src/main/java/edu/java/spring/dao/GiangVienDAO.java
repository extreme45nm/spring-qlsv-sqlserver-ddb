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

import edu.java.spring.model.GiangVien;
import edu.java.spring.model.GiangVienMapper;

public class GiangVienDAO {
	private Log log = LogFactory.getLog(GiangVienDAO.class);
	private DataSource dataSource;
	private JdbcTemplate templateObject;
	private String insertSQL;
	
	@Autowired
	@Qualifier("giangVienMapper")
	GiangVienMapper mapper;
	
	public List<GiangVien> selectGiangVienByName(String ten){
		mapper = new GiangVienMapper();
		String sql = "SELECT * FROM GiangVien WHERE tengv like ?";
		List<GiangVien> students = templateObject.query(sql, new Object[]{'%'+ten+'%'},mapper);
		return students;
	}
	
	public void deleteGiangVien(int magv){
		
		System.out.println("DAO.deleteGiangVien() invoked!!\n");
		String sql = "DELETE FROM GiangVien WHERE magv = ?";
		
		templateObject.update(sql, magv);
	}
	
	public void updateGiangVien(GiangVien gv){
		
		System.out.println("DAO.updateGiangVien() invoked!!");
		String sql = "UPDATE GiangVien "
				+ "SET makhoa=?,tengv=?,tdhv=?,ns=?,capham=? WHERE magv=?";
		templateObject.update(sql,gv.getMakhoa(),gv.getTengv(),gv.getTdhv(),gv.getNs(),gv.getCapham()
				,gv.getMagv());
		log.info("Updated RECORD with ID = "+gv.getMagv());
		return;
	}
	
	public GiangVien loadGiangVien(int magv){
		mapper = new GiangVienMapper();
		String sql = "SELECT * from GiangVien WHERE magv = ?";
		return templateObject.queryForObject(sql,new Object[]{magv},mapper);
	}
	
	public List<GiangVien> listGiangVien(){
		mapper = new GiangVienMapper();
		String sql = "SELECT * FROM GiangVien";
		List<GiangVien> danhSach = templateObject.query(sql,mapper);
		return danhSach;			
	}
	
	public void insert(final GiangVien gv){
		
		PreparedStatementCreator kreator =
				new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement
							(Connection konn) throws SQLException {
						PreparedStatement stmt = 
								konn.prepareStatement(insertSQL);
						
						stmt.setString(1, gv.getMakhoa());
						stmt.setString(2, gv.getTengv());
						stmt.setString(3,gv.getTdhv());
						stmt.setDate(4, gv.getNs());
						stmt.setString(5, gv.getCapham());
						return stmt;
					}
				};
				
		templateObject.update(kreator);
		log.info("\n Created RECORD name = "+gv.getMagv());
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
	
	public void createTableGiangVien(String tableName,String createTableSQL) throws SQLException{	
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
