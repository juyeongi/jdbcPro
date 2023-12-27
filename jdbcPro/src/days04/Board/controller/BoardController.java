package days04.Board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import days04.Board.domain.BoardDTO;
import days04.Board.service.BoardService;

public class BoardController {
	private Scanner scanner;
	   private int selectedNumber;
	   private BoardService service;
	   
	   //페이징처리 필드
	   private int currentPage = 1;
	   private int numberPerPage =10; // 한 페이지에 출력할 게시물 수
	   private int numberOfPageBlock= 10; // 페이지 블럭 수
	   
	   public BoardController() {
	      this.scanner = new Scanner(System.in);
	   }
	   public BoardController(BoardService service) {
	      this();
	      this.service = service;
	   }


	public void boardStart() {
		while(true ) {
			메뉴출력();
			메뉴선택();
			메뉴처리();
		}
	}
	private void 메뉴처리() {
		switch(this.selectedNumber) {
		case 1:
			새글쓰기()	;
			break;
		case 2:
			목록보기();
			break;
		case 3:
			상세보기();
			break;
		case 4:
			수정하기();
			break;
		case 5:
			삭제하기();
			break;
		case 6:
			검색하기();
			break;
		case 7:
			exit();
			break;
		} 		
	}

	private void 검색하기() {
		System.out.println(">검색조건 : 제목(1), 내용(2), 작성자(3), 제목+내용(4) 선택");
		int searchCondition = this.scanner.nextInt();
		System.out.print("> 검색어 입력");
		String searchWord = this.scanner.next();
		
		//목록보기() 코딩
		System.out.println(">현재페이지(currentPage) 번호 입력");
		this.currentPage = this.scanner.nextInt();
		//ArrayList<BoardDTO> list =  this.service.searchService(searchCondition, searchWord);
		ArrayList<BoardDTO> list =  this.service.searchService(
														this.currentPage
														, this.numberPerPage
														, searchCondition
														, searchWord);

		//view 출력담당
		System.out.println("\t\t\t\t 게시판");
		System.out.println("----------------------------------------------------------------------------");
		System.out.printf("%s\t  %-40s\t  %s\t%-10s\t%s\n","글번호","글제목","글쓴이","작성일","조회수");

		System.out.println("----------------------------------------------------------------------------");
		if (list==null) {
			System.out.println("\t\t게시글 없음");
		}	else {
			Iterator<BoardDTO> ir	= list.iterator();
			while (ir.hasNext()) {
				BoardDTO dto = ir.next();
				System.out.printf("%d\t%-30s \t%s\t %-10s\t%d\n"
						,dto.getSeq()
						,dto.getTitle()
						,dto.getWriter()
						,dto.getWritedate()
						,dto.getReaded());
			}
		}
		System.out.println("----------------------------------------------------------------------------");
//		System.out.println("\t\t\t [1] 2 3 4 5 6 7 8 9 10 >");
		String pagingBlock = this.service.pageService (
				this.currentPage
				,this.numberPerPage
				,this.numberOfPageBlock
				,searchCondition
				,searchWord
				);
System.out.println(pagingBlock);

		System.out.println("----------------------------------------------------------------------------");

		일시정지();

	}
//--------------------------------	
	private void 삭제하기() {
		System.out.println(">삭제할 게시글번호(seq)입력");
		int seq= this.scanner.nextInt();
		
		int rowCount = this.service.deleteService(seq);
		if (rowCount ==1) System.out.printf("%d 게시글 삭제 완료\n", seq);
		일시정지();
	}
//--------------------------------	
	private void 수정하기() {
		System.out.println(">수정할 게시글번호(seq)입력");
		int seq= this.scanner.nextInt();
		// 수정 전 게시글 정보
		BoardDTO dto = this.service.viewService(seq);
		if (dto ==null) {
			System.out.println("> 해당 게시글 존제하지 않음");
			return;
		}
		//게시글 출력(view)담당
		System.out.println("\t ㄱ. 글번호 : "+seq);
		System.out.println("\t ㄴ. 작성자 : "+dto.getWriter());
		System.out.println("\t ㄷ. 조회수 : "+dto.getReaded());
		System.out.println("\t ㄹ. 글제목 : "+dto.getTitle());
		System.out.println("\t ㅁ. 글내용 : "+dto.getContent());
		System.out.println("\t ㅂ. 작성일 : "+dto.getWritedate());
		
		//수정할 데이터 
		System.out.println(">email 입력");
		String email = this.scanner.next();
		System.out.println(">제목 입력");
		String title = this.scanner.next();
		System.out.println(">내용 입력");
		String content = this.scanner.next();
		
		//입력받은 값 dto객체
		dto = BoardDTO.builder()
								.seq(seq)
								.email(email)
								.title(title)
								.content(content)
								.build();
		
		int rowCount = this.service.updateService(dto);
		if (rowCount ==1) System.out.printf("%d 게시글 수정 완료\n", seq);
		일시정지();
	}
//--------------------------------	
	private void 상세보기() {
		System.out.println(">게시글번호(seq)입력");
		int seq= this.scanner.nextInt();
		BoardDTO dto = this.service.viewService(seq); //해당 게시글 정보
		if(dto==null) {
			System.out.println("게시글이 존재하지 않습니당~");
			return;
		}
		//게시글 출력(view)담당
		System.out.println("\t ㄱ. 글번호 : "+seq);
		System.out.println("\t ㄴ. 작성자 : "+dto.getWriter());
		System.out.println("\t ㄷ. 조회수 : "+dto.getReaded());
		System.out.println("\t ㄹ. 글제목 : "+dto.getTitle());
		System.out.println("\t ㅁ. 글내용 : "+dto.getContent());
		System.out.println("\t ㅂ. 작성일 : "+dto.getWritedate());
		
		System.out.println("\t\n [수정] [삭제] [목록(home)]");
		일시정지();
		
	}
//--------------------------------	
	private void 목록보기() {
		System.out.println(">현제페이지(currentPage) 번호 입력");
		this.currentPage = this.scanner.nextInt();
//		ArrayList<BoardDTO> list =  this.service.selectService();
		ArrayList<BoardDTO> list =  this.service.selectService(currentPage ,numberPerPage);
		
		//view 출력담당
		System.out.println("\t\t\t\t 게시판");
		System.out.println("----------------------------------------------------------------------------");
		System.out.printf("%s\t  %-40s\t  %s\t%-10s\t%s\n","글번호","글제목","글쓴이","작성일","조회수");

		System.out.println("----------------------------------------------------------------------------");
		if (list==null) {
			System.out.println("\t\t게시글 없음");
		}	else {
			Iterator<BoardDTO> ir	= list.iterator();
			while (ir.hasNext()) {
				BoardDTO dto = ir.next();
				System.out.printf("%d\t%-30s \t%s\t %-10s\t%d\n"
						,dto.getSeq()
						,dto.getTitle()
						,dto.getWriter()
						,dto.getWritedate()
						,dto.getReaded());
			}
		}
		System.out.println("----------------------------------------------------------------------------");
//		System.out.println("\t\t\t [1] 2 3 4 5 6 7 8 9 10 >");
		String pagingBlock = this.service.pageService (
										this.currentPage
										,this.numberPerPage
										,this.numberOfPageBlock
				);
		System.out.println(pagingBlock);
		System.out.println("----------------------------------------------------------------------------");

		일시정지();
	}
	private void 새글쓰기() {
		System.out.println(">writer, pwd, email, title, tag, content 입력");
		//임경재,1234,im@sist.co.kr,제목,0,내용
		String [] datas = this.scanner.next().split(",");
		String writer= datas[0];
		String pwd= datas[1];
		String email= datas[2];
		String title= datas[3];
		int tag= Integer.parseInt( datas[4]);
		String content= datas[5];
		
		BoardDTO dto = BoardDTO.builder()
												.writer(writer)
												.pwd(pwd)
												.email(email)
												.title(title)
												.tag(tag)
												.content(content)
												.build();
		int rowCount = this.service.insertService(dto);
		if (rowCount ==1 ) System.out.println("새글 쓰기 완료");
		일시정지();		
	}
	private void 메뉴선택() {
		System.out.println(">메뉴 선택");
		this.selectedNumber = this.scanner.nextInt();
	}

	private void 메뉴출력() {
		String [] menus = {"새글"," 목록", "보기","수정","삭제","검색","종료"};
		System.out.println("[메뉴]");
		for (int i = 0; i < menus.length; i++) {
			System.out.printf("%d. %s\t ", i+1, menus[i]);

		}
		System.out.println();
	}
	private void 일시정지() {
		System.out.println(" \t\t 계속하려면 엔터치세요.");
		try {
			System.in.read();
			System.in.skip(System.in.available()); // 13, 10
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	private void exit() {
		DBConn.close();
		System.out.println("\t\t\t  프로그램 종료!!!");
		System.exit(-1);
	}

}
