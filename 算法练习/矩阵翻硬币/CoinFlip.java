import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class CoinFlip{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger n = sc.nextBigInteger();
		BigInteger m = sc.nextBigInteger();
		System.out.println(bigintSqrt(n).multiply(bigintSqrt(m)));
		
	}

	/*			
	 * ţ�ٵ����� ��a��ƽ���� f(x)=x^2-a; f'(x)=2x;
	 * 
	 * ���ݹ�ʽX0=X0-F(X)/F'(X) 
	 * �� x��ʼֵΪa 
	 * x=x-(x^2-a)/2x; 
	 * x=(x+a/x)/2; 
	 * ��|x-a|<0.001
	 * ��ôx����ƽ���� ���� ��x���������������
	 */

//	public static BigInteger bigintSqrt(BigDecimal v) {
//		BigDecimal temp;
//		BigDecimal x = v;
//		BigDecimal tow = BigDecimal.valueOf(2);
//		while (true) {
//			temp = (x.add(v.divide(x,2000,RoundingMode.HALF_DOWN))).divide(tow,2000,RoundingMode.HALF_DOWN);
//			if (temp.subtract(x).abs().compareTo(BigDecimal.valueOf(0.001)) == -1)
//				break;
//			x = temp;
//		}
//		return temp.toBigInteger();
//	}
	
	//���������� ����ֱ��ȡ������С��
	public static BigInteger bigintSqrt(BigInteger v) {
		
		BigInteger tow = BigInteger.valueOf(2);
		BigInteger prv = v.divide(tow);
		BigInteger now=(prv.add(v.divide(prv))).divide(tow);
		while (true) {
			//System.out.println(prv+" "+now);
			if (prv.compareTo(now) <=0)
				break;
			prv = now;
			now = (prv.add(v.divide(prv))).divide(tow);
			
			
		}
		return now;
	}
	

		
	
		
	

}
