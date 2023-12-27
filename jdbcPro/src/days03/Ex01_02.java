package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.util.DBConn;

import domain.DeptEmpSalgradeVO;
import domain.SalgradeVO;

public class Ex01_02 {
	/*
    [실행결과]
    1등급   (     700~1200 ) - 2명
          20   RESEARCH   7369   SMITH   800
          30   SALES         7900   JAMES   950
    2등급   (   1201~1400 ) - 2명
       30   SALES   7654   MARTIN   2650
       30   SALES   7521   WARD      1750   
    3등급   (   1401~2000 ) - 2명
       30   SALES   7499   ALLEN      1900
       30   SALES   7844   TURNER   1500
    4등급   (   2001~3000 ) - 4명
        10   ACCOUNTING   7782   CLARK   2450
       20   RESEARCH   7902   FORD   3000
       20   RESEARCH   7566   JONES   2975
       30   SALES   7698   BLAKE   2850
    5등급   (   3001~9999 ) - 1명   
       10   ACCOUNTING   7839   KING   5000
	 */

	/*
	 * 등급 
SELECT grade, losal, hisal, COUNT (*) cnt
FROM salgrade s JOIN emp e ON sal BETWEEN losal AND hisal
GROUP BY grade, losal, hisal 
ORDER BY grade;
	 */
	/*
 SELECT d.deptno , dname, ename, sal, grade
FROM dept d RIGHT JOIN emp e ON d.deptno = e.deptno
    JOIN salgrade s ON sal BETWEEN losal AND hisal
WHERE grade =1;

	 */
	//@builder
	/*
	 * 1)
			SalgradeVO vo = new SalgradeVO();
			vo.setGrade(1);
			vo.setLowsal(1000);
			vo.setHisal(2000);
			vo.setCnt(2);
	 */
	//2)
	//SalgradeVO vo = new SalgradeVO(1, 1000,2000, 2);
	/*
	 * 3)
			SalgradeVO vo = SalgradeVO.builder()
														.grade(1)
														.lowsal(1000)
														.
														.build();
	 */
	public static void main(String[] args) {
		//등급
		String sql = "SELECT grade, losal, hisal, COUNT (*) cnt "
				+ "FROM salgrade s JOIN emp e ON sal BETWEEN losal AND hisal "
				+ "GROUP BY grade, losal, hisal "
				+ "ORDER BY grade ASC";
		//emp정보
		String empsql = "SELECT d.deptno , dname, empno, ename, sal "
				+ "FROM dept d RIGHT JOIN emp e ON d.deptno = e.deptno "
				+ "    JOIN salgrade s ON sal BETWEEN losal AND hisal "
				+ "WHERE grade =? ";

		Connection conn = null;
		ResultSet rs = null, emprs = null;
		PreparedStatement pstmt = null, emppstmt = null;
		LinkedHashMap<SalgradeVO, ArrayList<DeptEmpSalgradeVO>> map = new LinkedHashMap<>();
		SalgradeVO vo= null;  //key
		ArrayList<DeptEmpSalgradeVO> emplist = null;  //value
		DeptEmpSalgradeVO empvo= null;

		int deptno;
		String dname;
		int empno ;
		String ename;
		double sal ;
		int grade ;

		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt sql ? 파라미터값 유무 확인
			rs=pstmt.executeQuery();

			if (rs.next()) {
				do {
					grade =rs.getInt(1);
					// vo   key
					vo= new SalgradeVO(rs.getInt(1)
							,rs.getInt(2)
							,rs.getInt(3)
							,rs.getInt(4));
					//empsql 실행
					emppstmt = conn.prepareStatement(empsql);
					emppstmt.setInt(1, grade);
					emprs = emppstmt.executeQuery();

					if (emprs.next()) {
						emplist = new ArrayList<DeptEmpSalgradeVO>();
						do {
							deptno = emprs.getInt(1);
							dname = emprs.getString(2);
							empno = emprs.getInt(3);
							ename = emprs.getString(4);
							sal = emprs.getDouble(5) ;

							empvo = new DeptEmpSalgradeVO(deptno, dname, empno, ename, sal, grade);
							//value
							emplist.add(empvo);

						} while (emprs.next());
						map.put(vo, emplist);

						emprs.close();
						emppstmt.close();
					}//if

				} while (rs.next());
				printSalgrade(map);
			}//if
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close(); 
				pstmt.close(); 
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end");
	}

	private static void printSalgrade(LinkedHashMap<SalgradeVO, ArrayList<DeptEmpSalgradeVO>> map) {
		Set<Entry<SalgradeVO, ArrayList<DeptEmpSalgradeVO>>> set =  map.entrySet(); //key , value 집합
		Iterator<Entry<SalgradeVO, ArrayList<DeptEmpSalgradeVO>>> ir = set.iterator();
		while (ir.hasNext()) {
			Entry<SalgradeVO,ArrayList<DeptEmpSalgradeVO>> entry =ir.next();
			SalgradeVO vo = entry.getKey();
			ArrayList<DeptEmpSalgradeVO> list = entry.getValue();
			System.out.printf("%d등급 ( %d~%d)- %d 명\n",
					vo.getGrade(), vo.getLowsal(), vo.getHisal(), vo.getCnt());
			//20   RESEARCH   7369   SMITH   800
			Iterator<DeptEmpSalgradeVO> empir = list.iterator();
			while (empir.hasNext()) {
				DeptEmpSalgradeVO empvo = empir.next();
				System.out.printf("\t\t%d\t%s\t%d\t%s\t%.2f\n"
						,empvo.getDeptno(), empvo.getDname(), empvo.getEmpno(),empvo.getEname(), empvo.getSal());
			}//while
		}//while
	}

	private static void printSalgrade(ArrayList<SalgradeVO> list) {
		Iterator<SalgradeVO> ir = list.iterator();
		while (ir.hasNext()) {
			SalgradeVO vo = (SalgradeVO) ir.next();
			System.out.printf("%d등급 ( %d~%d)- %d 명\n",
					vo.getGrade(), vo.getLowsal(), vo.getHisal(), vo.getCnt());
		}//while
	}
}//class
