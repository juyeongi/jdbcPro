package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import domain.EmpVO;

public class Ex06_03 {
	public static void main(String[] args) {
		// 복습
		//[ CallableStatement ] emp테이블 CRUD
		// update
		
		String sql = "{call up_updemp(?,?,?,?,?,?,?,?)}"; 
		
		Connection conn = null;
		CallableStatement cstmt = null;
		EmpVO vo = null;
		
		int empno =9999;
		String ename="hong";
		String job="manager";
		int mgr=7839;
		String hiredate ="2023/09/21";
		double sal =1500;
		double comm =500;
		int deptno =40;
		
		int rowCount = 0;

		try {
			conn = DBConn.getConnection() ;
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
			
			if (rowCount==1) {
				System.out.println("사원정보 수정 완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end");	
	}//main
}//class 


//create or replace PROCEDURE up_updemp
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
//    oename emp.ename%type;
//    ojob emp.job%type ;
//    omgr emp.mgr%type ;
//    osal emp.sal%type ;
//    ocomm emp.comm%type ;
//    odeptno emp.deptno%type;
//begin
//    update emp 
//    set   ename= nvl( pename, ename ) 
//        , job = nvl( pjob, job ) 
//        , mgr = nvl( pmgr, mgr ) 
//        , sal= nvl( psal, sal ) 
//        , comm = nvl (pcomm, comm ) 
//        , deptno = nvl(pdeptno, deptno )
//    where empno = pempno ;
//    commit;
//end; 

