package days03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.RowSetInternal;

import com.util.DBConn;

import domain.EmpVO;

public class Ex06_02 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 복습
		//[ CallableStatement ] emp테이블 CRUD

/*
		int empno ;
		String ename;
		String job;
		int mgr;
		String hiredate;
		double sal ;
		double comm ;
		int deptno;
*/		
//insert 
		String sql = "{call up_insemp (?,?,?,?,?,?,?,?)}";

		Connection conn = null; 
		CallableStatement cstmt = null;
		EmpVO vo = null;
		
		int empno =9999;
		String ename="hong";
		String job="CLERK";
		int mgr=7839;
		String hiredate ="2023/09/21";
		double sal =1000;
		double comm =500;
		int deptno =40;
		int rowCount = 0;

		try {
			conn = DBConn.getConnection();
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, empno);
			cstmt.setString(2, ename);
			cstmt.setString(3, job);
			cstmt.setInt(4, mgr);
			cstmt.setString(5, hiredate);
			cstmt.setDouble(6, sal);
			cstmt.setDouble(7, comm);
			cstmt.setInt(8, deptno);

			rowCount = cstmt.executeUpdate();
			if (rowCount ==1) {
				System.out.println("사원정보 추가 완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end");

	}//main
}//class 

/*
 		BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
		System.out.println("1. empno 입력  ");
		empno = Integer.parseInt( br.readLine() );

		System.out.println("2. ename 입력  "); 
		ename =  br.readLine() ;

		System.out.println("3.  job 입력  ");
		job = br.readLine() ;

		System.out.println("4.  mgr 입력  ");
		mgr = Integer.parseInt( br.readLine() );

		System.out.println("5. hiredate 입력  ");
		hiredate = br.readLine() ;

		System.out.println("6.  sal 입력  ");
		sal = Double.parseDouble(br.readLine());

		System.out.println("7.  comm 입력  ");
		comm = Double.parseDouble(br.readLine());

		System.out.println("8.  deptno 입력  ");
		deptno = Integer.parseInt( br.readLine() );

  */


//create or replace PROCEDURE up_insemp
//(
//    pempno emp.empno%type 
//    ,pename emp.ename%type :=null
//    ,pjob emp.job%type :=null
//    ,pmgr emp.mgr%type :=null
//    ,phiredate emp.hiredate%type :=null
//    ,psal emp.sal%type :=null
//    ,pcomm emp.comm%type :=null
//    ,pdeptno emp.deptno%type :=null
//    
//)
//is
//begin
//    insert into emp values (pempno, pename, pjob,pmgr, phiredate, psal, pcomm, pdeptno );
//    commit;
//end; 