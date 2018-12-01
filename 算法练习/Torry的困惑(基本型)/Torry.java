import java.util.ArrayList;
import java.util.Scanner;

public class Torry{
	private static final int MOD=50000;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		System.out.println(primeSum(n));
		
	}
	public static int primeSum (int n) {
		ArrayList<Integer> num=new ArrayList<Integer>();
		num.add(2);
		int sum=2;
		int count=1;
		boolean flag=true;
		for (int i = 3; count<n; i++) {
			flag=true;
			for (int j = 0; j <num.size(); j++) {
				if(num.get(j)*num.get(j)>i)break;
				if(i%num.get(j)==0){
					flag=false;
					break;
				}
			}
			if(flag){
				sum=(sum*i)%MOD;
				num.add(i);
				//System.out.print(i+" ");
				count++;
			}
		}
		return sum;
		
	}
}