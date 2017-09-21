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
		int [] temp2 = new int[6];
		for(int i=0; i<10; i++) {
			temp[i]=random.nextInt(10)+1;
		}
		while(set.size()<6) {
			set.add(random.nextInt(16));
		}
		for(int i=0; i<4; i++) 
		{
			for(int j=0; j<4; j++) 
			{
				array[i][j]=100;
			}
		}
		for(int item : set) {
			array[item/4][item%4]=0;
		}
		int k=0;
		for(int i=0; i<4; i++) 
		{
			for(int j=0; j<4; j++) 
			{
				if(array[i][j]==100) 
				{
					array[i][j]=temp[k];
					k++;
				}
				System.out.print(array[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
}
