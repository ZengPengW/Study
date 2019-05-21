import java.util.Scanner;

public class Hive {

	//private static int count=0;
	public static void main(String[] args)  {
		Scanner sc=new Scanner(System.in);
		
		int a=0,b=0;	
		int n= sc.nextInt();
		long []dp=new long[51];
		dp[1]=1;dp[2]=2;dp[3]=3;
		for (int i =4; i <51; i++) {
			dp[i]=dp[i-1]+dp[i-2];
		}
//		int []mark;
		while(n-->0) {
			a=sc.nextInt();
			b=sc.nextInt();
			System.out.println(dp[b-a]);
//			mark=new int [50];
//			count=0;
//			mark[a]=1;
//			dfs(a,b,mark);
//			System.out.println(count);
		}
		
	}
// 	递归方式
//	public static void dfs(int a, int b, int[] mark) {
//		if(a>b)return;
//		if(a==b) {
//			count++;
//			return;
//		}
//		
//		//下&右上
//		if(a+1<=b&&mark[a+1]==0) {
//			mark[a+1]=1;
//			dfs(a+1, b, mark);
//			mark[a+1]=0;
//		}
//		
//		//右
//		if(a+2<=b&&mark[a+2]==0) {
//			mark[a+2]=1;
//			dfs(a+2, b, mark);
//			mark[a+2]=0;
//		}
//		
//		
//	}
	
	

}
