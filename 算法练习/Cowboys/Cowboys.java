import java.util.Scanner;


public class Cowboys {
	private static int len;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		char[] s=scan.next().toCharArray();
		len=s.length;
		int st = 0,ed = 0;
		boolean flag=true;
		for (int i = 0; i < s.length; i++) {
			if(s[(i)%len]-1==s[(i+1)%len]){
				st=(i+len+2)%len;
				ed=(i+len-1)%len;
				flag=false;
				break;
			}
			
		}
		if(flag){
			System.out.println(1);
			return;
		}
		
		System.out.println(f1(st, ed, s)+f2(st, ed, s));
		
		
	}
	public static int f1(int st,int ed,char[] s) {
		int[][]dp=new int [len][2];
		dp[st][0]=1;
		dp[st][1]=0;
		
		while (st!=ed) {
			st=(st+1)%len;
			int lo=(st+len-1)%len;
			if(s[st]-1==s[lo]){
				dp[st][0]=dp[lo][1];
				dp[st][1]=0;
			}else if (s[st]+1==s[lo]) {
				dp[st][0]=dp[lo][0];
				//dp[st][1]=dp[lo][1];
				dp[st][1]=dp[(st+len-2)%len][1]+dp[(st+len-2)%len][0];
				if(dp[st][1]==0) dp[st][1]=1;
			}else {
				dp[st][0]=dp[lo][0]+dp[lo][1];
				dp[st][1]=0;
			}
			
		} 	
		return dp[st][0]+dp[st][1];	
	}
	
	public static int f2(int st,int ed,char[] s) {
		int id=2;
		if(s[st]=='B'&&s[(st+1)%len]=='A'){
			st=(st+2)%len;
			id+=2;
		}else if(s[st]=='B'&&s[(st+1)%len]=='B') {
			return 0;
		}
		
		if(s[ed]=='A'&&s[(ed-1+len)%len]=='B'){
			ed=(ed-2+len)%len;
			id+=2;	
		}else if(s[ed]=='A'&&s[(ed-1+len)%len]=='A'){
			return 0;
		}
//	AABABB
//	BBBAAA
		if(id>=len)return 1;
		
		int[][]dp=new int [len][2];
		dp[st][0]=1;
		dp[st][1]=0;
		while (st!=ed) {
			st=(st+1)%len;
			int lo=(st+len-1)%len;
			if(s[st]-1==s[lo]){
				dp[st][0]=dp[lo][1];
				dp[st][1]=0;
			}else if (s[st]+1==s[lo]) {
				dp[st][0]=dp[lo][0];
				//dp[st][1]=dp[lo][1];
				dp[st][1]=dp[(st+len-2)%len][1]+dp[(st+len-2)%len][0];
				if(dp[st][1]==0) dp[st][1]=1;
			}else {
				dp[st][0]=dp[lo][0]+dp[lo][1];
				dp[st][1]=0;
			}
			
		} 	
		return dp[st][0]+dp[st][1];	
		
		
		
	}
	
}

