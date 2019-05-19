
import java.util.Scanner;
public  class Stone {
	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);
		 int t=sc.nextInt();
		 double []x,y;
		 int n;
		 while(t-->0){
			 n=sc.nextInt();
			 x=new double[n];
			 y=new double[n];
			for (int i = 0; i <n; i++) {
				 x[i]=sc.nextInt();
				 y[i]=sc.nextInt();
			}
			f(x,y);	
		
		 }
	}

	public static void f(double[] x, double[] y) {
		int len=x.length;
		//每个三角形面积
		double []s=new double[len-1];
		//cx cy每个三角形重心
		double cx;
		double cy;
		
		double threesum=0,cxs=0,cys=0;
		
		for (int i = 1; i < len-1; i+=1) {
			cx=x[0]+x[i]+x[i+1];
			cy=y[0]+y[i]+y[i+1];
			s[i]=( (x[i] - x[0]) * (y[i+1] -y[0]) - (x[i+1] - x[0]) * (y[i] - y[0]) ) / 2;
			cxs+=(cx*s[i]);
			cys+=(cy*s[i]);
			threesum+=s[i];
		}
		threesum*=3;
		System.out.println(String.format("%.2f %.2f", cxs/threesum,cys/threesum));
		
		
	}

	

	
}
