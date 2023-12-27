package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import domain.EmpVO;

public class Test03 {
		//emp테이블에서 사원명 (LIKE) /부서번호/job  (LIKE)으로 검색 
		// 검색된 정보 조회
	
	public static void main(String[] args) {
		 String ename;
		 String job ;
		 int mgr;
		 String hiredate ;
		double sal ;
		double comm ;
		int deptno;  
		
		Scanner scanner = new Scanner(System.in);
		
		int searchCondition;
		String searchWord;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		EmpVO vo = null;
		ArrayList<EmpVO> list = null;
		
		System.out.println("검색조건 입력");
		searchCondition = scanner.nextInt();
		System.out.println("검색어 입력");
		searchWord = scanner.next();
		
		
		
	}//main
}
