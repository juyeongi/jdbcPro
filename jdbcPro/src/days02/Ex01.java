package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.DBConn;

import domain.EmpVO;

public class Ex01 {
	public static void main(String[] args) {
		Connection conn = null;
		int deptno = 30;
		String sql =String.format("SELECT * FROM emp WHERE deptno = %d", deptno ); 
		Statement stmt= null;
		ResultSet rs = null;
		ArrayList<EmpVO> list = null;
		
		 int empno ;
		 String ename; 
		 String job;
		 int mgr;
		 String hiredate ;
		double sal ;
		double comm ;
		
		EmpVO vo = null;
		
		conn = DBConn.getConnection();
		
		try {
		stmt= conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);

			rs= stmt.executeQuery(sql);
			
			if (rs.next()) {
				list = new ArrayList<EmpVO>();
				do {
					 empno = rs.getInt("empno") ;
					 ename=rs.getString("ename");
					 job= rs.getString("job");
					 mgr= rs.getInt("mgr");
					 hiredate = rs.getString("hiredate");
					 sal =rs.getDouble ( "sal");
					 comm=rs.getDouble ( "comm");
					 deptno = rs.getInt("deptno");
					 
					 vo= new EmpVO( empno, ename,job,mgr,hiredate,sal,comm, deptno);
					 list.add(vo);
				}while(rs.next());
			}//if
			PrintEmpList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
	
	
		DBConn.close();
	}//main
	private static void PrintEmpList(ArrayList<EmpVO> list) {
		if (list == null) {
			System.out.println("사원정보없음");
			return;
		}
		int count= list.size();
		int deptno = list.get(0).getDeptno();
		System.out.printf("%d 부서 사원수 : %d 명\n", deptno, count);
		Iterator<EmpVO> ir = list.iterator();
		while (ir.hasNext()) {
			EmpVO vo = ir.next();
			System.out.println(vo);
		}

	}

}//class
