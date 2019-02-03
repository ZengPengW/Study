import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Ring {
	private static class Node{
		ArrayList<Integer> al;
		public Node() {
			al=new ArrayList<Integer>();
		}
	}
	private static ArrayList<Integer> th=new ArrayList<Integer>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Node[]dis=new Node[100001];
		int []fa=new int[100001];
		for (int i =1; i <100001 ; i++) {
			fa[i]=i;
			dis[i]=new Node();
		}
		for (int i = 0; i < n; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			dis[a].al.add(b);
			dis[b].al.add(a);
			int f1=checkFa(fa, a);
			int f2=checkFa(fa, b);
			if(f1!=f2){
				fa[f2]=f1;
			}else {
				dis[a].al.remove(dis[a].al.size()-1);
				dis[b].al.remove(dis[b].al.size()-1);
				th.add(a);
				dfs(a, b, dis);
			}
		}	
	}

	public static void dfs(int st,int ed,Node[]dis) {
		if(st==ed){
			Collections.sort(th,new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					if(o1>o2)return 1;
					else if(o1<o2)return -1;
					return 0;
				}
			});
			for (Integer i : th) {
				System.out.print(i+" ");
			}
			System.exit(0);
		}
		for (int i =dis[st].al.size()-1; i >=0; i--) {
				int v=dis[st].al.get(i);
				if(!th.contains(v)){
					th.add(v);
					dfs(v, ed, dis);
					th.remove(th.size()-1);	
				}	
		}
		
	}
	public static int checkFa(int []fa,int x) {
		if(fa[x]==x)return x;
		else
			fa[x]=checkFa(fa, fa[x]);
		return fa[x];
	}

}
