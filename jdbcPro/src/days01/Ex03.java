package days01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.DBConn;

import domain.DeptVO;

public class Ex03 {
	public static void main(String[] args) {
		//scott.dept 테이블 - CRUD
		// domain.DeptVO 한 부서 정보 저장객체
		
 		//[dept 테이블 조회 ]
		Connection conn= DBConn.getConnection();
		
		// statement 작업자 불러오기 + CRUD  ( SELECT )
		Statement stmt = null;   //sql 
		String sql = " SELECT * FROM dept " ; 	 // ; 입력 x
		ResultSet rs = null;
		
		//try~catch 밖에 변수선언 
		int deptno = 0;				
		String dname =null;
		String loc = null;
		DeptVO vo = null ;
		ArrayList<DeptVO> list= null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			//.next() 메서드 :  rs 로부터 읽어올 다음 레코드 확인 후 다음레코드로 이동 
//			boolean rs.next(); //true
			
			//해당 레코드 읽기
//			System.out.println(rs.next() ) ;		//true
			// rs.getInt(1);
//			System.out.println(vo);		//arraylist로 출력
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					 deptno = rs.getInt("deptno");
					 dname = rs.getString("deptno");
					 loc = rs.getString("loc");
					 vo = new DeptVO(deptno, dname, loc) ;
					 list.add(vo);
				}while (rs.next());
			}//if
			printDeptList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("end");
		
	}//main
	private static void printDeptList(ArrayList<DeptVO> list) {
		if (list ==null) {
			System.out.println(">부서 존재하지 않음");
			return;
		}//if
		Iterator<DeptVO> ir = list.iterator();
		while (ir.hasNext()) {
			DeptVO vo = ir.next();
			System.out.println(vo);  
		}//while
	}
}//class
