package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.EmpVO;

public class EmpDAOImpl implements EmpDAO{

	private PreparedStatement pstmt= null;
	private ResultSet rs = null;
	private Connection conn = null;

	//setter 함수를 통한 DI
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	//Constructor DI
	public EmpDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}


	// 조회 메서드
	public ArrayList<EmpVO> getSelect() {
		ArrayList<EmpVO> list = null;

		String sql ="SELECT * FROM emp ";

		int empno ;
		String ename;
		String job;
		int mgr;
		String hiredate ;
		double sal ;
		double comm ;
		int deptno;
		
		EmpVO vo = null;

		try {
			pstmt = conn.prepareStatement(sql); 

			rs= pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<EmpVO>();
				do {
					empno = rs.getInt("empno") ;
					ename=rs.getString("ename");
					job= rs.getString("job");
					mgr= rs.getInt("mgr");
					hiredate = rs.getString("hiredate");
					sal =rs.getDouble ( "sal");
					comm=rs.getDouble ( "comm");
					deptno = rs.getInt("deptno");
					vo= new EmpVO( empno, ename,job,mgr,hiredate,sal,comm, deptno);
					list.add(vo);
				}while(rs.next());
			}//if
			//			PrintEmpList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}

		return list;
	}
	// 검색
	public ArrayList<EmpVO >getSelect(int searchCondition , String searchWord){
		ArrayList<EmpVO> list = null;

		String sql ="SELECT * FROM emp WHERE 	";

		if ( searchCondition == 1 ) { // 사원명
			sql += String.format(" REGEXP_LIKE (ename, '%s', 'i')", searchWord);
		}
		else if ( searchCondition == 2 ) {  // 부서번호
			sql += String.format(" deptno IN (%s)", searchWord);         
		} else if ( searchCondition == 3 ) { // 잡
			sql += String.format(" REGEXP_LIKE (job, '%s', 'i')", searchWord);
		} // if// if

		System.out.println(sql);

		int empno ;
		String ename;
		String job;
		int mgr;
		String hiredate ;
		double sal ;
		double comm ;
		int deptno;

		EmpVO vo = null;

		try {
			pstmt=  conn.prepareStatement(sql); 

			rs= pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<EmpVO>();
				do {
					empno = rs.getInt("empno") ;
					ename=rs.getString("ename");
					job= rs.getString("job");
					mgr= rs.getInt("mgr");
					hiredate = rs.getString("hiredate");
					sal =rs.getDouble ( "sal");
					comm=rs.getDouble ( "comm");
					deptno = rs.getInt("deptno");
					vo= new EmpVO( empno, ename,job,mgr,hiredate,sal,comm, deptno);
					list.add(vo);
				}while(rs.next());
			}//if
			//			PrintEmpList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}

		return list;
	}
	// 추가
	public int add(EmpVO vo ) {
		int rowCount = 0;
		String sql = 
	            String.format("INSERT INTO emp ( empno, ename, job, mgr, hiredate, sal, comm, deptno ) "
	                  + " VALUES ( %d, '%s', '%s', %d, '%s', %f, %f, %d)"
	                  , vo.getEmpno(), vo.getEname(), vo.getJob(), vo.getMgr()
	                  , vo.getHiredate(), vo.getSal(), vo.getComm(), vo.getDeptno() );

	      try {
	         pstmt = conn.prepareStatement(sql); 
	         rowCount = pstmt.executeUpdate();

	      } catch (SQLException e) { 
	         e.printStackTrace();
	      } finally {
	         try {
	            pstmt.close();
	         } catch (SQLException e) { 
	            e.printStackTrace();
	         }
	      }
		return rowCount;
	}
	// 수정
	// 수정할 사원 정보 불러오는 함수
	public EmpVO get(int empno) { 
		String sql ="SELECT * FROM emp WHERE empno=	 "+empno;

		System.out.println(sql);

		String ename;
		String job;
		int mgr;
		String hiredate ;
		double sal ;
		double comm ;
		int deptno;

		EmpVO vo = null;

		try {
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();

			if (rs.next()) {

					empno = rs.getInt("empno") ;
					ename=rs.getString("ename");
					job= rs.getString("job");
					mgr= rs.getInt("mgr");
					hiredate = rs.getString("hiredate");
					sal =rs.getDouble ( "sal");
					comm=rs.getDouble ( "comm");
					deptno = rs.getInt("deptno");
					vo= new EmpVO( empno, ename,job,mgr,hiredate,sal,comm, deptno);

			}//if
			//			PrintEmpList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}

		return vo;
	}
	//사원정보 수정 함수
	public int update(EmpVO vo) {
		int rowCount = 0;
		String sql = 
	            String.format("UPDATE emp "
	            		+ "SET ename ='%s' , job='%s', mgr=%d, sal=%f, comm=%f, deptno=%d "
	            		+ " WHERE empno = %d"
	                , vo.getEname(), vo.getJob(), vo.getMgr()
	                  , vo.getSal(), vo.getComm(), vo.getDeptno(), vo.getEmpno());

	      try {
	         pstmt = conn.prepareStatement(sql); 
	         rowCount = pstmt.executeUpdate();

	      } catch (SQLException e) { 
	         e.printStackTrace();
	      } finally {
	         try {
	            pstmt.close();
	         } catch (SQLException e) { 
	            e.printStackTrace();
	         }
	      }
		return rowCount;
	}
	
	// 삭제
	public int delete (int empno) {
		int rowCount = 0;
		String sql = 
				String.format("DELETE FROM emp WHERE empno =%d", empno) ;
	      try {
	         pstmt = conn.prepareStatement(sql); 
	         rowCount = pstmt.executeUpdate();

	      } catch (SQLException e) { 
	         e.printStackTrace();
	      } finally {
	         try {
	            pstmt.close();
	         } catch (SQLException e) { 
	            e.printStackTrace();
	         }
	      }
		return rowCount;
	}
}





