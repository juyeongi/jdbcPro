package days02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.DBConn;

import domain.DeptVO;

public class Ex05 {
			
			//PreparedStatement
			//days01.Ex03.java > PreparedStatement 수정
		
	public static void main(String[] args) {
		
		//[dept 테이블 조회 ]
				Connection conn= DBConn.getConnection();
				
				// statement 작업자 불러오기 + CRUD  ( SELECT )
				PreparedStatement pstmt = null;   //sql 
				String sql = " SELECT * FROM dept " ; 	 // ; 입력 x
				ResultSet rs = null;
				
				//try~catch 밖에 변수선언 
				int deptno = 0;				
				String dname =null;
				String loc = null;
				DeptVO vo = null ;
				ArrayList<DeptVO> list= null;
				
				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						list = new ArrayList<>();
						do {
							 deptno = rs.getInt("deptno");
							 dname = rs.getString("dname");
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
						pstmt.close();
						rs.close();
						DBConn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
				System.out.println("end");
	}
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
}
