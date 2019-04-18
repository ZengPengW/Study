import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Metal{
private static class Node{
	
	ArrayList<Integer> to=new ArrayList<Integer>();
	ArrayList<Integer> w=new ArrayList<Integer>();
}
private static int n,S,K;
private static Node[] node;
	public static void main(String[] args) throws IOException {
		//Scanner scan=new Scanner(System.in);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] sread;
		sread=br.readLine().trim().split(" ");
		n=Integer.parseInt(sread[0]);
		S=Integer.parseInt(sread[1]);
		K=Integer.parseInt(sread[2]);
		int x,y,w;
		 node=new Node[n+1];
		for (int i = 0; i < node.length; i++) {
			node[i]=new Node();
		}
		for (int i = 1; i <n; i++) {
			sread=br.readLine().trim().split(" ");
			x=Integer.parseInt(sread[0]);
			y=Integer.parseInt(sread[1]);
			w=Integer.parseInt(sread[2]);
			node[x].to.add(y);
			node[x].w.add(w);
			node[y].to.add(x);
			node[y].w.add(w);
		}
		
		dfs(S);
		System.out.println(dp[S][K]);
		
	}
	private static int dp[][]=new int [100005][12];
	private static int []mark=new int [100005];
	public static void dfs(int p) {
		mark[p]=1;
		
		int tonext,w,remain;
		
		ArrayList<Integer> to=node[p].to;
		ArrayList<Integer> ww=node[p].w;
		for (int i = 0; i <to.size(); i++) {
			tonext=to.get(i);
			if(mark[tonext]==1)continue;
			w=ww.get(i);
			dfs(tonext);
			
			for (int j =K; j>=0; j--) {
				dp[p][j]+=dp[tonext][0]+w*2;
				for (remain =1; remain <=j; remain++) {
					dp[p][j]=Math.min(dp[p][j], dp[p][j-remain]+dp[tonext][remain]+remain*w);
				}
			}
			
			
			
			
			
			
			
		}
		
		
		
		
	}
	
}
