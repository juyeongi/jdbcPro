package days02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Ex02 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//dept 부서 추가
		int deptno;  //pk = not null, unique key 
		String dname ;
		String loc;
		
		BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
		System.out.println("1. 부서번호 입력  ");
		deptno = Integer.parseInt( br.readLine() );
		
		System.out.println("2. 부서이름 입력  "); 
		dname =  br.readLine() ;
		
		System.out.println("3. 지역 입력  ");
		loc = br.readLine() ;
		
		String sql = String.format("INSERT INTO dept ( deptno, dname, loc ) VALUES ( '%d', '%s', '%s' ) ",deptno, dname, loc);
		
		Connection conn = null;
		//conn.setAutoCommit(); // 자동커밋
		Statement stmt = null;
		int rowCount = 0 ;  // 영향받은 레코드 수 

		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
			//ResultSet 결과값 60
			//stmt.executeQuery(sql);  select문 처리
			rowCount = stmt.executeUpdate(sql	);    // INSERT, DELETE, UPDATE
			if (rowCount==1) {
				System.out.println("부서추가 성공");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				DBConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(" end ");
		
	}//main
}//class 
