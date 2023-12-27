package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;

import domain.DeptVO;
import oracle.jdbc.internal.OracleTypes;

public class Ex05_02 {
	/*
CREATE OR REPLACE PROCEDURE up_insdept
(
    pdname dept.dname%type := null
    ,ploc dept.loc%type := null
)
IS
BEGIN
insert into dept values (seq_dept.nextval, pdname, ploc);
    commit;
--EXCEPTION
END;
	 */
//	[ CallableStatement ] insert
	//up_insdept
	public static void main(String[] args) {
		String sql = "{call up_insdept(?, ?)}";   //pdname, ploc

		Connection conn = null;
		CallableStatement cstmt = null;
		DeptVO vo = null;

		
		int deptno;
		String dname = "QC", loc = "SEOUL";
		int rowCount = 0;
		
		try {
			conn =DBConn.getConnection();
			cstmt = conn.prepareCall(sql);

			cstmt.setString(1, dname);
			cstmt.setString(2, loc);
			
			rowCount=cstmt.executeUpdate();
			if (rowCount==1) {
				System.out.println(">부서 추가 완료");
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
