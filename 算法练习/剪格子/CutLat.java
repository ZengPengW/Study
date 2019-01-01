import java.util.Scanner;


public class CutLat {
	
	private static int n;
	private static int m;
	private static int summ;
	private static int minv=999999;
	
	private static int[][] dir={
		{0,-1},
		{0,1},
		{-1,0},
		{1,0}
	};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		 m=sc.nextInt();
		 n=sc.nextInt();
		
		int [][]lat=new int[n+1][m+1];
		for (int i = 1; i <=n; i++) 
			for (int j = 1; j <=m; j++) {
				lat[i][j]=sc.nextInt();
				summ+=lat[i][j];
			}
				int [][]mark=new int[n+1][m+1];
			dfs(lat, mark, lat[1][1], 1, 1, 1);	
			
			System.out.println(minv);
		
	}

	public static void dfs(int[][]lat,int [][]mark,int sum,int x,int y,int count) {
		if(count>minv)return;
		if(summ-sum==sum){
			if(minv>count)minv=count;
			return;
		}
		
			for (int j =0; j <4; j++) {
				int tx=x+dir[j][0];
				int ty=y+dir[j][1];
				if((tx>0&&tx<=m)
					&&(ty>0&&ty<=n)
					&&mark[ty][tx]==0){
					mark[ty][tx]=1;
					dfs(lat, mark, sum+lat[ty][tx], tx, ty,count+1);
					mark[ty][tx]=0;
				}
				
			}	
	
		
			
			
			
		
		
	}

}










