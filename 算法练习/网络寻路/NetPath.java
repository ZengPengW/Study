import java.util.Scanner;

public class NetPath {
	private static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		int[][] dis = new int[n + 1][n + 1];

		int u, v;
		for (int i = 0; i < m; i++) {
			u = sc.nextInt();
			v = sc.nextInt();
			dis[u][v] = 1;
			dis[v][u] = 1;
		}
		int[] mark = new int[n + 1];
		long ks = System.currentTimeMillis();
		for (int i = 1; i <= n; i++) {
			mark[i] = 1;
			dfs(dis, mark, i, i, 1);
			mark[i] = 0;

		}

		System.out.println(sum);
		System.out.println(System.currentTimeMillis() - ks);
	}

	
	
	private static int sum=0;
	public static void dfs(int[][] dis, int[] mark, int cur, int start,int index) {
		if (index == 4) {
			sum+=1;
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			
			if ((dis[cur][i] == 1 && mark[i] == 0)) {
				mark[i] = 1;
				dfs(dis, mark, i, start, index + 1);
				mark[i] = 0;
				
			} else if (dis[cur][i] == 1 && i == start && index == 3) {
				mark[i] = 1;
				dfs(dis, mark, i, start, index + 1);
			}

		}
	}


}
