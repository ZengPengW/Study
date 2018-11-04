import java.util.LinkedList;
import java.util.Scanner;

public class PathFans{

	private static int n;
	private static int[][] num;
	private static LinkedList<String> map;
	private static int[][] mark;
	private static int[][] method = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		num = new int[2][n];
		map = new LinkedList<String>();
		mark = new int[n][n];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < n; j++) {
				num[i][j] = sc.nextInt();
			}
		}

		mark[0][0] = 1;
		map.add("" + 0);
		dfs(0, 0);
	}

	public static void dfs(int x, int y) {
		if (x == (n - 1) && y == (n - 1)) {
			if (check()) {

				for (int i = 0; i < map.size(); i++) {
					System.out.print(map.get(i) + " ");

				}

			}
			return;
		}

		for (int i = 0; i < method.length; i++) {
			if (x + method[i][0] >= 0 && x + method[i][0] < n
					&& y + method[i][1] >= 0 && y + method[i][1] < n
					&& mark[x + method[i][0]][y + method[i][1]] == 0) {
				mark[x + method[i][0]][y + method[i][1]] = 1;
				map.add("" + (x + method[i][0] + (y + method[i][1]) * n));

				dfs(x + method[i][0], y + method[i][1]);
				map.remove("" + (x + method[i][0] + (y + method[i][1]) * n));
				mark[x + method[i][0]][y + method[i][1]] = 0;

			}

		}

	}

	public static boolean check() {
		int[][] arr = new int[2][n];
		for (int i = 0; i < mark.length; i++) {
			for (int j = 0; j < mark[i].length; j++) {
				if (mark[i][j] != 0) {
					arr[0][i]++;
					arr[1][j]++;
				}
			}
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < n; j++) {
				if (num[i][j] != arr[i][j]) {
					return false;
				}
			}
		}
		return true;

	}
}