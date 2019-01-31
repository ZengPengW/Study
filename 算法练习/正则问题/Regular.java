import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Regular {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();

		s = s.replace("x(", "x+(");
		s = s.replace(")(", ")+(");
		s = s.replace(")x", ")+x");

		s = setStr(s + "|");
		//System.out.println(s);
		System.out.println(getLength(s));

	}

	private static String setStr(String s) {

		int id = 0;
		char c;
		int shu = 0;
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);

			if (c == '(') {
				sb.append(c);
				id++;
			} else if (c == ')') {
				sb.append(c);
				id--;
			} else if (c == '|') {
				if (id == 0) {
					sb.insert(shu, "(");
					sb.append(")");
					sb.append(c);
					shu = sb.length();
				} else {
					sb.append(c);
				}
			} else {
				sb.append(c);
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	// ((xx|xxx)x|(x|xx))xx
	public static int getLength(String s) {
		Stack<Character> sk = new Stack<Character>();
		ArrayList<String> al = new ArrayList<String>();
		boolean isright = false;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == 'x') {
				int count = 1;
				i++;
				while (i < s.length()) {
					if (s.charAt(i++) == 'x')
						count++;
					else {
						i -= 2;
						break;
					}
				}
				al.add("" + count);
			} else if (ch == '(') {
				isright = false;
				sk.push(ch);
			} else if (ch == ')') {
				isright = true;
				char c;
				while (!sk.isEmpty()) {
					c = sk.pop();
					if (c == '(')
						break;
					else {
						al.add(String.valueOf(c));
					}
				}
			} else {
				isright = false;
				char c;
				while (!sk.isEmpty()) {
					c = sk.peek();
					if (c == '(')
						break;
					else {
						sk.pop();
						al.add(String.valueOf(c));
					}
				}
				sk.push(ch);
			}
		}

		while (!sk.isEmpty()) {
			al.add(String.valueOf(sk.pop()));
		}

		Stack<Integer> op = new Stack<Integer>();
		int sum = 0;
		for (int i = 0, len = al.size(); i < len; i++) {
			String v = al.get(i);
			if (v.equals("|") || v.equals("+")) {
				int a = op.pop();
				int b = op.pop();
				if (v.equals("|")) {
					op.push(Math.max(a, b));
				} else {
					op.push(a + b);
				}

			} else {
				// System.out.println(v);
				op.push(Integer.valueOf(v));
			}

		}

		return op.pop();
	}
}
