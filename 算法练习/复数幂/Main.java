import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;



public class Main {
	//(a+bi)(c+di)=(ac-bd)+(bc+ad)i
	public static void main(String[] args) throws FileNotFoundException {
		PrintStream ps=new PrintStream(new FileOutputStream("D:/cs.txt"));
		System.setOut(ps);
		BigInteger [] bd=new BigInteger[2];
		BigInteger a=BigInteger.valueOf(2);
		BigInteger b=BigInteger.valueOf(3);
		
		bd[0]=a;
		bd[1]=b;
		for (int i =2; i <=123456; i++) {
			bd=f(bd[0], bd[1], a, b);
		}
		System.out.println(bd[0]+"+"+bd[1]+"i");
		
	}
	public static BigInteger[] f(BigInteger a,BigInteger b,BigInteger c,BigInteger d) {
		BigInteger [] bd=new BigInteger[2];
		bd[0]=(a.multiply(c)).subtract(b.multiply(d));
		bd[1]=(b.multiply(c)).add(a.multiply(d));
		 return bd;
	}



}
