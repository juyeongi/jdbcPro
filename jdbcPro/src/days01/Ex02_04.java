package days01;

import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

public class Ex02_04 {
	public static void main(String[] args) throws SQLException {
		
		//다른서버연결 
		String ipaddress = "192.168.19.167" ;   // IP주소
		String sid= "xe";
		String url = String.format("jdbc:oracle:thin:@%s:1521:%s" , ipaddress, sid) ;
		String user = "scott";
		String password = "tiger";
		
		Connection conn = DBConn.getConnection();
		
		System.out.println(conn.isClosed());
		
		//conn.close() x
		DBConn.close();
		
		System.out.println("end");
	}//main
}//class
