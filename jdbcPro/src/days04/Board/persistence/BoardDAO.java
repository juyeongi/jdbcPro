package days04.Board.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.Board.domain.BoardDTO;

public interface BoardDAO {
	//1. 게시글 목록조회 //페이징처리 x
	ArrayList<BoardDTO> select() throws SQLException;
	
	//2. 게시글 쓰기 함수
	int insert(BoardDTO dto) throws SQLException;
	//3. 조회수증가
	void increaseReaded(int seq) throws SQLException;
	//4. 게시글 상세보기
	BoardDTO view(int seq) throws SQLException;
	//5. 게시글 삭제
	int delete(int seq) throws SQLException;
	//6. 게시글 수정
	int update(BoardDTO dto) throws SQLException;
	//7. 게시글 검색
	ArrayList<BoardDTO> Search(int searchCondition, String searchWord) throws SQLException;
	//7-2. 게시글검색 +페이징처리
	ArrayList<BoardDTO> Search(int currentPage, int numberPerPage, int searchCondition, String searchWord)  throws SQLException;
	//8-1. 총 레코드 수
	int getTotalRecords() throws SQLException;
	//8-2. 총 페이지 수
	int getTotalPages(int numberPerPage) throws SQLException;
	//9. 게시글 목록조회 //페이징처리 
	ArrayList<BoardDTO> select(int currentPage, int numberPerPage ) throws SQLException;

	int getTotalPages(int numberPerPage, int searchCondition, String searchWord) throws SQLException;

}
