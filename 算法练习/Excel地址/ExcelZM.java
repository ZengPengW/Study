import java.util.ArrayList;
import java.util.Scanner;

public class ExcelZM {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		double num=sc.nextDouble();
		f(num);

	}
	public static void f(double num) {
		String str=""+num;
	int len=str.length();

	double [] db=new double[len];
	
for (int i = 0; i < db.length; i++) {
	if (i!=0) {
		db[i]=26*Math.pow(26, i)+db[i-1];
	}else {
		db[i]=26*Math.pow(26, i);
	}
	 
	
}

double sum=0;
ArrayList<Integer> al=new ArrayList<Integer>();

	for (int i = 0; i <=26; i++) {
		if (num==0) {
			break;
		}
		for (int j = 1; j <=26; j++) {
			sum=j*Math.pow(26, i);
			
			if (sum==num) {
				al.add(j);
				i=-1;
				num=num-sum;
				break;
			}
			if (i>0&&num-sum<=db[i-1]) {
				al.add(j);
				i=-1;
				num=num-sum;
				break;
			}
		}
		
	}

	
	for (int i = 0; i < al.size(); i++) {
		System.out.print((char)(al.get(i)+64));
		
	}
	}

}
