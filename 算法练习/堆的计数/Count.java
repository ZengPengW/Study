
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
		int h=(int) (Math.log(n)/Math.log(2)+1);//����
		//System.out.println(h);
		long Curlast=(long) (n-(Math.pow(2, h-1)-1));//��ǰ���һ�����
		
		long lasttow=(long) Math.pow(2, h-2);//�����ڶ���ĸ���
		
		long notchild=lasttow-Curlast/2;//û����Ҷ�ĸ���
		if(Curlast%2!=0)notchild--;
		
		long sum2=1;
		long temp=1;
		for (int i =1; i <h-2; i++) { //1--h-2 �������
			long cur=(long) Math.pow(2,i-1);
			temp=1;
			for (long j =cur; j>0; j--) 
				temp=(temp*j)%MOD;
			sum2=(sum2*temp)%MOD;
		}
		
		long sum3=1;
		for (long i =Curlast; i>0; i--)//���һ�ŵ�ȫ����
			sum3=(i*sum3)%MOD;
		

		long sum4=1;
		for (long i =lasttow; i>0; i--) //�����ڶ��ŵ�ȫ����
			sum4=(i*sum4)%MOD;
		
		long zp=0;
		if(notchild>=Curlast){
			
			for (int i =1; i <=Curlast; i++) {//iΪ������ѡ��ѡ����
				long js=C(Curlast, i);//���һ�㼸��ѡ��
				long js2=C(lasttow-i, notchild-i);//�����㼸��ѡ��
				long v=js*js2;//�ܺ�
				
				long sum5=1;
				for (long j =lasttow-i; j>0; j--) //��ǰ��������ڶ��ŵ�ԭ����ȫ����
					sum5=(i*sum5)%MOD;
				
				long sum6=1; //��ǰ��������ڶ��ŵĽ�������ȫ����
				for (int j = i; j >0; j--) 
					sum6=(i*sum6)%MOD;
					
				long tp=(sum5*sum6)%MOD;//�����������
				zp=(zp+tp*sum3*v)%MOD;//�õ�����һ�е�����������Ե����ٳ�����ѡ��
			}
		}else {
			for (int i =1; i <=notchild; i++) {
				long js=C(Curlast, i);//���һ�㼸��ѡ��
				long js2=C(lasttow-i, notchild-i);
				long v=js*js2;
				
				long sum5=1;
				for (long j =lasttow-i; j>0; j--) //��ǰ��������ڶ��ŵ�ԭ����ȫ����
					sum5=(i*sum5)%MOD;
				
				long sum6=1; //��ǰ��������ڶ��ŵĽ�������ȫ����
				for (int j = i; j >0; j--) 
					sum6=(i*sum6)%MOD;
					
				long tp=(sum5*sum6)%MOD;//�����������
				zp=(zp+tp*sum3*v)%MOD;//�õ�����һ�е�����������Ե����ٳ�����ѡ��
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
