package days02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.EmpVO;
import persistence.EmpDAO;
import persistence.EmpDAOImpl;

public class Ex04 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//emp 사원정보 추가
		//emp 사원명,부서번호, JOB 검색
		//emp 사원정보 수정
		//emp 사원정보 삭제
		//db연동 class 생성 
		//DAO   db처리객체 persistence.EmpDAO
		//모든 사원 정보조회
		ArrayList<EmpVO> list = null;
		Connection conn = DBConn.getConnection();
		
		EmpDAOImpl dao = new EmpDAOImpl(conn) ;
		//1) 조회
	//	list = dao.getSelect();
	//	PrintEmpList(list);
		//2) 검색
		/*
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색조건 입력");
		int searchCondition=scanner.nextInt();
		System.out.print("검색어입력");
		String searchWord = scanner.next();
		
		list = dao.getSelect(searchCondition, searchWord) ;
		*/
		//3) 추가
		/*
		int empno;
	      String ename;
	      String job;
	      int mgr;
	      String hiredate; 
	      double sal;
	      double comm;
	      int deptno;

	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      System.out.print("1. empno 입력 ? ");      
	      empno = Integer.parseInt( br.readLine() );      
	      System.out.print("2. ename 입력 ? ");
	      ename = br.readLine() ;      
	      System.out.print("3. job 입력 ? ");
	      job = br.readLine() ;
	      System.out.print("4. mgr 입력 ? ");
	      mgr = Integer.parseInt( br.readLine() );
	      System.out.print("5. hiredate 입력 ? ");
	      hiredate = br.readLine() ;
	      System.out.print("6. sal 입력 ? ");
	      sal = Double.parseDouble(br.readLine()) ;
	      System.out.print("7. comm 입력 ? ");
	      comm = Double.parseDouble(br.readLine()) ;
	      System.out.print("8. deptno 입력 ? ");
	      deptno = Integer.parseInt( br.readLine() );
	      
	      EmpVO vo = new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
		
	      int rowCount = dao.add(vo);
	      if (rowCount ==1 ) System.out.println("사원 추가완료");	  
	      */
		//4) 수정 empno/ hiredate x
		/*
		int empno;
	      String ename;
	      String job;
	      int mgr;
	      String hiredate; 
	      double sal;
	      double comm;
	      int deptno;

	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      System.out.print("1. 수정할 empno 입력 ? ");      
	      empno = Integer.parseInt( br.readLine() );      
	      EmpVO vo = dao.get(empno);  //수정 전 정보 
		
	      System.out.println(vo);  //수정할 객체
	      
	      System.out.print("2. 수정할 ename 입력 ? ");
	      ename = br.readLine() ;    
	      if(ename.equals("") )ename = vo.getEname();
	      System.out.print("3.수정할  job 입력 ? ");
	      job = br.readLine() ;
	      if(job.equals("") )job = vo.getJob();
	      System.out.print("4. 수정할 mgr 입력 ? ");
	      try { // 미입력시 기존 정보 유지
		      mgr = Integer.parseInt( br.readLine() );
	      }catch (Exception e) {
	    	  sal= vo.getSal();
		}
	      System.out.print("6. 수정할 sal 입력 ? ");
	      try { // 미입력시 기존 정보 유지
		      sal = Double.parseDouble(br.readLine()) ;
	      }catch (Exception e) {
	    	  sal= vo.getSal();
		}
	      System.out.print("7. 수정할 comm 입력 ? ");
	      try { // 미입력시 기존 정보 유지
		      comm = Double.parseDouble(br.readLine()) ;
	      }catch (Exception e) {
	    	  sal= vo.getSal();
		}
	      System.out.print("8. 수정할 deptno 입력 ? ");
	      try { // 미입력시 기존 정보 유지
		      deptno = Integer.parseInt( br.readLine() );
	      }catch (Exception e) {
	    	  sal= vo.getSal();
		}

	    
	      //입력x  > 기존 값으로 설정
	      vo = new EmpVO(empno, ename, job, vo.getMgr(), vo.getHiredate(), sal, vo.getComm(),vo.getDeptno());
	      int rowCount = dao.update(vo);
	      if (rowCount ==1 ) System.out.println("사원정보 수정완료");	  
	      */
		//5) 사원정보 삭제
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      System.out.print("1. 수정할 empno 입력 ? ");      
	      int empno = Integer.parseInt( br.readLine() );      
		
	    int rowCount = dao.delete(empno);
	    if (rowCount ==1 ) System.out.println("사원정보 삭제완료");	
		DBConn.close();
		System.out.println("end");
		
	}//main

		private static void PrintEmpList(ArrayList<EmpVO> list) {
			if (list == null) {
				System.out.println("사원정보없음");
				return;
			}
			int count= list.size();
			System.out.printf("사원수 : %d 명\n",count);
			Iterator<EmpVO> ir = list.iterator();
			while (ir.hasNext()) {
				EmpVO vo = ir.next();
				System.out.println(vo);
			}

		}	
		
		

}//class 
