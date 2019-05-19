
import java.util.Scanner;

public class Jump {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		long []num,dp;
		long max;
		while (true) {
			n=sc.nextInt();
			if(n==0)return;
			num=new long[n];
			dp=new long[n];
			for (int i = 0; i <n; i++) {
				num[i]=sc.nextInt();
			}
			dp[0]=num[0];
			for (int i =1; i<n; i++) {
				for (int j = 0; j <i; j++) {
					if(num[i]>num[j])
						dp[i]=Math.max(dp[i], dp[j]+num[i]);
				}
				dp[i]=Math.max(dp[i], num[i]);
			}
			max=Integer.MIN_VALUE;
			for (long l : dp) {
				max=max<l?l:max;
			}
			System.out.println(max);
		}
	}
	

}
