import java.util.LinkedList;
import java.util.Scanner;


public class CutChange {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = sc.nextInt();
		}
		f(num);
	}

	public static void f(int[] num) {

		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0, len = num.length; i < len; i++) {
			if (!list.contains(num[i])) {
				System.out.print("-"+num[i] + " ");
				list.add(num[i]);
			} else {
				int index = list.lastIndexOf(num[i]);
				System.out.print((list.size() - index - 1)+" ");
				list.remove(index);
				list.add(num[i]);
			}
		}
	}

		
		
		
	}

}
