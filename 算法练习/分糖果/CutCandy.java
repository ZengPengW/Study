import java.util.Scanner;

public class CutCandy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] []child = new int[2][n];
		for (int i = 0; i < n; i++)
			child[0][i] = sc.nextInt();
		f(child, n);
	}

	public static void f(int[][] child,int n) {
		int count=0,cur = 0;
		int e=0;
		boolean bl=false;
		while (!bl) {
			e=1-e;
			bl=true;
			for (int i = 0; i < n; i++) {
				int id=i==0?n-1:i-1;
				 child[e][id]=child[1-e][i]/2+child[1-e][id]/2;
				if(child[e][id]%2!=0){
					count++;
					child[e][id]++;
				}
				
				if(i>0&&cur!=child[e][id])
					bl=false;
				else 
					cur=child[e][id];
				
			}
		}
		
		System.out.println(count);
	}
}
