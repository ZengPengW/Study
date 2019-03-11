import java.util.Scanner;

public class Dance {
	private static 	int maxx=Integer.MIN_VALUE,maxy=Integer.MIN_VALUE,minx=Integer.MAX_VALUE,miny=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		int [][]point=new int[n][2];
	
		for (int i = 0; i <n; i++) {
			point[i][0]=scan.nextInt();
			point[i][1]=scan.nextInt();
			if(minx>point[i][0])minx=point[i][0];
			else if(maxx<point[i][0])maxx=point[i][0];
			if(miny>point[i][1])miny=point[i][1];
			else if(maxy<point[i][1])maxy=point[i][1];
		}
		int count=0;
		for (int j =miny; j <maxy; j++) {
		for (int i = minx; i <maxx; i++) {
			if(!isInner(point, n, i, j))continue;
			if(!isInner(point, n, i, j+1))continue;
			if(!isInner(point, n,i+1, j))continue;
			if(!isInner(point, n,i+1, j+1))continue;
			count++;
		}	
		}
		System.out.println(count);
	}
	public static boolean isInner(int [][]point,int n,int x,int y) {
		if(x>maxx||x<minx||y>maxy||y<miny)return false;
		boolean bl=false;
		for (int i = 0,j=n-1; i <n; j=i++) {
			if((point[i][1]>y)!=(point[j][1]>y)
					&&(x<((point[j][0]-point[i][0])*(y-point[i][1])/(point[j][1]-point[i][1])+point[i][0]))) {
				bl=!bl;
			}		
		}
		return bl;
	}
}