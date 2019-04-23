
import java.util.Scanner;

public class Knapsack{

	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
	
		int []wi=new int[n+1];
		int []vi=new int[n+1];
		for (int i = 1; i <=n; i++) {
			wi[i]=sc.nextInt();
			vi[i]=sc.nextInt();
			
		}
		f(wi,vi, m,n);

	}
	private static void f(int []wi,int []vi,int m,int n) {
		int [][]dp=new int[n+1][m+1];
		for (int i = 1; i <=n; i++) {
			for (int j = 1; j <=m; j++) {
				if(wi[i]<=j) {
					dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-wi[i]]+vi[i]);
				}else {
					dp[i][j]=dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[n][m]);
	}

}
