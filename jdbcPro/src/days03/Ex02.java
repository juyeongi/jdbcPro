package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

public class Ex02 {
	public static void main(String[] args) {
		//[트랜젝션 처리]
		// 논리적인 작업단위
		// commit / rollback	
		// ex) 계좌이체
		// a 통장 돈 인출 update : dept > deptno 50 추가 o
		// b 통장 돈 입금 update : dept > deptno 50 추가 x
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		String sql = "INSERT INTO dept VALUES (?, ?, ?) ";
		
		DBConn.getConnection();
		//부서 추가  insert 
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 50);
			pstmt.setString(2, "QC1");
			pstmt.setString(3, "SEOUL");
			rowCount = pstmt.executeUpdate();
			if (rowCount ==1) System.out.println("1번 성공");
			
			pstmt.setInt(1, 50);  //pk
			pstmt.setString(2, "QC2");
			pstmt.setString(3, "SEOUL");
			rowCount = pstmt.executeUpdate();
			if (rowCount ==1) System.out.println("2번 성공");
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
//			conn.rollback(null);
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		DBConn.close();
	}//main
}
