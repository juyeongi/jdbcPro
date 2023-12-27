package days01;

public class Ex01 {
	public static void main(String[] args) {
		// java <-  JDBC 드라이버 -> oracle 연동
		// java + database = connectivity
		// 자바의 db에 연결 및 CRUD 작업을 하기 위한 표준 인터페이스
		// 오라클사 + jdbc  > 구현 클래스 == jdbc드라이버
		// 11g xe  >  ojdbc6.jar + jdk 1.6 이상
		// oracle 폴더 > ojdbc6.jar > c드라이브로 복사
		
//		[JDBC 드라이버 종류]
//		1) type1 : ODBC
//		2) type2 : C, C++ 언어로 만든 라이브러리를 사용해서 DB연동
//		3) type3 : 미들웨어 서
//		4) type4: Thin 드라이버. 순수 자바, 가장 많이 사용
//				
//		[DBMS와 연결 방법]
//		1) JDBC 드라이버를 메모리상에 로딩 - Class.froName() 메서드
//		2) Connection 객체 얻어오는 작업 - Driver[Manager]클래스 getConnection() 메서드
//		3) 작업자(Statement)객체 - CRUD 필요한 작업\
//				(1) Statement 객체
//				(2) [Prepared]Statement 객체 : 성능 빠름
//				(3) [Callable]Statement 객체 : 저장프로시저, 저장함수 사용
//		4) 연결 종료 - Connection 객체 Close
//	
	}
}