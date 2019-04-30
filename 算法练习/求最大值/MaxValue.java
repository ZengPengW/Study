import java.util.Scanner;

public class MaxValue {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		int [][]num=new int [n+1][2];
		int j=0,ai,bi;
		for (int i = 1; i <=n; i++) {
			ai=sc.nextInt();
			bi=sc.nextInt();
			if(ai<0&&bi<0)continue;
			num[j+1][0]=ai;
			num[j+1][1]=bi;
			j++;
		}
		n=j;
	//	f1(num,n);
		f2(num,n);
	}
	public static void f2(int[][] num, int n) throws InterruptedException {
		int [][]dp=new int [n+1][200005];
		
		int t =100000;
		for (int i = 1; i <=n; i++) {
			for (int j = 0; j <=200000; j++) {
				dp[i][j]=-9999999;
			}
		}
		
		
		for (int i =1; i <=n; i++) {
			dp[i][num[i][0]+t]=num[i][1];
		}
		

		for (int i = 2; i <=n; i++) {
			for (int j = 0; j <=200000; j++) {
				dp[i][j]=Math.max(dp[i][j], dp[i-1][j]);
				if(j-num[i][0]<0||j-num[i][0]>200000) continue;
				dp[i][j]=Math.max(dp[i][j], dp[i-1][j-num[i][0]]+num[i][1]);
			}
		}
	    int ans = -9999999;
	    for (int j = 0; j <= t; j++)
	    {
	        ans = Math.max(ans, dp[n][j + t] >= 0 ? j + dp[n][j + t] : 0);
	    }

		System.out.println(ans);
		
	}
	//Ö»ÄÜÄÃ58%
	public static void f1(int[][] num, int n) {
		int []mark=new int[n+1];
		int []dp1=new int [n+2];
		int []dp2=new int [n+2];
		int id=0;
		for (int i = 1; i <=n; i++) {
			id=0;
			for (int j = 1; j <=n; j++) {
				if(mark[j]==0) {
					if(num[j][0]+dp1[i-1]>=0
					   &&num[j][1]+dp2[i-1]>=0
					   &&dp1[i-1]+dp2[i-1]<(dp1[i-1]+dp2[i-1]+num[j][0]+num[j][1])) {
						id=j;
						dp1[i]=dp1[i-1]+num[j][0];
						dp2[i]=dp2[i-1]+num[j][1];
						
					}
				}
			}
			if(id==0) {
				dp1[i]=dp1[i-1];
				dp2[i]=dp2[i-1];
			}
			mark[id]=1;
		}
		
		System.out.println(dp1[n]+dp2[n]);
	}
	
	
}
