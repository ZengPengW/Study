import java.util.Scanner;

public class TriangleNum {
	private static int n;
	private static int max=0;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		 n=scan.nextInt();
		int num[][] =new int [n+1][];
		for (int i = 1; i < num.length; i++) {
			num[i]=new int[i+1];
			for (int j = 1; j <i+1; j++) 
			num[i][j]=scan.nextInt();
		}
	//	f(num, 1, 0, 0);
	//	System.out.println(max);
		DP(num);
	}
	public static void f(int [][]num,int id,int sum,int last) {
		if(id>n) {
			if(max<sum)max=sum;
			return;	
		}
			if(id==1) 
				f(num, id+1,sum+num[id][1],1);
			else  {
				for (int i = last; i < num[id].length&&i<=last+1; i++) 
				f(num, id+1,sum+num[id][i],i);
			}
	}
	
	
	public static void DP(int[][] num) {
		int [][]dp=new int [num.length][];
		
		
		int k=num.length-2;
		dp[k]=new int [num[k].length];
			
		for (int j =1; j < dp[k].length; j++) 
		dp[k][j]=Math.max(num[k+1][j]+num[k][j], num[k+1][j+1]+num[k][j]);
		
			
		
		for (int i =num.length-3; i>=1; i--) {
			dp[i]=new int [num[i].length];
			
			for (int j =1; j < dp[i].length; j++) {
				dp[i][j]=Math.max(dp[i+1][j]+num[i][j], dp[i+1][j+1]+num[i][j]);
			}
			
		}
		
		System.out.println(dp[1][1]);
	}
}
