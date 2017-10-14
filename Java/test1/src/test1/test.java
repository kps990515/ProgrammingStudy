package test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		int [] inputArray = {1, 4, 10, 6, 2};//1 2 4 6 10
		Arrays.sort(inputArray);
	    int jump=inputArray[0]+1;
	    int count=0;
	    for(int i=1; i<inputArray.length; i++){
	        while(inputArray[i]%jump==0){
	            jump++;
	            System.out.println("i = "+i+"jump = " +jump);
	        }
	    }
	   
}
	
}