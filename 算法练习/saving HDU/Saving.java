
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Saving {
	private static class Pro{
		int pi,mi;

		public Pro(int pi, int mi) {
			super();
			this.pi = pi;
			this.mi = mi;
		}
		
	}
	public static void main(String[] args) {
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));			
		Scanner sc=new Scanner(System.in);
		
		int v,n,pi,mi,sum;
		List<Pro> list;	
		while (sc.hasNextInt()) {
			v=sc.nextInt();
			if(v==0)break;
			n=sc.nextInt();
			list=new ArrayList<Pro>();
			while (n-->0) {
				pi=sc.nextInt();
				mi=sc.nextInt();
				list.add(new Pro(pi, mi));
			}
			Collections.sort(list, new Comparator<Pro>() {

				@Override
				public int compare(Pro o1, Pro o2) {
					if(o1.pi>o2.pi)return -1;
					else if(o1.pi<o2.pi)return 1;
					return 0;
				}
			});
			
			sum=0;
			for (Pro pro : list) {
				if(pro.mi<=v) {
					v-=pro.mi;
					sum+=pro.pi;
				}else {
					sum+=(v*pro.pi);
					break;
				}
			}
			System.out.println(sum);
		}
		

	}
}
