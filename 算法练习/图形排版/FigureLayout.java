import java.util.Scanner;

public class FigureLayout{

	public static int min = 1999999999;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		double[][] img = new double[N][2];
		for (int i = 0; i < N; i++) {

			img[i][0] = sc.nextDouble();
			img[i][1] = sc.nextDouble();
		}

		f(M, N, img);
		System.out.println(min);
	}

	public static void f(int M, int N, double[][] img) {
		int heigh = 0;
		int flag = 0;
		double syW = M;
		int sum = 0;
		for (int i = 0; i < img.length; i++) {
			syW = M;
			flag = 0;
			heigh = 0;
			sum = 0;
			for (int j = 0; j < img.length; j++) {
				if (j != i) {
					if (img[j][0] <= syW) {
						if (heigh < img[j][1]) {
							heigh = (int) img[j][1];
						}
						flag = 0;
						syW -= img[j][0];
					} else {
						if (syW == 0)
							j--;
						else {
							if (heigh < Math
									.ceil((syW / img[j][0]) * img[j][1])) {
								heigh = (int) Math.ceil((syW / img[j][0])
										* img[j][1]);
							}
						}
						sum += heigh;
						heigh = 0;
						flag = 1;
						syW = M;
						if (sum>=min) {
							break;
						}
					}
				}
			}
			if (flag == 0) {
				sum += heigh;
			}
			min = Math.min(min, sum);
		}

	}

}
