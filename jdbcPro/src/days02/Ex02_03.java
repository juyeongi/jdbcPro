package days02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class Ex02_03 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//부서정보 수정
		//1. 원래값
		//2. 부서명, 지역 수정
		
		BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
		int deptno ;
		String dname;
		String loc;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet	rs = null;
		String sql = null;
		int rowCount = 0;
		
		System.out.print("수정할 부서번호 입력");
		deptno = Integer.parseInt( br.readLine() );
		System.out.print("수정할 부서명 입력");
		dname = br.readLine() ;
		System.out.print("수정할 지역명 입력");
		loc = br.readLine() ;
		
		// 해당 부서번호의 수정 전 부서명/지역명 저장
		//originalDname, originalLoc 변수
		sql =String.format("SELECT* FROM dept WHERE deptno = %d", deptno);
		String originalDname, originalLoc;
		conn = DBConn.getConnection();   // 수정 후 닫기
		try {
			stmt = conn.createStatement();
			rs=  stmt.executeQuery(sql);
			// rs.next() 다음 레코드 유무 확인 , 다음레코드로 이동
			if(rs.next()) {
			originalDname = rs.getString("dname");
			originalLoc = rs.getString("loc");
			
				System.out.println(originalDname);
			}else {
				System.out.println(" 부서존재x");
				return;
			}
			//////update//////
			//if ( dname ==" ")
			if(dname.equals("")) dname= originalDname;
			if(loc.equals("")) loc= originalLoc;
			sql =String.format("UPDATE dept SET dname= '%s', loc = '%s' WHERE deptno = %d"
					, dname, loc, deptno);
//			System.out.println(sql );
			//실행
			rowCount = stmt.executeUpdate(sql);
			if(rowCount==1) {
				System.out.println("수정 완료");
			}//if
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		DBConn.close();
	}//main

}
