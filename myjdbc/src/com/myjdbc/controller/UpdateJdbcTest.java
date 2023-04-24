package com.myjdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateJdbcTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BS","BS");
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql = "UPDATE DEPARTMENT SET DEPT_TITLE='학생부',LOCATION_ID='L3'"
					+ "WHERE DEPT_ID='D0'";
			int result = stmt.executeUpdate(sql);
			
			if(result>0) {
				conn.commit();
				System.out.println("업데이트 성공");
			}
			else conn.rollback();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
