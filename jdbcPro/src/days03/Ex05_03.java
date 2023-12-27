package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import domain.DeptVO;

public class Ex05_03 {
	/*
CREATE OR REPLACE PROCEDURE up_upddept
(
    pdeptno dept.deptno%type 
    ,pdname dept.dname%type := null
    ,ploc dept.loc%type := null
)
IS
    dname dept.dname%type;
    oloc dept.loc%type;
BEGIN

//    select dname, loc into odname, oloc
//    from dept 
//    where deptno = pdeptno;
//    
//    if pdname is null then
//        pdname := odname;
//    end if;
//    if ploc is null then
//        ploc := oloc;
//    end if;
//    
//    update dept
//    set dname = pdname, loc = ploc
//    where deptno = pdeptno;

    update dept
    set dname = NVL(pdname, dname), loc = NVL( ploc, loc)
    where deptno = pdeptno;
    commit;
--EXCEPTION
END;
	 */
//	[ CallableStatement ] update
	public static void main(String[] args) {
		String sql = "{call up_upddept(?, ?, ?)}";   //pdname, ploc

		Connection conn = null;
		CallableStatement cstmt = null;
		DeptVO vo = null;


		int deptno;
		String dname = "EQC", loc = "ESEOUL";
		int rowCount = 0;

		try {
			conn =DBConn.getConnection();
			cstmt = conn.prepareCall(sql);

			cstmt.setInt(1, 50);
			cstmt.setString(2, dname);
			cstmt.setString(3, loc);

			rowCount=cstmt.executeUpdate();
			if (rowCount==1) {
				System.out.println(">부서 수정 완료");
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