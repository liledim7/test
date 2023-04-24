package com.myjdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.myjdbc.model.vo.Department;

public class MyjdbcTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BS","BS");
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM DEPARTMENT";
			rs = stmt.executeQuery(sql);
			List<Department> dp = new ArrayList<>();
			while(rs.next()) {
				Department dd = new Department();
				dd.setDeptId(rs.getString(1));
				dd.setDeptTitle(rs.getString(2));
				dd.setLocationId(rs.getString(3));
				dp.add(dd);
			}
			dp.forEach(d-> System.out.println(d));
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}













