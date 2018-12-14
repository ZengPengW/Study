
import java.util.Scanner;

public class Museum{
	private static final int Factorial4=24;
	private static int index=1;
	public static void main(String[] args) {
	Scanner scan=new Scanner(System.in);
	while (true) {
		int num=scan.nextInt();
		if(num==0)return;
		if(num%2!=0){
			System.out.println("Case "+(index++)+": "+0);
			continue;
		}
		f(num);
	}
	}
	//C£¨£¨n+4£©/2£¬£¨n-4£©/2 £©=C£¨£¨n+4£©/2£¬4 £©
	//C£¨£¨n+4£©/2 - 1£¬£¨n-4£©/2 -1£©=C£¨£¨n+2£©/2 £¬4 £© 
	//C£¨£¨n+4£©/2£¬4 £©+ C£¨£¨n+2£©/2 £¬4 £©
	public static void f(int num) {
		long c1=(num+4)/2;
		long c2=(num+2)/2;
		long temp1=c1;
		long temp2=c2;
		for (int i = 1; i <4; i++) {
			c1*=(temp1-i);
			c2*=(temp2-i);
		}
		System.out.println("Case "+(index++)+": "+(c1/Factorial4+c2/Factorial4));
		
	}
}

