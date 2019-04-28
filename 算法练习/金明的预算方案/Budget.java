
import java.util.Arrays;
import java.util.Scanner;

public class Budget {

	private static class Pro {

		int[] v = new int[3];
		int[] p = new int[3];

		@Override
		public String toString() {
			return Arrays.toString(v) + " " + Arrays.toString(p);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		Pro[] pros = new Pro[m + 1];
		for (int i = 0; i < pros.length; i++) {
			pros[i] = new Pro();
		}
		int v1, p1, q1;

		int id = 1;
		int[] point = new int[m + 1];
		for (int i = 1; i <= m; i++) {
			v1 = sc.nextInt();
			p1 = sc.nextInt();
			q1 = sc.nextInt();

			if (q1 == 0) {
				pros[id].v[0] = v1;
				pros[id].p[0] = p1;
				point[i] = id;
				id++;
			} else {

				if (pros[point[q1]].v[1] == 0) {
					pros[point[q1]].v[1] = v1;
					pros[point[q1]].p[1] = p1;

				} else {
					pros[point[q1]].v[2] = v1;
					pros[point[q1]].p[2] = p1;
				}

			}

		}

		f(pros, n, m);
	}

	public static void f(Pro[] pros, int n, int m) {
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (j >= pros[i].v[0]) {
					int[] v = pros[i].v;
					int[] p = pros[i].p;
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[0]] + v[0] * p[0]);// 不买附件 且买与不买情况；

					// 买附件1
					if (j - v[0] - v[1] >= 0)
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[0] - v[1]] + v[0] * p[0] + v[1] * p[1]);

					// 买附件2
					if (j - v[0] - v[2] >= 0)
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[0] - v[2]] + v[0] * p[0] + v[2] * p[2]);

					// 买附件1 2
					if (j - v[0] - v[1] - v[2] >= 0)
						dp[i][j] = Math.max(dp[i][j],
								dp[i - 1][j - v[0] - v[2] - v[1]] + v[0] * p[0] + v[2] * p[2] + v[1] * p[1]);

				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[m][n]);

	}
}
