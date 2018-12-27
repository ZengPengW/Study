import java.util.Scanner;

public class Helix {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		f(x, y);

	}

	public static void f(int x, int y) {

			double temp =0;
			for (int i =2*Math.max( Math.abs(y), Math.abs(x))-2; i >=2; i-=2) 
				temp+=i*4;
			temp+=1;
			
			int max=Math.max(Math.abs(y), Math.abs(x));
			while (true) {
				//ÍùÉÏ
			if(y<max&&x>0){
				temp+=max-y;
				y=max;
			}else if (y==max&&x>-max) {//×ó
				temp+=x-(-max);
				x=-max;
			}else if (x==(-max)) {//ÏÂ
				temp+=(2*max-1)-(max-y);
				System.out.println((long)temp);
				return;
			}else if (y==(-max)&&x<max) {//ÓÒ
				temp+=max-x;
				x=max;
			}
			}
	
	}
}
