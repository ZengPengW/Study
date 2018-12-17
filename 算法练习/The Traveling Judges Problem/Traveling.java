import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Traveling {
	private static class Edge{
		int c1;
		int c2;
		int v;
		public Edge(int c1,int c2,int v) {
			this.c1=c1;
			this.c2=c2;
			this.v=v;
		}
	}
	private static int nc;
	private static int dc;
	private static int nr;
	private static int nj;
	private static int [][]book;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		nc=sc.nextInt();
		dc=sc.nextInt();
		nr=sc.nextInt();
		book=new int[nc+1][nc+1];//初始化标记
		ArrayList<Edge> edge=new ArrayList<Edge>(nr);
		for (int i = 0; i <nr; i++) 
			edge.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		
		nj=sc.nextInt();
		int []st=new int[nj];
		for (int i = 0; i <nj; i++) 
			st[i]=sc.nextInt();
	
		f(st, edge);
	}
	
	public static void f(int []st,ArrayList<Edge> edge){
		Collections.sort(edge, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if(o1.v<o2.v)return -1;
				else if (o1.v>o2.v) return 1;
				return 0;
			}
		});
		
		
		
		
		int []fa=new int[nc+1];
		for (int i = 0; i < fa.length; i++) 
			 fa[i]=i;
		
		//Queue<Edge> que=new LinkedList<Edge>();
		int [][]road=new int[nc+1][nc+1];
		int count=0;
		Edge e=null;
		for (int i = 0; i <nr; i++) {
			e=edge.get(i);
			int f1=checkfa(fa, e.c1);
			int f2=checkfa(fa, e.c2);
			
			if(f1!=f2){
				fa[f2]=f1;
				road[e.c1][e.c2]=e.v;
				road[e.c2][e.c1]=e.v;
				count++;
			}
			if(count==nc-1)break;
		}
		
		String []s=new String[nj];
		String []ch;
		for (int i = 0; i < st.length; i++) {
			int sta=st[i];
			int[] mark=new int[nc+1];
			mark[sta]=1;
			lujin="";
			minsum=999999;
			for (int j = 0; j <nr; j++) {
				e=edge.get(j);
				if(e.c1==sta||e.c2==sta){
					road[e.c1][e.c2]=e.v;
					road[e.c2][e.c1]=e.v;
				}
			}
			
			dfs(sta, road, mark, 0,Integer.toString(sta));
			s[i]=lujin;
			ch=s[i].split("-");
			for (int j =1; j <ch.length; j++) {
				int a=Integer.valueOf(ch[j-1]);
				int b=Integer.valueOf(ch[j]);
					book[a][b]=1;	
				
			}
			
		
		}
		
		int [][]mark=new int[nc+1][nc+1];
		int sum=0;
		for (int i = 0; i < s.length; i++) {
			ch=s[i].split("-");
			for (int j =1; j <ch.length; j++) {
				int a=Integer.valueOf(ch[j-1]);
				int b=Integer.valueOf(ch[j]);
				if(mark[a][b]==0){
					sum+=road[a][b];
					mark[a][b]=1;	
				}
			}
			
		}
		System.out.println("distance = "+sum);
		
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
		
		
	}
	private static String lujin="";
	private static int minsum=999999;
	
	private static void dfs(int sta,int [][]road,int[] mark,int sum,String lj) {
		if(sta==dc){
			if(sum<minsum){
				minsum=sum;
				lujin=lj;
			}
			return;
		}
		
		for (int i =1; i <=nc; i++) {
			if(road[sta][i]>0&&mark[i]==0){
				mark[i]=1;	
				if(book[sta][i]==0){
					sum+=road[sta][i];
				}
				dfs(i, road, mark,sum,lj+"-"+i);
				if(book[sta][i]==0){
					sum-=road[sta][i];
				}
				mark[i]=0;
			}
			//if(!lujin.equals(""))return;
		}
		
	
		
	}
	
	private static int checkfa(int []fa,int index) {
		if(fa[index]==index)return index;
		else {
			return checkfa(fa, fa[index]);
		}
	}
}









