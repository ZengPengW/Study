
public class StringSearh {

	public static void main(String[] args) {

		System.out.println(KPMsearch("abacadabrac", "abra"));

		System.out.println(DefSearch("abacadabrac", "abra"));

		System.out.println(VBSearch("abacadabrac", "abra"));
		String s="baaaaaaaaaaaaabbaaabaaaaaaaaaaaaaaaaaaaaabaaaabaaaaaaaaaabababaaaabaaabababaaaababaabaabaabaaaaaaaa";
		System.out.println(BoyerMoore(s, "aa"));

	}

	// z(<普通--暴力子字符串查找算法>)
	public static int DefSearch(String str, String pat) {
		int m = pat.length();
		int n = str.length();

		for (int i = 0; i <= Math.abs(m - n); i++) {

			int j;
			for (j = 0; j < m; j++)
				if (str.charAt(i + j) != pat.charAt(j))
					break;

			if (j == m)
				return i;

		}

		return -1;

	}

	// x显示回退法--暴力查找子字符串
	public static int VBSearch(String str, String pat) {
		int j, m = pat.length();
		int i, n = str.length();

		for (j = 0, i = 0; j < m && i < n; i++) {

			if (str.charAt(i) == pat.charAt(j))
				j++;
			else {
				i -= j;
				j = 0;
			}
		}
		if (j == m)
			return i - j;
		return -1;
	}

	// KPM子字符串查找算法
	public static int[] getnext(String pat) {
		int len = pat.length();
		int[] next = new int[len];
		char[] p = pat.toCharArray();
		int j = 0;
		int k = -1;
		next[0] = -1;
		while (j < len-1) {

			if (k == -1 || p[j] == p[k]) {
				++j;
				++k;
				if (p[j] == p[k])
					next[j] = next[k];
				else
					next[j] = k;
			} else {
				k = next[k];
			}
		}

		return next;
	}

	public static int KPMsearch(String str, String pat) {
		int[] next = getnext(pat);
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		int slen = s.length;
		int plen = p.length;

		int i = 0, j = 0;
		while (i < slen && j < plen) {
			if (j == -1 || s[i] == p[j]) {
				++i;
				++j;
			} else {
				j = next[j];
			}
		}
		if (j == plen)
			return i - j;
		return -1;

	}

//Boyer_Moore字符串匹配算法

	private static int[] getRight(String pat) {
		int m = pat.length();
		int r = 256;
		int[] right = new int[r];

		for (int i = 0; i < right.length; i++) {
			right[i] = -1;
		}

		for (int i = 0; i < m; i++) {
			right[pat.charAt(i)] = i;
		}
		return right;
	}

	public static int BoyerMoore(String txt,String pat){
	 
		int[] right=getRight(pat);
		int n=txt.length();
		int m=pat.length();
		int skip=0;
		for (int i = 0; i <=n-m; i+=skip) {
			skip=0;
			for (int j = m-1; j>=0; j--) {
				if(pat.charAt(j)!=txt.charAt(i+j)) {
					int a=j-right[txt.charAt(i+j)];//坏后缀:当前匹配串的索引 - 文本匹配失败的字符出现在匹配串最右位置的索引;
				//	int b=(m-1)-right[pat.charAt(m-1)];//好后缀:用匹配串 匹配成功的最末尾字符的索引 - 匹配串中的上一次出现此字符的位置
					//skip=Math.max(a, b);
					skip=a;
					if(skip<1)skip=1;
					break;
				}
				
			}
			if(skip==0)return i;
		}
	
	return -1;
	
}
	

}
