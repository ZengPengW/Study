
import java.util.ArrayList;
import java.util.Scanner;

 
public class ThreeBinarySum {
	private static int L,R;
	public static void main(String[] args) {
		//System.out.println(Integer.parseInt("222222",3));728
		Scanner sc=new Scanner(System.in);
		 L=sc.nextInt();
		 R=sc.nextInt();
		init();
		for (int i = 0; i <=222222; i++) {
			if(checkGoodNum(i)) {
				count++;
			}
		}
		System.out.println(count);
	}
	
	private static ArrayList<Integer> prime=new ArrayList<Integer>();
	private static void init() {
		prime.add(2);
		boolean flag=true;
		for (int i =3; i <=12; i++) {
			flag=true;
			for (int j = 0; j <prime.size(); j++) {
				int v=prime.get(j);
				if(v*v>i)break;
				if(i%v==0) {
					flag=false;
					break;
				}
			}
			if(flag) {
				prime.add(i);
			}
		}
	}
	private static int count=0;
	
	private static boolean checkGoodNum(int num) {
		String str=String.valueOf(num);
		int sum=0,numm=0;
		for (int i = 0; i <str.length(); i++) {
			numm=str.charAt(i)-'0';
			if(numm>2)return false;
			sum+=(numm);
		}
		if(prime.contains(sum)||(sum>=L&&sum<=R)) {
		
			return true;
		}
		return false;
		
	}
}
