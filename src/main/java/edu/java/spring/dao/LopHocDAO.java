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

import edu.java.spring.model.LopHoc;
import edu.java.spring.model.LopHocMapper;

public class LopHocDAO {
	private Log log = LogFactory.getLog(LopHocDAO.class);
	private DataSource dataSource;
	private JdbcTemplate templateObject;
	private String insertSQL;
	
	@Autowired
	@Qualifier("lopHocMapper")
	LopHocMapper mapper;
	
	public List<LopHoc> selectLopHocByName(String malop){
		mapper = new LopHocMapper();
		String sql = "SELECT * FROM LopHoc WHERE malop like ?";
		List<LopHoc> lophocs = templateObject.query(sql, new Object[]{'%'+malop+'%'},mapper);
		return lophocs;
	}
	
	public void deleteLopHoc(int malop){
		
		System.out.println("DAO.deleteLopHoc() invoked!!\n");
		String sql = "DELETE FROM LopHoc WHERE malop = ?";
		
		templateObject.update(sql, malop);
	}
	
	public void updateLopHoc(LopHoc lophoc){
		
		System.out.println("DAO.updateLopHoc() invoked!!");
		String sql = "UPDATE LopHoc "
				+ "SET tengvcn=?,mack=?,maloptruong=? WHERE malop=?";
		templateObject.update(sql,lophoc.getGvcn(),lophoc.getMack(),lophoc.getMaloptruong()
				,lophoc.getMalop());
		log.info("Updated RECORD with maLop = "+lophoc.getMalop());
		return;
	}
	
	public LopHoc loadLopHoc(int malop){
		mapper = new LopHocMapper();
		String sql = "SELECT * from LopHoc WHERE malop = ?";
		return templateObject.queryForObject(sql,new Object[]{malop},mapper);
	}
	
	public List<LopHoc> listLopHoc(){
		mapper = new LopHocMapper();
		String sql = "SELECT * FROM LopHoc";
		List<LopHoc> danhSach = templateObject.query(sql,mapper);
		return danhSach;			
	}
	
	public void insert(final LopHoc lophoc){
		
		PreparedStatementCreator kreator =
				new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement
							(Connection konn) throws SQLException {
						PreparedStatement stmt = 
								konn.prepareStatement(insertSQL);
						stmt.setInt(1, lophoc.getMalop());
						stmt.setString(2, lophoc.getGvcn());
						stmt.setInt(3, lophoc.getMack());
						stmt.setInt(4, lophoc.getMaloptruong());
						return stmt;
					}
				};
				
		templateObject.update(kreator);
		log.info("\n Created RECORD name = "+lophoc.getMalop());
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
	
	public void createTableLopHoc(String tableName,String createTableSQL) throws SQLException{	
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
