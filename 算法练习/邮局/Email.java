import java.util.ArrayList;
import java.util.Scanner;


public class Email {
private static class Poi{
	int x,y;
	public Poi(int x,int y){
		this.x=x;
		this.y=y;
	}
	
}
private static ArrayList<Poi> cm;
private static ArrayList<Poi> yg;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n= sc.nextInt();
		int m= sc.nextInt();
		int k= sc.nextInt();
		cm=new ArrayList<Poi>(n);
		yg=new ArrayList<Poi>(m);
		
		int x,y;
		for (int i = 0; i < n; i++) {
			x=sc.nextInt();
			y=sc.nextInt();
			cm.add(new Poi(x, y));	
		}
		
		for (int i = 0; i < m; i++) {
			x=sc.nextInt();
			y=sc.nextInt();
			yg.add(new Poi(x, y));
			
		}
		mindis(n, m);
		int []arr=new int[k];
		dfs(k, m, 0, 0, arr);
		for (int i : num) {
			System.out.print((i+1)+" ");
		}
	}
public static void dfs(int k,int m,int index,int id,int []arr) {
	if(id==k){
		f(arr);
		return;
	}
	for (int i =index; i < m; i++) {
		arr[id]=i;
		dfs(k, m,i+1,id+1,arr);
		
	}
}
private static double min=999999;
private static int [] num;
public static void f(int []arr) {
	

	double ds,minds=999999,sum=0;
	for (int j = 0,len2=cm.size(); j < len2; j++) {
		minds=999999;
		for (int i = 0,len1=arr.length; i < len1; i++) {
			ds=mindis[j][arr[i]];
			if(minds>ds)minds=ds;
		}
		sum+=minds;
		if(sum>min)return;
	}
	

	
	
		min=sum;
		num=arr.clone();
	
		
}
private static double[][]mindis;
public static void mindis(int n,int m) {
	mindis=new double[n][m];
	Poi p1,p2;
	double ds;
	for (int i = 0; i <n; i++) {
		p1=cm.get(i);
		
		for (int j = 0; j < m; j++) {
			p2=yg.get(j);
			ds=dis(p1, p2);
			mindis[i][j]=ds;
		}
		
		
	}
}
public static double dis(Poi p1,Poi p2) {
	double x1=p1.x;
	double x2=p2.x;
	double y1=p1.y;
	double y2=p2.y;
	
	return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
}

}
