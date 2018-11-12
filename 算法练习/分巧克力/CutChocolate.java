import java.util.Scanner;

public class CutChocolate {

	private static int N;
	private static int K;
	private static int max=0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		K = scan.nextInt();
		int[][] cho = new int[N][2];
		for (int i = 0; i < cho.length; i++) {
			cho[i][0] = scan.nextInt();
			cho[i][1] = scan.nextInt();
			if (max < cho[i][0] || max <cho[i][1]) {
				max = Math.max(cho[i][0], cho[i][1]);
			}
		}
		f2(cho);
		//f1(cho);
		scan.close();

	}


	public static void f1(int[][] cho) {

		long sum = 0;
		for (long i = max; max / i >= 1; i--) {

			sum = 0;
			for (int j = 0; j < cho.length; j++) {
				long w = cho[j][0] / i;
				long h = cho[j][1] / i;
				sum = sum + w * h;
			}
			if (sum >= K) {

				System.out.println(i);
				return;
			}
		}

	}

	
	public static void f2(int[][] cho) {
		long sum = 0;
		int index = max / 2;
		int l = 1;
		int r = max;
		while (l <= r) {
			sum = 0;
			for (int j = 0; j < cho.length; j++) {
				long w = cho[j][0] / index;
				long h = cho[j][1] / index;
				sum = sum + w * h;
			}
			if (sum < K) {
				r = index - 1;
				index = (r + l) / 2;

			} else if (sum >= K) {
				l = index + 1;
				index = (r + l) / 2;
			}

		}

		//System.out.println(l);
		for (long i = l; i > 0; i--) {

			sum = 0;
			for (int j = 0; j < cho.length; j++) {
				long w = cho[j][0] / i;
				long h = cho[j][1] / i;
				sum = sum + w * h;
			}
			if (sum >= K) {

				System.out.println(i);
				return;
			}
		}

	}

}
