
public class SquareTens{
	
	private static int[] book = new int[10];

	public static void main(String[] args) throws Exception {
		String[] arr = new String[10];
		f(arr, 0);
	}

	public static void f(String[] arr, int index) {
		if (index >= 10) {
			if (check(arr)) {
				for (String str : arr) {
					System.out.print(str);
				}
				System.exit(0);
			}

			return;
		}

		for (int i = 9; i >= 0; i--) {
			if (book[i] == 0) {
				arr[index] = "" + i;
				book[i] = 1;
				f(arr, index + 1);
				book[i] = 0;
			}
		}
	}

	public static boolean check(String[] arr) {
		String str = arr[0] + arr[1] + arr[2] + arr[3] + arr[4] + arr[5]
				+ arr[6] + arr[7] + arr[8] + arr[9];
		long a = Long.valueOf(str);
		long num = (long) Math.sqrt(a);

		if (num * num == a) {
			return true;
		}
		return false;
	}

}
