package days01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.EmpVO;

public class Test01 {
	public static void main(String[] args) {
		//domain.EmpVO 클래스선언
		//emp 테이블의 모든 사원정보 출력
		//조건) 부서번호 입력받아서 해당 부서원들만 출력
		//printEmpList(arraylist<empvo> list) 출력
		
		Scanner scanner = new Scanner(System.in);
		int s = scanner.nextInt(); 
		Connection conn = DBConn.getConnection() ;
		Statement stmt = null;
		String sql = "SELECT * FROM emp WHERE deptno = "+ s ; 
		ResultSet rs = null;
		
		 int empno =0 ;
		 String ename=null;
		 String job=null;
		 int mgr=0;
		 String hiredate=null ;
		 int sal =0;
		 int comm =0;
		 int deptno =0;
		 EmpVO vo = null;
		 ArrayList<EmpVO> list = null ; 
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				list = new ArrayList<EmpVO>();
				do {
					empno = rs.getInt("empno");
					ename = rs.getString("ename");
					job = rs.getString("job");
					mgr = rs.getInt("mgr");
					hiredate = rs.getString("hiredate");
					sal = rs.getInt("sal");
					comm = rs.getInt("comm");
					deptno = rs.getInt("deptno");
					vo= new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
					list.add(vo);
				}while (rs.next());
			}//if
			PrintEmpList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}//main

	private static void PrintEmpList(ArrayList<EmpVO> list) {
		if (list == null) {
			System.out.println("사원정보없음");
			return; 
		}
		Iterator<EmpVO> ir = list.iterator();
		while (ir.hasNext()) {
			EmpVO vo = ir.next();
			System.out.println(vo);
		}
	}



}//class
