import java.util.Scanner;

public class Main
{
	private static int [][]num;
	private static int [][]dp;
	public static void main(String[] args)
	{
		
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		
		dp=new int[n+1][2];
		
		for (int i = 1; i <=n; i++) {
			dp[i][1]=scan.nextInt();	
		}
		
		 num=new int[n+1][100];
		for (int i = 0; i <n-1; i++) {
			int a=scan.nextInt();
			int b=scan.nextInt();
			int j=0;
			while(num[a][j]!=0)j++;
			num[a][j]=b;
			
			int k=0;
			while(num[b][k]!=0)k++;
			num[b][k]=a;
			
		}
		
		dfs(1, 0);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	
	}
	
	
	public static void dfs(int ch,int fa) {
		int child=num[ch][0];
		if (child==0) {
			return;
		}
		
		for (int i = 0; num[ch][i]!=0; i++) {
			child=num[ch][i];
			if (child!=fa) {
				dfs(child, ch);
				dp[ch][0]+=Math.max(dp[child][0], dp[child][1]);
				dp[ch][1]+=dp[child][0];
			}	
		}
		
	}
}