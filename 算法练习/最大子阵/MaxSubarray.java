import java.util.Scanner;


public class MaxSubarray {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int [][]dp=new int[n+2][m+2];
		
		int v;
		//long ks=System.currentTimeMillis();
		for (int i = 1; i <=n; i++) {
			for (int j = 1; j <=m; j++) {
				v=sc.nextInt();
				dp[i][j]=dp[i-1][j]+v;
				
			}
		}
		
		f(dp, n, m);
		//System.out.println(System.currentTimeMillis()-ks);
	}
	
	public static void f(int[][] dp,int n,int m) {
		int maxv=-999999;
		int sum=0;
		for (int i = 1; i<=n; i++) {
			
			for (int j = i; j<=n; j++) {
				sum=0;
				for (int k = 1; k <=m; k++) {
					sum+=dp[j][k]-dp[i-1][k];
					if(sum>maxv)maxv=sum;
					if(sum<0)sum=0;
				}
				
			}
			
		}
		
		System.out.println(maxv);
		
		
		
	}

}
