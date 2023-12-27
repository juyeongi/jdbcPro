package days02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.DBConn;

import domain.EmpVO;

public class Ex06 {
	/*
    [실행결과]
    1등급   (     700~1200 ) - 2명
          20   RESEARCH   7369   SMITH   800
          30   SALES         7900   JAMES   950
    2등급   (   1201~1400 ) - 2명
       30   SALES   7654   MARTIN   2650
       30   SALES   7521   WARD      1750   
    3등급   (   1401~2000 ) - 2명
       30   SALES   7499   ALLEN      1900
       30   SALES   7844   TURNER   1500
    4등급   (   2001~3000 ) - 4명
        10   ACCOUNTING   7782   CLARK   2450
       20   RESEARCH   7902   FORD   3000
       20   RESEARCH   7566   JONES   2975
       30   SALES   7698   BLAKE   2850
    5등급   (   3001~9999 ) - 1명   
       10   ACCOUNTING   7839   KING   5000
	 */
	public static void main(String[] args) {
		// 조회 

		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT deptno, ename, empno, job, sal\r\n"
				+ "FROM emp\r\n"
				+ "ORDER BY sal" ;
		ResultSet rs = null;
		
		int deptno=0;
		String ename=null;
		int empno=0;
		String job=null;
		int sal=0;
		EmpVO vo = null;
		ArrayList<EmpVO> list = null ;
		
		try {
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				list =new ArrayList<EmpVO>();
				
				do {
				deptno = rs.getInt("deptno");
				ename = rs.getString("ename");
				empno = rs.getInt("empno");
				job = rs.getString("job");
				sal = rs.getInt("sal");
				vo = new EmpVO(empno, ename, job, empno, job, sal, sal, deptno) ;
				list.add(vo);
				}while(rs.next());
				
			}
			printEmpList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	private static void printEmpList(ArrayList<EmpVO> list) {
		if (list==null) {
			System.out.println("x");
			return;
		}
		Iterator<EmpVO> ir = list.iterator();
		while (ir.hasNext()) {
			EmpVO vo = ir.next();
			System.out.println(vo);
		}
	}
}//class
