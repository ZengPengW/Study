
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Separation  {
	private static final int max=99999;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int p,r;
		int [][]dis;
		int []mark;
		String s1,s2;
		int ans=1;
		
		while(true){
			p=sc.nextInt();
			r=sc.nextInt();
			if(p==0&&r==0)break;
			dis=new int[p+1][p+1];
			mark=new int[p+1];
			for (int i = 0; i <=p; i++) {
				mark[i]=i;
				for (int j = 0; j <=p; j++) {
					if(i==j)continue;
					dis[i][j]=max;
					
				}
			}
			
			
			Map<String, Integer> map=new HashMap<String,Integer>();
			sc.nextLine();
			ans=1;
			for (int i = 0; i < r; i++) {
				s1=sc.next();
				s2=sc.next();
				if(!map.containsKey(s1))map.put(s1, ans++);
				if(!map.containsKey(s2))map.put(s2, ans++);
				int a=map.get(s1);
				int b=map.get(s2);
				
				
				if(a!=b){
					dis[a][b]=1;
					dis[b][a]=1;
				}
			}
			sc.nextLine();

			floyd(dis);
			
			
		}
	}

	private static int number=1;
	private static void floyd(int[][] dis) {
		int len=dis.length;
		for (int i = 1; i <len; i++) {
			for (int j = 1; j < len; j++) {
				if(i==j)continue;
				for (int k = 1; k < len; k++) {
					if(dis[j][k]>dis[j][i]+dis[i][k]){
						dis[j][k]=dis[j][i]+dis[i][k];
						dis[k][j]=dis[j][k];
					}
				}
			}
			
		}
		int mmax=-9999999;
		for (int i = 1; i < len; i++) {
			for (int j = i+1; j <len; j++) {
			 mmax=dis[i][j]>mmax?dis[i][j]:mmax;	
			}
		}
		
			if(mmax!=max)System.out.println("Network "+(number++)+": "+mmax);
			else System.out.println("Network "+(number++)+": DISCONNECTED");
		System.out.println();
		
	}
	
	

}
