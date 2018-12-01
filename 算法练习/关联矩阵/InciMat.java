import java.util.Scanner;

public class InciMat{
	private static int[][] edge;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		int m=scan.nextInt();
		edge=new int[n+1][m+1];
		for (int i = 1; i <=m; i++) {
			int a=scan.nextInt();
			int b=scan.nextInt();
			edge[a][i]=1;
			edge[b][i]=-1;
		}
		
		for (int i = 1; i < edge.length; i++) {
			for (int j = 1; j < edge[i].length; j++) 
				System.out.print(edge[i][j]+" ");
			
			System.out.println();
		}
		
	}
}