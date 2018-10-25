import java.util.Scanner;

public class LifeTree {

	private static int n;
	private static int[] m;
	private static int Max = -1999999999;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			m[i] = sc.nextInt();
		}

		int[][] map = new int[n + 1][n + 1];
		for (int i = 1; i < n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = 1;
			map[b][a] = 1;
		}
		int[] book = new int[n + 1];
		dfs(map, 1, book);
		System.out.println(Max);

	}

	public static void dfs(int[][] map, int index, int[] book) {
		book[index] = 1;
		for (int i = 1; i < n + 1; i++) {

			if (map[index][i] == 1 && book[i] == 0) {

				dfs(map, i, book);
				if (m[index] + m[i] > m[index]) {
					m[index] = m[index] + m[i];
				}
				Max = Math.max(Max, m[index]);
			}

		}

	}

}