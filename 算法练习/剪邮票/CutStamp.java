public class CutStamp {
	private static int sum = 0;
	private static int[] book = new int[13];
	private static int[] num = new int[5];
	private static int[][] map1 = new int[3][5];
	private static int[][] fx = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int js = 1;

	public static void main(String[] args) {
		f(0, 1);
		System.out.println(sum);
	}

	public static void fz() {
		for (int i = 0; i < 5; i++) {
			int j = num[i];
			if (j <= 4) {
				map1[0][j] = j;
			} else if (j <= 8) {
				map1[1][j - 4] = j;
			} else {
				map1[2][j - 8] = j;
			}
		}
	}

	public static void hs() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				map1[i][j] = 0;
			}
		}
	}

	public static int[] zuobiao() {
		int[] arr = new int[2];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				if (map1[i][j] != 0) {
					arr[0] = i;
					arr[1] = j;
					break;
				}

			}
			if (arr[0] != 0) {
				break;
			}
		}

		return arr;
	}

	public static void f(int index, int id) {

		if (index == 5) {
			fz();
			int[] arr = zuobiao();
			check(arr[0], arr[1]);
			if (js == 5) {
				sum++;
			}
			hs();
			js = 1;

			return;
		}
		for (int i = id; i <= 12; i++) {
			if (book[i] == 0) {
				num[index] = i;
				book[i] = 1;
				f(index + 1, i + 1);
				book[i] = 0;
				num[index] = 0;
			}

		}

	}

	public static void check(int x, int y) {

		map1[x][y] = 999;
		for (int k = 0; k < 4; k++) {
			if (x + fx[k][0] >= 0 && x + fx[k][0] < 3 && y + fx[k][1] >= 1
					&& y + fx[k][1] < 5
					&& map1[x + fx[k][0]][y + fx[k][1]] != 0
					&& map1[x + fx[k][0]][y + fx[k][1]] != 999) {
				js++;
				check(x + fx[k][0], y + fx[k][1]);
			}
		}
	}

}

