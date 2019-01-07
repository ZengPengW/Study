import java.util.Scanner;

public class ErrorBill {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		int min=999999;
		int max=0;
		int []num=new int[100*100+1];
		
		String s=null;
		int temp = 0 ;
		boolean bl=false;
		for (int i = 0; i <n; i++) {
			s=sc.nextLine();
			
			for (int j = 0,len=s.length(); j < len; j++) {
				temp=0;
				bl=false;
				while (j<len) {
					if(s.charAt(j)!=' '){
					bl=true;
					temp=temp*10+(s.charAt(j)-'0');	
					j++;
					}else {
						break;
					}
				}
				if(bl){
					num[temp]++;
					if(temp>max)max=temp;
					if(temp<min)min=temp;
				}
				
			}
		}
		
		f(num, min, max);
		
	}
	public static void f(int[] num,int min,int max) {
		int cut = 0,repeat = 0;
		int count=0;
		for (int i = min; i <=max; i++) {
			if(num[i]==0){
				cut=i;
				count++;
			}
			if(num[i]>1){
				repeat=i;
				count++;
			}
			if (count==2) {
				break;
			}
		}
		System.out.println(cut+" "+repeat);
		
		
		
	}

}
