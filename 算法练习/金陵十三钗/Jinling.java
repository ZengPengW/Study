import java.util.LinkedList;
import java.util.Scanner;

public class Jinling {
	private static int [][]arr;
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int n= sc.nextInt();
		arr=new int [n+1][n+1];
		
		for (int i = 1; i <=n; i++) 
			for (int j = 1; j <=n; j++) {
				arr[i][j]=sc.nextInt();
			}
		
			//dfs(n,0);//递归70%分
			f(n);//状压DP  100%
		
		
	//	System.out.println(max);
		
		
	}
	public static void f(int n) {
		String str="";
		for (int i = 0; i < n; i++) {
			str+="0";
		}
	int maxstatus = 1<<n;
	int []dp=new int[1<<n+5];
	
	
	int row;
	String s;
	for (int i = 1; i <maxstatus; i++) {
		row=getRow(i);
		s=Integer.toString(i,2);
		int len=s.length();
		if(len<n) {
			len=n-len;
			while (len>0) {
				len--;
				s="0"+s;
			}
		}
		String ss=null;
		for (int j =0; j <n; j++) {
			if(s.charAt(j)=='0') 
				continue;
			ss=strRep(s,j);//将一置换成0
			int v=Integer.parseInt(ss, 2);
			dp[i]=Math.max(dp[i], dp[v]+arr[row][j+1]);
		}
	}
	
	System.out.println(dp[maxstatus-1]);
	
		
	}
	
	
	public static String strRep(String s, int j) {
		StringBuilder sb=new StringBuilder();
		sb.append(s.substring(0, j));
		sb.append("0");
		sb.append(s.substring(j+1));
		return sb.toString();
	}
	private static int getRow(int i) {
		String s=Integer.toBinaryString(i);
		int row=0;
		for (int j = 0; j <s.length(); j++) {
			if(s.charAt(j)=='1')row++;
		}
		return row;
	}


	private static int []mark=new int[14];
	private static LinkedList<Integer> lks=new LinkedList<Integer>();
	public static void dfs(int n,int index) {
	
		if(index>=n) {
			option();
			return;
		}
		
		for (int i =1; i <=n; i++) {
			if(mark[i]==0) {
				lks.add(i);
				mark[i]=1;
				dfs(n, index+1);
				lks.removeLast();
				mark[i]=0;
			}
			
			
			
		}
		
		
	}
	private static int max=Integer.MIN_VALUE;
	private static void option() {
		int id=1;
		int sum=0;
		for (int i : lks) {
			sum+=arr[id++][i];
		}
		
		
		max=sum>max?sum:max;
		
	}
}
