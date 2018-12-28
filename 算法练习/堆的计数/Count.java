
import java.util.Scanner;



public class Count
{
	private static final int MOD=1000000009;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int []num=new int[n+1];		
		f(n);
		
		
	}
	
	
	public static void f(int n) {
		int h=(int) (Math.log(n)/Math.log(2)+1);//树高
		//System.out.println(h);
		long Curlast=(long) (n-(Math.pow(2, h-1)-1));//当前最后一层个数
		
		long lasttow=(long) Math.pow(2, h-2);//倒数第二层的个数
		
		long notchild=lasttow-Curlast/2;//没有树叶的个数
		if(Curlast%2!=0)notchild--;
		
		long sum2=1;
		long temp=1;
		for (int i =1; i <h-2; i++) { //1--h-2 所有情况
			long cur=(long) Math.pow(2,i-1);
			temp=1;
			for (long j =cur; j>0; j--) 
				temp=(temp*j)%MOD;
			sum2=(sum2*temp)%MOD;
		}
		
		long sum3=1;
		for (long i =Curlast; i>0; i--)//最后一排的全排列
			sum3=(i*sum3)%MOD;
		

		long sum4=1;
		for (long i =lasttow; i>0; i--) //倒数第二排的全排列
			sum4=(i*sum4)%MOD;
		
		long zp=0;
		if(notchild>=Curlast){
			
			for (int i =1; i <=Curlast; i++) {//i为本次所选所选个数
				long js=C(Curlast, i);//最后一层几种选法
				long js2=C(lasttow-i, notchild-i);//倒二层几种选法
				long v=js*js2;//总和
				
				long sum5=1;
				for (long j =lasttow-i; j>0; j--) //当前情况倒数第二排的原本数全排列
					sum5=(i*sum5)%MOD;
				
				long sum6=1; //当前情况倒数第二排的交换的数全排列
				for (int j = i; j >0; j--) 
					sum6=(i*sum6)%MOD;
					
				long tp=(sum5*sum6)%MOD;//倒二所有情况
				zp=(zp+tp*sum3*v)%MOD;//用倒数第一行的所有情况乘以倒二再乘所有选法
			}
		}else {
			for (int i =1; i <=notchild; i++) {
				long js=C(Curlast, i);//最后一层几种选法
				long js2=C(lasttow-i, notchild-i);
				long v=js*js2;
				
				long sum5=1;
				for (long j =lasttow-i; j>0; j--) //当前情况倒数第二排的原本数全排列
					sum5=(i*sum5)%MOD;
				
				long sum6=1; //当前情况倒数第二排的交换的数全排列
				for (int j = i; j >0; j--) 
					sum6=(i*sum6)%MOD;
					
				long tp=(sum5*sum6)%MOD;//倒二所有情况
				zp=(zp+tp*sum3*v)%MOD;//用倒数第一行的所有情况乘以倒二再乘所有选法
			}
		}
		long tmp=(sum3*sum4+zp)%MOD;
		System.out.println((sum2*tmp)%MOD);

		
	}
	public static long C(long n,long r ) {
		long temp1=1,temp2=1;
		for (long i =n,len=n-r; i >len; i--) 
			temp1=temp1*i;
		for (long i =r; i >0; i--) 
			temp2=temp2*i;
		return temp1/temp2;
	}
	
}
