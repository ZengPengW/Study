import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class Ring
{
	private static class Node{
		ArrayList<Integer> next=new ArrayList<Integer>();	//能到的点	
	}
	private static int n;
	public static void main(String[] args) throws IOException {		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String []read=in.readLine().split(" ");
		n=Integer.valueOf(read[0]);		
		int []fa=new int[n+1];
		for (int i =1; i <=n; i++) {
			fa[i]=i;
		}
		
		Node[] dir=new Node[n+1];
		int j=n;
		while (j-->0) {
			read=in.readLine().split(" ");
			int a=Integer.valueOf(read[0]);	
			int b=Integer.valueOf(read[1]);	
			int f1=fa(fa, a);
			int f2=fa(fa, b);
			if(f1!=f2){				
				fa[f2]=f1;
				if(dir[a]==null)dir[a]=new Node();
				if(dir[b]==null)dir[b]=new Node();
				dir[a].next.add(b);
				dir[b].next.add(a);
			}else {
				int []mark=new int[n+1];
				mark[a]=1;
				dfs(mark, dir, a, b);
				return;
			}
		}
	}
	public static void dfs(int []mark,Node[] dir,int st,int ed) {
		if(st==ed){
			for (int i = 1; i <=n; i++) {
				if(mark[i]==1)
					System.out.print(i+" ");
			}
			System.exit(0);
		}
		if(dir[st]==null)return;
		List<Integer> go=dir[st].next;
		for (Integer i : go) {
			if(mark[i]==0){
				mark[i]=1;
				dfs(mark, dir, i, ed);
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