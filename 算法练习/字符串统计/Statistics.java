import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Statistics{
	
	public static void main(String[] args) {
	
		Scanner scan=new Scanner(System.in);
		int L=scan.nextInt();
		String s=scan.next();
		System.out.println(Statistics(s, L));
	}
	public static String Statistics(String s,int L) {
		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		String str="";
		while(L<=s.length()) {
			for (int i = 0; i < s.length()-L; i++) {
			str=s.substring(i, i+L);
			if(!hm.containsKey(str))hm.put(str, 1);
			else hm.put(str, hm.get(str)+1);
		}
			L++;
		}
		
		
		ArrayList<Map.Entry<String,Integer>> al=new ArrayList<Map.Entry<String,Integer>>(hm.entrySet());
		Collections.sort(al, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(java.util.Map.Entry<String, Integer> o1, java.util.Map.Entry<String, Integer> o2) {
						if(o2.getValue().compareTo(o1.getValue())==0) {
							int val=(o2.getKey().length()-(o1.getKey().length()));
							return val;
						}
				else return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		return al.get(0).getKey();
	}
	
}

