import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class ProductMin {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int T=scan.nextInt();
		int n;
		
		ArrayList<int[]> num=new ArrayList<int[]>();
		for (int i = 0; i <T; i++) {
			n=scan.nextInt();
			
			for (int k = 0; k <2; k++) {
				int[] num1=new int [n];
			for (int j = 0; j <n; j++) 
			num1[j]=scan.nextInt();
				  
			num.add(num1);
			}
		}
		for (int i = 0; i < num.size(); i++) {
			Arrays.sort(num.get(i));
		}
		int sum=0;
		int id=0;
		for (int i = 0; i < T; i++) {
			sum=0;
			for (int j = 0; j <num.get(id).length; j++) {
				sum+=(num.get(id)[j]*num.get(id+1)[num.get(id).length-1-j]);
			}
			System.out.println(sum);
			id+=2;
			
		}
		
	
	}
	
	
}

