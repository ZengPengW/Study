import java.util.HashMap;
import java.util.Scanner;

public class Louti {

	private static HashMap<Long, Long> hs = new HashMap<Long, Long>();
    private static long m;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		m = sc.nextLong();

		long sum = 0;

		long sum1 = 0;
		for (long i = 1; i <= n; i++) {
			sum = jisaun(i);
			sum1 = (sum1 + sum*sum)%m;
		}

		System.out.println((sum1 + 1) % m);
	}

	public static long jisaun(long n) {

		if (n <= 0) {
			return 0;
		}
		if (n == 2) {
			return 2;
		}
		if (n == 1) {
			return 1;
		}
		if (hs.containsKey(n)) {
			return hs.get(n);
		}
		long a=1;
		long b=2;
        long temp=0;
        for (int i = 3; i <=n; i++) {
			temp=(a+b)%m;
			a=b;
			b=temp;
			
		}
			if (!hs.containsKey(n)) {
			hs.put(n, temp);
		}
		return temp;
	}
}
