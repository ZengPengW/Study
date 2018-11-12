import java.util.Scanner;

public class LifeGame{
	private static int sum = 0;

	public static void main(String[] args) throws Exception {
		int[][] cell = new int[5000][5000];
		Scanner scan = new Scanner(System.in);
		for (int i = 2000; i < 2011; i++) {
			String str = scan.nextLine();//输入题目中的初始数据
			for (int j = 0; j < str.length(); j++) {
				if (String.valueOf(str.charAt(j)).equals("X")) {
					cell[i][j + 2000] = 1;
					//System.out.println(i+" "+(j+3000));
				}
			}
		}
//		for (int[] is : cell) {
//			for (int i : is) {
//				if (i == 1) {
//					sum++;
//				}
//			}}
		
		//System.out.println(sum);
		sum=0;
		for (long j = 0; j < 1000000000; j++) {
			f(cell);
			for (int[] is : cell) {
				for (int i : is) {
					if (i == 1) {
						sum++;
					}
				}
			}
			System.out.print(sum + " ");
			sum = 0;
		}

	}

	public static void f(int[][] cell) {
		int[][] live = new int[5000][5000];
		int[][] die = new int[5000][5000];

		int li = 0;

		for (int i = 0; i <5000; i++) {
			for (int j = 0; j < 5000; j++) {
				li = 0;
				if (i - 1 >= 0 && i + 1 < 5000 && j + 1 < 5000 && j - 1 >= 0) {
					for (int k = i - 1; k <= i + 1; k++) {
						for (int l = j - 1; l <= j + 1; l++) {
							if (!(k==i&&l==j)&&cell[k][l] == 1)
								li++;
						}
					}
				}
				if (cell[i][j] == 1 && (li < 2 || li > 3)) {
					die[i][j] = 1;
				} else if (cell[i][j] == 0 && li == 3) {
					live[i][j] = 1;
				}

			}

		}

		for (int i = 0; i < 5000; i++) {
			for (int j =0; j < 5000; j++) {
				if (die[i][j] == 1) {
					cell[i][j] = 0;
				}
				if (live[i][j] == 1) {
					cell[i][j] = 1;
				}

			}

		}
		System.gc();
	}
}
