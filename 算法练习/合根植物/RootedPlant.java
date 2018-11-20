
import java.util.Scanner;

public class RootedPlant{
	private static int m;
	private static int n;
	private static int [] map;
	private static int [][] arr;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		 m=sc.nextInt();
		 n=sc.nextInt();
		int k=sc.nextInt();
		arr=new int[k][2];
		for (int i = 0; i < arr.length; i++) {
			arr[i][0]=sc.nextInt();
			arr[i][1]=sc.nextInt();
			
		}
		map=new int[m*n+1];
		for (int i = 1; i < map.length; i++) {
			map[i]=i;
		}
   		f();
	}
	public static void f() {
		int sum=0;
		for (int i = 0; i < arr.length; i++) {
			
			int f1=dfs(arr[i][0]);
			int f2=dfs(arr[i][1]);
			if (f1!=f2) {
				map[f2]=map[f1];
			}
			
		}
		for (int i = 1; i < map.length; i++) {
			if (map[i]==i) {
				sum++;
			} 
			
		}
		System.out.println(sum);
	}
	public static int dfs(int a) {
		
		int b=map[a];
		if (b==a) {
			return b;
		}
		
		return dfs(map[b]);
		
		
	}

}
