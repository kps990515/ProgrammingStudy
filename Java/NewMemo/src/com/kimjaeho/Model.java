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

public class Model {
	//��ü �޸� �����ϴ� �����
		ArrayList<Memo> list = new ArrayList<>();
		//������ �۹�ȣ
		int lastIndex = 1;
		
		//�����ͺ��̽� �ּ� : ���
		private final String DB_DIR = "G:\\Programming\\Java\\Database";
		private final String DB_FILE = "memo.txt";
		private File database = null;

		//new�ϴ� ���� �� ������ ����ȴ�
		public Model()
		{
			File dir = new File(DB_DIR);
			// ���丮 ���� ���� üũ
			if(dir.exists())
			{
				dir.mkdirs(); // ��λ� ���丮�� ������ �ڵ�����
				//dir.mkdir() -> ��λ� ����ִ°� ������ ����
			}
			// seperator = OS���� �ٸ� ���丮 ���̿� �ִ� ������ �߰����ִ� ��(\\)
			File file = new File(DB_DIR + File.separator + DB_FILE);
			// ������ ��������
			if(!file.exists())
			{
				try 
				{
					file.createNewFile();
				}
				catch(Exception e) 
				{
					e.printStackTrace();
				}
			}
			database = file;
		}

		// ������ �����ϱ� ���� :: �� ����� ����
		private final String COLUMN_SEP = "::";
		
		public void create(Memo memo)
		{
			//�۹�ȣ
			memo.no = lastIndex++;

			//����(output) Stream�� ����
			FileOutputStream fos = null;
			
			try 
			{
				//1. ����(output) Stream�� ����
				fos = new FileOutputStream(database, true);
				//2. Stream�� �߰�ó��(�ؽ�Ʈ enconding ����) -> ���� Stream
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				//3. ����ó��
				BufferedWriter bw = new BufferedWriter(osw);
				
				//������ ������ �����ڷ� �и��Ͽ� ������ ���ڿ��� �ٲ۴�.
				String row = memo.no + COLUMN_SEP + memo.name + COLUMN_SEP + memo.content + COLUMN_SEP + memo.datetime + "\n";
				// ���� �־��ֱ�(�Ȳ��� ���� �� �� ������ ��� ���常��)
				bw.append(row);
				//���� ������ ������ ��������
				bw.flush();
				// bw.close() -> ���� ���� 
			} 
			/* <������ 1�� & append ����ó�� ���� �����������> ������ Exception���� �ذ�
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
			
			// Stream ���� -> finally���� ������ catch�� ���� ������ �Ȳ��� ���� �־
			finally
			{
				//Stream�� �����Ǳ� ���� ������ �߻��� ���� �����Ƿ� null üũ
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
		
		public ArrayList<Memo> getList()
		{
			//�����Ͱ� �ߺ����� ������ �ʵ��� ����Ҹ� �����ִ� �۾��� �ʿ��ϴ�(�������� �ҷ����� ��� Ŭ�� �� ��)
			list.clear();
			
			//1. �д� ��Ʈ���� ����
			//java8���� �ٲ� ���
			//�̷��� �ϸ� stream�� ���� �� �ʿ䰡 ����!!!
			//try-with ������ �ڵ����� fis.close�� �߻�
			try(FileInputStream fis = new FileInputStream(database)) 
			{
				//2. ���� file encoding�� �ٲ��ִ� ����Ŭ������ ���
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				//3. ����ó��
				BufferedReader br = new BufferedReader(isr);
				
				String row;
				// ���ο� ���� ���پ� �о row�� �����ϰ�
				// row�� null���� �� �� ���� �б� �׸��� ����
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
			for(int i = 0; i < list.size(); i++) {  //index�� �˱� ���ؼ� for�� ���
				if(list.get(i).no == num) {
					deleteIndex = i;
					break;
				}
			}
			if(deleteIndex >= 0) list.remove(deleteIndex);
		}
}