import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Stack {
	private static class Node{
		List<Integer> ls=new ArrayList<Integer>();
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(sc.hasNext()) {
			int n=sc.nextInt();
			Set<Integer> val=new HashSet<Integer>();
			Node []plates=new Node[n+1];
			for (int i = 1; i <=n; i++) {
				int hi=sc.nextInt();
				int x;
				plates[i]=new Node();
				for (int j = 0; j <hi; j++) {
					x=sc.nextInt();
					plates[i].ls.add(x);
					val.add(x);
				}
			}
			
			DP(n,plates,val);
		}
	}
	
	public static int discrete(Set<Integer> val, int []id) {
		int ans=0;
		for (int i : val) {
			id[i]=++ans;
		}
		return ans;
	}
	//https://blog.csdn.net/hao_zong_yin/article/details/79807322
	public static void DP(int n,Node []plates,Set<Integer> val) {
		int []id=new int [10002];
		int ans=discrete(val, id);
		int x;
		int vis[][]=new int[51][2501];
		Node[]heap=new Node[2501];
		for (int i = 1; i <=n; i++) {
			for (int j = plates[i].ls.size()-1; j>=0 ; j--) {
				x=id[plates[i].ls.get(j)];
				if(heap[x]==null)heap[x]=new Node();
				if (vis[i][x]==0) {
					vis[i][x]=1;
					heap[x].ls.add(i);
				}
			}
		}
		
		int [][]dp=new int[2501][51];
		for (int i = 0; i < 2501; i++) {
		for (int j = 0; j < 51; j++) {
			dp[i][j]=9999999;
		}	
		}
		for (int i = 0; i < heap[1].ls.size(); i++) {
			dp[1][heap[1].ls.get(i)]=heap[1].ls.size()-1;
		}
		
		
		for (int i = 2; i <=ans; i++) {
			for (int a = 0; a < heap[i].ls.size(); a++) {
				for (int b = 0; b < heap[i-1].ls.size(); b++) {
					int j=heap[i].ls.get(a);
					int k=heap[i-1].ls.get(b);
					int cut=heap[i].ls.size();
					if(j==k)dp[i][j]=Math.min(dp[i][j], dp[i-1][j]+(cut==1?0:cut));
					else dp[i][j] = Math.min(dp[i][j], dp[i-1][k]+cut-1+(vis[k][i]==1?0:1));
				}
			}
		}
		
		
		int count=Integer.MAX_VALUE;
		for (int i = heap[ans].ls.size()-1; i >=0; i--) {
			count=Math.min(count, dp[ans][heap[ans].ls.get(i)]);
		}
		
		
		System.out.println("Case "+(cases++)+": "+(count*2-n+1));
	
		
	}
	private static int cases=1;
}
