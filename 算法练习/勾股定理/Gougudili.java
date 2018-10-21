import java.util.Scanner;

public class Gougudili {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		double a = sc.nextDouble();
		int sum = 0;
		int b = 0;
		for (double i = 1; i < a; i++) {

			b = (int) Math.sqrt(Math.pow(a, 2) - Math.pow(i, 2));
			if (b < i) {
				break;
			}
			if (Math.pow(b, 2) + Math.pow(i, 2) == Math.pow(a, 2)) {
				// System.out.println(i+" "+ b+" "+" "+ a);
				sum++;
			}
			// -----------------舍弃 时间复杂度大------------------
			// for (double j = (a-i+1)<i?i:(a-i+1); j <a; j++) {
			// if (Math.pow(i, 2)+Math.pow(j, 2)>Math.pow(a, 2)) {
			// break;
			// }
			//
			//
			// if (Math.pow(i, 2)+Math.pow(j, 2)==Math.pow(a, 2)) {
			// sum++;
			// System.out.println(i+" "+ j+" "+" "+ a);
			// }
			//
			// }

		}
		System.out.println(sum);

	}

}
