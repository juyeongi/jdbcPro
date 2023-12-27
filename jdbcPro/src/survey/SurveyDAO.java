package survey;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.Board.domain.BoardDTO;

public interface SurveyDAO {

	ArrayList<SurveyDTO> select()throws SQLException;

	int insert(SurveyDTO dto) throws SQLException;

	

}
