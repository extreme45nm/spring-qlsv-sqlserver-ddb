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

import edu.java.spring.model.ChuyenKhoa;
import edu.java.spring.model.ChuyenKhoaMapper;

public class ChuyenKhoaDAO{

	private Log log = LogFactory.getLog(ChuyenKhoaDAO.class);
	private DataSource dataSource;
	private JdbcTemplate templateObject;
	private String insertSQL;

	@Autowired
	@Qualifier("chuyenKhoaMapper")
	ChuyenKhoaMapper mapper;

	public List<ChuyenKhoa> selectChuyenKhoaByName(String ten){
		mapper = new ChuyenKhoaMapper();
		String sql = "SELECT * FROM ChuyenKhoa WHERE tenck like ?";
		List<ChuyenKhoa> chuyenkhoas = templateObject.query(sql, new Object[]{'%'+ten+'%'},mapper);
		return chuyenkhoas;
	}

	public void deleteChuyenKhoa(int mack){

		System.out.println("DAO.deleteChuyenKhoa() invoked!!\n");
		String sql = "DELETE FROM ChuyenKhoa WHERE mack = ?";

		templateObject.update(sql, mack);
	}

	public void updateChuyenKhoa(ChuyenKhoa ck){

		System.out.println("DAO.updateChuyenKhoa() invoked!!");
		String sql = "UPDATE ChuyenKhoa "
				+ "SET tenck=?,lanhdaock=? WHERE mack=?";
		templateObject.update(sql,ck.getTenck(),ck.getLanhdaock(),ck.getMack());
		log.info("Updated RECORD with ID = "+ck.getMack());
		return;
	}

	public ChuyenKhoa loadChuyenKhoa(int mack){
		mapper = new ChuyenKhoaMapper();
		String sql = "SELECT * from ChuyenKhoa WHERE mack = ?";
		return templateObject.queryForObject(sql,new Object[]{mack},mapper);
	}

	public List<ChuyenKhoa> listChuyenKhoa(){
		mapper = new ChuyenKhoaMapper();
		String sql = "SELECT * FROM ChuyenKhoa";
		List<ChuyenKhoa> danhSach = templateObject.query(sql,mapper);
		return danhSach;			
	}

	public void insert(final ChuyenKhoa ck){

		PreparedStatementCreator kreator =
				new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement
			(Connection konn) throws SQLException {
				PreparedStatement stmt = 
						konn.prepareStatement(insertSQL);
				
				stmt.setInt(1, ck.getMack());
				stmt.setString(2,ck.getTenck());
				stmt.setString(3, ck.getLanhdaock());
				return stmt;
			}
		};

		templateObject.update(kreator);
		log.info("\n Created RECORD name = "+ck.getTenck());
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

	public void createTableChuyenKhoa(String tableName,String createTableSQL) throws SQLException{	
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
