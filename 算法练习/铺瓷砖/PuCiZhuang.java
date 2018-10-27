import java.util.Scanner;

public class PuCiZhuang {

	private static int n;
	private static int m;
	private static final int MOD = 65521;
	private static boolean[] bl;
	private static long sum = 0;
	private static long summ = 0;
	private static int[][] style = {

	{ 1, 0, 0, 1 }, { 0, 1, 0, 1 }, { 0, 1, 1, 0 }, { 1, 0, 1, 0 },

	{ 1, 0, 1, 1 }, { 1, 1, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 0 },

	};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		int[][] book = new int[n + 2][m + 2];
		bl = new boolean[n + 2];
		dfs(book, 1, 1, 2);
		System.out.println(summ % MOD);

	}

	public static void dfs(int[][] book, int id1, int id2, int id3) {

		if (id1 >= 4 && bl[id1] == false) {
			int check = 0;
			for (int i = id1 - 3; i <= id1 - 2; i++) {
				for (int j = 1; j <= m; j++) {
					check = check + book[i][j];
				}
			}
			if (check != 2 * m) {
				return;
			} else {
				bl[id1] = true;
			}
		}
		if (sum == n * m) {
			summ = (summ + 1) % MOD;
			return;
		}

		for (int j = id1; j <= id3; j++) {
			for (int k = id2; k <= m; k++) {

				for (int i = 0; i < 8; i++) {
					if (book[j][k] == 1) {
						break;
					}
					if (book[j][k] == 0 && j - style[i][0] > 0
							&& j + style[i][1] <= n && k - style[i][2] > 0
							&& k + style[i][3] <= m
							&& book[j - style[i][0]][k] == 0
							&& book[j + style[i][1]][k] == 0
							&& book[j][k - style[i][2]] == 0
							&& book[j][k + style[i][3]] == 0

					) {
						book[j][k] = 1;

						book[j - style[i][0]][k] = 1;
						book[j + style[i][1]][k] = 1;
						book[j][k - style[i][2]] = 1;
						book[j][k + style[i][3]] = 1;

						sum = sum + style[i][0] + style[i][1] + style[i][2]
								+ style[i][3] + 1;

						dfs(book, j, k + 1, n);

						sum = sum
								- (style[i][0] + style[i][1] + style[i][2]
										+ style[i][3] + 1);

						book[j][k] = 0;
						book[j - style[i][0]][k] = 0;
						book[j + style[i][1]][k] = 0;
						book[j][k - style[i][2]] = 0;
						book[j][k + style[i][3]] = 0;
					}

				}

			}
			id2 = 1;

		}

	}

}