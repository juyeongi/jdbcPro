package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor	//생성자~
@Builder // 
@NoArgsConstructor	//default 생성자~
public class DeptEmpSalgradeVO {
	private int deptno;
	private String dname;
	private int empno ;
	private String ename;
	private double sal ;
	private int grade ;

}
