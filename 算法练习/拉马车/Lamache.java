import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Lamache {

	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		f(s1, s2);
	
	}

	public static void f(String s1, String s2) {
		ArrayList<String> sa1 = new ArrayList<String>();
		ArrayList<String> sa2 = new ArrayList<String>();
		for (int i = 0; i < s1.length(); i++) {
			sa1.add("" + s1.charAt(i));
		}
		for (int i = 0; i < s2.length(); i++) {
			sa2.add("" + s2.charAt(i));
		}

		Stack<String> sk = new Stack<String>();

		int wz;
		int index = 0;
		while (sa1.size() > 0 && sa2.size() > 0) {
			index++;
			if (index > 1500000) {
				System.out.println(-1);
				return;
			}
			while (true) {

				if (sk.contains(sa1.get(0))) {
					wz = sk.search(sa1.get(0));
					sk.push(sa1.get(0));
					sa1.remove(0);
					for (int i = 0; i <= wz; i++) {
						sa1.add(sk.pop());
					}

				} else {
					sk.push(sa1.get(0));
					sa1.remove(0);
					break;
				}

			}

			while (true) {

				if (sa1.size() < 1) {
					break;
				}
				if (sk.contains(sa2.get(0))) {
					wz = sk.search(sa2.get(0));
					sk.push(sa2.get(0));
					sa2.remove(0);
					for (int i = 0; i <= wz; i++) {
						sa2.add(sk.pop());
					}

				} else {
					sk.push(sa2.get(0));
					sa2.remove(0);
					break;
				}

			}

		}

		if (sa1.size() > 0) {
			for (int i = 0; i < sa1.size(); i++) {

				System.out.print(sa1.get(i));

			}
//			System.out.println("Aʤ");
		} else {
			for (int i = 0; i < sa2.size(); i++) {

				System.out.print(sa2.get(i));

			}
//			System.out.println("Bʤ");
		}

	}

}
