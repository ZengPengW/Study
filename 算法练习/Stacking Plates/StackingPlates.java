
import java.util.Scanner;

public class StackingPlates {

	private static int ans = 1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n;
		int hi;
		int[][] num;
		while (sc.hasNext()) {
			n = sc.nextInt();
			num=new int[n][];
		
			for (int i = 0; i < n; i++) {
				hi=sc.nextInt();
				num[i]=new int[hi];
				for (int j = 0; j < hi; j++) {
					num[i][j]=sc.nextInt();
					
				}
			}
		
			
			f(num);

		}

	}
//根据公式2m-n-1 m=堆数
	private static void f(int[][] num) {
		int n=num.length;
		int v;
		boolean flag=false;
		int cut=0;
		int mark=0;
		for (int i = 0; i < num[0].length; i++) {
			v=num[0][i];
			mark=0;
			for (int j = 1; j <n; j++) {
				flag=false;
				for (int k = 0; k < num[j].length; k++) {
					if(num[j][k]==0)continue;
					
					if(num[j][k]<v) {
						num[j][k]=0;
						flag=true;
					}
					
					
				}
				if(flag) {
					cut++;
					mark=1;
				}
			}
			if(mark>0) {
				if(i>0)cut++;
				for (int j = 0; j <i; j++) {
					num[0][j]=0;
				}
				
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < num[i].length; j++) {
				if(num[i][j]>0) {
					cut++;
					break;
				}
			}
		}
		System.out.println("Case "+(ans++)+": "+(cut*2-n-1));
	}

}
