public class SanYan {

	public static void main(String[] args) {

		int[] num = new int[7];
		int[] book = new int[10];
		dfs(0, num, book);
	}

	public static void dfs(int index, int[] num, int[] book) {

		if (index == 7) {
			jisuan(num);
			return;
		}

		for (int i = 0; i <= 9; i++) {

			if (book[i] == 1) {

				continue;
			}

			if (book[i] == 0) {
				num[index] = i;

				book[i] = 1;
			}

			dfs(index + 1, num, book);
			book[i] = 0;

		}

	}

	private static void jisuan(int[] num) {
		int summ = (num[4] * 10000 + num[5] * 1000 + num[2] * 100 + num[1] * 10);
		int sum = (((num[0] * 1000 + num[1] * 100 + num[2] * 10 + num[3]) + (num[4]
				* 1000 + num[5] * 100 + num[6] * 10 + num[1])) - summ);
		String str = "" + summ;
		if (str.length() != 5) {
			return;
		}
		if (sum <= 9 && sum >= 0 && num[0] != 0 && num[4] != 0) {
			for (int i : num) {
				if (i == sum) {

					return;
				}
			}
			if ((str.charAt(0) - '0') == num[4]
					&& (str.charAt(1) - '0') == num[5]
					&& (str.charAt(2) - '0') == num[2]
					&& (str.charAt(3) - '0') == num[1]) {

				// System.out.println("" + num[0] + num[1] + num[2] + num[3]
				// + "\n+" + num[4] + num[5] + num[6] + num[1] + "\n"
				// + sum);
				System.out.println("" + num[4] + num[5] + num[6] + num[1]);

			}

		}
	}

}
