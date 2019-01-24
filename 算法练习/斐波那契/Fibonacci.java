import java.math.BigInteger;
import java.util.Scanner;


public class Fibonacci {
//private static HashMap<Long, BigInteger> hm=new HashMap<Long, BigInteger>();
	private static long p;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long m=sc.nextLong();
		p=sc.nextLong();
//		hm.put(0l, BigInteger.ZERO);
//		hm.put(1l, BigInteger.ONE);
		op(n, m);
	}
	//F0=0£¬F1=1£¬Fn=Fn-1+Fn-2£¨n>=2£¬n¡ÊN*£©
	
	public static BigInteger f(long m){
		//if(hm.containsKey(m))return hm.get(m);
		if(m<=1)return BigInteger.valueOf(m);
		BigInteger a=BigInteger.ZERO,b=BigInteger.ONE,temp;
		for (long i =2 ; i <=m; i++) {
			temp=(a.add(b));
			a=b;
			b=temp;	
			//if(!hm.containsKey(i))hm.put(i, b);
		}
		return b;
	}
	public static BigInteger f1(long m,BigInteger fm){
		//if(hm.containsKey(m))return hm.get(m).mod(fm);
		if(m<=1)return BigInteger.valueOf(m);
		BigInteger a=BigInteger.ZERO,b=BigInteger.ONE,temp;
		for (long i =2 ; i <=m; i++) {
			temp=(a.add(b));
		//	if(!hm.containsKey(i))hm.put(i, b);
			a=b;
			b=temp.mod(fm);	
			
		}
		return b;
	}
	public static void op(long n,long m) {
		BigInteger fm=f(m);
		BigInteger fi = BigInteger.ZERO;
		for (long i = 1; i <=n; i++) {
			fi=(f1(i,fm).add(fi)).mod(fm);	
		}
		System.out.println(fi.mod(BigInteger.valueOf(p)));
	}
	
}
