import java.util.Scanner;

public class Probability {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = sc.nextInt();


		int b = sc.nextInt();
		int x = sc.nextInt();

		//f(n, a,b, x);
		DP(n, a,b, x);
		
	}
	
	
	
	public static void DP(int n, int a, int b, int x) {
		double [][]dp=new double[n+2][10005];
		double count=b-a+1;
		for (int i = 0; i < n; i++) {
			for (int j = a; j <=b; j++) {
				if(i==0){
					dp[i][j]=1/count;
				}else {
					for (int k =0; k <=x; k++) {
						if(dp[i-1][k]!=0){
							dp[i][k+j]+=(dp[i-1][k]/count);
						}
					}
				}
			}
		}
		
		System.out.println(String.format("%.4f", dp[n-1][x]));
	}
	
	
	
	
	public static void f(int n, int a, int b, int x) {
		int count =(b-a+1);
		long gs=(long) Math.pow(count, n);
		
		dfs(n, a, b, x, 0,"");
		
		System.out.println(ans/(gs*1.0));
		
	}
	private static int ans=0;
	//时间复杂度高 改用dp背包
	public static void dfs(int n, int a, int b, int x,int v,String s) {
		if(v>x){
			return;
		}
		
		if(v==x){
			ans++;
		//	System.out.println(s);
			return;
		}
		if(n==0){
			return;
		}
		for (int i = 0; i <=1; i++) {
			if(i==0){
				dfs(n-1, a, b, x,v,s);
			}else {
				for (int j = a; j <=b; j++) {
					dfs(n-1, a, b, x,v+j,s+j);
				}
				
			}
		}
	}
	private static long C(int count, int n) {
		long c1=1,n1=1;
		for (int i = 1; i <=n; i++) {
			c1*=count--;
			n1*=i;
		}
		
		
		return (c1/n1);
	}

	
}
