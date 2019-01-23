import java.util.Scanner;

public class Treasure {
	private static int [][]dir={
		{0,1},
		{1,0}
	};
	
	private static final int MOD=1000000007; 
	private static  int n; 
	private static  int m; 
	private static  int k; 
	private static  int count=0; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 n = sc.nextInt();
		 m = sc.nextInt();
		 k = sc.nextInt();
		
		int [][]poi=new int[n][m];
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < m; j++) {
				poi[i][j]=sc.nextInt();			
			}
		n--;m--;
	
		dfs(poi,-1,0, 0, -1);
		System.out.println(count%MOD);
	}
	
	public static void dfs(int [][]poi,int x,int y,int ans,int maxk) {
		//if(ans>k)return;
		if(ans==k&&x==n&&y==m){
			count=(count+1)%MOD;
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			int curx=dir[i][0]+x;
			int cury=dir[i][1]+y;
			if(curx>=0&&curx<=n
					&&cury>=0&&cury<=m){
				if(ans<k&&maxk<poi[curx][cury]){
					dfs(poi,curx, cury, ans+1, poi[curx][cury]);
					dfs(poi, curx, cury, ans, maxk);
				}else {
					dfs(poi, curx, cury, ans, maxk);
				}
				
			}
			
		}
	}
	

}
