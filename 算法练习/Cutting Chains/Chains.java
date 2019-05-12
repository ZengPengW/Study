import java.util.Scanner;

public class Chains {
	private static int number=1;
	private static int n;
	private static int [][]map;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int i,j;
		while(true){
			
			n=sc.nextInt();	
			if(n==0)break;
					
			map=new int [n+1][n+1];
			
		
			while(true){
				i=sc.nextInt();
				j=sc.nextInt();
				
				if(i==-1&&j==-1)break;
				map[i-1][j-1]=1;
				map[j-1][i-1]=1;
			}
			System.out.println("Set "+(number++)+": Minimum links to open is "+solve());
		}
			
	}

	private static int solve() {
		int s=1<<n;
		int min=1<<30;
		
		for (int i = 0; i < s; ++i) {
			System.out.println(Integer.toBinaryString(i));
			int cur=0;
			for (int j = 0; j <n; ++j) {
				if((i&(1<<j))>0){
					System.out.println(Integer.toBinaryString((i&(1<<j))));
					++cur;
				}
					
			}
			if(cur>=min)continue;
			if(node(i)&&ring(i,cur))
				min=min>cur?cur:min;
		}
		return min;
	}
private static int []vis;
	private static boolean ring(int s, int num) {
		vis=new int[20];
		int cnt=0;
		for (int i = 0; i <n; i++) {
			if((s&(1<<i))>0)continue;
			if(vis[i]>0)continue;
			++cnt;
			if(!dfs(s,i,-1))return false;
		}
		if(cnt>num+1)return false;
		return true;
	}

	private static boolean dfs(int s,int cur,int pre) {
		 vis[cur]=1;
		    boolean ok=true;
		    for(int i=0;i<n;++i){
		        if((s&(1<<i))<1 && map[cur][i]>0){
		            if(vis[i]<1){
		                if(!dfs(s,i,cur))
		                    ok=false;
		            }
		            else if(i!=pre) return false;//已经来过，但不是上一个，形成环。
		        }
		    }
		    if(!ok) return false;
		    return true;

	}

	private static boolean node(int s) {//判断是否形成结点。
		 for(int i=0;i<n;++i){
		        if((s&(1<<i))>0) continue;
		        int num=0;
		        for(int j=0;j<n;++j)
		            if((s&(1<<j))<1&&map[i][j]>0) ++num;
		        if(num>2) return false;
		    }
		    return true;
	
	}

	
	

}
