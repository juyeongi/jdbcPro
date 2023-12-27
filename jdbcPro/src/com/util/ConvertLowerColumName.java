package com.util;

public class ConvertLowerColumName {
	public static void main(String[] args) {
		String descEmp = "SURVEY_ID     NOT NULL NUMBER        \r\n"
				+ "USER_ID                NVARCHAR2(20) \r\n"
				+ "START_DATE    NOT NULL DATE          \r\n"
				+ "END_DATE      NOT NULL DATE          \r\n"
				+ "REGDATE                DATE          \r\n"
				+ "TITLE         NOT NULL VARCHAR2(100) \r\n"
				+ "STATE                  VARCHAR2(20)  \r\n"
				+ "SURVEY_ALLCNT          NUMBER(10)    \r\n"
				+ "CONTENT                VARCHAR2(300) \r\n"
				+ "OPTION_CNT             NUMBER(10)    ";
		String lowerDescEmp = descEmp.toLowerCase();
		String regex = "\\s+.+\\s+";
		
		lowerDescEmp=lowerDescEmp.replaceAll(regex, ";\r\n");
		
		System.out.println(lowerDescEmp);
			
	}//main
}//class
