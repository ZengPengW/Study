import java.util.Scanner;


public class Global {
	private static int [][]mark;
	private static int [][]dir={
		{-1,0},
		{1,0},
		{0,-1},
		{0,1}
		};
	private static int n;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n =sc.nextInt();
		char[][]map=new char[n][n];
		
		for (int i =0; i <n; i++) {
			map[i]=sc.next().toCharArray();		
		}
		
		mark=new int[n][n];
		int sum=0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[i][j]=='#'&&mark[i][j]==0){
					index=0;
					dfs(map, i, j);
					if(index>0)sum++;
				}
				
			}	
		}
		System.out.println(sum);
	}
	private static int index=0;
	public static void dfs(char[][]map,int x,int y) {
		
		
			mark[x][y]=1;
			boolean flag=false;
			for (int i = 0; i <4; i++) {
				if(x+dir[i][0]>=0&&x+dir[i][0]<n
						&&y+dir[i][1]>=0&&y+dir[i][1]<n
						&&mark[x+dir[i][0]][y+dir[i][1]]==0){
					
					
					if(map[x+dir[i][0]][y+dir[i][1]]=='#'){
						dfs(map, x+dir[i][0], y+dir[i][1]);
					}else {
						flag=true;
					}
					
					
				}
				
			}
			if(!flag)index++;
		
	}

}
