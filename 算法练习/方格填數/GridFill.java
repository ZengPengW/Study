public class GridFill {
	private static int[][] num = new int[5][6];
	private static int sum = 0;

	public static void main(String[] args) {
		int[] book = new int[10];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				num[i][j] = 999;

			}

		}

		dfs(book, 1, 2);
		System.out.println(sum);
	}

	public static void dfs(int[] book, int x, int y) {
		if (x == 3 && y == 4) {
			sum++;
			return;
		}

		if (y > 4) {
			y = 1;
			x++;
		}

		for (int k = 0; k <= 9; k++) {
			if (book[k] == 0) {
				if (check(x, y, k)) {
					book[k] = 1;
					num[x][y] = k;
					dfs(book, x, y + 1);
					book[k] = 0;
					num[x][y] = 999;
				} else {
					continue;
				}

			}

		}

	}

	public static boolean check(int x, int y, int num1) {
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (Math.abs(num1 - num[i][j]) == 1) {
					return false;
				}

			}

		}
		return true;
	}
}