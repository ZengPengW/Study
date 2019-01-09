import java.util.Scanner;


public class Unavailable {

	public static void main(String[] args) {
		
		int []num=new int[100000];
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		long ks=System.currentTimeMillis();
		f(n, m, num);
		System.out.println(System.currentTimeMillis()-ks);
	}
	public static void f(int n,int m,int[]num) {
		
		int temp=0;
		for (int i =0; i<100000; i++) 
			for (int j =0; n*i+m*j<100000 ; j++) {
				temp=n*i+m*j;
				num[temp]=1;
				
			}
			
		int id=0;
		int min=Math.min(n, m);
		for (int i = 0; i < 100000; i++) {
			if(num[i]==1)id++;
			else id=0;
			if(id==min){
				System.out.println(i-min);
				return;
			}
			
			
		}
	}

}
