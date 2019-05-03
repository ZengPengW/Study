import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	private static final BigInteger MOD = BigInteger.valueOf(987654321);
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		BigInteger n=sc.nextBigInteger();
		BigInteger m=sc.nextBigInteger();
		f(n,m);
	}
	private static void f(BigInteger n, BigInteger m) {
		BigInteger cnm=C(n,m);
		BigInteger sum=BigInteger.ZERO;
		
		BigInteger cni=n;
		BigInteger tempn=n;
		BigInteger i=BigInteger.ONE;
		BigInteger tempi= BigInteger.ONE;
		sum=sum.add((cnm.multiply(cni).multiply(i)));
		
		long n1=n.longValue();
		BigInteger jj;
		for (long j = 2; j <=n1; j++) {
			jj=BigInteger.valueOf(j);
			i=(jj.pow(3)).mod(MOD);
			tempn=tempn.subtract(BigInteger.ONE);
			cni=(cni.multiply(tempn));
			tempi=(tempi.multiply(jj));
			sum=(sum.add(cnm.multiply(cni.divide(tempi)).multiply(i))).mod(MOD);
		}
		System.out.println(sum);
		
	}
	private static BigInteger C(BigInteger n1, BigInteger m1) {
		
		if(n1.equals(m1))return BigInteger.ONE;
		
		BigInteger fz=BigInteger.ONE,fm=BigInteger.ONE;
		long n=n1.longValue();
		long m=m1.longValue();
		for (long i =n; i >n-m; i--) {
			fz=(fz.multiply(BigInteger.valueOf(i)));
		}
	
		for (long i =m; i >0; i--) {
			fm=(fm.multiply(BigInteger.valueOf(i)));
		}
		return fz.divide(fm).mod(MOD);
	}
	


}
