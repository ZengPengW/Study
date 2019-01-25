import java.util.Scanner;


public class Wave {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextInt();
		long s=sc.nextInt();
		long a=sc.nextInt();
		long b=sc.nextInt();
		f(n,s,a,b);

	}
	private static long [][]dp=new long[2][1100*1100];
	private static final int MOD=100000007;
	public static void f(long n, long s, long a, long b) {
		int e=0;
		dp[e][0]=1;
		
		for (int i = 1; i <n; i++) {
			e=1-e;
			
			for (int j = 0,v=i*(i+1)/2; j <=v; j++) {
				if(i>j)
					dp[e][j]=dp[1-e][j];
				else
					dp[e][j]=(dp[1-e][j]+dp[1-e][j-i])%MOD;
				
			}
			
		}
		long ans=0,t;
		for (int i = 0,v=(int) (n*(n-1)/2); i <=v; i++) {
			t=s-i*a+(n*(n-1)/2-i)*b;
			if(t%n==0){
				ans=(ans+dp[e][i])%MOD;
			}
			
		}
		System.out.println(ans);
		
	}

}
