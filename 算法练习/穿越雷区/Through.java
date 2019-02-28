
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Through
{
	public static void main(String[] args)
	{		
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x = 0,y = 0;
		String [][]map=new String[n][n];
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < n; j++) {
				map[i][j]=sc.next();
				if(map[i][j].equals("A")) {
					x=i;
					y=j;
				}
			}
		System.out.println(bfs(map, x, y));
		
	}
	private static class Point{
		int x,y,bs;
		String s;
		public Point(int x,int y,int bs,String s) {
			this.x=x;
			this.y=y;
			this.bs=bs;
			this.s=s;
		}
	}
	public static int bfs(String [][]map,int x,int y) {
		Queue<Point> que=new LinkedList<Point>();
		
		que.offer(new Point(x, y,0,"A"));
		Point p=null;
		int n=map.length;
		int [][]mark=new int[n][n];
		while(!que.isEmpty()) {
			p=que.poll();
			//ио
			if(p.x-1>=0&&mark[p.x-1][p.y]==0&&(!p.s.equals(map[p.x-1][p.y]))) {
				if(map[p.x-1][p.y].equals("B"))return p.bs+1;
				mark[p.x-1][p.y]=1;
				que.add(new Point(p.x-1,p.y,p.bs+1,map[p.x-1][p.y]));
			}
			//об
			if(p.x+1<n&&mark[p.x+1][p.y]==0&&(!p.s.equals(map[p.x+1][p.y]))) {
				if(map[p.x+1][p.y].equals("B"))return p.bs+1;
				mark[p.x+1][p.y]=1;
				que.add(new Point(p.x+1,p.y,p.bs+1,map[p.x+1][p.y]));
			}
			//вС
			if(p.y-1>=0&&mark[p.x][p.y-1]==0&&(!p.s.equals(map[p.x][p.y-1]))) {
				if(map[p.x][p.y-1].equals("B"))return p.bs+1;
				mark[p.x][p.y-1]=1;
				que.add(new Point(p.x,p.y-1,p.bs+1,map[p.x][p.y-1]));
			}
			//ср
			if(p.y+1<n&&mark[p.x][p.y+1]==0&&(!p.s.equals(map[p.x][p.y+1]))) {
				if(map[p.x][p.y+1].equals("B"))return p.bs+1;
				mark[p.x][p.y+1]=1;
				que.add(new Point(p.x,p.y+1,p.bs+1,map[p.x][p.y+1]));
			}
			
		}
		return -1;
		
	}
	
}
