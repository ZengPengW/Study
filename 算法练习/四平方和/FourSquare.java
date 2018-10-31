import java.util.Scanner;

public class FourSquare {

	private static int n;

	// private static long ks;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// ks = System.currentTimeMillis();
		n = sc.nextInt();
		int[] arr = new int[4];
		dfs(0, arr, 0);

	}

	public static void dfs(int index, int[] arr, int id) {

		if (index >= 4) {
			if (check(arr)) {
				for (int i : arr) {
					System.out.print(i + " ");
				}
				// long js = System.currentTimeMillis() - ks;
				// System.out.println(js);
				System.exit(0);
			}
			return;
		}
		for (int i = id; i * i <= n; i++) {
			arr[index] = i;
			dfs(index + 1, arr, i);
		}
	}

	public static boolean check(int[] arr) {
		double sum = 0;
		for (int i = 0; i < 4; i++)
			sum = sum + Math.pow(arr[i], 2);

		if ((int) sum == n)
			return true;

		return false;
	}
}
