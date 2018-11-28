import java.util.Scanner;

public class FormMax{
	private static long [][]dp;
	private static int []sum;
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		int k=scan.nextInt();
		sum=new int[n+1];
		dp=new long[n+1][k+1];
		for (int i = 1; i < sum.length; i++) {
			sum[i]=scan.nextInt()+sum[i-1];
			dp[i][0]=sum[i];
		}
		if(k>0) {
			for (int i = 2; i <=n; i++) {
				int x=Math.min(i-1, k);				
				for (int j =1; j <=x; j++) 
				for (int l =2; l <=i; l++)	
				dp[i][j]=Math.max(dp[i][j], dp[l-1][j-1]*(sum[i]-sum[l-1]));
			}
		}
			
	
			System.out.println(dp[n][k]);
		
	}
	
}

