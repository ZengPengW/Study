import java.util.Scanner;

public class Sudoku {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		String str;
		int [][]num=new int [10][10];
		int [][]markx=new int [10][10];
		int [][]marky=new int [10][10];
		int [][][]mark=new int[4][4][10];
		for (int i = 1; i <=9; i++) {
			str=sc.next();
			for (int j = 1; j <=9; j++) {
				num[i][j]=str.charAt(j-1)-'0';
				markx[i][num[i][j]]=1;
				marky[j][num[i][j]]=1;

				int idx=(int) Math.ceil(i/3.0);
				int idy=(int) Math.ceil(j/3.0);
				mark[idx][idy][num[i][j]]=1;
			}
		}
		
		dfs(num,mark, markx,marky, 1, 1);
	}

	private static void dfs(int [][]num,int [][][]mark,int [][]markx,int [][]marky,int x,int y) {
		if(x>9) {
			for (int i = 1; i <=9; i++) {
			for (int j = 1; j <=9; j++){
				System.out.print(num[i][j]);
			}
			System.out.println();
			}
			return;
		}
		if(num[x][y]==0) {
			
			int idx=(int) Math.ceil(x/3.0);
			int idy=(int) Math.ceil(y/3.0);
					
			for (int j = 1; j <=9; j++) {
				
				if(markx[x][j]==0&&marky[y][j]==0&&mark[idx][idy][j]==0) {
					num[x][y]=j;
					markx[x][j]=1;
					marky[y][j]=1;
					mark[idx][idy][j]=1;
					if(y+1<=9) {
						dfs(num,mark, markx,marky, x, y+1);
					}else if (y+1>9) {
						dfs(num,mark, markx,marky, x+1, 1);
					}
					num[x][y]=0;
					markx[x][j]=0;
					marky[y][j]=0;
					mark[idx][idy][j]=0;
				}
			}
		}else {
			if(y+1<=9) {
			dfs(num,mark, markx,marky, x, y+1);
			}else if (y+1>9) {
				y=1;
				x=x+1;
				dfs(num,mark, markx,marky, x, y);
			}
		}
		
	}
}