/*
public class EmpDAOimpl implements EmpDAO{

	private Statement stmt= null;
	private ResultSet rs = null;
	private Connection conn = null;

	//setter 함수를 통한 DI
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	//Constructor DI
	public EmpDAOimpl(Connection conn) {
		super();
		this.conn = conn;
	}


	// 조회 메서드
	public ArrayList<EmpVO> getSelect() {
		ArrayList<EmpVO> list = null;

		String sql ="SELECT * FROM emp ";

		int empno ;
		String ename;
		String job;
		int mgr;
		String hiredate ;
		double sal ;
		double comm ;
		int deptno;
		
		EmpVO vo = null;

		try {
			stmt= conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);

			rs= stmt.executeQuery(sql);

			if (rs.next()) {
				list = new ArrayList<EmpVO>();
				do {
					empno = rs.getInt("empno") ;
					ename=rs.getString("ename");
					job= rs.getString("job");
					mgr= rs.getInt("mgr");
					hiredate = rs.getString("hiredate");
					sal =rs.getDouble ( "sal");
					comm=rs.getDouble ( "comm");
					deptno = rs.getInt("deptno");
					vo= new EmpVO( empno, ename,job,mgr,hiredate,sal,comm, deptno);
					list.add(vo);
				}while(rs.next());
			}//if
			//			PrintEmpList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}

		return list;
	}
	// 검색
	public ArrayList<EmpVO >getSelect(int searchCondition , String searchWord){
		ArrayList<EmpVO> list = null;

		String sql ="SELECT * FROM emp WHERE 	";

		if ( searchCondition == 1 ) { // 사원명
			sql += String.format(" REGEXP_LIKE (ename, '%s', 'i')", searchWord);
		}
		else if ( searchCondition == 2 ) {  // 부서번호
			sql += String.format(" deptno IN (%s)", searchWord);         
		} else if ( searchCondition == 3 ) { // 잡
			sql += String.format(" REGEXP_LIKE (job, '%s', 'i')", searchWord);
		} // if// if

		System.out.println(sql);

		int empno ;
		String ename;
		String job;
		int mgr;
		String hiredate ;
		double sal ;
		double comm ;
		int deptno;

		EmpVO vo = null;

		try {
			stmt= conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);

			rs= stmt.executeQuery(sql);

			if (rs.next()) {
				list = new ArrayList<EmpVO>();
				do {
					empno = rs.getInt("empno") ;
					ename=rs.getString("ename");
					job= rs.getString("job");
					mgr= rs.getInt("mgr");
					hiredate = rs.getString("hiredate");
					sal =rs.getDouble ( "sal");
					comm=rs.getDouble ( "comm");
					deptno = rs.getInt("deptno");
					vo= new EmpVO( empno, ename,job,mgr,hiredate,sal,comm, deptno);
					list.add(vo);
				}while(rs.next());
			}//if
			//			PrintEmpList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}

		return list;
	}
	// 추가
	public int add(EmpVO vo ) {
		int rowCount = 0;
		String sql = 
	            String.format("INSERT INTO emp ( empno, ename, job, mgr, hiredate, sal, comm, deptno ) "
	                  + " VALUES ( %d, '%s', '%s', %d, '%s', %f, %f, %d)"
	                  , vo.getEmpno(), vo.getEname(), vo.getJob(), vo.getMgr()
	                  , vo.getHiredate(), vo.getSal(), vo.getComm(), vo.getDeptno() );

	      try {
	         stmt = conn.createStatement(); 
	         rowCount = stmt.executeUpdate(sql);

	      } catch (SQLException e) { 
	         e.printStackTrace();
	      } finally {
	         try {
	            stmt.close();
	         } catch (SQLException e) { 
	            e.printStackTrace();
	         }
	      }
		return rowCount;
	}
	// 수정
	// 수정할 사원 정보 불러오는 함수
	public EmpVO get(int empno) { 
		String sql ="SELECT * FROM emp WHERE empno=	 "+empno;

		System.out.println(sql);

		String ename;
		String job;
		int mgr;
		String hiredate ;
		double sal ;
		double comm ;
		int deptno;

		EmpVO vo = null;

		try {
			stmt= conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			rs= stmt.executeQuery(sql);

			if (rs.next()) {

					empno = rs.getInt("empno") ;
					ename=rs.getString("ename");
					job= rs.getString("job");
					mgr= rs.getInt("mgr");
					hiredate = rs.getString("hiredate");
					sal =rs.getDouble ( "sal");
					comm=rs.getDouble ( "comm");
					deptno = rs.getInt("deptno");
					vo= new EmpVO( empno, ename,job,mgr,hiredate,sal,comm, deptno);

			}//if
			//			PrintEmpList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}

		return vo;
	}
	//사원정보 수정 함수
	public int update(EmpVO vo) {
		int rowCount = 0;
		String sql = 
	            String.format("UPDATE emp "
	            		+ "SET ename ='%s' , job='%s', mgr=%d, sal=%f, comm=%f, deptno=%d "
	            		+ " WHERE empno = %d"
	                , vo.getEname(), vo.getJob(), vo.getMgr()
	                  , vo.getSal(), vo.getComm(), vo.getDeptno(), vo.getEmpno());

	      try {
	         stmt = conn.createStatement(); 
	         rowCount = stmt.executeUpdate(sql);

	      } catch (SQLException e) { 
	         e.printStackTrace();
	      } finally {
	         try {
	            stmt.close();
	         } catch (SQLException e) { 
	            e.printStackTrace();
	         }
	      }
		return rowCount;
	}
	
	// 삭제
	public int delete (int empno) {
		int rowCount = 0;
		String sql = 
				String.format("DELETE FROM emp WHERE empno =%d", empno) ;
	      try {
	         stmt = conn.createStatement(); 
	         rowCount = stmt.executeUpdate(sql);

	      } catch (SQLException e) { 
	         e.printStackTrace();
	      } finally {
	         try {
	            stmt.close();
	         } catch (SQLException e) { 
	            e.printStackTrace();
	         }
	      }
		return rowCount;
	}
}
*/