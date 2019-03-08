
import java.util.Scanner;

public class SwapBottle {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int []num=new int[n+1];
		for (int i = 1; i <=n; i++) {
			num[i]=sc.nextInt();
		}
		sort(num,n);
		System.out.println(ans);
	}
	private static int ans=0;
	private static void sort(int[] num, int n) {
		int min;
		boolean flag=false;
		for (int i = 1; i <=n; i++) {
			min=i;
			flag=false;
			for (int j =i+1; j <=n; j++) {
				if(num[min]>num[j]) {
					min=j;
					flag=true;
				}	
			}
			if(flag) {
				int temp=num[min];
				num[min]=num[i];
				num[i]=temp;
				ans++;
			}
			
		}
		
	}
	
}