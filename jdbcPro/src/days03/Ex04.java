package days03;
/*
 * 1.  로그인 처리
 *      아이디    : [    kenik    ]
 *      비밀번호 : [    1234     ]
 *      
 *      [로그인]   [회원가입]
 * 2.  up_logon
 *     회원테이블 = 아이디(PK), 비밀번호 X
 *     emp          = empno(PK), ename    
 * 3.      로그인 성공 : 0
 *          로그인 실패
 *                 ㄴ 아이디 존재하지 않으면  : 1
 *                 ㄴ 비밀번호 틀리면               : -1
 * */
/*
CREATE OR REPLACE PROCEDURE up_logon
(
    pid IN emp.empno%TYPE
    ,ppassword IN emp.ename%TYPE
    ,pcheck OUT NUMBER 
)
IS
    vpassword emp.ename%TYPE;
BEGIN
    SELECT COUNT(*) INTO pcheck
    FROM emp
    WHERE empno = pid;
    
    IF pcheck=1 THEN  --ID존재
    --비밀번호 확인
    SELECT ename INTO vpassword
    FROM emp
    WHERE empno = pid;
    
    IF vpassword = ppassword THEN
    pcheck := 0;
    ELSE
    pcheck := -1;
    END IF; 
    
    ELSE --ID 존재 X
        pcheck := 1;
    END IF;
    
--EXCEPTION
END;
*/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;

public class Ex04 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("> 로그인할 ID , PW 입력");
		int pID = scanner.nextInt();
		String pPWD = scanner.next();


		//String sql = "(call up_idcheck(pid=>?, pcheck=>?))" ; 
		String sql = "{call up_logon(?, ?, ?)}" ; 

		Connection conn = null;
		CallableStatement cstmt = null;
		int logonCheck = 0;

		try {
			conn =DBConn.getConnection();
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, pID);
			cstmt.setString(2, pPWD);
			cstmt.registerOutParameter(3, OracleTypes.INTEGER); //출력용 파라미터  number type
			cstmt.executeQuery();

			logonCheck = cstmt.getInt(3);
			if (logonCheck== 0) {
				System.out.println("로그인 성공 ");
			} else if (logonCheck== 1) {
				System.out.println("실패 -ID 존재하지 않음 ");
			} else {
				System.out.println("실패 -PW오류");
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
		
	}
}

