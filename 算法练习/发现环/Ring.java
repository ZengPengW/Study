import java.util.Scanner;
public class Ring
{
	private static int n;
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		n=in.nextInt();
		
	
		
		int []fa=new int[n+1];
		for (int i =1; i <=n; i++) {
			fa[i]=i;
		}
		
		int[][]dis=new int[n+1][n+1];
		for (int i = 0; i <n; i++) {
			int a=in.nextInt();
			int b=in.nextInt();
			
			int f1=fa(fa, a);
			int f2=fa(fa, b);
			if(f1!=f2){
				dis[a][b]=1;
				dis[b][a]=1;
				fa[f2]=f1;
			}else {
				int []mark=new int[n+1];
				mark[a]=1;
				dfs(mark, dis, a, b);
				return;
			}
		}
	}
	public static void dfs(int []mark,int [][]dis,int st,int ed) {
		if(st==ed){
			for (int i = 1; i <=n; i++) {
				if(mark[i]==1)
					System.out.print(i+" ");
			}
			System.exit(0);
		}
		
		for (int i =1; i <=n; i++) {
			if(dis[st][i]==1&&mark[i]==0){
				mark[i]=1;
				dfs(mark, dis, i, ed);
				mark[i]=0;
			}
		}
		
	}
	public static int fa(int[]fa ,int x) {
		if(fa[x]==x)return x;
		else{
			fa[x]=fa(fa, fa[x]);
			return fa[x];
		}
	}
}