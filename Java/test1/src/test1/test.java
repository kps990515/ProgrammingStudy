package test1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
	      HashSet<Integer>set = new HashSet<>();
	      Random random = new Random();
	      int [][] array = new int[4][4];
	      int [] temp = new int[10];
	      //���� �� 10�� 
	      for(int i=0; i<10; i++) {
	         temp[i]=random.nextInt(10)+1;
	      }
	      //���� ���� ���� ���� ��ġ 10��(0~15)
	      while(set.size()<10) {
	         set.add(random.nextInt(16));
	      }
	      int index=0;
	      //(0~15)�� ���� ���� �迭�� ��ġ(0,0)�� �ٲٰ�
	      // ���� �� 10���� �迭�� �ִ´�
	      for(int item : set) {
	         array[item/4][item%4]=temp[index];
	         index++;
	      }
	      //���
	      for(int i=0; i<4; i++) 
	      {
	         for(int j=0; j<4; j++) 
	         {
	            System.out.print(array[i][j]+"\t");
	         }
	         System.out.print("\n");
	      }
	   }
}