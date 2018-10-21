import java.util.Scanner;

public class Sudoku {
	private static int index = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int[][] book1 = new int[9][10];
		int[][] book2 = new int[9][10];
		int[][] num = new int[9][9];
		int[][] gezi = new int[9][10];
		String[] str = new String[9];
		for (int i = 0; i < 9; i++) {
			str[i] = sc.next();
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				num[i][j] = str[i].charAt(j) - '0';

				if (num[i][j] != 0) {
					index++;
					gezi[(j / 3) + (i / 3) * 3][num[i][j]] = 1;
					book1[i][num[i][j]] = 1;
					book2[j][num[i][j]] = 1;
				}
			}
		}
		f(num, book1, book2, gezi, 0, 0);
	}

	public static void f(int[][] num, int[][] book1, int[][] book2,
			int[][] gezi, int x, int y) {

		if (index == 81) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(num[i][j]);

				}
				System.out.println();
			}
			System.exit(0);
		}
		if (y == 9) {
			x++;
			y = 0;
		}
		for (int i = 1; i <= 9; i++) {
			if (num[x][y] != 0) {
				y++;
				if (y == 9) {
					x++;
					y = 0;

				}
				i = 0;
				continue;
			}
			if (num[x][y] == 0 && book1[x][i] == 0 && book2[y][i] == 0
					&& gezi[(y / 3) + (x / 3) * 3][i] == 0) {
				num[x][y] = i;
				book1[x][i] = 1;
				book2[y][i] = 1;
				gezi[(y / 3) + (x / 3) * 3][i] = 1;
				index++;
				f(num, book1, book2, gezi, x, y + 1);
				index--;
				num[x][y] = 0;
				book1[x][i] = 0;
				book2[y][i] = 0;
				gezi[(y / 3) + (x / 3) * 3][i] = 0;
			}

		}

	}
}
