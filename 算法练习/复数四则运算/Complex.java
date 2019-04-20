import java.util.Scanner;

public class Complex{
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		double a = sc.nextDouble();
		double b = sc.nextDouble();

		char op = sc.next().charAt(0);

		double c = sc.nextDouble();
		double d = sc.nextDouble();

		f(a, b, c, d, op);
	}

	private static void f(double a, double b, double c, double d, char op) {
		String str = "";
		double a1, b1;
		int temp;
		try {
			switch (op) {
			case '+':
				a1 = (a + c);
				temp = (int) a1;
				if (a1 == temp) {
					str += temp;
				} else {
					str += a1;
				}
				str += "+";
				b1 = (b + d);
				temp = (int) b1;
				if (b1 == temp) {
					str += temp;
				} else {
					str += b1;
				}
				str += "i";
				break;
			case '-':
				a1 = (a - c);
				temp = (int) a1;
				if (a1 == temp) {
					str += temp;
				} else {
					str += a1;
				}
				str += "+";
				b1 = (b - d);
				temp = (int) b1;
				if (b1 == temp) {
					str += temp;
				} else {
					str += b1;
				}
				str += "i";

				break;
			case '*':
				a1 = (a * c - b * d);
				temp = (int) a1;

				if (a1 == temp) {
					str += temp;
				} else {
					str += a1;
				}
				str += "+";
				b1 = (b * c + a * d);
				temp = (int) b1;
				if (b1 == temp) {
					str += temp;
				} else {
					str += b1;
				}
				str += "i";

				break;
			case '/':
				a1 = ((a * c + b * d) / (c * c + d * d));
				temp = (int) a1;
				if (a1 == temp) {
					str += temp;
				} else {
					str += a1;
				}
				str += "+";
				b1 = ((b * c - a * d) / (c * c + d * d));
				temp = (int) b1;
				temp = (int) b1;
				if (b1 == temp) {
					str += temp;
				} else {
					str += b1;
				}
				str += "i";

				break;
			default:
				System.out.println("error");
				return;

			}

			str = str.replace("+-", "-");
			if (str.indexOf("N") != -1)
				throw new RuntimeException();
			System.out.println(str);
		} catch (Exception e) {
			System.out.println("error");
		}

	}

}
