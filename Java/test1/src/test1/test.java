package test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class test {

	public static void main(String[] args) {
		int [][] matrix = {{1, 2, 1},
		          {2, 2, 2},
		          {2, 2, 2},
		          {1, 2, 3},
		          {2, 2, 1}};
		 List <String>list = new ArrayList<>();
		    StringBuilder sb = new StringBuilder();
		    for(int i=0; i<matrix.length-1;i++){
		        for(int j=0; j<matrix[i].length-1;j++){           
		            sb.append(matrix[i][j]+"");
		            sb.append(matrix[i][j+1]+"");
		            sb.append(matrix[i+1][j]+"");
		            sb.append(matrix[i+1][j+1]+"");
		            for(String item : list){
		                if(item.equals(sb.toString())){
		                    sb.delete(0,sb.length());
		                    break;
		                }
		            }
		            list.add(sb.toString());
		            System.out.println(sb.toString());
		            sb.delete(0,sb.length());
		        }
		    }
	}
}