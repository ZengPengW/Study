

public class LatticeSegmentation {
	private static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static long count = 0;

	public static void main(String[] args) {
		int[][] mark = new int[7][7];
		mark[3][3] = 1;
		dfs(3, 3, mark);
		System.out.println(count / 4);
	}

	private static void dfs(int x, int y, int[][] mark) {
		if (x == 0 || y == 0 || x == 6 || y == 6) {
			count++;
			return;
		}
		for (int i = 0; i < 4; i++) {
			int px = x + dir[i][0];
			int py = y + dir[i][1];
			if (px >= 0 && px <= 6 && py >= 0 && py <= 6 && mark[px][py] == 0) {
				mark[px][py] = 1;
				mark[6 - px][6 - py] = 1;
				dfs(px, py, mark);
				mark[px][py] = 0;
				mark[6 - px][6 - py] = 0;
			}
		}

	}
}