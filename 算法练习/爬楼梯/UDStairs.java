import java.util.Scanner;

public class UDStairs{

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		long n=scan.nextLong();
		int m=scan.nextInt();
		
		
		
		if(n<=0){
			System.out.println(0);
			return;
		}
		if(n==1){
			System.out.println(1%m);
			return;
		}
		if(n==2){
			System.out.println(6%m);
			return;
		}
		int a=1;
		int b=2;
		int sum=a*a+b*b;
		for (long i = 3; i <=n; i++) {
			int temp=(a+b)%m;
			a=b;
			b=temp;
			sum=(sum+temp*temp)%m;
		}
		System.out.println((sum+1)%m);
		
	}
//1 2 3 5
//1 4 9 25
	
}
