import java.util.Scanner;

public class Ktimes {

	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int N=scan.nextInt();
		int K=scan.nextInt();
		int num[]=new int[N];
		for (int i = 0; i < num.length; i++) 
			 num[i]=scan.nextInt();
		
		f2(num, K);
		//f1(num, K);
		
	}
	
	//方法一
	public static void f1(int []num,int k) {
		long summ=0;
		int sum=0;
		for (int i = 0; i < num.length; i++) {
			sum=0;
			for (int j = i; j < num.length; j++) {
				sum+=num[j];
				if (sum%k==0) {
					summ++;
				}	
			}	
		}
		System.out.println(summ);
		
	}
	
    //方法2
    public static void f2(int []num,int k) {
    	int [] sum=new int[num.length];
    	sum[0]=num[0]%k;
    	int [] count=new int[100000];
    	count[sum[0]]++;
    	long ans=0;
    	for (int i = 1; i < num.length; i++) {
			sum[i]=(sum[i-1]+num[i])%k;
			ans=ans+count[sum[i]];
			count[sum[i]]++;
		}
		System.out.println(ans+count[0]);
    	   
	}

}
