package days04.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.util.DBConn;

import days04.Board.domain.BoardDTO;
import days04.Board.persistence.BoardDAO;
import days04.Board.persistence.BoardDAOImpl;
import days04.Board.service.BoardService;

class BoardServiceTest {
	
	@Test
	void insertServiceTest() {
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		BoardService service = new BoardService(dao);
		
		BoardDTO dto = BoardDTO.builder()
				.writer("고길동")
				.pwd("1234")
				.email("go@naver.com")
				.title("세번째 게시글")
				.tag(0)
				.content("세번째 게시글")
				.build();
		int rowCount = service.insertService(dto);
		
		if (rowCount==1l) {
			System.out.println("게시글 작성 완료");
			return;
		}	

	}
/*
	@Test
	void selectServiceTest() {
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOimpl(conn);
		BoardService service = new BoardService(dao);
		
		ArrayList<BoardDTO> list =  service.selectService();
		if (list==null) {
			System.out.println("게시글 없음");
			return;
		}	
		Iterator<BoardDTO> ir = list.iterator();
		while (ir.hasNext()) {
			BoardDTO dto = ir.next();
			System.out.println(dto);
		}
	}
*/
}
