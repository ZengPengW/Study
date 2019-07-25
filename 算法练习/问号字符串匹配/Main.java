import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.next();
		String sub = scanner.next();
		System.out.println(f(string, sub));
	}

	public static int f(String string, String sub) {

		int id = 0;
		int idbef = 0;
		int idnext = 0;
		int flag = 0;// 前者问号个数
		char c;
		for (int i = 0; i < sub.length(); i++) {
			if(id>string.length())return -1;
			c = sub.charAt(i);
			if (c != '?') {
				idnext = string.substring(id).indexOf(c) ;
				if (idnext==-1) {
					return -1;
				}
				idnext+=+ id;
						
				int len=idnext - idbef - 1>0?idnext - idbef - 1:0;
				if (len <= flag * 3 && len >= flag) {
					idbef=idnext;
					id = idnext + 1;
					
					flag = 0;
				} else {
					return -1;
				}

			} else if (c == '?') {
				flag += 1;
				id++;
			}
		}

		return id;
	}

}
