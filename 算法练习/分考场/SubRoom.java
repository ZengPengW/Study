import java.util.ArrayList;
import java.util.Scanner;

public class SubRoom{

	private static int [][]nums;
	private static int n;
	private static int m;
	private static ArrayList<LinkedList<Integer>> room=new ArrayList<LinkedList<Integer>>();
	private static int min=Integer.MAX_VALUE;
	public static void main(String[] args) {
	Scanner scan=new Scanner(System.in);
	 n=scan.nextInt();
	 m=scan.nextInt();
	nums=new int[n+1][n+1];
	int a,b;
	for (int i = 0; i < m; i++) {
		a=scan.nextInt();
		b=scan.nextInt();
		nums[a][b]=1;
		nums[b][a]=1;
	}
	dfs(1);
	System.out.println(min);
	}
	public static void dfs(int cur) {
		
		if (cur>n) {
			min=Math.min(room.size(), min);
			return;
		}
			if (room.size()>=min) {
				return;
			}
		for (int i = 0; i < room.size(); i++) {
			if (check(i, cur)) {
				room.get(i).add(cur);
				dfs(cur+1);
				room.get(i).remove(room.get(i).size()-1);
			}
			
		}
			
				
		LinkedList<Integer> rm=new LinkedList<Integer>();
		rm.add(cur);
		room.add(rm);
		dfs(cur+1);
		room.remove(room.size()-1);	
			
		
			
		}
		
	public static boolean check(int id,int cur) {
		for (int i = 0; i < room.get(id).size(); i++) {
			if (nums[cur][room.get(id).get(i)]==1) {
				return false;
			}
		}
		return true;
				
		
	}




}