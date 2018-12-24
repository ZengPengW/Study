import java.util.Scanner;

public class ShrinkSum {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		char [] num=sc.next().toCharArray();
		f(num);
	}
	
	public static void f(char []num) {
		int sum=0;
		
		while(num.length>1){
			sum=0;
			for (int i = 0; i < num.length; i++) 
				sum+=(num[i]-'0');
			num=String.valueOf(sum).toCharArray();
			
		}
		
		System.out.println(sum);
		
	}
}