package days02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test02 {
		//emp 테이블에서 한 사원의 정보를 입력받아 추가
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int empno ;
		 String ename;
		 String job ;
		 int mgr;
		 String hiredate ;
		double sal ;
		double comm ;
		int deptno;  
		
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

		String sql = String.format("INSERT INTO emp ( empno, ename, job, mgr, hiredate, sal, comm, deptno ) "
				+ "VALUES ('%d', '%s', '%s', '%d', '%s','%f','%f', '%d')", empno, ename, job, mgr, hiredate, sal, comm, deptno);
		
		Connection conn = null;
		Statement stmt = null ;
		int rowCount = 0;

		try {
			conn = DBConn.getConnection(); 
			stmt = conn.createStatement();
			rowCount= stmt.executeUpdate(sql);
			if (rowCount ==1) {
				System.out.println("사원정보 추가 완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}//c

		}//c
		System.out.println("end");
	}//main
}//class
