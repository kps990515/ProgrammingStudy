package test1;

import java.util.ArrayList;
import java.util.HashSet;

public class test {

	public static void main(String[] args) {
		int sum=0;
		String s1 = "aabcc"; 
		String s2 = "adcaa";
		String [] array1 = s1.split("");
		String [] array2 = s2.split("");
		HashSet <String> set1 = new HashSet<>();
		HashSet <String> set2 = new HashSet<>();
		int count=0;
		for(int i=0; i<array1.length; i++)
		{
			set1.add(array1[i]); //abc
		}
    	for(int i=0; i<array2.length; i++)
		{
			set2.add(array2[i]); //abc
		}
		ArrayList <Integer> count1 = new ArrayList<>();
		ArrayList <Integer> count2 = new ArrayList<>();
		for(String item : set1)//abc
		{
			for(int i=0; i<array2.length; i++)//a-3 //c-1
			{
				if(item.equals(array2[i]))
				{
					count++;
				}
			}
			count1.add(count);
			count=0;
		}
		for(String item : set2)//adc a-2c-2
		{

			for(int i=0; i<array1.length; i++)
			{
				if(item.equals(array1[i]))
				{
					count++;
				}
			}
			count2.add(count);
			count=0;
		}
		for(int i=0; i<count1.size(); i++)
		{
			if(count1.get(i)>=count2.get(i))
			{
				count1.set(i,count2.get(i));
			}
		}
	    for(int i=0; i<count1.size(); i++)
			{
			sum+=count1.get(i);	
			}

		
		System.out.println(sum);
	}

}
