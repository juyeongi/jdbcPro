package days04.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.util.DBConn;

import days04.Board.domain.BoardDTO;
import days04.Board.persistence.BoardDAOImpl;

class BoardDAOImplTest {
	/*
	@Test

	void selectTest() {
		Connection conn = DBConn.getConnection();
		
		BoardDAOimpl dao = new BoardDAOimpl(conn);
		try {
			ArrayList<BoardDTO> list = dao.select();
			if (list==null) {
				System.out.println("게시글 없음");
				return;
			}	
			Iterator<BoardDTO> ir = list.iterator();
			while (ir.hasNext()) {
				BoardDTO dto = ir.next();
				System.out.println(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close();
		}
		
		

	}
*/
//	@Test
//
//	void insertTest() {
//		Connection conn = DBConn.getConnection();
//		
//		BoardDAOImpl dao = new BoardDAOImpl(conn);
//		try {
//			BoardDTO dto = BoardDTO.builder()
//										.writer("고길동")
//										.pwd("1234")
//										.email("hong@naver.com")
//										.title("두번째 게시글")
//										.tag(0)
//										.content("두번째 게시글")
//										.build();
//			int rowCount = dao.insert(dto);
//			
//			if (rowCount==1l) {
//				System.out.println("게시글 작성 완료");
//				return;
//			}	
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			DBConn.close();
//		}
//	}
//	
//	@Test
//
//	void totalRecordesTest() {
//		Connection conn = DBConn.getConnection();
//		BoardDAOImpl dao = new BoardDAOImpl(conn);
//		try {
//			int totalRecords = dao.getTotalRecords();
//				System.out.println("총 레코드 수 : " +totalRecords );
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			DBConn.close();
//		}
//	}
//	

	@Test

	void totalPagesTest() {
		Connection conn = DBConn.getConnection();
		BoardDAOImpl dao = new BoardDAOImpl(conn);
		try {
			int totalPages = dao.getTotalPages(10);
				System.out.println("총 레코드 수 : " +totalPages );

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close();
		}
	}
}
