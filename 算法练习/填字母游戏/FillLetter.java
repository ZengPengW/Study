import java.util.Scanner;

public class FillLetter {

	private static String[] game;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		game = new String[n];
		for (int i = 0; i < n; i++) {
			game[i] = scan.next();
		}
		f();
		scan.close();
	}

	public static boolean check(String str) {
		if (str.indexOf("*OL") != -1 || str.indexOf("LO*") != -1
				|| str.indexOf("L*L") != -1) {
			return true;
		}
		return false;
	}

	public static void f() {
		for (int i = 0; i < game.length; i++) {

			if (!check(game[i])) {
				System.out.println(method(game[i]));
			} else
				System.out.println(1);

		}
	}

	public static int method(String s) {
		int flag = 0;
		while (s.indexOf("*") != -1) {
			if (check(s)) {
				if (flag == 0)
					return 1;
				else
					return -1;
			}

			String newstr = null;
			int len = -1;
			while (true) {
				len = s.indexOf("*", len + 1);
				if (len == -1) {
					s = newstr;
					flag = 1 - flag;
					break;
				}

				newstr = s.substring(0, len) + "L"
						+ s.substring(len + 1, s.length());
				if (check(newstr))
					newstr = s.substring(0, len) + "O"
							+ s.substring(len + 1, s.length());

				if (!check(newstr)) {
					flag = 1 - flag;
					s = newstr;
					break;
				}

			}

		}
		return 0;

	}
}
