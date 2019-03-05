

import java.util.ArrayList;

public class CutStamp {

	public static void main(String[] args) {
		int[] mark = new int[13];
		ArrayList<Integer> arr = new ArrayList<Integer>(5);
		f(mark, arr, 0, 0);
		System.out.println(ans);

	}

	private static int ans = 0;

	public static void f(int[] mark, ArrayList<Integer> arr, int index, int cur) {
		if (index == 5) {
			if (check(arr)) {
				ans++;
			}
			return;
		}
		for (int i = cur + 1; i <= 12; i++) {
			if (mark[i] == 0) {
				arr.add(i);
				mark[i] = 1;
				f(mark, arr, index + 1, i);
				mark[i] = 0;
				arr.remove(arr.size() - 1);
			}
		}
	}

	private static boolean check(ArrayList<Integer> arr) {
		int stx = 0, sty = 0;
		boolean flag = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == arr.get(0)) {
					stx = i;
					sty = j;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

		for (int i = 1; i <= 4; i++) {
			int[][] mark = new int[3][4];
			mark[stx][sty] = 1;
			if (!dfs(stx, sty, arr.get(i), mark, arr)) {
				return false;
			}
		}

		return true;
	}

	private static int[][] map = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 },
			{ 9, 10, 11, 12 } };
	private static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static boolean dfs(int stx, int sty, int mdv, int[][] mark,
			ArrayList<Integer> arr) {
		if (map[stx][sty] == mdv) {
			return true;
		}

		for (int i = 0; i < 4; i++) {
			int x = dir[i][0] + stx;
			int y = dir[i][1] + sty;
			if (x >= 0 && x <= 2 && y >= 0 && y <= 3 && mark[x][y] == 0
					&& arr.contains(map[x][y])) {
				mark[x][y] = 1;
				if (dfs(x, y, mdv, mark, arr)) {
					return true;
				}
			}
		}
		return false;

	}
}