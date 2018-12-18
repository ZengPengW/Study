import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Eurodiffusion{
	private static class City{
		String name;
		int xl,yl,xh,yh;
		int count=0;
		int days=0;
		int c;
		public City(String name,int xl,int yl,int xh,int yh,int c) {
			this.name=name;
			this.xh=xh;
			this.xl=xl;
			this.yh=yh;
			this.yl=yl;
			this.c=c;
			count=(xh-xl+1)*(yh-yl+1)*(this.c-1);
		}
	}
	private static int idx=1;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		while (true) {
			int c=scan.nextInt();
			if(c==0)break;
			ArrayList<City>al=new ArrayList<City>((int)(c*0.75+1));
			int minx=99,miny=99,maxx=0,maxy=0;
			
			for (int i = 0; i < c; i++) {
				String name=scan.next();
				int xl=scan.nextInt();
				if(minx>xl)minx=xl;
				
				int yl=scan.nextInt();
				if(miny>yl)miny=yl;
				
				int xh=scan.nextInt();
				if(maxx<xh)maxx=xh;
				
				int yh=scan.nextInt();
				if(maxy<yh)maxy=yh;
				al.add(new City(name,xl,yl,xh,yh,c));			
			}
			int [][]map=new int[maxx+2][maxy+2];
			int [][][]coin=new int[maxx+2][maxy+2][c+1];
			int index=1;
			for (int i = 0; i < al.size(); i++) {
				int xl=al.get(i).xl;
				int yl=al.get(i).yl;
				int xh=al.get(i).xh;
				int yh=al.get(i).yh;

				for (int j =xl; j <=xh; j++) {
					for (int k = yl; k <=yh; k++) {
						map[j][k]=index;
						coin[j][k][map[j][k]]=1000000;
					}
				}	
				index++;	
			}
			
			f(map,coin,c,al,minx,miny,maxx,maxy);
			System.gc();
		}
		
		
	}
	public static void f(int[][]map,int [][][]coin,int c,ArrayList<City> al,int minx,int miny,int maxx, int maxy) {
		int [][][]p=new int[maxx+2][maxy+2][c+1];
		int []co=new int[c+1];
		int id=0;
		
		int index=0;
		int [][][]deliver;
		deliver=new int[maxx+2][maxy+2][c+1];//需要送人的硬币个数
		//初始化 
		for (int i =minx; i <=maxx; i++) {
			for (int j =miny; j <=maxy; j++) {
				if(map[i][j]!=0){
					deliver[i][j][map[i][j]]=coin[i][j][map[i][j]]/1000;
				}
			}	
		}
		//循环开始
		while(index<c&&c!=1){
			id++;
		for (int m =1; m <=c; m++) {
			for (int i =minx; i <=maxx; i++) {
				for (int j =miny; j <=maxy; j++) {
					
						if(deliver[i][j][m]!=0){
							//西
							if(map[i-1][j]!=0){
								coin[i-1][j][m]+=deliver[i][j][m];
								coin[i][j][m]-=deliver[i][j][m];
								
								if(m!=map[i-1][j]&&p[i-1][j][m]==0){
									p[i-1][j][m]=1;
									co[map[i-1][j]]+=1;
									if(co[map[i-1][j]]==al.get(map[i-1][j]-1).count){
										al.get(map[i-1][j]-1).days=id;
										index++;
									}
								}
							}
							//东
							if(map[i+1][j]!=0){
								coin[i+1][j][m]+=deliver[i][j][m];
								coin[i][j][m]-=deliver[i][j][m];
								
								if(m!=map[i+1][j]&&p[i+1][j][m]==0){
									p[i+1][j][m]=1;
									co[map[i+1][j]]+=1;
									if(co[map[i+1][j]]==al.get(map[i+1][j]-1).count){
										al.get(map[i+1][j]-1).days=id;
										index++;
									}
								}
							}
							
							//北
							if(map[i][j+1]!=0){
								coin[i][j+1][m]+=deliver[i][j][m];
								coin[i][j][m]-=deliver[i][j][m];
								
								if(m!=map[i][j+1]&&p[i][j+1][m]==0){
									p[i][j+1][m]=1;
									co[map[i][j+1]]+=1;
									if(co[map[i][j+1]]==al.get(map[i][j+1]-1).count){
										al.get(map[i][j+1]-1).days=id;
										index++;
									}
								}
							}
							
							//南
							if(map[i][j-1]!=0){
								coin[i][j-1][m]+=deliver[i][j][m];
								coin[i][j][m]-=deliver[i][j][m];
								
								if(m!=map[i][j-1]&&p[i][j-1][m]==0){
									p[i][j-1][m]=1;
									co[map[i][j-1]]+=1;
									if(co[map[i][j-1]]==al.get(map[i][j-1]-1).count){
										al.get(map[i][j-1]-1).days=id;
										index++;
									}
								}
							}
					
						}	
					}	
				}
			}
			
			
			deliver=new int[maxx+2][maxy+2][c+1];
			for (int i =minx; i <=maxx; i++) {
				for (int j =miny; j <=maxy; j++) {
					if(map[i][j]!=0){
					for (int l = 1; l <=c; l++) {
						deliver[i][j][l]=coin[i][j][l]/1000;
					}
				  }
				}	
			}
			
		}
		
		Collections.sort(al, new Comparator<City>() {

			@Override
			public int compare(City o1, City o2) {
				if(o1.days<o2.days)return -1;
				else if (o1.days>o2.days)return 1; 
				return o1.name.compareTo(o2.name);
			}
		});
		
		System.out.println("Case Number "+(idx++));
		
		for (int i = 0; i < al.size(); i++) 
			System.out.println("   "+al.get(i).name+"   "+al.get(i).days);
		
	}
	
}
