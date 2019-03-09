
import java.util.Scanner;

public class RobotPagoda
{
	private static int []count=new int[2];
	private static int ans=0;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int m=scan.nextInt();
		int n=scan.nextInt();
		count[0]=m;
		count[1]=n;
		int len=m+n;
		int h=(int)Math.sqrt(len*2);
		int []last=new int[h];
		dfs(last, h, 0);
		System.out.println(ans);
		
	}
	public static void dfs(int []last,int h,int index) {
		if(index==h) {
			if(check(last)) {
				ans++;
			}
			return;
		}
		for (int i = 0; i <=1; i++) {
			if(count[i]>0) {
				count[i]--;
				last[index]=i;
				dfs(last, h,index+1);
				count[i]++;
			}
		}
	}
	private static boolean check(int[] last) {
		int len=last.length;
		int [][]num=new int[len+1][len+1];
		num[len]=last;
		int m=count[0],n=count[1];
		
		for (int i = len-1; i>0; i--) {
			for (int j = 0; j <i; j++) {
				if(num[i+1][j]!=num[i+1][j+1]){
					num[i][j]=1;
					n--;
					if(n<0)return false;
					continue;
				}
				m--;
				if(m<0)return false;
			}
		}
		if(m==0&&n==0)return true;
		return false;
	}
}