import java.util.Scanner;

public class Gougudili {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		double n=sc.nextDouble();
		System.out.println(f(n));
	}

	private static long f(double n) {
		double v=n;
		n=n*n;
		long count=0;
		for (double i = 1; i <v ; i++) {
			double b=(int) Math.sqrt(n-(i*i));
			if(b<i)break;
			if(b*b+i*i==n) {
				//System.out.println(i+" "+b+" "+v);
				count++;
			}
		}
		return count;
	}

}
