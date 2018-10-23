public class AddCSub{
	public static void main(String[] args) {
		int[] book = new int[50];
		f(0, book, 1);

	}

	public static void f(int index, int[] book, int cur) {
		if (index >= 2) {
			if (check(book)) {
				for (int i = 0; i < book.length; i++) {
					if (book[i] == 1) {
						System.out.print(i + " ");
					}

				}
				System.out.println();
			}
			return;
		}

		for (int i = cur; i <= 48; i++) {

			if (book[i] == 0 && book[i - 1] == 0 && book[i + 1] == 0) {
				book[i] = 1;
				index++;
			} else {
				continue;
			}

			f(index, book, i);
			book[i] = 0;
			index--;
		}
	}

	public static boolean check(int[] book) {
		int sum = 1;
		int sum2 = 0;

		for (int i = 2; i <= 49; i++) {
			if (book[i - 1] == 1) {

				sum2 = i * (i - 1) + sum2;
				sum = sum - i - (i - 1);
			}
			sum = sum + i;

		}
		sum = sum + sum2;
		if (sum == 2015) {
			return true;
		}
		return false;
	}

}