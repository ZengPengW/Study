
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CoinChange {


	public static void main(String[] args) throws IOException {
		// BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		Scanner in = new Scanner(System.in);
//		System.setOut(new PrintStream(new FileOutputStream("D:/3.txt")));
//		int[] num = { 1, 5, 10, 25, 50 };
//		System.out.print("1,");
//		for (int i =1; i <=250; i++) {
//			count=0;
//			dfs(i,num,0,0);
//			System.out.print(count+",");
//		}

		int []map= {1,1,1,1,1,2,2,2,2,2,4,4,4,4,4,6,6,6,6,6,9,9,9,9,9,13,13,13,13,13,18,18,18,18,18,24,24,24,24,24,31,31,31,31,31,39,39,39,39,39,50,50,50,50,50,62,62,62,62,62,77,77,77,77,77,93,93,93,93,93,112,112,112,112,112,134,134,134,134,134,159,159,159,159,159,187,187,187,187,187,218,218,218,218,218,252,252,252,252,252,292,291,291,291,291,333,333,333,333,332,380,380,380,379,378,430,430,429,428,427,485,484,483,482,482,544,543,542,541,539,608,607,606,604,602,677,676,673,671,670,751,748,746,744,743,828,825,823,822,818,912,910,908,904,900,1001,999,995,990,986,1098,1093,1088,1084,1081,1196,1191,1186,1182,1177,1301,1296,1292,1285,1278,1413,1408,1400,1393,1387,1532,1524,1515,1508,1503,1654,1644,1637,1631,1622,1782,1773,1766,1757,1746,1916,1909,1898,1886,1875,2061,2049,2037,2025,2015,2208,2194,2181,2170,2156,2361,2348,2336,2321,2306,2521,2508,2492,2475,2459,2691,2673,2654,2637,2622,2863,2843,2824,2808,2789,3042,3021,3004,2983,2960,3228,3209,3187,3164,3140,3424,3401,3376,3351,3329,3623,3596,3570,3545,3517,3830};
		int n;
		while (in.hasNextInt()) {
			n = in.nextInt();
			System.out.println(map[n]);
		}

	}
//	private static int count=0;
//	private static void dfs(int n, int[] num,int index,int ans) {
//		if(n<0||ans>100)return;		
//		if(index==5){
//			if(n==0)count++;
//			return;
//		}
//		
//		for (int i = 0; i <=100; i++) {
//			dfs(n-(num[index]*i), num, index+1,ans+i);
//			
//		}
//		
//	}
}