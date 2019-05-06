
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 
public class MergeStones {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String []read=br.readLine().trim().split(" ");
		int n=Integer.parseInt(read[0]);
		int []num=new int[n+2];
		sum=new int[n+2];
		read=br.readLine().trim().split(" ");
		for (int i = 1; i <=n; i++) {
			num[i]=Integer.valueOf(read[i-1]);
			sum[i]=sum[i-1]+num[i];
		}
		f(num,n);
	}
	private static int[] sum;
	private static void f(int[] num,int n) {
		
		int [][]dp=new int[n+2][n+2];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				dp[i][j]=0x7fffffff;
			}
			dp[i][i]=0;
		}
		
		for (int len =2; len <=n; len++) {
			for (int i =1; i <=n; i++) {
				int j=i+len-1;
				if(j>n)continue;
				for (int k = i; k <j; k++) {
					dp[i][j]=Math.min(dp[i][j], dp[i][k]+dp[k+1][j]+sum[j]-sum[i-1]);
				}
			}
		}
		System.out.println(dp[1][n]);
		
	}

	

	

	
 
}
