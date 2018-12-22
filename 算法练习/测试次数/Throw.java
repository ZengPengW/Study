
import java.util.Scanner;

public class Throw{
//	private static int  maxv=0;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		//dfs(1, (n+1)/2, n,0, 3);
		//System.out.println(maxv);
		DP(n);
	}
	
	public static void DP(int n) {
		int[][]dp=new int[n+1][4];
		for (int i =1; i <=n; i++) 
			dp[i][1]=i;
		
		for (int i =2; i <=3; i++) {
			for (int j =1; j <=n; j++) {
				dp[j][i]=dp[j-1][i]+1; 
				for (int k =1; k <j; k++) {
					dp[j][i]=Math.min(dp[j][i], Math.max(dp[j-k][i], dp[k-1][i-1])+1);		
				}
				
			}
			
		}
		
		System.out.println(dp[n][3]);
	}
	//¶þ·Ö·¨Ê§°Ü
//	public static void dfs(int lo,int mid,int hi,int con,int sj) {
//		if(hi<lo){
//			if(maxv<con)maxv=con;
//			return;
//		}
//		if(sj==0){
//			return;
//		}
//		dfs(lo,(mid+lo)/2,mid-1,con+1,sj-1);
//		dfs(mid+1,(hi+mid)/2+1,hi,con+1,sj);
//	}
}

