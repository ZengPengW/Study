import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for (int i = 0; i < n; i++) {
			String num=sc.next();
			int ans=num.length()%3;
			if(ans==1)num="00"+num;
			else if (ans==2)num="0"+num;
			String s=null;
			s=""+Integer.toOctalString(Integer.valueOf(num.substring(0,3),16));
			System.out.print(s);
			for (int j = 3; j <=num.length()-3; j+=3) {
			
				s=""+Integer.toOctalString(Integer.valueOf(num.substring(j,j+3),16));
				
				int len=4-s.length();
				for (int k = 0; k < len; k++) {
					System.out.print(0);
				}	
				
				System.out.print(s);
			}	
			System.out.println();
		}
		
			
		
		
	}
}