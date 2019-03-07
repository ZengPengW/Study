
public class HomeWork {

	public static void main(String[] args) {
		int[] mark = new int[14];
		int[] arr = new int[8];
		f(mark, arr, 0);
		System.out.println(ans);
	}

	private static int ans = 0;

	public static void f(int[] mark, int[] arr, int index) {
		if (index == 8) {
			if (check(arr)) {
				ans++;
			}
			return;
		}
		for (int i = 1; i <= 13; i++) {
			if (mark[i] == 0) {
				arr[index] = i;
				mark[i] = 1;
				f(mark, arr, index + 1);
				mark[i] = 0;
			}
		}
	}

	private static boolean check(int[] arr) {
		int a = arr[0] + arr[1];
		if (a > 13 || a < 1)
			return false;
		int b = arr[2] - arr[3];
		if (b > 13 || b < 1)
			return false;
		int c = arr[4] * arr[5];
		if (c > 13 || c < 1)
			return false;
		int d = arr[6] / arr[7];
		if (d > 13 || d < 1 || d * arr[7] != arr[6])
			return false;
		if (a != b && a != c && a != d && b != c && b != d && c != d) {

			for (int i = 0; i < 8; i++) {
				if (arr[i] == a || arr[i] == b || arr[i] == c || arr[i] == d)
					return false;
			}
			return true;
		}

		return false;
	}
}