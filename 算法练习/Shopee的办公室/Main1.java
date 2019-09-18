package shopee;

import java.util.Scanner;


public class Main1 {

	private static long count =0;
	private static int x ;
	private static int y ;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		x=sc.nextInt();
		y=sc.nextInt();
		int n=sc.nextInt();
		
		int [][]boss=new int[x+1][y+1];
		for (;n-->0;) {
			boss[sc.nextInt()][sc.nextInt()]=1;
		}
		
		//dfs(0,0,boss);
		DP(boss);
		//System.out.println(count);
	}
	
	//动态规划
	public static void DP(int [][]boss) {
		long [][]dp=new long[x+1][y+1];
		dp[0][0]=1;
		
		for (int i = 0; i <=x; i++) {
			for (int j = 0; j <=y; j++) {
				if (boss[i][j]==1) {
					continue;
				}
				if (i-1>=0) {
					dp[i][j]+=dp[i-1][j];
				}
				
				if (j-1>=0) {
					dp[i][j]+=dp[i][j-1];
				}
				
				
			}
		}
		
		System.out.println(dp[x][y]);
	}
	
	
//深度优先搜索超时	
//	private static void dfs(int cx, int cy, int[][] boss) {
//		if (cx>x||cy>y||boss[cx][cy]==1) {
//			return;
//		}
//		if (cx==x&&cy==y) {
//			count++;
//			return;
//		}
//		
//		
//		dfs(cx+1, cy, boss);//右边
//		dfs(cx, cy+1, boss);//上边
//		
//		
//	}
}
