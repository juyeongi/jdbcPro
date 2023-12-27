package survey;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class SurveyDTO {
	
	private int survey_id;
	private String user_id;
	private Date start_date;
	private Date end_date;
	private Date regdate;
	private String title;
	private String state;
	private int survey_allcnt;
	private String content;
	private int  option_cnt;


	
}
