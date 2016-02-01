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

import edu.java.spring.model.MonHocMapper;
import edu.java.spring.model.MonHoc;
//import edu.java.spring.model.MonHocMapper;

public class MonHocDAO {
	
	private static Log log = LogFactory.getLog(MonHocDAO.class);
	
	private DataSource dataSource;
	private JdbcTemplate templateObject;
	private String insertSQL;
	
	@Autowired
	@Qualifier("monHocMapper")
	MonHocMapper mapper;
	
	public List<MonHoc> selectMonHocByName(String ten){
		mapper = new MonHocMapper();
		String sql = "SELECT * FROM MonHoc WHERE tenmon like ?";
		List<MonHoc> students = templateObject.query(sql, new Object[]{'%'+ten+'%'},mapper);
		return students;
	}
	
	public void deleteMonHoc(int mamon){
		
		System.out.println("DAO.deleteMonHoc() invoked!!\n");
		String sql = "DELETE FROM MonHoc WHERE mamon = ?";
		
		templateObject.update(sql, mamon);
	}
	
	public void updateMonHoc(MonHoc mh){
		
		System.out.println("DAO.updateMonHoc() invoked!!");
		String sql = "UPDATE MonHoc "
				+ "SET tenmon=?,sotc=?,giangvienchinh=? WHERE mamon=?";
		templateObject.update(sql,mh.getTenmon(),mh.getSotc(),mh.getGiangvienchinh(),mh.getMamon());
		log.info("Updated RECORD with ID = "+mh.getMamon());
		return;
	}
	
	public MonHoc loadMonHoc(int mamon){
		mapper = new MonHocMapper();
		String sql = "SELECT * from MonHoc WHERE mamon = ?";
		return templateObject.queryForObject(sql,new Object[]{mamon},mapper);
	}
	
	public List<MonHoc> listMonHoc(){
		mapper = new MonHocMapper();
		String sql = "SELECT * FROM MonHoc";
		List<MonHoc> danhSach = templateObject.query(sql,mapper);
		return danhSach;			
	}
	
	public void insert(final MonHoc mh){
		
		PreparedStatementCreator kreator =
				new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement
							(Connection konn) throws SQLException {
						PreparedStatement stmt = 
								konn.prepareStatement(insertSQL);
						
//						stmt.setInt(1,mh.getMamon());
						stmt.setString(1, mh.getTenmon());
						stmt.setInt(2, mh.getSotc());
						stmt.setString(3, mh.getGiangvienchinh());
						return stmt;
					}
				};
				
		templateObject.update(kreator);
		log.info("\n Created RECORD name = "+mh.getTenmon());
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
	
	public void createTableMonHoc(String tableName,String createTableSQL) throws SQLException{	
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getTables(null, null, tableName.toUpperCase(), null);
		
		if(rs.next()){
			//log.info("\n\nTable "+tableName+" already exist!\n\n");
			return;
		}
		
		templateObject.execute(createTableSQL);
	}
	

	public static Log getLog() {
		return log;
	}
	public static void setLog(Log log) {
		MonHocDAO.log = log;
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
