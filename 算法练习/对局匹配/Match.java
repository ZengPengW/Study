import java.util.ArrayList;
import java.util.Scanner;


public class Match {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		int []make=new int[100001];
		
		int v;
		for (int i = 0; i <n; i++) {
			v=sc.nextInt(); 
			++make[v];
		}
		f(make, k, n);
	}
	private static void f(int[]make,int k,int n){
		int sum=0;
		if(k==0){
			for (int i = 0; i <100001; i++) 
				if(make[i]>0)sum++;
			System.out.println(sum);
			return;
		}
		
		int []dp=new int[100002];
		int []v=new int[100002];
		for (int i = 0; i <k; i++) {
			int o=0;
			for (int j = i; j <=100000; j+=k) {
				v[o++]=make[j];
			}
			dp[0]=v[0];
			dp[1]=Math.max(dp[0], v[1]);
			for (int j = 2; j <o; j++) {
				dp[j]=Math.max(dp[j-1], dp[j-2]+v[j]);
			}
			sum+=dp[o-1];
		}
		System.out.println(sum);
	}

}
