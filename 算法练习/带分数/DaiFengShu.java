import java.util.Scanner;

public class DaiFengShu {
	private static int count = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n =sc.nextInt();
		int[] num = {1,2,3,4,5,6,7,8,9};
		long ks=System.currentTimeMillis();
		dfs(n,num, 0);
		System.out.println(System.currentTimeMillis()-ks);
		System.out.println(count);
	}

	public static void dfs(int n, int[] num, int index) {
		if (index >= 8) {
			check(num, n);
			return;
		}

		for (int i =index; i < 9; i++) {
			int temp=num[index];
			num[index] = num[i];
			num[i]=temp;
			dfs(n,num, index+1);
			temp=num[index];
			num[index] = num[i];
			num[i]=temp;
		}
	}

	public static void check(int[] num, int n) {
		for (int i = 0; i <7; i++) {
			int zs=op(num, 0, i);//整数
			if(zs>=n)return;
			int v=n-zs;
			for (int j =i+1; j <9; j++) {
				int fz=op(num, i+1, j);//分子
				int fm=op(num, j+1, 8);//分母
				if(fz<fm)continue;
				if(fz==fm*v)count++;
				
			}
			
		}

	}
	private static int op(int []num ,int st,int ed){
		int temp = 0;
		for (int i =st; i <=ed; i++) 
			temp=temp*10+num[i];
			
		return temp;	
		
	}
	
	
}
