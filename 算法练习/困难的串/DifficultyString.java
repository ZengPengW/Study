import java.util.Scanner;

public class DifficultyString {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int L = sc.nextInt();
		dfs(n, L, "");
	}

	public static void dfs(int n, int L, String pre) {
		if (check(pre))
			return;
		if (pre.length() == n) {
			System.out.println(pre);
			System.exit(0);
			return;
		}
		for (int i = 0; i < L; i++) {
			dfs(n, L, pre + (char) (i + 65));
		}
	}

	public static boolean check(String pre) {
		int len = pre.length() / 2;
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j <= pre.length() - 2 * i; j++) {
				if (pre.substring(j, j + i).equals(pre.substring(j + i, j + i + i)))
					return true;
			}
		}

		return false;
	}

}
