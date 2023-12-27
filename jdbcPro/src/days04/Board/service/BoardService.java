package days04.Board.service;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.Board.domain.BoardDTO;
import days04.Board.persistence.BoardDAO;
import days04.Board.persistence.BoardDAOImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//사용자 > BoardController> dto >BoardService> dto > BoardDAOimpl > db

@Data
@AllArgsConstructor
@Builder

public class BoardService {
	private BoardDAO dao = null;

	// 1. 게시글 목록 조회 서비스
	public ArrayList<BoardDTO> selectService() {
		ArrayList<BoardDTO> list = null;

		// 1) 로그기록
		System.out.println("> 게시글 목록 조회 -> 로그기록 작업");
		// 2)
		try {
			list = this.dao.select();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 2. 게시글 쓰기 서비스
	public int insertService(BoardDTO dto) {
		int rowCount = 0;
		// 1) 로그기록
		System.out.println("> 게시글 쓰기 -> 로그기록 작업");
		// 2)
		try {
			rowCount = this.dao.insert(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	// 3. 게시글 상세보기 서비스
	// 1) 게시글 조회수 증가
	// 2) 게시글 정보 조회
	// 3) 1)+2) 트랜잭션처리
	public BoardDTO viewService(int seq) {
		BoardDTO dto = null;
		try {
			// boarddao 인터페이스 getconn x 다운캐스팅
			((BoardDAOImpl) this.dao).getConn().setAutoCommit(false);
			//조회수 증가
			this.dao.increaseReaded(seq);
			//게시글 정보조회
			dto = this.dao.view(seq);
			//로그기록
			System.out.println("> 게시글 상세보기 -> 로그기록 작업");
			((BoardDAOImpl) this.dao).getConn().commit();
		} catch (Exception e) {
			try {
				((BoardDAOImpl) this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				((BoardDAOImpl) this.dao).getConn().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return dto;
	}
	// 4) 게시글삭제
	public int deleteService(int seq) {
		int rowCount = 0;
		// 1) 로그기록
		System.out.println("> 게시글 삭제 -> 로그기록 작업");
		// 2)
		try {
			rowCount = this.dao.delete(seq);
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return rowCount;
	}
//게시글수정
	public int updateService(BoardDTO dto) {
		int rowCount = 0;
		System.out.println("> 게시글 삭제 -> 로그기록 작업");
		try {
			rowCount= this.dao.update(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public ArrayList<BoardDTO> searchService(int searchCondition, String searchWord) {
		ArrayList<BoardDTO> list = null;

		// 1) 로그기록
		System.out.println("> 게시글 목록 검색 -> 로그기록 작업");
		// 2)
		try {
			list = this.dao.Search(searchCondition, searchWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	//검색하기() + 페이징처리
	public ArrayList<BoardDTO> searchService(int currentPage, int numberPerPage, int searchCondition,
			String searchWord) {
		ArrayList<BoardDTO> list = null;

		// 1) 로그기록
		System.out.println("> 게시글 목록 검색 -> 로그기록 작업");
		// 2)
		try {
//			list = this.dao.Search(searchCondition, searchWord);
			list = this.dao.Search(currentPage, numberPerPage, searchCondition, searchWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}



	

	public ArrayList<BoardDTO> selectService(int currentPage, int numberPerPage) {
		ArrayList<BoardDTO> list = null;

		System.out.println("> 게시글 목록 조회 -> 로그기록 작업");

		try {
			list = this.dao.select(currentPage, numberPerPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public String pageService(int currentPage, int numberPerPage, int numberOfPageBlock) {
		String pagingBlock = "\t\t\t";
		int totalPages;
		try {
			totalPages = this.dao.getTotalPages(numberPerPage);
			 int start = (currentPage -1) /numberOfPageBlock * numberOfPageBlock +1 ;
	         int end= start + numberOfPageBlock -1;         
	         end =   end > totalPages ? totalPages : end;

	         if( start != 1 )  pagingBlock +=" < ";          
	         for (int j = start; j <= end; j++) {
	        	 pagingBlock += String.format(currentPage==j?"[%d] " : "%d " , j);
	         }         
	         if( end != totalPages )  pagingBlock +=" > ";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return pagingBlock;
	}

	public String pageService(int currentPage, int numberPerPage
											, int numberOfPageBlock, int searchCondition, String searchWord) {

		String pagingBlock = "\t\t\t";
		int totalPages;
		try {
//			totalPages = this.dao.getTotalPages(numberPerPage);
			totalPages = this.dao.getTotalPages(numberPerPage, searchCondition,searchWord );
			int start = (currentPage -1) /numberOfPageBlock * numberOfPageBlock +1 ;
			int end= start + numberOfPageBlock -1;         
			end =   end > totalPages ? totalPages : end;

			if( start != 1 )  pagingBlock +=" < ";          
			for (int j = start; j <= end; j++) {
				pagingBlock += String.format(currentPage==j?"[%d] " : "%d " , j);
			}         
			if( end != totalPages )  pagingBlock +=" > ";
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return pagingBlock;
	}


}// class
