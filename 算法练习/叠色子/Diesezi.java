import java.util.Scanner;

public class Diesezi{
	private static int n;
	private static int m;
	private static int S;
	private static final int MOD = 1000000007;
	private static int sum;
	public static int init[] = { -1, 4, 5, 6, 1, 2, 3 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		int[][] db = new int[7][7];
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			db[a][b] = 1;
			db[b][a] = 1;
		}

		S = 4;
		int b = 4;
		for (int i = 1; i < n; i++) {
			S = (S * b) % MOD;

		}
		S = S % MOD;
		f(db);
	}

	public static void f(int[][] db) {
		int[][] arr = new int[2][7];

		for (int j = 1; j < 7; j++) {

			arr[0][j] = 1;

		}

		int e = 0;
		for (int k = 1; k <= n - 1; k++) {
			e = 1 - e;
			for (int i = 1; i < 7; i++)
				arr[e][i] = 0;

			for (int i = 1; i < 7; i++) {
				for (int j = 1; j < 7; j++) {
					if (db[init[i]][j] != 1) {
						arr[e][i] = (arr[e][i] + arr[1 - e][j]) % MOD;
					}
					//System.out.println("dp[" + e + "][" + i + "]=" + arr[e][i]);
				}

			}
		}

		int sum = 0;
		for (int i = 1; i < 7; i++) {
			sum = (sum + arr[e][i]) % MOD;

		}
		//System.out.println("sum = " + sum);
		System.out.println((sum * S) % MOD);

	}

}