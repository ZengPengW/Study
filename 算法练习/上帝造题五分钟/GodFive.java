import java.util.Scanner;

public class GodFive {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n =sc.nextInt();
		int q=sc.nextInt();
		int []a=new int[n];
		for (int i = 0; i <n; i++) {
			a[i]=sc.nextInt();
		}
		int [][]query=new int[q][2];
		for (int i = 0; i <q; i++) {
			query[i][0]=sc.nextInt();
			query[i][1]=sc.nextInt();
		}
		f(query,a);
	}

	public static void f( int[][] query, int[] a) {
		int n=a.length;
		int [][]dp=new int[n][n];
		for (int i = 0; i <n; i++) {
			for (int j = i; j <n; j++) {
				if(i==j)dp[i][j]=a[j];
				else {
					dp[i][j]=Math.min(dp[i][j-1], a[j]);
				}
			}
		}
		int len=query.length;
		for (int i = 0; i <len; i++) {
			System.out.println(dp[query[i][0]][query[i][1]]);
		}
		
	}
}
