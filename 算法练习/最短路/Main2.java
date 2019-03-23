import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main2 {
	private static class Edge {
		List<Integer> go = new ArrayList<Integer>();
		List<Integer> weight = new ArrayList<Integer>();
	};

	private static int[] val;
	private static int m;
	private static int n;

	public static void main(String[] args) throws IOException {
		// Scanner scan = new Scanner(System.in);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] instr = null;
		instr = in.readLine().trim().split(" ");
		n = Integer.parseInt(instr[0]);
		m = Integer.parseInt(instr[1]);

		val = new int[n + 1];
		Edge[] map = new Edge[n + 1];
		for (int i = 0; i <= n; i++) {
			val[i] = 999999;
			map[i] = new Edge();
		}

		int u, v, l;

		for (int i = 0; i < m; i++) {
			instr = in.readLine().trim().split(" ");
			u = Integer.parseInt(instr[0]);
			v = Integer.parseInt(instr[1]);
			l = Integer.parseInt(instr[2]);
			map[u].go.add(v);
			map[u].weight.add(l);
		}

		val[1] = 0;
		dijkstra(map);
		// bellman_ford();
		// bellman_ford_que(map) ;
		for (int i = 2; i < val.length; i++) {
			System.out.println(val[i]);
		}

	}

	public static void dijkstra(Edge[] map) {
		Edge edge = null;
		int[] mark = new int[n + 1];
		int temp = 999999;
		int id = 0;
		int len = map.length;
		for (int u = 1; u < n; u++) {
			temp = 999999;
			for (int i = 1; i < len; i++) {

				if (mark[i] == 0 && val[i] < temp) {
					temp = val[i];
					id = i;
				}

			}
			mark[id] = -1;
			// &&mark[j]==0
			edge = map[id];
			for (int i = edge.go.size() - 1; i >= 0; i--) {
				int go = edge.go.get(i);
				int weight = edge.weight.get(i);
				if (mark[go] == 0 && val[go] > val[id] + weight) {
					val[go] = val[id] + weight;
				}
			}

		}
	}

	public static void bellman_ford_que(Edge[] map) {

		Queue<Integer> id = new LinkedList<Integer>();

		id.offer(1);
		Edge edge = null;
		while (!id.isEmpty()) {
			int w = id.poll();
			edge = map[w];
			for (int i = edge.go.size() - 1; i >= 0; i--) {
				int go = edge.go.get(i);
				int weight = edge.weight.get(i);
				if (val[go] > val[w] + weight) {
					val[go] = val[w] + weight;
					if (!id.contains(go))
						id.offer(go);

				}
			}

		}
	}
}
