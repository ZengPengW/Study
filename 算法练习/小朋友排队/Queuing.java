import java.util.Scanner;


public class Queuing {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int []num=new int[n];
		for (int i = 0; i < n; i++) 
			num[i]=sc.nextInt();
	
		Sort(num);
		
	}

	private static void Sort(int[]num) {
		long mark[]=new long[num.length+1];
		long count[]=new long[num.length+1];
		long sum=0;
		for (int i = num.length-1; i >=0; i--) {
			for (int j = i-1; j>=0; j--) {
				if(num[i]<num[j]){
					count[i]++;
					count[j]++;
					mark[i]+=count[i];
					mark[j]+=count[j];
				}
				
			}
			sum+=mark[i];
		}
		
		
		System.out.println(sum);
	}

	
}
