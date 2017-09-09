package com.kimjaeho;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Model {
	//전체 메모를 저장하는 저장소
		ArrayList<Memo> list = new ArrayList<>();
		//마지막 글번호
		
		//데이터베이스 주소 : 상수
		private final String DB_DIR = "G:\\Programming\\Java\\Database";
		private final String DB_FILE = "memo.txt";
		private File database = null;
		//번호데이터베이스 주소 : 상수
		private final String NO_DIR = "G:\\Programming\\Java\\Database";
		private final String NO_FILE = "number.txt";
		private File noDatabase = null;
		

		//new하는 순간 이 영역이 실행된다
		public Model()
		{
			File dir = new File(DB_DIR);
			File dir2 = new File(NO_DIR);
			// 디렉토리 존재 유무 체크
			if(dir.exists()&&dir2.exists())
			{
				dir.mkdirs(); // 경로상에 디렉토리가 없으면 자동생성
				//dir.mkdir() -> 경로상에 비어있는게 있으면 에러
			}
			// seperator = OS마다 다른 디렉토리 사이에 있는 슬래쉬 추가해주는 것(\\)
			File file = new File(DB_DIR + File.separator + DB_FILE);
			File file2 = new File(NO_DIR + File.separator + NO_FILE);
			// 파일의 존재유무
			if(!file.exists()||!file.exists())
			{
				try 
				{
					file.createNewFile();
					file2.createNewFile();
				}
				catch(Exception e) 
				{
					e.printStackTrace();
				}
			}
			database = file;
			noDatabase = file2;
		}

		// 내용을 구분하기 위한 :: 을 상수로 선언
		private final String COLUMN_SEP = "::";
		
		public void create(Memo memo)
		{
			//Number파일 읽어오기
			try(FileInputStream fis = new FileInputStream(noDatabase)) 
			{
				//2. 실제 file encoding을 바꿔주는 래퍼클래스를 삿용
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				//3. 버퍼처리
				BufferedReader br = new BufferedReader(isr);
				String row;
				// 새로운 줄을 한줄씩 읽어서 row에 저장하고
				// row가 null값이 될 때 까지 읽기 그리고 종료
				while((row = br.readLine()) != null)
				{	//set으로 만들어버릴까?
					String tempRow[]=row.split("\n");
					int length = tempRow.length;
					memo.no = length;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//쓰는(output) Stream을 선언
			FileOutputStream fos = null;
			FileOutputStream fos2 = null;
			
			try 
			{
				//1. 쓰는(output) Stream을 연다
				fos = new FileOutputStream(database, true);
				fos2 = new FileOutputStream(noDatabase, true);
				//2. Stream을 중간처리(텍스트 enconding 변경) -> 래퍼 Stream
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				OutputStreamWriter osw2 = new OutputStreamWriter(fos2);
				//3. 버퍼처리
				BufferedWriter bw = new BufferedWriter(osw);
				BufferedWriter bw2 = new BufferedWriter(osw2);
				//저장할 내용을 구분자로 분리하여 한줄의 문자열로 바꾼다.
				String row = memo.no + COLUMN_SEP + memo.name + COLUMN_SEP + memo.content + COLUMN_SEP + memo.datetime + "\n";
				String number = memo.no + "\n";
				// 내용 넣어주기(안끄면 버퍼 다 찰 때까지 계속 저장만함)
				bw.append(row);
				bw2.append(number);
				//버퍼 내용을 강제로 내보내기
				bw.flush();
				bw2.flush();
				// bw.close() -> 버퍼 끄기 
			} 
			/* <원래는 1번 & append 예외처리 각각 해줘야하지만> 상위인 Exception으로 해결
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			*/
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			// Stream 끄기 -> finally쓰는 이유는 catch에 오류 잡히면 안꺼질 수도 있어서
			finally
			{
				//Stream이 생성되기 전에 오류가 발생할 수도 있으므로 null 체크
				if(fos != null)
				{
					try 
					{
						fos.close();
					} 
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		public Memo read(int num)
		{
			try(FileInputStream fis = new FileInputStream(database)) 
			{
				//2. 실제 file encoding을 바꿔주는 래퍼클래스를 삿용
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				//3. 버퍼처리
				BufferedReader br = new BufferedReader(isr);
				
				String row;
				// 새로운 줄을 한줄씩 읽어서 row에 저장하고
				// row가 null값이 될 때 까지 읽기 그리고 종료
				while((row = br.readLine()) != null)
				{
					String tempRow[] = row.split(COLUMN_SEP);
					Memo memo = new Memo();
					memo.no = Integer.parseInt(tempRow[0]);
					memo.name = tempRow[1];
					memo.content = tempRow[2];
					memo.datetime = Long.parseLong(tempRow[3]);
					if(memo.no==num)
					{
						return memo;
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
		public ArrayList<Memo> getList()
		{
			//데이터가 중복으로 쌓이지 않도록 저장소를 지워주는 작업이 필요하다(누군가가 불러오기 계속 클릭 할 시)
			list.clear();
			
			//1. 읽는 스트림을 연다
			//java8에서 바뀐 방법
			//이렇게 하면 stream을 따로 끌 필요가 없다!!!
			//try-with 절에서 자동으로 fis.close가 발생
			try(FileInputStream fis = new FileInputStream(database)) 
			{
				//2. 실제 file encoding을 바꿔주는 래퍼클래스를 삿용
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				//3. 버퍼처리
				BufferedReader br = new BufferedReader(isr);
				
				String row;
				// 새로운 줄을 한줄씩 읽어서 row에 저장하고
				// row가 null값이 될 때 까지 읽기 그리고 종료
				while((row = br.readLine()) != null)
				{
					String tempRow[] = row.split(COLUMN_SEP);
					Memo memo = new Memo();
					memo.no = Integer.parseInt(tempRow[0]);
					memo.name = tempRow[1];
					memo.content = tempRow[2];
					memo.datetime = Long.parseLong(tempRow[3]);
					
					list.add(memo);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return list;
		}
		
		public void remove(int num)
		{
			int deleteIndex = -1;
			for(int i = 0; i < list.size(); i++) {  //index를 알기 위해서 for문 사용
				if(list.get(i).no == num) {
					deleteIndex = i;
					break;
				}
			}
			if(deleteIndex >= 0) list.remove(deleteIndex);
		}
}
