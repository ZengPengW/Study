import java.util.Scanner;

public class LakeVex {	
	private static int count=0;
	private static int[] mark;
	private static int n;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int m=scan.nextInt();
		 n=scan.nextInt();
		int [] num=new int[m+n];
		if(m>=n) {
			for (int i = 0; i <m+n; i++) 
			num[i]=1;
		}else {
			System.out.println(0);
			return;
		}
		
		mark=new int [m+n];
		dfs(num, 0,1);
		System.out.println(count);
		
	}
	public static void dfs(int []num,int id,int index) {
		
		if(id>=n&&check(num)) {
			count++;
			return;
		}
		
		for (int i =index; i < num.length; i++) {
			if(mark[i]==0) {
			num[i]=-1;
			mark[i]=1;
			dfs(num, id+1,i+1);
			num[i]=1;
			mark[i]=0;
			}
		}
	}
	
	
	public static boolean check(int []num) {
		
		int sum=0;
		for (int i = 0; i < num.length; i++) {
			sum+=num[i];
			if(sum<0)return false;
		}
		return true;
		
		
		
		
		
	}
}

