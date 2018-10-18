import java.math.BigInteger;
import java.util.Scanner;

public class Louti {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		BigInteger n = sc.nextBigInteger();
		BigInteger m = sc.nextBigInteger();

		BigInteger sum = BigInteger.ZERO;
		BigInteger[] sum1 = new BigInteger[Integer.valueOf(n.toString())];
		if (m.compareTo(BigInteger.ZERO) == 0) {
			System.out.println("分母不能为零");
			return;
		}
		if (n.compareTo(BigInteger.ZERO) == 0) {
			if (m.compareTo(BigInteger.ONE) == 0) {
				System.out.println(0);
			} else {
				System.out.println(1);
			}

			return;
		}
		for (int i = 1; i <= n.intValue(); i++) {
			sum = jisaun(i);
			sum1[i - 1] = sum;
		}
		sum = BigInteger.ZERO;
		for (int j = 0; j < sum1.length; j++) {

			sum = sum1[j].multiply(sum1[j]).add(sum);
		}
		sum = (sum.add(BigInteger.ONE)).mod(m);
		System.out.println(sum);
	}

	public static BigInteger jisaun(long n) {

		if (n <= 0) {
			return BigInteger.ZERO;
		}
		if (n == 2) {
			return BigInteger.valueOf(2);
		}
		if (n == 1) {
			return BigInteger.ONE;
		}

		return jisaun(n - 1).add(jisaun(n - 2));
	}
}
