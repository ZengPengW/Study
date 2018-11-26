import java.util.Scanner;
public class MatMulti{
	
	public static void main(String[] args) {
	
		Scanner scan=new Scanner(System.in);
		int m=scan.nextInt();
		int s=scan.nextInt();
		int n=scan.nextInt();
		int [][]num1=new int[m][s];
		int [][]num2=new int[s][n];
		for (int k = 0; k <2; k++) {
		if(k==0) {
			for (int i = 0; i <m; i++) 
		    for (int j = 0; j <s; j++) 
			num1[i][j]=scan.nextInt();
		}else {
			for (int i = 0; i <s; i++) 
		    for (int j = 0; j <n; j++) 
			num2[i][j]=scan.nextInt();
		}	
		}
		
		MatMulti(num1, num2);
		
	}
	public static  void MatMulti(int[][]num1,int[][] num2) {
		int sum=0;
		for (int i = 0; i < num1.length; i++) {
			for (int j = 0; j < num2[0].length; j++) {
				sum=0;
				for (int k = 0; k < num1[i].length; k++) {
				sum+=num1[i][k]*num2[k][j];	
				}
				System.out.print(sum+" ");
			}
			System.out.println();
		}
		
		
	}
	
	
}

