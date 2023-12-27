package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;
 
import domain.EmpVO;
import oracle.jdbc.internal.OracleTypes;

public class Ex06 {
	public static void main(String[] args) {
		// 복습
		//[ CallableStatement ] emp테이블 CRUD
		//select 
		
		String sql = " {call up_selemp(?)}"; 
		
		Connection conn = null;
		CallableStatement cstmt = null;
		EmpVO vo = null;
		ArrayList<EmpVO> list = null;
		ResultSet rs = null;
		
		 int empno ;
		 String ename;
		 String job;
		 int mgr;
		 Date hiredate ;
		 double sal ;
		 double comm ;
		 int deptno ;
		 

		 try {
			conn = DBConn.getConnection();
			cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			
			rs = (ResultSet) cstmt.getObject(1);
			while (rs.next()) {
				  empno = rs.getInt("empno");
				  ename = rs.getString("ename");
				  job = rs.getString("job");
				  mgr= rs.getInt("empno");
				  hiredate = rs.getDate("hiredate");
				  sal = rs.getDouble("sal");
				  comm = rs.getDouble("comm");
				  deptno= rs.getInt("deptno");
				  
				  vo = new EmpVO(empno, ename, job, mgr, job, sal, comm, deptno);
				  System.out.println(vo);
			}//while
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
//
//create or replace PROCEDURE up_selemp
//(
//    pempcursor out SYS_REFCURSOR
//)
//is
//begin
//    open pempcursor for 
//    select * from emp; 
//end; 
