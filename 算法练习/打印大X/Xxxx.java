import java.util.Scanner;

public class Xxxx {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int h = sc.nextInt();
		dy(w, h);
		sc.close();
	}

	public static void dy(int w, int h) {
		int wd = h - 1 + w;
		String str = "";
		String str1 = "";
		for (int k = 0; k < w; k++) {
			str = str + "*";
		}
		str1 = str;
		for (int i = 0; i < (h - 1) / 2; i++) {

			if (i == 0) {
				System.out.println(String.format(
						"%s%" + (wd - (2 * w) - (2 * i)) + "s%s", str1, "", str1)
						.replace(" ", "."));

			} else {
				if ((wd - (2 * w) - (2 * i)) <= 0) {
					str = "";
					for (int j = 0; j < (wd - (2 * i)); j++) {
						str = str + "*";

					}

					System.out.println(String.format(
							"%" + i + "s%s%" + i + "s", "", str, "").replace(
							" ", "."));
				} else {
					System.out.println(String.format(
							"%" + i + "s%s%" + (wd - (2 * w) - (2 * i))
									+ "s%s%" + i + "s", "", str1, "", str1, "")
							.replace(" ", "."));
				}

			}
		}
		System.out.println(String.format(
				"%" + ((wd - w) / 2) + "s%s%" + ((wd - w) / 2) + "s", "", str1,
				"").replace(" ", "."));

		for (int i = ((h - 1) / 2) - 1; i >= 0; i--) {

			if (i == 0) {
				System.out.println(String.format(
						"%s%" + (wd - (2 * w) - (2 * i)) + "s%s", str1, "",
						str1).replace(" ", "."));

			} else {

				if ((wd - (2 * w) - (2 * i)) <= 0) {
					str = "";
					for (int j = 0; j < (wd - (2 * i)); j++) {
						str = str + "*";

					}

					System.out.println(String.format(
							"%" + i + "s%s%" + i + "s", "", str, "").replace(
							" ", "."));
				} else {

					System.out.println(String.format(
							"%" + i + "s%s%" + (wd - (2 * w) - (2 * i))
									+ "s%s%" + i + "s", "", str1, "", str1, "")
							.replace(" ", "."));
				}

			}
		}

	}
}
