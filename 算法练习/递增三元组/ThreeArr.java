import java.util.Scanner;


public class ThreeArr {

	public static void main(String args[]) {
		 Scanner sc= new Scanner(System.in);
		 int n =sc.nextInt();
		 int [][]num=new int[3][n];
		 for (int i = 0; i <3; i++) {
			 for (int j = 0; j < n; j++) {
				num[i][j]=sc.nextInt();	
			}
		}
		 System.out.println(count(num, n));
	}
	public static long count(int [][]num,int n) {
		int []abc=new int[100000];//a组 对应值的个数
		int []ABC=new int[100000];//比b组x值大的c组值个数

	
			for (int j =0; j <n; j++) {
				abc[num[0][j]]++;
				if(ABC[num[1][j]]>0)continue;
				for (int k =0; k <n; k++) {
					if(num[1][j]<num[2][k]){
						ABC[num[1][j]]++;
					}	
				}	
			}	
		
		
		long sum =0;
		int []mark=new int[1000000];
		for (int i = 0; i <n; i++) {
			if(mark[num[0][i]]==0){
				for (int j = 0; j <n; j++) {
					if(num[0][i]<num[1][j]){
						sum+=abc[num[0][i]]*ABC[num[1][j]];
						mark[num[0][i]]=1;
					}
					
				}	
			}
								
		}
		
		return sum;
		
	}
}