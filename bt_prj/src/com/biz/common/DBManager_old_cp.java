package com.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager_old_cp {
	
	public Connection dbConn() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "kosmo", "0000");
			if(conn != null)
				System.out.println("conn success");
			else
				System.out.println("conn falid");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	public void dbClose(ResultSet rs, PreparedStatement pstmt ,Connection conn) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();  
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void dbClose(PreparedStatement pstmt ,Connection conn) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();  
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void dbClose(Connection conn) {
		try {
			if(conn != null) conn.close();  
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
