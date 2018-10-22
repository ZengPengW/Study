import java.util.ArrayList;
import java.util.Scanner;

public class Gsidui {
@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] num = new int[n + 1];
		int[][] db = new int[n + 1][2];
		
		ArrayList[] al = new ArrayList[n+1];
		for (int i = 0; i < al.length; i++) {
			al[i]=new ArrayList<Integer>();
		}
		for (int i = 2; i <=n; i++) {
			num[i] = sc.nextInt();
			al[num[i]].add(i);
		}
		for (int i =n; i >0; i--) {
			db[i][0] = 1;
			db[i][1] = 1;
			for (int j = 0; j < al[i].size(); j++) {
				db[i][1] = db[i][1] * db[(Integer) al[i].get(j)][0];
				db[i][0] = db[i][0]
						* (db[(Integer) al[i].get(j)][0] + db[(Integer) al[i]
								.get(j)][1]);
			}
			
		}
		
System.out.println(((db[1][0]+db[1][1])-1)%10007);
	}

}
