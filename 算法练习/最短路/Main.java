import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static int[][] map;
	private static int[] val;
	private static int m;
	private static int n;
	public static void main(String[] args) throws IOException {
		//Scanner scan = new Scanner(System.in);
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String []instr=null;
		instr=in.readLine().trim().split(" ");
		n = Integer.parseInt(instr[0]);
		m =  Integer.parseInt(instr[1]);
		map = new int[n + 1][n + 1];
		val = new int[n + 1];

		for (int i = 0; i < map.length; i++) {
			val[i] = 999999;
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = 999999;

			}
		}

		int u, v, l;
		for (int i = 0; i < m; i++) {
			instr=in.readLine().trim().split(" ");
			u = Integer.parseInt(instr[0]);
			v = Integer.parseInt(instr[1]);
			l = Integer.parseInt(instr[2]);
			map[u][v] = l;
		}
		map[1][1] = 0;
		val[1] = 0;
		//dijkstra();
		// bellman_ford();
		bellman_ford_que() ;
		for (int i = 2; i < val.length; i++) {
			System.out.println(val[i]);
		}

	}

	public static void dijkstra() {
		int[] mark = new int[map.length];
		int temp = 999999;
		int id = 0;
		int len=map.length;
		for (int u = 1; u < len; u++) {
			temp = 999999;
			for (int i = 1; i < len; i++) {
				
					if (mark[i] == 0 && val[i] < temp) {
						temp = val[i];
						id = i;
					}
				
			}
			mark[id] = -1;
			// &&mark[j]==0
			for (int j = 1; j < len; j++) {
				if (map[id][j] != 999999 && mark[j] == 0 && val[id] + map[id][j] < val[j])
					val[j] = val[id] + map[id][j];
			}

		}
	}

	public static void bellman_ford() {
		int len=map.length;
		boolean flag = false;
		for (int i = 1; i <n; i++) {
			flag = false;
			for (int j = 1; j < len; j++) {
				for (int k = 1; k < len; k++) {
					if (map[j][k] != 999999 && val[j] != 999999 && val[k] > map[j][k] + val[j]) {
						val[k] = map[j][k] + val[j];
						flag = true;
					}
				}
			}

			if (!flag) {
				break;
			}
		}

		if(flag) {
			flag = false;
			for (int j = 1; j < len; j++) {
				for (int k = 1; k < len; k++) {
					if (map[j][k] != 999999 && val[j] != 999999 && val[k] > map[j][k] + val[j]) {
						flag = true;
					}
				}
			}
			if (flag) {
				System.out.println("存在负环-无最短路");
				System.exit(0);
			}
		}
		

	}

public static void bellman_ford_que() {
	int len=map.length;
	Queue<Integer> id=new LinkedList<Integer>();
	
	id.offer(1);
	
	while(!id.isEmpty()){
		int w=id.poll();
		for (int i = 1; i < len; i++) {
			if(map[w][i]!=999999&&val[i]>map[w][i]+val[w]){
				val[i]=map[w][i]+val[w];
				if(!id.contains(i))id.offer(i);
				
			}	
		}
		
	}
}
}
