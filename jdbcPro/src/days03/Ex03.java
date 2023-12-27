package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Callable;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class Ex03 {
	/*
	[ CallableStatement ]
	 저장 프로시저, 저장함수
	 
	 회원가입 > ID 중복체크 //
	 emp테이블 empno (ID)
	*/
	/*
	 CREATE OR REPLACE PROCEDURE up_idcheck
(
    pid IN emp.empno%TYPE
    ,pcheck OUT NUMBER
)
IS
BEGIN
    SELECT COUNT(*) INTO pcheck
    FROM emp
    WHERE empno = pid;
--EXCEPTION
END
	 */
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("> 중복체크할 ID 입력");
		int pID = scanner.nextInt();
		
		//String sql = "(call up_idcheck(pid=>?, pcheck=>?))" ; 
		String sql = "{call up_idcheck(?, ?)}" ; 
		
		Connection conn = null;
		CallableStatement cstmt = null;
		int idCheck = 0;
		
		try {
			conn =DBConn.getConnection();
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, pID);
			cstmt.registerOutParameter(2, OracleTypes.INTEGER); //출력용 파라미터  number type
			cstmt.executeQuery();
	
			idCheck = cstmt.getInt(2);
			if (idCheck== 0) {
				System.out.println("사용가능한 ID");
			} else {
				System.out.println("이미 사용중인 ID");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cstmt.close();
				DBConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("end");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}//main
}
