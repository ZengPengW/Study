import java.io.IOException;
import java.util.Scanner;

public class FractionalMatrix{


	public static void main(String[] args) {
		// BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		Scanner in = new Scanner(System.in);
		int n;
		double []dp=new double [50001];
		dp[1]=1;dp[2]=3;
		double add=0.5;
		for (int i = 3; i <=50000; i++) {
			add+=(1.0/i);
			dp[i]=(add*2)+1+dp[i-1];
		}
		while (in.hasNextInt()) {
			n=in.nextInt();
			if(n==0)break;
			System.out.println(String.format("%.2f", dp[n]));
		}
	}
}