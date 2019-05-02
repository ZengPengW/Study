import java.util.HashMap;
import java.util.Scanner;

public class Main{
	private static final long MOD = 99999999;
	private static HashMap<String, Long> hm=new HashMap<String,Long>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		hm.put("1,1", 2l);
		hm.put("1,2", 3l);
		hm.put("2,1", 1l);
		hm.put("2,2", 4l);
		hm.put("3,1", 6l);
		hm.put("3,2", 5l);
		for (int i = 4; i <=n; i++) {
			f(i,1);
			//f(i,2);
		}
		System.out.println(f(n,1));
		System.out.println(f(n,2));
	}

	private static long f(long n, int i) {
		String pre=n+","+i;
		if(hm.containsKey(pre)){
			return hm.get(pre);
		}
		
		if(i==1){
			long v=(f(n-1, 2)+2*f(n-3, 1)+5)%MOD;
			if(!hm.containsKey(pre))hm.put(pre, v);
			return v;
		}else {
			long v=(f(n-1, 1)+2*f(n-3, 2)+3*f(n-3, 1)+3)%MOD;
			if(!hm.containsKey(pre))hm.put(pre, v);
			return v;
		}
		
	}
}
