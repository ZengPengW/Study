import java.util.Scanner;

public class LetGra{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		char[][] ch = new char[26][26];
		for (int i = 0; i < ch[0].length; i++) {
			ch[0][i] = (char) ('A' + i);
		}
		f(ch, n,m);
	}

	public static void f(char[][] ch, int n,int m) {

		int id = 0;
		while (id + 1 < n) {

			for (int i = id + 1; i < ch.length; i++) {
				ch[i][id] = ch[id][i];
			}
			id++;
			for (int i = id; i < ch[id].length; i++) {
				ch[id][i] = (char) ('A' + i - id);
			}
		}

		for (int i = 0; i <n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(ch[i][j]);
			}
			System.out.println();
		}

	}

}
