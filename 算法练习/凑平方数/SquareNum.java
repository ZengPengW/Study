import java.util.ArrayList;

public class SquareNum {

	public static void main(String[] args) {

		long[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		long[] mark;
		for (int i = 1; i <= 10; i++) {
			mark = new long[10];
			f(num, mark, i, "");
		}
		f(0, "");
		System.out.println(ans);
	}

	private static int ans = 0;
	private static ArrayList<String> al = new ArrayList<String>();

	public static void f(long[] num, long[] mark, long count, String s) {
		if (count == s.length()) {
			if (s.length() > 1 && s.charAt(0) == '0')
				return;
			long numb = (long) Math.sqrt(Long.valueOf(s));
			if (numb * numb == Long.valueOf(s)) {
				al.add(s);
				// System.out.println(s);
			}

			return;
		}

		for (int i = 0; i <= 9; i++) {
			if (mark[i] == 0) {
				mark[i] = 1;
				f(num, mark, count, s + i);
				mark[i] = 0;
			}
		}
	}

	public static void f(int index, String s) {
		if (s.length() == 10) {
			ans++;
			return;
		}

		int len = al.size();
		String str = null;
		boolean bl = true;
		for (int i = index; i < len; i++) {
			bl = true;
			str = al.get(i);
			for (int j = 0; j < s.length(); j++) {
				if (str.contains(s.charAt(j) + "")) {
					bl = false;
					break;
				}
			}
			if (bl)
				f(i + 1, s + str);
		}
	}

}