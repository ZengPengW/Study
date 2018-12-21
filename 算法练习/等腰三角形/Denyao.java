package À¶ÇÅ±­;

import java.util.Scanner;

public class Denyao {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		f(num);	
	}
	public static void f(int num) {
		int count=num+(num-1)+(2*num-3);
		
		StringBuilder sb=new StringBuilder();
		int v=0;
		while (sb.length()<count) {
			v++;
			sb.append(v);	
		}
		String str=sb.substring(0, count);
		
		System.out.println(String.format("%"+(num-1)+"s%c", "",str.charAt(0)).replace(" ", "."));
		
		int id=1;
		for (int i = 2; i <num; i++) {
			System.out.println(String.format("%"+(num-i)+"s%c%"+id+"s%c", "",str.charAt(i-1),"",str.charAt(count-(i-1))).replace(" ", "."));	
			id+=2;
		}
		System.out.println(str.substring(num-1, count-(num-2)));
		
	}
}
