import java.util.ArrayList;
import java.util.Scanner;

public class Fenkaochang {

	private static int sum=0;
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[][] stu = new int[m][2];
		for (int i = 0; i <m; i++) {
			stu[i][0]=sc.nextInt();
			stu[i][1]=sc.nextInt();	
		}
		
		
		ArrayList<String> al = new ArrayList<String>();
		
		ArrayList<String> a2 = new ArrayList<String>();
		
		for (int i = 1; i <=n; i++) {
			al.add(""+i);
			a2.add(""+i);
			
		}

		for (int j = 1; j <= n; j++) {
			if (a2.size()<=0) {
				break;
			}
			if (al.contains(""+j)) {
				
				for (int i = j-1; i < stu.length; i++) {
					if (stu[i][0] == j) {
						
						al.remove(""+stu[i][1]);
					}

				}
				
					a2.removeAll(al);
				
				al.clear();
				
					al.addAll(a2);
				
				sum++;
			}

		}

		System.out.println(sum);
	}

}