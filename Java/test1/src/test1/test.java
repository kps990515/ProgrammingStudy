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
	      //랜덤 값 10개 
	      for(int i=0; i<10; i++) {
	         temp[i]=random.nextInt(10)+1;
	      }
	      //랜덤 값을 넣을 랜덤 위치 10개(0~15)
	      while(set.size()<10) {
	         set.add(random.nextInt(16));
	      }
	      int index=0;
	      //(0~15)의 값을 실제 배열의 위치(0,0)로 바꾸고
	      // 랜덤 값 10개를 배열에 넣는다
	      for(int item : set) {
	         array[item/4][item%4]=temp[index];
	         index++;
	      }
	      //출력
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