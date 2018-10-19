import java.util.ArrayList;

public class Duishu {
	private static int[] arr;
	private static int[] book;

	public static void main(String[] args) {
		arr = new int[] { 7, 4, 0, 0, 0, 0, 4, 0, 7, 0, 0, 0, 0, 0 };
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(6);
		temp.add(8);
		temp.add(0);
		temp.add(1);
		book = new int[8];
		book[7] = 1;
		book[4] = 1;
		f(2, arr, temp);

	}

	public static void f(int index, int[] arr, ArrayList<Integer> temp) {

		if (temp.size() == 14) {

			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]);

			}
			System.out.println();

			return;
		}

		while (true) {
			if (temp.contains(index)) {
				index++;
			} else break;
			

		}

		for (int i = 1; i <= 6; i++) {
			if (i == 4) {
				i++;
			}
			if (book[i] == 0 && (index + i + 1) < arr.length&& !temp.contains(index + i + 1)) {
				temp.add(index);
				temp.add(index + i + 1);
				book[i] = 1;
				arr[index] = i;
				arr[index + i + 1] = i;
			} else continue;

			f(index + 1, arr, temp);
			book[i] = 0;
			temp.remove((Object) index);
			temp.remove((Object) (index + i + 1));
		}
	}

}