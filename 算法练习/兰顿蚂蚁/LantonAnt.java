import java.util.Scanner;

public class LantonAnt {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();

		int[][] map = new int[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();

			}
		int x, y, k;
		char s;
		x = sc.nextInt();
		y = sc.nextInt();
		s = sc.next().charAt(0);
		k = sc.nextInt();
		dfs(map, x, y, s, k);
	}

	public static void dfs(int[][] map, int x, int y, char s, int k) {

		if(k==0){
			System.out.println(x+" "+y);
			return;
		}
		k--;
		if (map[x][y] == 0) {
			map[x][y]=1;			
			switch (s) {
			case 'U':
				s='L';
				y--;				
				dfs(map, x, y, s, k);
				break;
			case 'D':
				s='R';
				y++;
				dfs(map, x, y, s, k);
				break;
			case 'L':
				s='D';
				x++;
				dfs(map, x, y, s, k);
				break;
			case 'R':
				s='U';
				x--;
				dfs(map, x, y, s, k);
				break;

			default:
				break;
			}
		} else {
			map[x][y]=0;
			switch (s) {
			case 'U':
				s='R';
				y++;
				dfs(map, x, y, s, k);
				break;
			case 'D':
				s='L';
				y--;
				dfs(map, x, y, s, k);
				break;
			case 'L':
				s='U';
				x--;
				dfs(map, x, y, s, k);
				break;
			case 'R':
				s='D';
				x++;
				dfs(map, x, y, s, k);
				break;

			default:
				break;
			}
		}

	}

}
