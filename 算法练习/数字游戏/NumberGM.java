import java.math.BigInteger;
import java.util.Scanner;

public class NumberGM {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		BigInteger n =sc.nextBigInteger();
		BigInteger k =sc.nextBigInteger();
		long T =sc.nextLong();
		f2(n, k, T);
	}
public static void f2(BigInteger n,BigInteger k ,long T) {
	//1+(1+n)*n/2
	BigInteger indexL=BigInteger.ZERO,sum=BigInteger.ZERO,v;
	for (int i = 0; i < T; i++) {
		v=(BigInteger.ONE.add((BigInteger.ONE.add(indexL)).multiply(indexL).divide(BigInteger.valueOf(2)))).mod(k);
		//System.out.println(v);
		sum=sum.add(v);
		indexL=indexL.add(n);
		
	}
	System.out.println(sum);
		
}
public static void f1(BigInteger n1,BigInteger k1 ,long T) {
	long n=n1.longValue(),k=k1.longValue();
	long x=1,ans=1;
    long l=1,r=n;
    for(int i=1; i<T; i++)
    {
        x+=(l+r)*n/2;
      //  System.out.println(x);
        x=x%k;
        ans+=x;
        l=1+i*n;
        r=n+i*n;
    }

	System.out.println(ans);
}

}
