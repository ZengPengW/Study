public class PaiXing {
	public static void dfs(long a, int ans) {
		if (ans == 13) {
			if (a == 0) {
				count++;
			}
			return;
		}

		for (int i = 0; i <= 4; i++) {
			dfs(a - i, ans + 1);
		}
	}

	public static void main(String[] args) {
		int[] num = new int[14];
		for (int i = 0; i < num.length; i++) {
			num[i] = 4;
		}
		dfs(13, 0);
		System.out.println(count);
	}

}
