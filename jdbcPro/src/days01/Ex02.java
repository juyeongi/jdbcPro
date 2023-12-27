package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex02 {
	public static void main(String[] args) {
//		1. JDBC 드라이버 로딩
//		2. DriverManager.getConnection()
//		3. Satatement CRUD
//		4. Connection Close()
		//javapro > build path > libraries > ojdbc6.jar 불러오기
 
		//db연결 확인
		
		String className= "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		
		Connection conn = null;
		try {
			Class.forName(className);		// (1)
			 conn = DriverManager.getConnection(url, user, password);		//(2)
			
			 //3. Satatement CRUD
			 
			 // 닫혔을 시 true,  <-> false
			System.out.println( conn.isClosed() );  //db연결 확인
			 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// main
}//class
