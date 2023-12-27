package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import domain.EmpVO;

public class Ex06_04 {
	public static void main(String[] args) {
		// 복습
		//[ CallableStatement ] emp테이블 CRUD
		//delete 
		
		String sql = "{call up_delemp(?)}";
		
		Connection conn = null;
		CallableStatement cstmt = null;
		EmpVO vo = null;
		
		int rowCount = 0;
		
		try {
			conn = DBConn.getConnection();
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, 9999);
			
			rowCount = cstmt.executeUpdate();
			if (rowCount==1) {
				System.out.println("사원정보 삭제 완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
//
//create or replace PROCEDURE up_delemp
//(
//    pempno emp.empno%type
//)
//
//begin
//    delete from emp
//    where empno = pempno ; 
//    commit ;
//end; 
//
