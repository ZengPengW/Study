import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class Taste {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		String str = null;
		for (int i = 1; i <= n; i++) {
			str = sc.nextLine().trim();
			if (!hm.containsKey(str)) {
				hm.put(str, 1);
			} else {
				hm.put(str, hm.get(str) + 1);
			}
			// System.out.println(str);
		}
		System.out.println(different(hm));
	}

	public static long different(HashMap<String, Integer> hm) {
		long ans = 0;
		boolean flag = true;
		ArrayList<String> mark = new ArrayList<String>();
//		for (String s : hm.keySet()) {
//			if(mark.contains(s))continue;
//			mark.add(s);
//			for (String p : hm.keySet()) {
//				if(mark.contains(p))continue;
//				flag=true;
//				for (int i = 0; i <s.length(); i+=2) {
//				//	if(s.charAt(i)==' ')continue;
//					if(p.charAt(i)==s.charAt(i)) {
//						flag=false;
//						break;
//					}
//				}
//				if(flag) {
//					ans=ans+(hm.get(s)*hm.get(p));
//					mark.add(p);
//					break;
//				}
//			}
//		}
		String s1, s2;
		Iterator it = hm.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Integer> en = (Entry<String, Integer>) it.next();
			s1 = en.getKey();
			if (mark.contains(s1))
				continue;
			mark.add(s1);
			Iterator p = hm.entrySet().iterator();
			while (p.hasNext()) {
				Entry<String, Integer> pp = (Entry<String, Integer>) p.next();
				s2 = pp.getKey();
				if (mark.contains(s2))
					continue;
				flag = true;
				for (int i = 0; i < s1.length(); i += 2) {
					if (s1.charAt(i) == s2.charAt(i)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					ans = ans + (en.getValue() * pp.getValue());
					mark.add(s2);
					break;
				}
			}
		}

		return ans;
	}
}