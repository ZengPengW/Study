import java.util.Scanner;

public class GetTogether{

	private static int n; 
	private static int L; 
	private static int [][] arr; 
	private static int [] poi=new int[4]; 
	private static long sum=Long.MAX_VALUE; 
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		L=scan.nextInt();
		arr=new int[n][2];
		for (int i = 0; i <n; i++) {
			arr[i][0]=scan.nextInt();
			arr[i][1]=scan.nextInt();
		}
		poi[3]=L;
		dfs(0, 0);
		System.out.println(sum);
	}
	public static void dfs(int index,int id) {
		if (index==3) {
			Count();
			return;
		}
		
		
		for (int i = id; i <=L; i++) {
			poi[index]=i;
			dfs(index+1,i);
		}
		
	}
	public static void Count() {
		long summ=0;
		for (int i = 0; i < arr.length; i++) {
			
			 if ( arr[i][0]<=poi[0]) {
				summ+=(poi[0]-arr[i][0])*arr[i][1];
			}else if(arr[i][0]<=poi[1]){
				summ+=(poi[1]-arr[i][0])*arr[i][1];
			}else if (arr[i][0]<=poi[2]) {
				summ+=(poi[2]-arr[i][0])*arr[i][1];
			}else {
				summ+=(poi[3]-arr[i][0])*arr[i][1];
			}
			
		}
		if (summ<sum) {
			sum=summ;
		}
	}

}
