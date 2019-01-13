import java.util.Scanner;


public class Palindrome {

	public static void main(String[] args) {
		int n=new Scanner(System.in).nextInt();
		f(n);

	}
	public static void f(int n) {
		String s;
		int sum=0,mark=-1;
		for (int i =10001; i <=999999; i++) {
			s=Integer.toString(i);
			sum=0;
			for (int j = 0,len=s.length(); j <len; j++) 
				sum+=(s.charAt(j)-'0');
				
			if(sum!=n)continue;
			if(check(s)){
				System.out.println(s);
				mark++;
			}
				
			
		}
		if(mark==-1)System.out.println(-1);
	}
	private static boolean check(String s) {
		int sumlen=s.length();
		int len=sumlen/2;
		sumlen-=1;
		for (int j = 0; j <len; j++) 
			if(s.charAt(j)!=s.charAt(sumlen-j))return false;
		
		return true;
		
	}

}
