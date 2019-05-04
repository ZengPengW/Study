
import java.util.ArrayList;
import java.util.Scanner;

public class Factorization {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		getNum(n);
		print(n);
		
	}
	public static void print(int n) {
		int tempn=n;
		int v;
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < num.size(); i++) {
			if(n==1)break;
			v=num.get(i);
			while (true) {
				if(n%v==0){
					n=n/v;
					sb.append(v+"*");
				}else{
					break;
				}
			}
		}
		if(sb.length()>0)System.out.println(sb.toString().substring(0, sb.length()-1));
		else System.out.println(tempn);
		
	}
	private static ArrayList<Integer> num=new ArrayList<Integer>();
	public static void getNum(int n){
		n=n/2;
		num.add(2);
		int v;
		boolean flag=true;
		for (int i = 3; i <=n; i++) {
			flag=true;
			for (int j = 0; j < num.size(); j++) {
				v=num.get(j);
				if(v*v>i)break;
				if(i%v==0){
					flag=false;
					break;
				}
				
			}
			if(flag)num.add(i);
			
		}
		
		
	};
	
	


}
