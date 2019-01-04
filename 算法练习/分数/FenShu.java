

public class FenShu {
	public static void main(String[] args) {
		double d1=0;
		
		long b=3;
		long a=2;
		for (int i =4,j=3; j <=20; i*=2,j++) {
			long fm=a*i;
			long fz=b*i;
			fz=fz+a;
			long gcd=gcd(fz, fm);
			b=fz/gcd;
			a=fm/gcd;
		}
	System.out.println(b+"/"+a);
		
		
	
		
	}
	
	public static long gcd(long a,long b) {
		if(a%b==0)return b;
		else return gcd(b, a%b);
		
	}
	
}
