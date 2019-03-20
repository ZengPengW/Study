
import java.util.Scanner;

public class Maze {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = null;
		char[][] dir = new char[10][10];
		for (int i = 0; i < 10; i++) {
			s = sc.next();
			for (int j = 0; j < 10; j++) {
				dir[i][j] = s.charAt(j);
			}
		}
		int ans = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int[][] mark = new int[10][10];
				if (dfs(i, j, dir, mark)) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	private static boolean dfs(int x, int y, char[][] dir,int [][]mark) {

		switch (dir[x][y]) {
		case 'U':
			if(x-1<0)return true;
			else if (mark[x-1][y]==1) 
			return false;
			else {
				mark[x-1][y]=1;
				return dfs(x-1, y, dir, mark);
			}
			
		case 'D':
			if(x+1>9)return true;
			else if (mark[x+1][y]==1) 
			return false;
			else {
				mark[x+1][y]=1;
				return dfs(x+1, y, dir, mark);
			}
			
		case 'L':
			if(y-1<0)return true;
			else if (mark[x][y-1]==1) 
			return false;
			else {
				mark[x][y-1]=1;
				return dfs(x, y-1, dir, mark);
			}
			
		case 'R':
			if(y+1>9)return true;
			else if (mark[x][y+1]==1) 
			return false;
			else {
				mark[x][y+1]=1;
				return dfs(x, y+1, dir, mark);
			}
		
	}
		return true;

  }
}