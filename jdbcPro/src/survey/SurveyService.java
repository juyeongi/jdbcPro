package survey;

import java.sql.SQLException;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class SurveyService {
	private SurveyDAO dao = null;
	
	//설문조사 목록 조회 	
	public ArrayList<SurveyDTO> selectService() {
	ArrayList<SurveyDTO> list = null;
	System.out.println("> 설문조사 목록조회 '로그기록' ");
	
	try {
		list = this.dao.select();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return list;
}
	

	public ArrayList<SurveyDTO> selectService(int currentPage, int numberPerPage) {
		ArrayList<SurveyDTO> list = null;
		System.out.println("> 설문조사 목록조회 '로그기록' ");
		
		try {
			list = this.dao.select();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}


	public int insertService(SurveyDTO dto) {
		int rowCount = 0;
		System.out.println(">설문조사 쓰기 '로그작업' ");
		try {
			rowCount = this.dao.insert(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

}
