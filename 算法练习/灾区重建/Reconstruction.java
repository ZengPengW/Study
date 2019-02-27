import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Reconstruction {

	private static class Edge {
		int xi, yi, pi;

		public Edge(int xi, int yi, int pi) {
			this.pi = pi;
			this.yi = yi;
			this.xi = xi;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N, M, Q;
		N = sc.nextInt();
		M = sc.nextInt();
		Q = sc.nextInt();
		ArrayList<Edge> edge = new ArrayList<He.Edge>();
		for (int i = 0; i < M; i++) {
			int xi = sc.nextInt();
			int yi = sc.nextInt();
			int pi = sc.nextInt();
			edge.add(new Edge(xi, yi, pi));
		}
		Collections.sort(edge, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.pi > o2.pi)
					return 1;
				else if (o1.pi < o2.pi)
					return -1;
				return 0;
			}
		});
		int[][] dis = kruskal(edge, N);
		int Li, Ri, Ki, Ci;
		for (int i = 0; i < Q; i++) {
			Li = sc.nextInt();
			Ri = sc.nextInt();
			Ki = sc.nextInt();
			Ci = sc.nextInt();
			f(N, dis, Li, Ri, Ki, Ci);
		}

	}

	public static void f(int N, int[][] dis, int Li, int Ri, int Ki, int Ci) {
		max = 0;
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = Li; i <= Ri; i++) {
			if (i % Ki == Ci) {
				al.add(i);
			}
		}
		int st = al.remove(0);
		int[] mark = new int[N + 1];
		mark[st] = 1;
		dfs(al, dis, mark, st, N, -1);
		System.out.println(max);
	}

	private static int max = 0;

	private static void dfs(ArrayList<Integer> al, int[][] dis, int[] mark,
			int st, int N, int mx) {
		if (al.contains(st)) {
			max = max < mx ? mx : max;
		}
		for (int i = 1; i <= N; i++) {
			if (dis[st][i] != 9999999 && mark[i] == 0) {
				mark[i] = 1;
				dfs(al, dis, mark, i, N, mx < dis[st][i] ? dis[st][i] : mx);
			}
		}
	}

	public static int[][] kruskal(ArrayList<Edge> edge, int N) {
		ArrayList<Edge> minTree = new ArrayList<Edge>();

		int[] fa = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			fa[i] = i;
		}

		int index = 0, f1, f2;
		Edge ed = null;
		int[][] dis = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++)
			for (int j = 0; j <= N; j++) {
				dis[i][j] = 9999999;
			}
		while (minTree.size() < N - 1) {
			ed = edge.get(index++);
			f1 = fa(fa, ed.xi);
			f2 = fa(fa, ed.yi);
			if (f1 != f2) {
				fa[f2] = f1;
				minTree.add(ed);
				dis[ed.xi][ed.yi] = ed.pi;
				dis[ed.yi][ed.xi] = ed.pi;
			}
		}

		return dis;

	}

	private static int fa(int[] fa, int x) {
		if (fa[x] == x)
			return x;
		else {
			fa[x] = fa(fa, fa[x]);
			return fa[x];
		}

	}
}
