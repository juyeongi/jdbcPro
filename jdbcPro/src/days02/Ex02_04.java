package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;

public class Ex02_04 {
	public static void main(String[] args) { 
		//부서정보삭제
 
		//삭제할 부서번호 입력받기
		Scanner scanner = new Scanner(System.in);

		Connection conn= null;
		Statement stmt = null;

		String deptno;
		int rowCount = 0; 

		System.out.print("삭제할 부서번호 입력");
		deptno=scanner.next();

		String sql= "DELETE FROM dept WHERE deptno IN (" + deptno + ")" ;
		
		System.out.println(sql); //쿼리 확인

		try {
			conn= DBConn.getConnection();
			stmt = conn.createStatement();
			rowCount = stmt.executeUpdate(sql);
			
			if(rowCount>0) {
				System.out.println("부서삭제 완료");
			}//if

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("end");

	}//main
}//class
