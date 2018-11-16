
import java.util.Scanner;
import java.util.TreeMap;


public class Qujian {
	private static double num=0;
	private static int n;
	private static TreeMap<Integer,Integer> tm=new TreeMap<Integer, Integer>();
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		
		for (int i = 0; i <n; i++) {
			int a=scan.nextInt();
			int b=scan.nextInt();
			if (!tm.containsKey(a)) {
				tm.put(a,b);	
			}else {
				tm.put(a,tm.get(a)>b?tm.get(a):b);
				if(tm.get(a)==b&&a==0){
					tm.put(a,b+(b-a)/2);
					num=num<(b-a)/2?(b-a)/2:num;
				}else if(tm.get(a)==b&&a!=0){
					tm.put(a-(b-a)/2,b);
					num=num<(b-a)/2?(b-a)/2:num;
				}
			}
			
		}
		
		f();
		
	}
public static void f() {
		
		double index=1;
		double lastval=0;
	for (Integer left : tm.keySet()) {
		
		if (index==1) {
			if(num==0){num=left;}
			lastval=tm.get(left);
			index++;
		}else if (index!=n&&lastval>=left) {
			
			if (lastval<tm.get(left)) {
				lastval=tm.get(left);
			}
			index++;
		}else if(index!=n&&lastval<left) {
			double temp=((left-lastval)/2);
			num=temp>num?temp:num;
			lastval=tm.get(left)-temp;
			index++;
		}else {
			double temp =tm.get(left);
			if (temp<10000) {
				temp=Math.abs(10000-temp);
				num=temp>num?temp:num;
			}
		}
		
		
	}
	String s=String.format("%.1f", num);
	int idof=s.indexOf(".");
	if (s.charAt(idof+1)>='1') {
		System.out.println(s);	
	}else 
		System.out.println(s.substring(0, idof));
	}
	
}