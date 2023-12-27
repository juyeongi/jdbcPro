package days02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test04 {
		//emp테이블에서 사원의 정보 수정
		//수정할 사원번호, 기타 사원번호 입력 수정
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int empno ;
		 String ename;
		 String job ;
		 int mgr;
		 String hiredate ;
		double sal ;
		double comm ;
		int deptno; 
		 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1.empno 입력 ");
		empno =Integer.parseInt(br.readLine());
		System.out.println("2.ename 입력 ");
		ename =  br.readLine();
		System.out.println("3.job 입력 ");
		job =  br.readLine();
		System.out.println("4.mgr 입력 ");
		mgr =Integer.parseInt(br.readLine());
		System.out.println("5.hiredate 입력 ");
		hiredate =  br.readLine();
		System.out.println("6.sal 입력 ");
		sal = Double.parseDouble(br.readLine());
		System.out.println("7.comm 입력 ");
		comm = Double.parseDouble(br.readLine());
		System.out.println("8.deptno 입력 ");
		deptno =Integer.parseInt(br.readLine());
		
		conn = DBConn.getConnection();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//main
}
