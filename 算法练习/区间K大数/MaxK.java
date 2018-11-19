import java.util.Scanner;
public class MaxK{

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		int n =scan.nextInt();
		int []num=new int[n];
		for (int i = 0; i < num.length; i++) {
			 num[i]=scan.nextInt();
		}
		int m=scan.nextInt();
		int [][]list=new int[m][3];
		for (int i = 0; i < m; i++) {
			list[i][0]=scan.nextInt();
			list[i][1]=scan.nextInt();
			list[i][2]=scan.nextInt();
			f(num,list[i]);
		}
		
	}
	
	public static void f(int[]num,int []li) {
		int []arr=new int[li[1]-li[0]+1];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=num[i+li[0]-1];
		}
		Qksort(arr, 0, arr.length-1);
		System.out.println(arr[li[2]-1]);
	}
	private static int qf(int []num ,int lo,int hi) {
		int temp=num[lo];
		
		while (lo<hi) {
			while(lo<hi&&num[hi]<=temp)hi--;
			num[lo]=num[hi];
			while(lo<hi&&num[lo]>=temp)lo++;
			num[hi]=num[lo];
		}
		num[hi]=temp;
		return hi;
		
	}
	
	public static void Qksort(int[] num,int lo,int hi) {
		if (lo>=hi) {
			return;
		}
		
		int index=qf(num, lo,hi);
		Qksort(num, lo, index-1);
		Qksort(num, index+1, hi);
	}
}

	
	

