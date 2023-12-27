package days04;

import java.sql.Connection;

import com.util.DBConn;

import days04.Board.controller.BoardController;
import days04.Board.persistence.BoardDAO;
import days04.Board.persistence.BoardDAOImpl;
import days04.Board.service.BoardService;

public class Ex01 {
	// [게시판만들기]
	// 모델 1방식  : 글쓰기, 목록, 수정, 삭제 >> 로직처리
	// ex) 중국집 서빙, 요리, 주문 A가 모두 처리
	// 모델 2방식(MVC패턴) : 글쓰기 > 글쓰기서비스 > DAO > DB처리
	//								   글쓰기 컨트롤러 < 글쓰기서비스 < DAO < DB처리
	// ex) 중국집 서빙 :A / 요리 :B / 주문 : C 업무분담
	// Model 로직처리 객체
	// View 출력 객체
	// Controller 모든 요청/처리 담당 객체
	public static void main(String[] args) {
		//1. 시퀀스/ 테이블생성
/*
CREATE SEQUENCE seq_tbl_cstVSBoard;

CREATE TABLE tbl_cstVSBoard (
  seq NUMBER NOT NULL PRIMARY KEY ,
  writer VARCHAR2(20) NOT NULL ,
  pwd VARCHAR2(20) NOT NULL ,
  email VARCHAR2(100),
  title VARCHAR2(200) NOT NULL ,
  writedate DATE DEFAULT (SYSDATE),
  readed NUMBER DEFAULT(0),
  tag NUMBER(1) DEFAULT(0),
  content CLOB
);
*/
		//2. domain.BoardDTO.java 클래스 생성	> select() / insert() 추상메서드
		//3. persistence.BoardDAOimpl.java 인터페이스 생성  
		//4. 단위테스트 insert()/ select() days04.test.BoardDAOimlpTest.java
		//5. 서비스클래스  >> 트랙잭션처리
		//		하나의 게시글 보기
		//			ㄴ 로그 기록			dao.로그기록()
		//			ㄴ 조회수 1증가		dao.조회수증가(seq)
		//			ㄴ 게시글 정보		dao.게시글정보(seq)
		//6. 단위테스트 BoardServiceTest.java
		//7. days04.board.controller.BoardController.java  > 입력/ 출력(view역할 추가)
		//8. days04.Ex01.java 테스트
		Connection conn = DBConn.getConnection()	;
		BoardDAO dao = new BoardDAOImpl(conn);
		BoardService service = new BoardService (dao);
		BoardController controller = new BoardController(service);
		
		controller.boardStart();
		
		
		//9. 목록, 새글쓰기, 상세보기
		//10. 삭제
		//11. 수정
		//12. 검색
		//13. [페이징처리] 
		//필드
//		private int currentPage = 1;		// 현재 페이지번호
//		private int numberPerPage =10; // 한 페이지에 출력할 게시물 수
//		private int numberOfPageBlock= 10; // 페이지 블럭 수
		
		//baordController. 선언
		
		// 1) 현재페이지 번호 필드   currentPage
		// 2) 페이지당 출력 게시글 수 필드    numberPerPage
		//3) 페이지 블럭 수 필드 
		//4)총 레코드 수 boarddaoimpl gettotalrecoreds();
//				  페이지 수 					   getTotalPages(;)
//		6) 쿼리 확인 
		
//		7)boardContraller
//					service.selectService
		
		
		/*
		 *1) sequence 생성 -seq_테이블명
		 *2) tbl_cstvsboard 테이블생성
		 *3) boardDTO
		 *4) boardDAO, boardDAOImpl > 단위테스트
		 *		insert() , select () 
		 *5) boardService
		 *		insertService(), selectService()
		 *6) boardController
		 *7) controller.boardStart()
		 *8) search()
		 *		페이징처리
		 *			1)현재 페이지번호
		 *			2)한 페이지당 출력할 게시글 수
		 *			3)페이징 블럭 수
		 *			4)총 페이지수 
		 *
		 *9)검색하기() + 페이징처리
		 *
		 * */
	}
}
