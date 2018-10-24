import java.util.Scanner;

public class GaoSeng {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] str = sc.nextLine().split(" ");
		int[] arr = new int[str.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		f(arr);

	}

	public static void f(int [] arr) {
      int flag=0;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = arr[i]+1; j <arr[i+1]; j++) {
				int temp=arr[i];
				arr[i]=j;
				if (check(arr)) {
					flag=1;
					System.out.println(temp+" "+j);
					System.exit(0);
				}
				arr[i]=temp;
				
			}
			
		}
		if (flag==0) {
			System.out.println(-1);
		}
	}
	
	private static boolean check(int [] arr) {
		String[] str=new String[arr.length/2];
		int m=0;
		for (int i = 0; i < str.length; i++) {
			str[i]=Integer.toBinaryString(arr[i*2+1]-arr[i*2]-1);
			if (str[i].length()>m) {
				m=str[i].length();
			}
		}
		for (int i = 0; i < m; i++) {
			boolean tag=true;
			for (int j = 0; j < str.length; j++) {
				int k=str[j].length()-(m-i);
				if (k>=0&&str[j].charAt(k)=='1') {
					tag=!tag;
					
				}
				
			}
			if (tag==false) {
				return false;
			}
			
		}
		
		
		return true;
	}
}