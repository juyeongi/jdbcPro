package days02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;

public class Ex05_02 {
	
	//days02.Ex02_02.java > PreparedStatement 수정
		
	public static void main(String[] args) {
		//부서정보 수정 + 삭제
		//검색조건 - 1 :deptno, 2: dname, 3: loc

		Scanner scanner = new Scanner(System.in);

		int searchCondition;
		String searchWord;

		Connection conn= null;
		ResultSet rs = null;
		PreparedStatement pstmt = null; 

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
			sql +="deptno = ?";	// ? 바인딩변수
		} else if (searchCondition ==2) {
//			sql +="REGEXP_LIKE (dname, ?, 'i')  ";  // ? 바인딩변수
			//sql+= "dname_LIKE '%검색어%";
			sql+= "dname_LIKE ?";
		} else if  (searchCondition ==3) {
			sql+= "REGEXP_LIKE (loc, ?, 'i')  ";  // ? 바인딩변수
		}
		System.out.println(sql); //쿼리 확인

		try {
			conn= DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			//java.sql.SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수:: 1   > ?에 대한 파라미터값 x
			if (searchCondition ==1) {
				pstmt.setInt(1,Integer.parseInt( searchWord) );
			} else if(searchCondition ==2){
				pstmt.setString(1, "%"+searchWord.toUpperCase()+"%");
			} else{
				pstmt.setString(1, searchWord);
				// pstmt.setNString(1, searchWord);     // setNString > NVARCHAR
			}

			
			rs = pstmt.executeQuery();

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

		System.out.printf("검색결과 : %d\n ",list.size());

		Iterator<DeptVO> ir = list.iterator();
		while (ir.hasNext()) {
			DeptVO vo = ir.next();
			System.out.println(vo);  
		}//while
	}

}
