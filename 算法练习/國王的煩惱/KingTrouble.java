import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KingTrouble {
	private static int maxt=0;
	private static class Edge implements Comparable<Edge>{
		int a,b,t;
		public Edge(int a,int b,int t){
			this.t=t;
			this.a=a;
			this.b=b;
		}
		@Override
		public int compareTo(Edge o) {
			if(o.t<t)return -1;
			else if(o.t>t)return 1;
			return 0;
		}
	}
	private static int n;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		int m=sc.nextInt();
		ArrayList<Edge> al=new ArrayList<Edge>();
		int []fa=new int[n+1];
		for (int i = 0; i < fa.length; i++) 
			fa[i]=i;
			
		
		for (int i = 0; i < m; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int t=sc.nextInt();
			al.add(new Edge(a, b, t));
			
			
		}
		Collections.sort(al);
		
		Edge e;
		int day=-10,sum=0;
		for (int i = 0; i < m; i++) {
			e=al.get(i);
			if(!setFa(fa, e.a, e.b)&&day!=e.t){
				sum++;
				day=e.t;
			}
			
		}
		System.out.println(sum);
	}
	public static boolean setFa(int[] fa,int a,int b) {
		fa[a]=checkFa(a, fa);
		fa[b]=checkFa(b, fa);
		if(fa[a]!=fa[b]){
			fa[fa[b]]=fa[a];
			//fa[b]=fa[a];
			return false;
		}
		return true;
		
	}
	public static int checkFa(int x,int []fa) {
		if(fa[x]==x)return x;
		else 
			fa[x]= checkFa(fa[x], fa);
		
		return fa[x];
	}

}
