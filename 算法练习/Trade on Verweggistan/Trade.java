import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
public class Trade {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int w;
		int [][]dp = null;
		while(true) {
			w=sc.nextInt();
			if(w==0)return;
			dp = null;
			f(w,dp,sc);
		
		}
		
		
	}
	private static int ans=1;
	private static void f(int w, int[][] dp,Scanner sc) {
		dp=new int[w+2][21];
		
		int sum=0,max=0;
		List<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
		int b;
		for (int i = 1; i <=w; i++) {
			
			b=sc.nextInt();
			if(b==0)continue; 
			dp[i][0]=10-sc.nextInt();
			max=dp[i][0];
			
			
			for (int j =1; j <b; j++) {
				dp[i][j]=10-sc.nextInt()+dp[i][j-1];
				max=Math.max(max, dp[i][j]);
			}
			if(max>=0) {
				ArrayList<Integer> al=new ArrayList<Integer>();
				if(max==0)al.add(0);
				for (int j = 0; j < b; j++) {
					if(dp[i][j]==max) {
						al.add(j+1);
					}
					
				}
				
				list.add(al);	
				sum+=max;
			}
		}
	
		
		if (list.size()>0) {
			System.out.print("Workyards "+(ans++)+"\r\n" + 
					"Maximum profit is "+sum+".\r\n" + 
					"Number of pruls to buy: ");
			
			TreeSet<Integer> t=new TreeSet<Integer>();
			dfs(list,0,0,t);
			int count =0;
			for (Integer i : t) {
				if(count>=10)break;
				System.out.print(i+" ");
				count++;
			}
			System.out.println();
		}else {
			System.out.println("Workyards "+w+"\r\n" + 
					"Maximum profit is 0.\r\n" + 
					"Number of pruls to buy: 0");
		}
		
		
	}
	
	
	private static void dfs(List<ArrayList<Integer>> list, int index,int sum,TreeSet<Integer> t) {
		
		if(index>=list.size()) {
			t.add(sum);
			return;
		}
		
		ArrayList<Integer> tr=list.get(index);
		for (Integer i : tr) {
			dfs(list, index+1, sum+i,t);
		}
			
		
		
		
	}


}
