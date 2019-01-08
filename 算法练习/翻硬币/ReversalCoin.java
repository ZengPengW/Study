import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ReversalCoin {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String mat = sc.next();
		//f2(str, mat);
		bfs(str, mat);
	}

	public static void bfs(String str, String mat) {
		Queue<String> que = new LinkedList<String>();
		que.offer(str);
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put(str, 0);
		char ch[] = null;
		String temp = null;
		String s = null;
		int times = 0;
		while (!que.isEmpty()) {
			s = que.poll();
			ch = s.toCharArray();
			times = hm.get(s);
			for (int i = 0, len = ch.length - 1; i <= len; i++) {

				if (ch[i] != mat.charAt(i)) {
					if(i==len)temp = change(ch, i, i - 1);
					else temp = change(ch, i, i + 1);
					
					if (!hm.containsKey(temp)) {
						if (temp.equals(mat)) {
							System.out.println(times + 1);
							return;
						}
						hm.put(temp, times + 1);
						que.offer(temp);
					}
					// 回溯
					if(i==len)temp = change(ch, i, i - 1);
					else temp = change(ch, i, i + 1);
				}

			}

		}

	}

	private static String change(char[] ch, int id1, int id2) {
		ch[id1] = ch[id1] == '*' ? 'o' : '*';
		ch[id2] = ch[id2] == '*' ? 'o' : '*';
		return new String(ch);
	}

	//广搜超时，采用方法2
	public static void f2(String str, String mat) {
		char[] ch=str.toCharArray();
		int count=0;
		for (int i = 0, len = ch.length - 1; i <= len; i++) {

			if (ch[i] != mat.charAt(i)) {
				count++;
				if(i==len) change(ch, i, i - 1);
				else  change(ch, i, i + 1);
			}

		}
		System.out.println(count);
	}
}
