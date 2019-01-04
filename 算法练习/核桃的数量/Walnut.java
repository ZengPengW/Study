import java.util.Scanner;

public class Walnut {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int[]num=new int[3];
		for (int i = 0; i <3; i++) 
			num[i]=sc.nextInt();
		
		System.out.println(lcm(num));
			
		
	}
	private static int gcd(int a,int b) {
		if(a%b==0)return b;
		else return gcd(b, a%b);
	}
	private static int lcm(int a,int b) {
		return a*b/gcd(a, b);
	}
	public static int lcm(int[]num) {
		int lcmm=num[0];
		for (int i = 1; i < num.length; i++){
			lcmm=lcm(lcmm, num[i]);
		}
		return lcmm;
	}
	
}
