package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;

public class Ex02_02 {
	public static void main(String[] args) {
		//부서정보 수정 + 삭제
		//검색조건 - 1 :deptno, 2: dname, 3: loc
		
		Scanner scanner = new Scanner(System.in);
	
		int searchCondition;
		String searchWord;
		
		Connection conn= null;
		ResultSet rs = null;
		Statement stmt = null; 
		
		int deptno;
		String dname;
		String loc; 
		
		DeptVO vo = null;
		ArrayList<DeptVO> list= null;
		
		System.out.print("검색조건 입력");
		searchCondition=scanner.nextInt();
		System.out.print("검색어입력");
		searchWord = scanner.next();

		String sql= "SELECT * "
				+ "FROM dept "
				+"WHERE ";
		if (searchCondition ==1) {
			sql += String.format("deptno IN (%s) ", searchWord );
		} else if (searchCondition ==2) {
			sql += String.format("REGEXP_LIKE (dname, '%s', 'i')  ", searchWord );
		} else if  (searchCondition ==3) {
			sql+= String.format("REGEXP_LIKE (loc, '%s', 'i')  ", searchWord );
		}
		System.out.println(sql); //쿼리 확인
		
		try {
			conn= DBConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs.next()) {
				list = new ArrayList<>();
				do {
					 deptno = rs.getInt("deptno");
					 dname = rs.getString("dname");
					 
					 if (searchCondition ==2) {
						 searchWord=searchWord.toUpperCase();
						 dname.replaceAll(searchWord, "["+searchWord+"]");
					 }//if
					 
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
		
	}
	private static void printDeptList(ArrayList<DeptVO> list) {
		if (list ==null) {
			System.out.println(">부서 존재하지 않음");
			return;
		}//if
		
		System.out.printf("검색결과 : %d\n ",list.size());
		
		Iterator<DeptVO> ir = list.iterator();
		while (ir.hasNext()) {
			DeptVO vo = ir.next();
			System.out.println(vo);  
		}//while
	}
	
}
