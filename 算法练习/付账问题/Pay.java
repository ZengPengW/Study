
import java.util.Arrays;
import java.util.Scanner;

public class Pay {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		double s = scan.nextInt();
		double[]num=new double[n];
		
		for (int i = 0; i < n; i++) {
			num[i]=scan.nextDouble();
		}
		
		System.out.println(String.format("%.4f", op(num, n, s)));
	}
	public static double op(double[]num,int n,double s) {
		Arrays.sort(num);
		
		double div=s/n;
		int ans=n;
		double divv=div;
		
		double []pay=new double[n];
		for (int i = 0; i <n; i++) {
			if(num[i]>=divv) {
				pay[i]=divv;
				s-=pay[i];
				ans--;
			}else {
				pay[i]=num[i];
				s-=pay[i];
				ans--;
				divv=s/ans;
			}
		}
		
		return SD(pay,div);
		
		
	}
	private static double SD(double []pay,double div) {
		int n=pay.length;
		double sum=0;
		for (int i = 0; i <n ; i++) {
			sum+=(Math.pow((pay[i]-div),2));
		}
		
		
		
		return Math.sqrt(sum/n);
	
	}
}
