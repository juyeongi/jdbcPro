package survey;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.management.loading.PrivateClassLoader;
import javax.tools.DocumentationTool.DocumentationTask;

import com.util.DBConn;

import days04.Board.service.BoardService;

public class SurveyController {
	private Scanner scanner;
	private int selectedNumber;
	private SurveyService service;
	
	private int currentPage = 1;
	private int numberPerPage = 10;
	private int numberOfPageBlock =10;
	
	public SurveyController() {
		this.scanner = new Scanner(System.in);
	}
	public SurveyController(SurveyService service) {
		this();
		this.service = service;
	}
	
	public void surveyStart(){
		while (true) {
			메뉴출력();
			메뉴선택();
			메뉴처리();
		}
	}

	private void 메뉴출력() {
		String[] menus = {"새글","목록"};
		System.out.println("[메뉴]");
		for (int i = 0; i < menus.length; i++) {
			System.out.printf("%d.%s\t",i+1,menus[i]);
		}
		System.out.println();
	}
	private void 메뉴선택() {
		System.out.println("> 메뉴 선택");
		this.selectedNumber = this.scanner.nextInt();
	}
	private void 메뉴처리() {
		switch (this.selectedNumber) {
		case 1:
			새글쓰기();
			break;
		case 2:
			목록보기();
			break;
		case 3:
			exit();
			break;
		}
	}

	//목록보기--------------------------------------------------------
	private void 목록보기() {
		System.out.println("현재페이지 번호 입력 ");
		this.currentPage = this.scanner.nextInt();
		ArrayList<SurveyDTO> list = this.service.selectService();
//		ArrayList<SurveyDTO> list = this.service.selectService(currentPage, numberPerPage)	;
		System.out.println("\t\t\t\t 게시판 ");
		System.out.println("-".repeat(70));
		System.out.printf("%s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t  ", "설문번호","질문","작성자","시작일","종료일","항목수","투표수","상태" );
		System.out.println();
		System.out.println("-".repeat(70));
		if (list==null) {
			System.out.println("\t\t 게시글없음");
		} else {
			Iterator<SurveyDTO>ir = list.iterator();
			while (ir.hasNext()) {
				SurveyDTO dto = ir.next();
				System.out.printf("%d\t %s\t %s\t %s\t %s\t %d\t %d\t %s\n"
						,dto.getSurvey_id()
						,dto.getTitle()
						,dto.getUser_id()
						,dto.getStart_date()
						,dto.getEnd_date()
						,dto.getOption_cnt()
						,dto.getSurvey_allcnt()
						,dto.getState());
				
			}
		}
		System.out.println("-".repeat(70));
		System.out.println("\t\t\t [1] 2 3 4 5 6 7 8 9 10 >");
//		String pagingBlock = this.service.pageService (
//				this.currentPage
//				,this.numberPerPage
//				,this.numberOfPageBlock
//				);
//		System.out.println(pagingBlock);
		System.out.println("-".repeat(70));
		일시정지();
	}
	//새글쓰기--------------------------------------------
	private void 새글쓰기() {
		System.out.println(" > survey_id ,title, user_id,  start_date,  end_date, option_cnt, content 입력");
		
		String [] datas = this.scanner.next().split(",");
		int survey_id= Integer.parseInt( datas[0]);
		String title = datas[1];
		String user_id = datas[2];
		int option_cnt= Integer.parseInt( datas[3]);

		
		SurveyDTO dto = SurveyDTO.builder()
				.survey_id(survey_id)
				.title(title)
				.user_id(user_id)
				.option_cnt(option_cnt)
				.build();
//1,좋아하는 과목,관리자,2023-09-01,2023-09-30,국영수,3,진행중
		//1,좋아하는여배우,관리자,5
		int rowCount = this.service.insertService(dto);
		if(rowCount == 1 ) System.out.println(" 설문조사 작성 완료 ");
		일시정지();
	}
	
	private void 일시정지() {
		System.out.println("\t\t계속하려면 엔터치세요");
		try {
			System.in.read();
			System.in.skip(System.in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	
	private void exit() {
		DBConn.close();
		System.out.println("\t\t\t 프로그램 종료");
		System.exit(-1);
	}
	
}