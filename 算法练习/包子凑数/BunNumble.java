import java.util.Scanner;

public class BunNumble {

	private static int n;
	private static final int N = 100000;
	private static int[] arr = new int[N];
	private static int[] num;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();

		num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = scan.nextInt();
			if (num[i] < min) {
				min = num[i];
			}
		}
		scan.close();
		int gcd = num[0];
		for (int i = 1; i < num.length; i++) {
			gcd= gcd(gcd, num[i]);
		}
		if (gcd != 1) {
			System.out.println("INF");
			return;
		}
		build(0, 0);
		check();

	}

	public static int gcd(int a, int b) {

		if (b == 0) return a;
		
		return gcd(b, a % b);
	}

	public static void build(int summ, int index) {
		if (index >= n) {

			arr[summ] = 1;
			return;
		}

		for (int j = 0; summ + num[index] * j <= N - 1
				&& arr[summ + num[index] * j] == 0; j++) {

			build(summ + num[index] * j, index + 1);

		}

	}

	public static void check() {
		int sum = 0;
		int flag = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] == 0) {
				sum++;
			}
			if (arr[i] == 1) {
				flag++;
			} else {
				flag = 0;
			}
			if (flag == min) {
				System.out.println(sum);
				return;
			}

		}
		// System.out.println("INF");

	}

}
