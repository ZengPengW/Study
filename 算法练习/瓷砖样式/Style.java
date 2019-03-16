import java.util.HashSet;

public class Style {
	private static long ans=0;
	public static void main(String[] args) {
	int [][]mark=new int[3][10];
		f(mark, 0, 0);
		
		System.out.println(ans);
	}
	private static int [][]dir= {{0,0},{0,1},{1,0},};
	private static HashSet<String> hs=new HashSet<String>();
	public static void f(int [][]mark,int px,int py) {
		if (px>2) {
			return;
		}
		if (px>=2&&py>=9) {
			StringBuilder sb=new StringBuilder();
			for (int i = 0; i <3; i++) {
				for (int j = 0; j <10; j++) {
					sb.append(mark[i][j]);
				}
			}
			String s=sb.toString();
			if(!hs.contains(s)){
				ans++;
				hs.add(s);
			}
			
			return;
		}
		
		if(mark[px][py]==0) {
			
		for (int i =1; i <=2; i++) {//1黄2橙
		
			for (int j = 1; j <=2; j++) {//1横2竖
				int x=px+dir[j][0];
				int y=py+dir[j][1];
				if(x>2||y>9||mark[x][y]!=0)continue;
				
				mark[px][py]=i;
				mark[x][y]=i;
				if(!check(mark, px, py, x, y,i)) {
					mark[px][py]=0;
					mark[x][y]=0;
					continue;
				}
				
				
				int x1=px;
				int y1=py+1;
				if(y1>9) {
					x1+=1;
					y1=0;
				}
				f(mark, x1, y1);
				
				
				mark[px][py]=0;
				mark[x][y]=0;
				
			}
		}	
		}else {
			int x=px;
			int y=py+1;
			if(y>9) {
				x+=1;
				y=0;
			}
			f(mark, x, y);
		}
		
	}
	
	public static boolean check(int [][]mark ,int px,int py,int x,int y,int j) {
		if(px-1>=0) {
			int a=0;
			if(mark[px-1][py]==j)a++;
			if(py+1<10&&mark[px-1][py+1]==j)a++;
			if(py+1<10&&mark[px][py+1]==j)a++;
			if (a==3) return false;
		
		}
		
		if(px-1>=0) {
			int a=0;
			if(mark[px-1][py]==j)a++;
			if(py-1>=0&&mark[px-1][py-1]==j)a++;
			if(py-1>=0&&mark[px][py-1]==j)a++;
			if (a==3) return false;
		
		}
		
		if(px+1<3) {
			int a=0;
			if(mark[px+1][py]==j)a++;
			if(py-1>=0&&mark[px+1][py-1]==j)a++;
			if(py-1>=0&&mark[px][py-1]==j)a++;
			if (a==3) return false;
		}
		
		if(px+1<3) {
			int a=0;
			if(mark[px+1][py]==j)a++;
			if(py+1<10&&mark[px+1][py+1]==j)a++;
			if(py+1<10&&mark[px][py+1]==j)a++;
			if (a==3) return false;
		}
		
		//第二个方块
		if(x-1>=0) {
			int a=0;
			if(mark[x-1][y]==j)a++;
			if(y+1<10&&mark[x-1][y+1]==j)a++;
			if(y+1<10&&mark[x][y+1]==j)a++;
			if (a==3) return false;
		
		}
		
		if(x-1>=0) {
			int a=0;
			if(mark[x-1][y]==j)a++;
			if(y-1>=0&&mark[x-1][y-1]==j)a++;
			if(y-1>=0&&mark[x][y-1]==j)a++;
			if (a==3) return false;
		
		}
		
		if(x+1<3) {
			int a=0;
			if(mark[x+1][y]==j)a++;
			if(y-1>=0&&mark[x+1][y-1]==j)a++;
			if(y-1>=0&&mark[x][y-1]==j)a++;
			if (a==3) return false;
		}
		
		if(x+1<3) {
			int a=0;
			if(mark[x+1][y]==j)a++;
			if(y+1<10&&mark[x+1][y+1]==j)a++;
			if(y+1<10&&mark[x][y+1]==j)a++;
			if (a==3) return false;
		}
		return true;
		
	}
}