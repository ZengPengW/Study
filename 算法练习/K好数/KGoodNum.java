import java.util.Scanner;


public class KGoodNum
{
	private static final int MOD=1000000007;
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		int K=scan.nextInt();
		int L=scan.nextInt();
		f(K,L);
	
	}
	public static void f(int k,int l) {
		int [][]db=new int[2][k];
		for (int i = 0; i <k; i++) {
			db[0][i]=1;
		}
		int e=0;
		for (int i = 2; i <=l; i++) {
			e=1-e;
			for (int j = 0; j < k; j++) {
				db[e][j]=0;
				for (int m = 0; m < k; m++) {
					if(m!=j+1&&m!=j-1){
						db[e][j]=db[e][j]+db[1-e][m];
						db[e][j]%=MOD;
					}	
				}	
			}	
		}
		
		int sum=0;
		for (int i = 1; i <k; i++) {
			sum+=db[e][i];
			sum%=MOD;
		}
		System.out.println(sum);
	}
	
	
}