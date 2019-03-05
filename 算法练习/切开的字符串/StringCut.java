
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class StringCut {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		String s = scan.next();
		long max = 0;
		for (int i = 1; i < n - 1; i++) {
			long v = zhw(i, s.substring(0, i)) * fzhw(n - i, s.substring(i, n));
			max = max > v ? max : v;

			v = zhw(i, s.substring(0, i)) * fzhw(n - i, s.substring(i, n));
			max = max > v ? max : v;
		}
		System.out.println(max);
	}

	public static long zhw(int n, String s) {
		List<String> ls = new ArrayList<String>();
		String str = null;
		for (int i = 1; i <= n; i += 2) {
			for (int j = 0; j <= n - i; j++) {
				str = s.substring(j, i + j);
				if (isHW(str) && !ls.contains(str)) {
					ls.add(str);
				}
			}
		}

		return ls.size();
	}

	public static long fzhw(int n, String s) {
		List<String> ls = new ArrayList<String>();
		String str = null;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= n - i; j++) {
				str = s.substring(j, i + j);
				if (!ls.contains(str)) {
					if (str.length() % 2 == 0 || !isHW(str)) {
						ls.add(str);
					}
				}
			}
		}

		return ls.size();
	}

	public static boolean isHW(String s) {
		int len = s.length();
		if (len == 1)
			return true;
		for (int i = 0; i < len / 2; i++) {
			if (s.charAt(i) != s.charAt(len - i - 1))
				return false;
		}
		return true;
	}

}
