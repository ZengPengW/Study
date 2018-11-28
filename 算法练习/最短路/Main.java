import java.util.Scanner;

public class Main {
	private static int[][] map;
	private static int[] val;
	private static int m;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		m = scan.nextInt();
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
			u = scan.nextInt();
			v = scan.nextInt();
			l = scan.nextInt();
			map[u][v] = l;
		}
		map[1][1] = 0;
		val[1] = 0;
		//dijkstra();
		// bellman_ford();
		bellman_ford_que() 
		for (int i = 2; i < val.length; i++) {
			System.out.println(val[i]);
		}

	}

	public static void dijkstra() {
		int[] mark = new int[map.length];
		int temp = 99999999;
		int id = 0;

		for (int u = 1; u < mark.length; u++) {
			temp = 99999999;
			for (int i = 1; i < map.length; i++) {
				for (int k = 1; k < map.length; k++) {
					if (map[i][k] != 999999 && mark[k] == 0 && val[k] < temp) {
						temp = val[k];
						id = k;
					}
				}
			}
			mark[id] = -1;
			// &&mark[j]==0
			for (int j = 1; j < mark.length; j++) {
				if (map[id][j] != 999999 && mark[j] == 0 && val[id] + map[id][j] < val[j])
					val[j] = val[id] + map[id][j];
			}

		}
	}

	public static void bellman_ford() {

		boolean flag = false;
		for (int i = 1; i <= m; i++) {
			flag = false;
			for (int j = 1; j < map.length; j++) {
				for (int k = 1; k < map.length; k++) {
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
			for (int j = 1; j < map.length; j++) {
				for (int k = 1; k < map.length; k++) {
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

	Queue<Integer> id=new LinkedList<Integer>();
	
	id.offer(1);
	
	while(!id.isEmpty()){
		int w=id.poll();
		for (int i = 1; i < map.length; i++) {
			if(map[w][i]!=999999&&val[i]>map[w][i]+val[w]){
				val[i]=map[w][i]+val[w];
				if(!id.contains(i))id.offer(i);
				
			}	
		}
		
	}
}
}

}
