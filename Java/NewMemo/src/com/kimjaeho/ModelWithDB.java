package com.kimjaeho;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


public class ModelWithDB {
	
	private static final String URL = "jdbc:mysql://localhost:3306/memo";
	private static final String ID = "root";
	private static final String PW = "dream9025";
	Connection con = null;
	
	public ModelWithDB()
	{
		// 특정컴퓨터를 찾기 위한 주소 체계
		// 아이피 = 213.112.142.132
		// url = naver.com
		// port = 특정 프로그램에 할당되는 세부 번지
		// 1~6만번대 / 2000번대 밑은 이미 표준으로 사용되고 있다
		// 소켓 = 아이피 + 포트
		// 표준프로토콜 = http:// 아이피(주소) : 포트(80)
		// 특정 프로그램에 액세스 하기위한 주소체계
		// = 프로토콜 이름://아이피(주소) : 포트
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");//드라이버 동적으로 로드
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}

	public void create(Memo memo)
	{
		// 1. 데이터베이스 연결
		try(Connection con = DriverManager.getConnection(URL, ID, PW))
		{
			// 2. 쿼리를 실행
			// 2.1 쿼리 생성
			String query = " insert into memo(name,content.datetime)"
						   + " values(?,?,?)";
			// 2.2 쿼리를 실행가능한 상태로 만들기
			PreparedStatement pstmt = con.prepareStatement(query);
			// 2.3 물음표에 값을 세팅
			pstmt.setString(1, memo.name);
			pstmt.setString(2, memo.content);
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			// 2.4 쿼리를 실행
			pstmt.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} // 3. 데이터베이스 연결 해제
	}
	
	public Memo read(int no)
	{
		Memo memo = new Memo();
		// 1. 데이터베이스 연결
		try(Connection con = DriverManager.getConnection(URL, ID, PW))
		{
			// 2. 쿼리를 실행
			// 2.1 쿼리 생성
			String query = "select * from memo where no = "+no;
			// 2.2 쿼리를 실행가능한 상태로 만들기
			Statement stmt = con.createStatement();
			// 2.3 select한 결과값을 돌려받기 위해 쿼리를 실행
			ResultSet rs = stmt.executeQuery(query);
			// 결과셋을 반복하면서 하나씩 꺼내기
			if(rs.next())
			{
				memo.no = rs.getInt("no");
				memo.name = rs.getString("name");
				memo.content = rs.getString("content");
				memo.datetime = rs.getLong("datetime");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} // 3. 데이터베이스 연결 해제
		return memo;
	}
	
	public ArrayList<Memo> getList()
	{
		ArrayList<Memo>list = new ArrayList<>();
		// 1. 데이터베이스 연결
		try(Connection con = DriverManager.getConnection(URL, ID, PW))
		{
			// 2. 쿼리를 실행
			// 2.1 쿼리 생성
			String query = "select * from memo";
			// 2.2 쿼리를 실행가능한 상태로 만들기
			java.sql.Statement stmt = con.createStatement();
			// 2.3 select한 결과값을 돌려받기 위해 쿼리를 실행
			ResultSet rs = stmt.executeQuery(query);
			// 결과셋을 반복하면서 하나씩 꺼내기
			while(rs.next())
			{
				Memo memo = new Memo();
				memo.no = rs.getInt("no");
				memo.name = rs.getString("name");
				memo.content = rs.getString("content");
				memo.datetime = rs.getLong("datetime");
				list.add(memo);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} // 3. 데이터베이스 연결 해제
		return list;
	}
	
	public void remove(int num)
	{

	}
}
