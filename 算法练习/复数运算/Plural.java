import java.util.Scanner;

public class Plural{
	/*
	 加法法则
	(a+bi)+(c+di)=(a+c)+(b+d)i。
	减法法则
	(a+bi)-(c+di)=(a-c)+(b-d)i
	乘法法则
	z1=a+bi，z2=c+di展开得: ac+adi+bci+bdi2
	(a+bi)(c+di)=(ac-bd)+(bc+ad)i

	除法法则
	(a+bi)/(c+di)=(ac+bd)/(c2+d2) +((bc-ad)/(c2+d2))i
	*/
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String operator = scan.next();
		double[] num = new double[4];
		for (int i = 0; i < num.length; i++)
			num[i] = scan.nextDouble();
		System.out.println(operation(operator, num));
		
	}
	public static String operation(String operator, double... num) {
		int o = 0;
		if(operator.equals("+"))o=1;
		else if (operator.equals("-"))o=2;
		else if (operator.equals("*"))o=3;
		else if (operator.equals("/"))o=4;
		
		switch (o) {
		case 1:return Add(num[0], num[1], num[2], num[3]);
		case 2:return Subtract(num[0], num[1], num[2], num[3]);
		case 3:return Multiplication(num[0], num[1], num[2], num[3]);
		case 4:return Division(num[0], num[1], num[2], num[3]);
		default:break;
		}
		return "";
	}
	
	

	private static String Add(double a,double b,double c,double d) {
		double fact=a+c;
		double sham=b+d;
		return String.format("%.2f",fact)+"+"+String.format("%.2f",sham)+"i";
	}

	private static String Subtract(double a,double b,double c,double d) {
		double fact=a-c;
		double sham=b-d;
		return String.format("%.2f",fact)+"+"+String.format("%.2f",sham)+"i";
	}

	private static String Multiplication (double a,double b,double c,double d) {
		double fact=a*c-b*d;
		double sham=b*c+a*d;
		return String.format("%.2f",fact)+"+"+String.format("%.2f",sham)+"i";
	}

	private static String Division (double a,double b,double c,double d) {
		double fact=(a*c+b*d)/(c*c+d*d);
		double sham=(b*c-a*d)/(c*c+d*d);
		return String.format("%.2f",fact)+"+"+String.format("%.2f",sham)+"i";
	}

}
