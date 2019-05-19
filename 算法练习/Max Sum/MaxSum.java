
import java.util.Scanner;
public  class MaxSum {
	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);
		 int t=sc.nextInt();
		 int n;
		 int []dp;
		 int id,st = 0,ed = 0;
		 int max;
		 int cases=1;
		 while(t-->0){
			 n=sc.nextInt();
			 id=0;
			 dp=new int[n];
			 max=0x80000000;
			 while (n-->0) {			
				dp[id++]=sc.nextInt();
			 }
			int sum1=0,sum2=0;
			st = 0;ed = 0;
			 for (int i = 0; i <dp.length; i++) {	
				 	sum1+=dp[i];
					if(sum1>max){
						max=sum1;				
						ed=i;
					}
					if(sum1<0){
						sum1=0;
					}				 
			 }
			 for (int i = ed; i>=0; i--) {
				sum2+=dp[i];
				if(sum2==max){
					st=i;
				}
			}
			 System.out.println("Case "+(cases++)+":");
			 System.out.println(max+" "+(st+1)+" "+(ed+1));
			if(t!=0) System.out.println();
			 
		 }
	}

	

	
}
