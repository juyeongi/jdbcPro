package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.EmpVO;

public interface EmpDAO {




	// 조회 메서드
	ArrayList<EmpVO> getSelect() ;
		
	// 검색
	ArrayList<EmpVO >getSelect(int searchCondition , String searchWord);
		
	// 추가
	int add(EmpVO vo ) ;
		
	// 수정
	// 수정할 사원 정보 불러오는 함수
	 EmpVO get(int empno); 
		
	//사원정보 수정 함수
	int update(EmpVO vo);
		
	// 삭제
	int delete (int empno);
		
}
