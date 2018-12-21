import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class Lucky{
	public static void main(String[] args) {
		// 3 5 7 9 15 21 25 27 35 45 49
		
		// 59084709587505
	
		TreeSet<Long> tr=new TreeSet<Long>();
		Queue<Long> q=new LinkedList<Long>();
		q.offer(3l);q.offer(5l);q.offer(7l);
		tr.add(3l);tr.add(5l);tr.add(7l);
		while (!q.isEmpty()) {
			long a=q.poll();
			tr.add(a);
			long c3=a*3;
			long c5=a*5;
			long c7=a*7;
			
			if(!q.contains(c3)&&c3<=59084709587505l)q.offer(c3);
			if(!q.contains(c5)&&c5<=59084709587505l)q.offer(c5);
			if(!q.contains(c7)&&c7<=59084709587505l)q.offer(c7);
			
			
		}
		
		Iterator<Long> it=tr.iterator();
		int id=1;
		while (it.hasNext()) {
			long ll = (long) it.next();
			if(ll==59084709587505l){
			System.out.println(id);
			return;
			}
			id++;
		}
	}
}

