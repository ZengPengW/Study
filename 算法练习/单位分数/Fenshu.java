import java.util.Scanner;

public class Fenshu {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] ix = new int[n];
		dwfs(n, 0, ix, 2);

	}

	public static void dwfs(int n, int id, int[] ix, int index) {

		if (id == n) {

			js(ix);
			return;
		}
		if (index >= 30) {
			return;
		}

		for (int i = index; i < 30; i++) {

			ix[id] = i;
			index = i + 1;
			dwfs(n, id + 1, ix, index);

		}
	}

	public static void js(int[] ix) {

		long sum = 1l;
		long summ = 0l;
		long sum1 = 1l;

		for (int i = 0; i < ix.length; i++) {
			sum = sum * ix[i];// fm
			for (int j = 0; j < ix.length; j++) {
				if (j != i) {
					sum1 = sum1 * ix[j];// fz
				}
			}
			summ = sum1 + summ;
			sum1 = 1;
		}

		if (sum == summ) {
			for (int j = 0; j < ix.length; j++) {

				System.out.print("1/" + ix[j] + " ");
			}

			System.out.println();

		}

	}

}