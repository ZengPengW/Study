import java.util.Scanner;

public class SerialNum{

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[]num=new int[n+1];
		for (int i = 1; i <=n; i++) 
			num[i]=sc.nextInt();
		
		f(num);
			
		
	}
	public static void f(int[] num) {
		int n=num.length-1;
		
		int max,min,count=0;
		for (int i = 1; i <=n; i++) {
			max=num[i];
			min=num[i];
			for (int j =i; j <=n; j++) {
				if(max<num[j])max=num[j];
				if(min>num[j])min=num[j];
				
				if(max-min==j-i)count++;
				
			}
			
		}
		System.out.println(count);
	}
}
