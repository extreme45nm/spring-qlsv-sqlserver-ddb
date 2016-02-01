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

import edu.java.spring.model.SinhVienMonHoc;
import edu.java.spring.model.SinhVienMonHocMapper;

public class SinhVienMonHocDAO {
	
	private Log log = LogFactory.getLog(SinhVienMonHocDAO.class);
	private DataSource dataSource;
	private JdbcTemplate templateObject;
	private String insertSQL;
	
	@Autowired
	@Qualifier("svmhMapper")
	SinhVienMonHocMapper mapper;
	
	public void deleteSVMH(int mamon){
		String sql = "DELETE FROM SinhVienMonHoc WHERE mamon = ?";
		templateObject.update(sql,mamon);
		log.info("\nDELETED RECORD FROM SinhVienMonHoc WHERE masv = "+mamon);
		return;
	}
	
	public void updateSVMH(SinhVienMonHoc svmh){
		String sql = "UPDATE SinhVienMonHoc SET masv=?,diem=? WHERE mamon = ?";
		templateObject.update(sql,svmh.getMasv(),svmh.getDiem(),svmh.getMamon());
		log.info("UPDATED RECORD IN SinhVienMonHoc With MaMon = "+svmh.getMamon());
		return;
	}
	
	public SinhVienMonHoc loadSVMH(int mamon){
		mapper = new SinhVienMonHocMapper();
		String sql = "SELECT * FROM SinhVienMonHoc WHERE mamon = ?";
		SinhVienMonHoc svmh = templateObject.queryForObject(sql, new Object[]{mamon},mapper);
		return svmh;
	}
	
	public List<SinhVienMonHoc> listSVMH(){
		
		mapper = new SinhVienMonHocMapper();
		String sql = "SELECT * FROM SinhVienMonHoc";
		List<SinhVienMonHoc> list = templateObject.query(sql,mapper);
		return list;
	}
	
	public void insert(final SinhVienMonHoc svmh){

		PreparedStatementCreator kreator =
				new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement
							(Connection konn) throws SQLException {
						PreparedStatement stmt = 
								konn.prepareStatement(insertSQL);
						
						stmt.setInt(1, svmh.getMamon());
						stmt.setInt(2, svmh.getMasv());
//						stmt.setInt(3, svmh.getSotc());
						stmt.setDouble(3, svmh.getDiem());
						return stmt;
					}
				};
				
		templateObject.update(kreator);
		log.info("\n Created RECORD SVMH ID = "+svmh.getMasv());
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
	
	public void createTableSinhVienMonHoc(String tableName,String createTableSQL) throws SQLException{	
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
