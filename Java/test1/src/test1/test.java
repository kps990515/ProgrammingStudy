package test1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
	      Scanner sc = new Scanner(System.in);
	      String input="";
	      char alphabet = 0;
	      System.out.println("소문자 알파벳 하나를 입력하시오 : ");
	      input = sc.nextLine();
	      char [] temp = input.toCharArray();
	      for(int i=0; i<temp.length; i++) {
	         if(temp[i]<'a'||temp[i]>'z') {
	            System.out.println("소문자 알파벳이 아닙니다");
	            break;
	         }
	         else if(temp.length>1) {
	            System.out.println("소문자 알파벳 하나만 입력하시오");
	            break;
	         }else {
	            alphabet = temp[0];
	         }
	      }
	      int anumber = (int)alphabet;
	      int decrease = anumber;
	      for(int i=0; i<=anumber-97; i++)
	      {
	         for(int j=97; j<=decrease ; j++) {
	            System.out.print(((char)(j)));
	         }
	         decrease--;
	         System.out.print("\n");
	      }
	   }
}