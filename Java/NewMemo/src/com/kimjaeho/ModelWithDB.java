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
		// Ư����ǻ�͸� ã�� ���� �ּ� ü��
		// ������ = 213.112.142.132
		// url = naver.com
		// port = Ư�� ���α׷��� �Ҵ�Ǵ� ���� ����
		// 1~6������ / 2000���� ���� �̹� ǥ������ ���ǰ� �ִ�
		// ���� = ������ + ��Ʈ
		// ǥ���������� = http:// ������(�ּ�) : ��Ʈ(80)
		// Ư�� ���α׷��� �׼��� �ϱ����� �ּ�ü��
		// = �������� �̸�://������(�ּ�) : ��Ʈ
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");//����̹� �������� �ε�
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}

	public void create(Memo memo)
	{
		// 1. �����ͺ��̽� ����
		try(Connection con = DriverManager.getConnection(URL, ID, PW))
		{
			// 2. ������ ����
			// 2.1 ���� ����
			String query = " insert into memo(name,content.datetime)"
						   + " values(?,?,?)";
			// 2.2 ������ ���డ���� ���·� �����
			PreparedStatement pstmt = con.prepareStatement(query);
			// 2.3 ����ǥ�� ���� ����
			pstmt.setString(1, memo.name);
			pstmt.setString(2, memo.content);
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			// 2.4 ������ ����
			pstmt.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} // 3. �����ͺ��̽� ���� ����
	}
	
	public Memo read(int no)
	{
		Memo memo = new Memo();
		// 1. �����ͺ��̽� ����
		try(Connection con = DriverManager.getConnection(URL, ID, PW))
		{
			// 2. ������ ����
			// 2.1 ���� ����
			String query = "select * from memo where no = "+no;
			// 2.2 ������ ���డ���� ���·� �����
			Statement stmt = con.createStatement();
			// 2.3 select�� ������� �����ޱ� ���� ������ ����
			ResultSet rs = stmt.executeQuery(query);
			// ������� �ݺ��ϸ鼭 �ϳ��� ������
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
		} // 3. �����ͺ��̽� ���� ����
		return memo;
	}
	
	public ArrayList<Memo> getList()
	{
		ArrayList<Memo>list = new ArrayList<>();
		// 1. �����ͺ��̽� ����
		try(Connection con = DriverManager.getConnection(URL, ID, PW))
		{
			// 2. ������ ����
			// 2.1 ���� ����
			String query = "select * from memo";
			// 2.2 ������ ���డ���� ���·� �����
			java.sql.Statement stmt = con.createStatement();
			// 2.3 select�� ������� �����ޱ� ���� ������ ����
			ResultSet rs = stmt.executeQuery(query);
			// ������� �ݺ��ϸ鼭 �ϳ��� ������
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
		} // 3. �����ͺ��̽� ���� ����
		return list;
	}
	
	public void remove(int num)
	{

	}
}
