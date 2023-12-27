package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor	//생성자~
@Builder // 
@NoArgsConstructor	//default 생성자~
public class SalgradeVO {
	
	private int grade ;
	private int lowsal ;
	private int hisal ;
	private int cnt ;
}