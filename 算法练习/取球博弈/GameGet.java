import java.util.Arrays;
import java.util.Scanner;

public class GameGet {
	private static int[] format = new int[3];
	private static int[] game = new int[5];
	private static int[] ps = new int[2];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < format.length; i++) {
			format[i] = sc.nextInt();	
		}
		Arrays.sort(format);

		for (int i = 0; i < game.length; i++)
			game[i] = sc.nextInt();
		f();
	}

	public static void f() {
		for (int i = 0; i < 5; i++) {
			int num = game[i];
			ps[0] = 0;
			ps[1] = 0;
			while (num > 0) {
				if (format[0] > num)
					break;
				boolean b1 = false;
				for (int j = 2; j >= 0; j--) {
					if ((format[j] % 2 == 0 && ps[0] % 2 == 0)
							|| (format[j] % 2 != 0 && ps[0] % 2 != 0))
						continue;
					if (num - format[j] >= 0) {
						ps[0] += format[j];
						num -= format[j];
						b1 = true;
						break;
					}
				}

				if (!b1) {
					for (int j = 2; j >= 0; j--) {
						if (num - format[j] >= 0) {
							ps[0] += format[j];
							num -= format[j];
							break;
						}
					}
				}

				boolean b2 = false;
				for (int j = 2; j >= 0; j--) {
					if ((format[j] % 2 == 0 && ps[1] % 2 == 0)
							|| (format[j] % 2 != 0 && ps[1] % 2 != 0))
						continue;
					if (num - format[j] >= 0) {
						ps[1] += format[j];
						num -= format[j];
						b2 = true;
						break;
					}

				}

				if (!b2) {
					for (int j = 2; j >= 0; j--) {
						if (num - format[j] >= 0) {
							ps[1] += format[j];
							num -= format[j];
							break;
						}
					}
				}
			}

			if ((ps[0] % 2 == 0 && ps[1] % 2 == 0)
					|| (ps[0] % 2 != 0 && ps[1] % 2 != 0)) {
				System.out.print("0 ");
			} else if (ps[0] % 2 != 0 && ps[1] % 2 == 0) {
				System.out.print("+ ");
			} else {
				System.out.print("- ");
			}
		}
	}
}
