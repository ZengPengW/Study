
import java.util.ArrayList;
import java.util.Scanner;

public class TakeCandy {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int max = (int) Math.sqrt(n);
		ArrayList<Integer> zs = getZs(max);

		int[] dp = new int[n + 1];
		
		for (int i = 0; i < zs.size(); i++) {
			for (int j =4; j <= n; j++) {
				dp[j]=Math.max(dp[j-1], dp[j]);
				if (j % zs.get(i) == 0 && (j >= 2 * zs.get(i))) {
					dp[j] = Math.max(dp[j], dp[j - 2 * zs.get(i)] + zs.get(i));

				}

			}

		}
		System.out.println(dp[n]);

	}

	private static ArrayList<Integer> getZs(int max) {
		ArrayList<Integer> zs = new ArrayList<Integer>();
		zs.add(2);
		boolean flag = true;
		for (int i = 3; i <= max; i++) {
			flag = true;
			for (int j : zs) {
				if (j * j > i)
					break;
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				zs.add(i);
			}
		}
		return zs;

	}

}
