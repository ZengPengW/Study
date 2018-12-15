
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class BusTour {
	
	private static int gomin=Integer.MAX_VALUE;
	private static int backmin=Integer.MAX_VALUE;
	
	private static int[][]num;
	private static int n;
	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		int m=scan.nextInt();
		num=new int[n][n];
		for (int i = 0; i <n; i++) {
			for (int j = i; j <n; j++) {
				if(i!=j){
				num[i][j]=999999;
				num[j][i]=999999;
				}
			}
			
		}
		
		for (int i = 0; i <m; i++) {
			int u=scan.nextInt();
			int v=scan.nextInt();
			int t=scan.nextInt();
			
			num[u][v]=t;
			num[v][u]=t;
		}
		
		floyd(n);
		int []mark=new int[n];
		dfsFrontHf(0, (n-2)/2, 0, mark, 0);
		
		mark=new int[n];
		dfsFrontHfBk(n-1, (n-2)/2, 0, mark, 0);
		
		System.out.println(gomin+backmin);
	}

	
	
	public static void floyd(int n) {
		for (int i = 0; i <n; i++) {
			for (int j = 0; j <n; j++) {
				if(num[i][j]!=999999){
					for (int k = 0; k <n; k++) {
						num[i][k]=Math.min(num[i][k], num[i][j]+num[j][k]);
					}	
				}		
			}		
		}
	}
	
	public static void dfsFrontHf(int st,int mid,int sum,int []mark,int index) {
		 if(index==mid){
			 dfsLatterHf(st,mid,n-2, sum, mark, index);
			 return;
		 }
		for (int i = 1; i <=mid; i++) {
			if(mark[i]==0){
				mark[i]=1;
				dfsFrontHf(i,mid, sum+num[st][i], mark,index+1);
				mark[i]=0;
			}
			
		}
	}
	
	public static void dfsLatterHf(int st,int mid,int ed,int sum,int []mark,int index) {
		
		if(index==ed){
			sum+=num[st][n-1];
			if(sum<gomin)gomin=sum;
			return;
		}
		for (int i =mid+1; i <=ed; i++) {
			if(mark[i]==0){
				mark[i]=1;
				dfsLatterHf(i, mid,ed, sum+num[st][i], mark, index+1);
				mark[i]=0;
			}
			
		}
		
	}
	
	public static void dfsFrontHfBk(int st,int mid,int sum,int []mark,int index) {
		if(index==mid){
			dfsLatterHfBk(st, mid, n-2, sum, mark, index);
			return;
		}
		for (int i =1; i <=mid; i++) {
			if(mark[i]==0){
				mark[i]=1;
				dfsFrontHfBk(i, mid, sum+num[st][i], mark, index+1);
				mark[i]=0;
			}
			
		}
		
	}
	
	
	public static void dfsLatterHfBk(int st,int mid,int ed,int sum,int []mark,int index) {
		if(index==ed){
			sum+=num[st][0];
			if(sum<backmin)backmin=sum;
			return;
		}
		for (int i =mid+1; i <=ed; i++) {
			if(mark[i]==0){
				mark[i]=1;
				dfsLatterHfBk(i,mid,ed, sum+num[st][i], mark, index+1);
				mark[i]=0;
			}
			
		}
	}
	
	
	
}
