import java.util.Scanner;


public class Minister {
	private static int n;
	private static int max=0;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		int [][]dis=new int[n+1][n+1];
		for (int i = 1; i < n; i++) {
			int Pi=sc.nextInt();
			int Qi=sc.nextInt();
			int Di=sc.nextInt();
			dis[Pi][Qi]=Di;
			dis[Qi][Pi]=Di;
		}
		long ks=System.currentTimeMillis();
		int [][]mark=new int[n+1][n+1];
		
			for (int j = 1; j <=n; j++) {
				dfs(dis, mark,j,0);
			}
			
			int sum=0;
			for (int i = 1,v=11; i <=max; i++,v++) 
				sum+=v;
				
			
		System.out.println(sum);	
		System.out.println(System.currentTimeMillis()-ks);
		
	}
	public static void dfs(int [][]dis,int [][]mark,int start,int sum) {
		
		if(sum>max)max=sum;
		for (int i = 1; i <=n; i++) {
			if(i!=start&&mark[start][i]==0&&dis[start][i]>0){
				mark[start][i]=1;
				mark[i][start]=1;
				dfs(dis, mark, i, sum+dis[start][i]);
				mark[start][i]=0;
				mark[i][start]=0;
			}
			
		}
		
	}

}
