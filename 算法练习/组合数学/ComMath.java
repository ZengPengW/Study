import java.math.BigInteger;
import java.util.Scanner;


public class ComMath {
private static final BigInteger MOD=BigInteger.valueOf(999101);

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		BigInteger n=sc.nextBigInteger();
		BigInteger m=sc.nextBigInteger();
		BigInteger k=sc.nextBigInteger();
		System.out.println(op(n, m, k));
	}
	public static BigInteger op(BigInteger n,BigInteger m,BigInteger k) {
		BigInteger Cnm=C1(n, m);
		
		BigInteger sum=BigInteger.ZERO;
		for (BigInteger i =BigInteger.ZERO; i .compareTo(n)<=0; i=i.add(BigInteger.ONE)) {
			BigInteger v1=(C(n, i).multiply(Cnm));
			BigInteger v2=POW(i, k);
			sum=(sum.add((v1.multiply(v2)))).mod(MOD);
		}
		return sum.mod(MOD);
	}
	private static BigInteger POW(BigInteger i,BigInteger k) {
		
		BigInteger sum=i;
		for (BigInteger j = BigInteger.ONE; j.compareTo(k) <0; j=j.add(BigInteger.ONE)) 
			sum=(sum.multiply(i)).mod(MOD);
			
		
		return sum.mod(MOD);
	}
	
	private static BigInteger jcfz;
	private static BigInteger jcfm;
	private static BigInteger C(BigInteger n,BigInteger m){
		BigInteger fz=BigInteger.ONE;
		BigInteger fm=BigInteger.ONE;
		
		if(m.compareTo(BigInteger.ONE)>0){
			 jcfz=jcfz.multiply(n.subtract(m).add(BigInteger.ONE));
			 jcfm=jcfm.multiply(m);
			 return jcfz.divide(jcfm).mod(MOD);
		}
		for (BigInteger i =BigInteger.ZERO; i.compareTo(m)<0; i=i.add(BigInteger.ONE)) 
			fz=(fz.multiply(n.subtract(i)));
			jcfz=fz;
		for (; m.compareTo(BigInteger.ZERO)>0; m=m.subtract(BigInteger.ONE)) 
			fm=(fm.multiply(m));
			jcfm=fm;
		return fz.divide(fm).mod(MOD);
		
	}
	private static BigInteger C1(BigInteger n,BigInteger m){
		BigInteger fz=BigInteger.ONE;
		BigInteger fm=BigInteger.ONE;
		
		
		for (BigInteger i =BigInteger.ZERO; i.compareTo(m)<0; i=i.add(BigInteger.ONE)) 
			fz=(fz.multiply(n.subtract(i)));
			
		for (; m.compareTo(BigInteger.ZERO)>0; m=m.subtract(BigInteger.ONE)) 
			fm=(fm.multiply(m));
			
		return fz.divide(fm).mod(MOD);
		
	}

}
