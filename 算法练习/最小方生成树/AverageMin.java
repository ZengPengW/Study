
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AverageMin {

	private static class Node {
		int u, v, w;
		double val;

		public Node(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int n, m;
		int ce = 1;
		while (true) {

			n = scan.nextInt();
			m = scan.nextInt();
			if (n == 0 && m == 0)
				return;
			List<Node> list = new ArrayList<Node>();
			for (int i = 0; i < m; i++) {
				int u = scan.nextInt();
				int v = scan.nextInt();
				int w = scan.nextInt();
				list.add(new Node(u, v, w));
			}
			Collections.sort(list, new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					if (o1.w > o2.w)
						return 1;
					else if (o1.w < o2.w)
						return -1;
					return 0;
				}
			});

			int minv = 0, maxv = 0;
			for (int i = 0; i < n - 1; i++)
				minv += list.get(i).w;

			for (int i = m - 1; i > m - n; i--)
				maxv += list.get(i).w;

			ans =0x1.fffffffffffffP+1023;
			for (int i = minv; i <= maxv; i++) {
				getVariance(list, n, i);
			}
			System.out.println("Case " + (ce++) + ": " + (String.format("%.2f", ans / (n - 1))));

		}
	}

	private static double ans =0x1.fffffffffffffP+1023;

	public static void getVariance(List<Node> list, int n, double sum) {
		double ave = sum / (n - 1);// 平均值
		for (Node node : list) {
			node.val = StrictMath.pow((node.w - ave), 2);
		}

		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.val > o2.val)
					return 1;
				else if (o1.val < o2.val)
					return -1;
				return 0;
			}
		});

		int[] fa = new int[n + 1];
		for (int i =1; i <= n; i++) 
			fa[i] = i;
		

		
		int summ = 0;// 最小生成树权重和
		double div = 0;// (x-(x-))^2的和
		n=n-1;//方便计算
		ArrayList<Integer> al = new ArrayList<Integer>(n - 1);
		for (Node no : list) {
			int u = no.u;
			int v = no.v;
			int w = no.w;
			int f1 = getFa(fa, u);
			int f2 = getFa(fa, v);

			if (f1 != f2) {
				summ += w;
				div += no.val;
				fa[f2] = f1;
				al.add(w);
				// System.out.println(u+" "+v);
			}
			if (al.size() == n)
				break;
		}
		if (summ == sum) {
			if(ans>div)ans = div;
		}

	}

	private static int getFa(int[] fa, int x) {
		if (fa[x] == x)
			return x;
		else {
			fa[x] = getFa(fa, fa[x]);
			return fa[x];
		}
	}
}
