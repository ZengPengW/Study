public class Cousuanshi {
	private static int[] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static int sum = 0;

	public static void main(String[] args) {
		f(0);
		System.out.println(sum);
	}

	public static void f(int index) {
		if (index >= 9) {
			if (check()) {
				sum++;
				System.out.println("" + num[0] + "+" + num[1] + "/" + num[2]
						+ "+" + num[3] + num[4] + num[5] + "/" + num[6]
						+ num[7] + num[8]);
			}
		}
		for (int i = index; i < num.length; i++) {
			int temp = num[index];
			num[index] = num[i];
			num[i] = temp;
			f(index + 1);
			temp = num[index];
			num[index] = num[i];
			num[i] = temp;
		}

	}

	public static boolean check() {

		int B = num[1] * (num[6] * 100 + num[7] * 10 + num[8]);
		int C = num[2] * (num[6] * 100 + num[7] * 10 + num[8]);
		int A = num[0] * C;

		int DEF = num[2] * (num[3] * 100 + num[4] * 10 + num[5]);
		if ((B + DEF + A) == C * 10) {
			return true;
		}

		return false;
	}

}