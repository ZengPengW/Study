public class PaiXing {
	private static int sum = 0;
	private static int index = 0;

	public static void main(String[] args) {
		dfs(1);
		System.out.println(sum);

	}

	public static void dfs(int id) {

		if (index == 13) {
			sum++;
			return;
		}

		if (id > 13 || index > 13) {
			return;
		}

		for (int i = 0; i <= 4; i++) {
			index += i;
			dfs(id + 1);
			index -= i;

		}

	}

}
