import java.util.HashMap;
import java.util.Scanner;

public class PaintArea {

	private static long sum=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x1, y1, x2, y2;
		int maxx, maxy, minx, miny;
	//	int[][] map = new int[10001][10001];
		for (int i = 0; i < n; i++) {
			x1 = sc.nextInt();
			y1 = sc.nextInt();
			x2 = sc.nextInt();
			y2 = sc.nextInt();
			maxx = Math.max(x1, x2);
			minx = Math.min(x1, x2);
			maxy = Math.max(y1, y2);
			miny = Math.min(y1, y2);
		//	getS(map, maxx, maxy, minx, miny);
			getS(maxx, maxy, minx, miny);
			
		}
		
		System.out.println(hs.size());
		
		//System.out.println(sum);

	}
	
/*
 * 用数组内存超限
	private static void getS(int[][]map,int maxx,int maxy, int minx, int miny ) {
			for (int j = minx+1; j <=maxx; j++)
				for (int k = miny+1; k <= maxy; k++) {
					if(map[j][k]==0){
						map[j][k]=1;
						sum++;
					}
				}
	}
	
*/
	
	//用哈希表超时 
	private static HashMap<String,Integer> hs=new HashMap<String,Integer>();
	private static void getS(int maxx,int maxy, int minx, int miny ) {
		String temp;
			for (int j = minx+1; j <=maxx; j++)
				for (int k = miny+1; k <= maxy; k++) {
					temp=j+" "+k;
					if(!hs.containsKey(temp)){
						hs.put(temp, 1);
					}
				}
	}
}
