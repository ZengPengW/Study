import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class Calculator{
	private static int [][]p;
	private static long sum=0;
	private static int max=0;
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		p=new int[n+1][5];
		long [][] num=new long[n+1][];
		for (int i =1; i <p.length; i++) {
			int v=0;
			p[i][0]=scan.nextInt();
			p[i][1]=scan.nextInt();
			p[i][2]=scan.nextInt();
			p[i][3]=scan.nextInt();
			p[i][4]=scan.nextInt();
			sum+=p[i][0];
			num[i]=new long[p[i][0]];
			num[i][0]=p[i][1];
//			ai,?j?=?(ai,?j?-?1?*?xi?+?yi)mod mi¡£
			for (int j = 1; j < num[i].length; j++) {
				num[i][j]=(num[i][j-1]*p[i][2]+p[i][3])%p[i][4];
				if(num[i][j]<num[i][j-1])v++;
			}
			if(v>max)max=v;		
		}
		System.out.println(max);
		if(sum<200000)sort(num);
	
		
		
	}
	
	public static void sort(long[][] num) {
		List<Map.Entry<Long, Integer>> al=new ArrayList<Map.Entry<Long, Integer>>();
		HashMap<Long, Integer> hm=new HashMap<Long, Integer>();
		
		int[]mark=new int[p.length];
		for (int i = 0; i <=max; i++) {
			
			for (int k =1; k <num.length; k++) {
				if(mark[k]<num[k].length){
					hm.put(num[k][mark[k]], k);
					if(mark[k]+1>=num[k].length)mark[k]+=1;
				}else continue;
					
				
			for (int j = mark[k]+1; j < num[k].length; j++) {
				if(num[k][j-1]<num[k][j]){
					hm.put(num[k][j], k);
					mark[k]=j+1;
				}
				else{
					mark[k]=j;
					break;		
				}
			}
		}
		al.addAll(hm.entrySet());
		Collections.sort(al, new Comparator<Map.Entry<Long, Integer>>() {

			@Override
			public int compare(Entry<Long, Integer> o1,
					Entry<Long, Integer> o2) {
				if(o1.getKey()>o2.getKey())return 1;
				else if (o1.getKey()<o2.getKey()) return -1;
				else return 0;
			}
		});
		
	
		for (Entry<Long, Integer> ey : al) {
			System.out.println(ey.getKey()+" "+ey.getValue());
		}
		al.clear();
		hm.clear();
	}
		
		
  }
	
}

