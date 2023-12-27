package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.SalgradeVO;

public class Ex07 {
	
	//[Reflection 리플렉션]
	// 반사, 상, 반영
	//JDBC 리플렉션 : 결과물 (rs)에 대한 정보를 추출해서 사용할 수 있는 기술
	
	public static void main(String[] args) {
		String sql = "SELECT  table_name "
				+ " FROM tabs";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<String> tnList = null;
		String tableName = null;
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				tnList = new ArrayList<String>();
				do {
					tableName = rs.getString(1);
					tnList.add(tableName);
				} while (rs.next());
				printTableName(tnList);
			}//if
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//
		// 2)
		Scanner scanner = new Scanner(System.in);
		System.out.println("테이블명 입력");
		tableName = scanner.next();
		
		sql = String.format("SELECT * FROM %s", tableName);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			// 결과물 정보 얻어오는 객체
			ResultSetMetaData rsmd = rs.getMetaData();
			// 컬럼 수 파악 객체  .getColumnCount()
//			System.out.println(">컬럼 수 : " +rsmd.getColumnCount());
			//컬럼이름/타입/타입명/Precision  출력
			int columnCount = rsmd.getColumnCount();
			/*
			for (int i = 1; i < columnCount; i++) {
				String columnName =rsmd.getColumnName(i);
				int columnType = rsmd.getColumnType(i);
				String columnTypeName = rsmd.getColumnTypeName(i);
				//NUMBER ( p, s )
				int p = rsmd.getPrecision(i);
				int s = rsmd.getScale(i);
				
				System.out.println(columnName + "/" + columnType+ "/" + columnTypeName
						+ "(" + p+ "," + s +")");
			}
			*/
			System.out.println("-".repeat(10*columnCount));
			
			for (int i = 1; i < columnCount; i++) {
				System.out.printf("%s     ", rsmd.getColumnName(i));
			}
			System.out.println();
			System.out.println("-".repeat(10*columnCount));
			//rs 출력
			
			if (rs.next()) {
				do {
					//rs.getInt(columnCount);
					for (int i = 1; i < columnCount; i++) {
						int columnType = rsmd.getColumnType(i);
						int p = rsmd.getPrecision(i);
						int s = rsmd.getScale(i);
						if (columnType==2&&s==0) {  //NUMBER
							System.out.printf("%d / ",rs.getInt(i));
						}else if (columnType==2&&s !=0){
							System.out.printf("%.2f / ",rs.getDouble(i));
						}else if (columnType==12){
							System.out.printf("%s / ",rs.getString(i));
						}else if (columnType==93){
							System.out.printf("%tF / ",rs.getDate(i));	
						}//if
						
					}//for	
					System.out.println();
					
				} while (rs.next());
				
			} else {
				System.out.println("레코드 존재 x");
			}
			
			System.out.println("-".repeat(10*columnCount));
			/*
			if (rs.next()) {

				do {

				} while (rs.next());

			}//if
			*/
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
		DBConn.close();
		System.out.println("end");

	}//mian

	private static void printTableName(ArrayList<String> tnList) {
		System.out.println("[scott의 테이블 목록]");
		Iterator<String> ir =tnList.iterator();
		int count =1;
		while (ir.hasNext()) {
			String tableName = ir.next();
			System.out.printf("    %d.%s ", count ,tableName);
			if (count %5==0) System.out.println();	
			count++;
			}
		}
		

}//class
