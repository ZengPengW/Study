import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class CityBuild {

	private static class Poi implements Comparable<Poi>{
		int a,b,c;
		public Poi(int a,int b,int c){
			this.a=a;
			this.b=b;
			this.c=c;
		}
		@Override
		public int compareTo(Poi o) {
			if(c<o.c)return -1;
			else if (c>o.c) return 1;
			return 0;
		}
	}
	private static ArrayList<Poi> al;
	private static ArrayList<Poi> als;
	
	private static int n;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		int m=sc.nextInt();
		al=new ArrayList<Poi>();
		for (int i = 0; i < m; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			
			al.add(new Poi(a, b, c));
			
		}
		als=(ArrayList<Poi>) al.clone();
		
		int id=n+1;
		for (int i = 1; i <=n; i++){
			int c=sc.nextInt();
			if(c!=-1)als.add(new Poi(i, id, c));	
		}
		f(al);
		f(als);
		System.out.println(minv);
		
	}
private static int minv=999999999;
public static void f(ArrayList<Poi> arr) {
	Collections.sort(arr);
	int []fa=new int [n+2];
	for (int i = 0,len=fa.length; i < len; i++) 
		fa[i]=i;
		
	Poi p;
	int a,b,c,f1,f2,sum=0,i=0;
	int count=n-1,id=0;
	
	for (int len=arr.size();i<len;i++) 	
	  {
		p=arr.get(i);
		a=p.a;b=p.b;c=p.c;
		f1=checkFA(a, fa);
		f2=checkFA(b, fa);
		if(f1!=f2){
			fa[f2]=fa[f1];
			sum+=c;
			id++;	
		 }else if (c<0) {			
			sum+=c;
		}
		
	}

		if(id<count)return;
		if(minv>sum)minv=sum;

}
	
public static int checkFA(int x,int[]fa){
	if(fa[x]==x)return x;
	else {
		fa[x]=checkFA(fa[x], fa);
		return fa[x];
	}
}

}
