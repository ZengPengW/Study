import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class AlkaliBase{
	private static int n, m, k, sum;
	private static final int MOD=1000000007;
	private static HashMap<String, Integer> hm = new HashMap<String, Integer>();
	private static ArrayList<HashMap<String, Integer>> al = new ArrayList<HashMap<String, Integer>>();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();

		String[] dna = new String[n];
		for (int i = 0; i < dna.length; i++) {
			dna[i] = sc.next();
		}
		for (int i = 0; i < dna.length; i++) {
			String str = dna[i];
			for (int j = 0; j <= str.length() - k; j++) {
				String s = str.substring(j, j + k);
				if (!hm.containsKey(s)) {
					hm.put(s, 1);
				} else {
					hm.put(s, hm.get(s) + 1);
				}
			}
			al.add((HashMap<String, Integer>) hm.clone());
			hm.clear();
		}
			int [] arr=new int[m];
			dfs(0, 0, arr);
		System.out.println(sum%MOD);
		sc.close();
	}

	public static void dfs(int index, int id, int[] arr) {
		if (index == m) {
          check(arr);
          return;
		}

		for (int i = id; i < n; i++) {
			arr[index] = i;
			dfs(index + 1, i + 1, arr);

		}

	}

	public static void check(int[] arr) {
		HashMap<String, Integer> st = al.get(arr[0]);
		Set<String> lis = st.keySet();
		
		int summ = 1;
		int flag=0;
		for (String str : lis) {
			flag=0;
			summ=1;
			for (int j = 0; j < m; j++) {
				if (al.get(arr[j]).containsKey(str)) 
					summ = (summ*(al.get(arr[j]).get(str)%MOD))%MOD;
				 else {
					flag=1;
					break;
				}
			}
			if (flag==1) 
				continue;
			
			sum += summ%MOD;
		}
	}
}
