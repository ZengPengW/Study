import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Journal {
	private static class Note {
		int ts;
		int id;

		public Note(int ts, int id) {
			this.ts = ts;
			this.id = id;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		int k = sc.nextInt();
		List<Note> ls = new ArrayList<Rizhi.Note>((int) (n / 0.75 + 1));
		for (int i = 0; i < n; i++) {
			int ts = sc.nextInt();
			int id = sc.nextInt();
			ls.add(new Note(ts, id));
		}
		long ks=System.currentTimeMillis();
		f(ls, d, k);
		System.out.println("ÓÃÊ±£º"+(System.currentTimeMillis()-ks)+" ms");
	}

	public static void f(List<Note> ls, int d, int k) {


		TreeSet<Integer> ts = new TreeSet<Integer>();

		Note pre = null;
		Note next = null;
		int index = 0;

		for (int i = 0, len = ls.size(); i < len; i++) {
			pre = ls.get(i);
			if (ts.contains(pre.id))
				continue;
			index=1;
			for (int j =0; j < len; j++) {
				
				if (index >= k) {
					ts.add(pre.id);
					break;
				}
				next=ls.get(j);
				if(i==j)continue;
				if (pre.id == next.id && Math.abs(pre.ts - next.ts) < d)
					index++;
				else
					continue;

			}

		}
		Iterator<Integer> it=ts.iterator();
		while (it.hasNext()) 
			System.out.println(it.next());
			
		
		
	}

}
