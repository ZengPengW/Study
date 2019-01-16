import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class YueShuKaPian {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] rest = sc.nextLine().split(" ");
		String[] opt = sc.nextLine().split(" ");
		f(rest, opt);

	}
	
	private static void f(String[] rest, String[] opt) {
		int[] re = new int[rest.length];
		int[] op = new int[opt.length];
		int[] mark=new int[102];
		for (int i = 0, len = re.length; i < len; i++){
			re[i] = Integer.parseInt(rest[i]);
			mark[re[i]]++;
		}
			

		for (int i = 0, len = op.length; i < len; i++)
			op[i] = Integer.parseInt(opt[i]);

		HashMap<Integer, ArrayList<Integer>> hm=new HashMap<Integer, ArrayList<Integer>>();
		
		for (int i = 0, len = re.length; i < len; i++){
			ArrayList<Integer> hs=new ArrayList<Integer>();
			hs.add(re[i]);
			for (int j = 0; j < len; j++) {
				if(re[i]%re[j]==0||re[j]%re[i]==0)
					hs.add(re[j]);
			}
			hm.put(re[i], hs);
			
		}
		
	for (int i = 0,len=op.length; i < len; i++) {
		
		mark[op[i]]--;
		
		if(!dfs(op[i], mark,hm)){
			System.out.println(op[i]);
			return;
		}
		mark[op[i]]++;
	}

	}
	
	private static boolean dfs(int st,int []mark,HashMap<Integer, ArrayList<Integer>> hm) {
		ArrayList<Integer> al=hm.get(st);
		//ArrayList<Integer> con=new ArrayList<Integer>();
		for (int i=al.size()-1;i>=0;i--) {
			int v = al.get(i);
			//if(con.contains(v))continue;
			if(mark[v]>0){
				//con.add(v);
				mark[v]--;
				boolean bl=dfs(v, mark,hm);
				mark[v]++;
				if(bl==false)
					return true;
			}
		}
		
		
//		for (int i = re.length-1; i >=0; i--) {
//			if(mark[re[i]]>0&&(re[i]%st==0||st%re[i]==0)){
//				mark[re[i]]--;
//				boolean bl=dfs(re[i], mark, re);
//				mark[re[i]]++;
//				if(bl==false)
//					return true;
//			}
//			
//		}
		
	return false;
}
}
