import java.util.Scanner;

public class CutChocolate {

	private static int N;
	private static int K;
	private static int min;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		K = scan.nextInt();
		int[][] cho = new int[N][2];
		for (int i = 0; i < cho.length; i++) {
			cho[i][0] = scan.nextInt();
			cho[i][1] = scan.nextInt();
			if (min < cho[i][0] || min < cho[i][1]) {
				min = Math.min(cho[i][0], cho[i][1]);
			}
		}
		f(cho);
		scan.close();

	}

	public static void f(int[][] cho) {

		long sum = 0;
		for (long i = min; min / i >= 1; i--) {
			
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
