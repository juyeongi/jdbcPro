package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//싱글톤 (Singleton)
// db연결 클래스 생성
public class DBConn {

	private static Connection conn = null;
	private DBConn() {}
	
	//다른 서버 연결 
	public static Connection getConnection(String url, String user, String password) {
		if (conn ==null) {
			String className= "oracle.jdbc.driver.OracleDriver";
			
			try {
				Class.forName(className);	
				conn = DriverManager.getConnection(url, user, password);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //catch
		}//if
		return conn;

	}
	
	
	
	//localhost 연결
	public static Connection getConnection() {
		if (conn ==null) {
			String className= "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			try {
				Class.forName(className);	
				conn = DriverManager.getConnection(url, user, password);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //catch
		}//if
		return conn;

	}
	
	public static void close() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}//if
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		conn=null;
	}//close
}//DBConn
