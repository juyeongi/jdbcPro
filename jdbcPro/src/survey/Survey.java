package survey;

import java.sql.Connection;

import com.util.DBConn;

import days04.Board.controller.BoardController;
import days04.Board.persistence.BoardDAO;
import days04.Board.persistence.BoardDAOImpl;
import days04.Board.service.BoardService;

public class Survey {
	public static void main(String[] args) {
		Connection conn = DBConn.getConnection()	;
		SurveyDAO dao = new SurveyDAOImpl(conn);
		SurveyService service = new SurveyService (dao);
		SurveyController controller = new SurveyController(service);
		
		controller.surveyStart();
	}

}
