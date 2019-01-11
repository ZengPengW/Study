import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class DangerCoefficient {

	private static int n;
	private static int m;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		int[][] dis=new int[n+1][n+1];
		for (int i = 0; i < m; i++) {
			int u=sc.nextInt();
			int v=sc.nextInt();
			dis[u][v]=1;
			dis[v][u]=1;
		}
		int x=sc.nextInt();
		int y=sc.nextInt();
		DF(x, y, dis);
		
	}
	
	private static HashMap<Integer, Integer> hm=new HashMap<Integer, Integer>();
	private static int count =0;//可走路数 
	public static void DF(int x,int y,int [][]dis) {
		int []ro=new int[1002];
		int[]mark=new int[n+1];
		mark[x]=1;
		dfs(x, y, dis, mark, ro, 0);
		
		int sum=0;
		for (int i : hm.keySet()) {
			if(hm.get(i)==count)sum++;
		}
		System.out.println(sum-1);
	}
	private static void dfs(int x,int y,int[][]dis,int []mark,int []ro,int index) {
	
		if(x==y){
			count++;
			for (int i = 0,len=ro.length; i < len; i++) {
				if(ro[i]>0){
					if(hm.containsKey(ro[i]))
						hm.put(ro[i], hm.get(ro[i])+1);
					else 
						hm.put(ro[i], 1);
				}
				else break;
			}
			return;
		}
		
		for (int i =1; i <=n; i++) {
			if(dis[x][i]>0&&mark[i]==0){
				ro[index]=i;
				mark[i]=1;
				dfs(i, y, dis, mark, ro, index+1);
				mark[i]=0;
				ro[index]=0;
			}
			
		}
	}

}












