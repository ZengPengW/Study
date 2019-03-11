
import java.util.Scanner;

public class ExcelCount {
	private static int  n;
	private static int m;
	private static double[][] ex;
	private static String[][] str;

	public static void main(String[] args) {
//ceshi
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		str = new String[n + 1][m + 1];

		ex = new double[n + 1][m + 1];
		for (int i = 1; i < n + 1; i++) {

			for (int j = 1; j < m + 1; j++) {
				str[i][j] = sc.next();
			}
		}
		for (int j = 1; j <= n; j++) {
			for (int k = 1; k <= m; k++) {
				ex[j][k] = f(str[j][k]);
				str[j][k] = String.format("%.2f", ex[j][k]);
				System.out.print(str[j][k] + " ");
			}
			System.out.println();
		}

	}

	public static double f(String s) {
		if (!s.startsWith("SUM") && !s.startsWith("AVG")
				&& !s.startsWith("STD")) {
			return Double.valueOf(s);
		} else {
			String sp = s.substring(0, 3);

			if (sp.equals("SUM")) {
				return SUM(s);
			}
			if (sp.equals("AVG")) {
				return AVG(s);
			}
			if (sp.equals("STD")) {
				return STD(s);
			}
		}

		return 0;

	}

	public static double SUM(String s) {

		double sum = 0;
		String[] sp = s.substring(4, s.length() - 1).split("[,:]");

		for (int j = Integer.parseInt(sp[0]); j <= Integer.parseInt(sp[2]); j++) {
			for (int i = Integer.parseInt(sp[1]); i <= Integer.parseInt(sp[3]); i++) {

				sum += f(str[j][i]);
			}

		}

		return sum;

	}

	public static double AVG(String s) {
		String[] sp = s.substring(4, s.length() - 1).split("[,:]");
		int gs = (Integer.parseInt(sp[2]) - Integer.parseInt(sp[0]) + 1)
				* (Integer.parseInt(sp[3]) - Integer.parseInt(sp[1]) + 1);
		return Double.valueOf(String.format("%.2f", SUM(s) / gs));
	}

	public static double STD(String s) {
		double svg = AVG(s);
		double sum = 0;
		String[] sp = s.substring(4, s.length() - 1).split("[,:]");
		int gs = (Integer.parseInt(sp[2]) - Integer.parseInt(sp[0]) + 1)
				* (Integer.parseInt(sp[3]) - Integer.parseInt(sp[1]) + 1);
		for (int j = Integer.parseInt(sp[0]); j <= Integer.parseInt(sp[2]); j++) {
			for (int i = Integer.parseInt(sp[1]); i <= Integer.parseInt(sp[3]); i++) {

				sum += Math.pow((f(str[j][i]) - svg), 2);
			}

		}

		return Double.valueOf(String.format("%.2f", Math.sqrt(sum / gs)));
	}

}
