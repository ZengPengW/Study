import java.util.LinkedList;
import java.util.Scanner;


public class CutChange {

	private static int n;
	private static LinkedList<Long> ls=new LinkedList<Long>();
	private static long[] num;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		num=new long[n];
		for (int i = 0; i < num.length; i++) 
			num[i]=sc.nextLong();
			f();

	}
	public static void f() {
		for (int i = 0; i < num.length; i++) {
			if (!ls.contains(num[i])) {
				System.out.print((num[i]*-1)+" ");
				ls.add(num[i]);
			}else {
				ls.add(num[i]);
				System.out.print((ls.lastIndexOf(num[i])-ls.indexOf(num[i])-1)+" ");
				ls.remove(ls.indexOf(num[i]));
			}	
		}
		
		
		
		
	}

}
