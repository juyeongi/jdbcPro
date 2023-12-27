package survey;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import days04.Board.domain.BoardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SurveyDAOImpl implements SurveyDAO{
	private Connection conn = null;

	@Override
	public ArrayList<SurveyDTO> select() {
		String sql = "select survey_id, title, user_id, regdate, start_date, end_date, survey_allcnt, state "
				+ "FROM survey "
				+ "ORDER BY survey_id DESC";
		ResultSet rs = null;
		PreparedStatement pstmt= null;
		ArrayList<SurveyDTO> list = null;
		SurveyDTO vo=null;
		
		int survey_id;
		String title;
		String user_id;
		Date regdate;
		Date start_date;
		Date end_date;
		String state;
		int survey_allcnt;
		
		try {
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			if (rs.next()) {
				list = new ArrayList<SurveyDTO>()	;
				do {
					survey_id= rs.getInt("survey_id");
					title = rs.getString("title");
					user_id = rs.getString("user_id");
					regdate = rs.getDate("regdate");
					start_date = rs.getDate("start_date");
					end_date = rs.getDate("end_date");
					survey_allcnt = rs.getInt("survey_allcnt");
					state = rs.getString("state");
					vo = SurveyDTO.builder().survey_id(survey_id)
														.title(title)
														.user_id(user_id)
														.regdate(regdate)
														.start_date(start_date)
														.end_date(end_date)
														.survey_allcnt(survey_allcnt)
														.state(state)
														.build();
					list.add(vo);
				} while (rs.next());
			}//if
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
// 새글쓰기-----------------------------------------------------------
	@Override
	public int insert(SurveyDTO dto) throws SQLException {
		int rowCount = 0;
		String sql = "INSERT INTO survey ( survey_id ,title, user_id, regdate , start_date,  end_date, content, option_cnt)  "
					+ " VALUES (?,?,?,?,?,?,?,? )";
		PreparedStatement pstmt = null;
		pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getSurvey_id() );
			pstmt.setString(2,dto.getTitle() );
			pstmt.setString(3, dto.getUser_id());
			pstmt.setDate(4, dto.getRegdate());
			pstmt.setDate(5, dto.getStart_date());
			pstmt.setDate(6, dto.getEnd_date());
			pstmt.setString(7, dto.getContent());
			pstmt.setInt(8, dto.getOption_cnt());
			rowCount = pstmt.executeUpdate();
		pstmt.close();
		return rowCount;
	}
//	1,좋아하는여배우,관리자,2023-03-01,2023-03-15,5

}
