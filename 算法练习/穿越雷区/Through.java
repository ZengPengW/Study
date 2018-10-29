package À¶ÇÅ±­;

import java.util.ArrayList;
import java.util.Scanner;

public class Through {
	private static int n;
	private static int[][] map;
	private static int[][] sta = new int[1][2];
	private static int[][] end = new int[1][2];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		String str;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				str = sc.next();
				if (str.equals("+")) {
					map[i][j] = 1;
				} else if (str.equals("-")) {
					map[i][j] = 0;
				} else if (str.equals("A")) {
					map[i][j] = 2;
					sta[0][0] = i;
					sta[0][1] = j;
				} else {
					end[0][0] = i;
					end[0][1] = j;
					map[i][j] = 3;
				}
			}

		}
		int[][] bs = new int[n][n];
		bfs(map, bs);

	}

	public static int[][] Node(int x, int y) {
		int[][] arr = new int[1][2];
		arr[0][0] = x;
		arr[0][1] = y;
		return arr;
	}

	public static void bfs(int[][] map, int[][] bs) {

		ArrayList<int[][]> al = new ArrayList<int[][]>();
		al.add(Node(sta[0][0], sta[0][1]));

		while (!al.isEmpty()) {
			int[][] arr = al.remove(0);
			int ud = arr[0][0];
			int lr = arr[0][1];

			if (ud - 1 >= 0 && bs[ud - 1][lr] == 0
					&& map[ud - 1][lr] != map[ud][lr]) {
				bs[ud - 1][lr] = bs[ud][lr] + 1;
				al.add(Node(ud - 1, lr));
				if (ud - 1 == end[0][0] && lr == end[0][1]) {
					System.out.println(bs[ud - 1][lr]);
					return;
				}
			}
			if (ud + 1 < n && bs[ud + 1][lr] == 0
					&& map[ud + 1][lr] != map[ud][lr]) {
				bs[ud + 1][lr] = bs[ud][lr] + 1;
				al.add(Node(ud + 1, lr));
				if (ud + 1 == end[0][0] && lr == end[0][1]) {
					System.out.println(bs[ud + 1][lr]);
					return;
				}
			}
			if (lr - 1 >= 0 && bs[ud][lr - 1] == 0
					&& map[ud][lr - 1] != map[ud][lr]) {
				bs[ud][lr - 1] = bs[ud][lr] + 1;
				al.add(Node(ud, lr - 1));
				if (ud == end[0][0] && lr - 1 == end[0][1]) {
					System.out.println(bs[ud][lr - 1]);
					return;
				}

			}
			if (lr + 1 < n && bs[ud][lr + 1] == 0
					&& map[ud][lr + 1] != map[ud][lr]) {
				bs[ud][lr + 1] = bs[ud][lr] + 1;
				al.add(Node(ud, lr + 1));
				if (ud == end[0][0] && lr + 1 == end[0][1]) {
					System.out.println(bs[ud][lr + 1]);
					return;
				}

			}

		}

	}

}
