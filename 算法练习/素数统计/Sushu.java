public class Sushu {

	public static void main(String[] args) {
		long ks = System.currentTimeMillis();
		int[] arr = f(2000000);
		long js = (System.currentTimeMillis() - ks);
		System.out.println("time: " + js + "ms");
		// for (int i : arr) {
		// if (i!=0) {
		// System.out.print(i+" ");
		// }else {
		// break;
		// }

		// }

	}

	public static int[] f(int n) {
		int[] arr = new int[n];
		int count = 0;
		boolean bl = true;
		arr[0] = 2;
		count++;

		for (int i = 3; i <= n; i = i + 2) {
			bl = true;
			for (int j = 0; arr[j] * arr[j] <= i && arr[j] > 0 && arr[j] < i; j++) {
				if (i % arr[j] == 0) {
					bl = false;
					break;
				}

			}
			if (bl == false) {
				continue;
			}
			arr[count] = i;
			count++;

		}
		System.out.println("前" + n + "个数内 共 " + count + " 个素数");
		return arr;
	}
}