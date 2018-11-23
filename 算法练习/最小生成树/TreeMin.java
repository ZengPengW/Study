package À¶ÇÅ±­;

import java.util.Arrays;
import java.util.Scanner;

public class TreeMin {
	private static class Edge implements Comparable<Edge>{
			 int sj,ej,lj;
			@Override
			public int compareTo(Edge o) {
				if(o.lj==this.lj)return 0;
				return o.lj>this.lj?-1:1;
			}
		}
	private static int[] ci; 
	private static int n;
	private static Edge[] edge;
	private static int [] node;
	private static int min=9999;
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		n=scan.nextInt();
		int p=scan.nextInt();
		node=new int[n+1];
//		for (int i = 0; i < node.length; i++) {
//			node[i]=i;
//		}
		edge=new Edge[p+1];
		edge[0]=new Edge();
		ci=new int[n+1];
		for (int i = 1; i <n+1; i++) {
			ci[i]=scan.nextInt();
			if(min>ci[i])min=ci[i];
			node[i]=i;
		}
		int sj,ej,lj;
		for (int i =1; i <=p; i++) {
			edge[i]=new Edge();
			sj=scan.nextInt();
			ej=scan.nextInt();
			lj=scan.nextInt();
			edge[i].sj=sj;
			edge[i].ej=ej;
			edge[i].lj=lj*2+ci[sj]+ci[ej];
			
		}
		Arrays.sort(edge);
		
		setTree();
		
//		for (int i = 1; i <p+1; i++) {
//			System.out.println(edge[i].toString());
//		}
		
		
		
	}
	
	public static void setTree() {
		int sum=0;
		int index=0;
		for (int i = 1; i < edge.length; i++) {
			if(setfa(edge[i].sj, edge[i].ej)){
				sum+=edge[i].lj;
				index++;
				if (index==n-1) break;
			}	
		}
		System.out.println(sum+min);
		
	}
	public static int findroot(int id) {
		if(node[id]==id)return id;
		else 
		return findroot(node[id]);
	}
	
	public static boolean setfa(int a,int b) {
		int f1=findroot(a);
		int f2=findroot(b);
		
		if (f1!=f2) {
			node[f2]=f1;
			return true;
		}else 
			return false;
			
	}

	
}
