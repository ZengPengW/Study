
import java.util.ArrayList;
import java.util.Scanner;


public class BalloonsBox{
	static class Point{
			double x,y,z;
			public Point(double x,double y,double z){
				this.x=x;
				this.y=y;
				this.z=z;		
			}
		}
	private static final double PI=Math.PI;
	private static ArrayList<Point> cuboid;
	private static ArrayList<Point> ball;
	private static int[] id;
	private static double maxboV=0;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		cuboid=new ArrayList<Point>(2);
		int n=scan.nextInt();
		id=new int[n];
		for (int i = 0; i < id.length; i++)
			id[i]=i;
		
		cuboid.add(new Point(scan.nextDouble(), scan.nextDouble(), scan.nextDouble()));
		cuboid.add(new Point(scan.nextDouble(), scan.nextDouble(), scan.nextDouble()));
		
		ball=new ArrayList<Point>(n);
		for (int i = 0; i <n; i++) 
			ball.add(new Point(scan.nextDouble(), scan.nextDouble(), scan.nextDouble()));
		f(ball);
			
	}	
	private static double dis(Point p1,Point p2){
		return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y)+(p1.z-p2.z)*(p1.z-p2.z));	
	}
	
	private static double minR(Point p){
		Point p1=cuboid.get(0);
		Point p2=cuboid.get(1);
		double minx=Math.min(Math.abs(p1.x-p.x),Math.abs(p2.x-p.x));
		double miny=Math.min(Math.abs(p1.y-p.y),Math.abs(p2.y-p.y));
		double minz=Math.min(Math.abs(p1.z-p.z),Math.abs(p2.z-p.z));
		return minx<miny?minx<minz?minx:minz:miny<minz?miny:minz;
		//return Math.min(minx, Math.min(miny, minz));
	}
	private static void dfs(int []id,int index){
		
		if(index>=id.length-1){
			maxbollV(id);
			return;
		}
		for (int i =index,len=id.length; i <len; i++) {
			int temp =id[index];
			id[index]=id[i];
			id[i]=temp;
			dfs(id, index+1);
			temp =id[index];
			id[index]=id[i];
			id[i]=temp;
			
		}
		
	}
	private static void maxbollV(int []id){
		double []R=new double[id.length];
		double v=0;
		Point curPoint=null;
		for (int i = 0; i < id.length; i++) {
			curPoint=ball.get(id[i]);
			if(!((curPoint.x>cuboid.get(0).x&&curPoint.x<cuboid.get(1).x||curPoint.x<cuboid.get(0).x&&curPoint.x>cuboid.get(1).x)
					&&(curPoint.y>cuboid.get(0).y&&curPoint.y<cuboid.get(1).y||curPoint.y<cuboid.get(0).y&&curPoint.y>cuboid.get(1).y)
					&&(curPoint.z>cuboid.get(0).z&&curPoint.z<cuboid.get(1).z||curPoint.z<cuboid.get(0).z&&curPoint.z>cuboid.get(1).z)))
					continue;
				
			
			double minr=minR(curPoint);
			int j = 0;
			for (; j <i; j++) {
				if(R[id[j]]<=0)continue;
				double d=dis(ball.get(id[i]), ball.get(id[j]))-R[id[j]];
				if(d<minr){
					minr=d;
				}
				if(minr<=0)continue;
			}
			if(minr>0){
			R[id[i]]=minr;
			v+=(4.0/3.0)*PI*Math.pow(minr, 3);	
			}	
		}
		if(maxboV<v)maxboV=v;	
	}
	public static void f(ArrayList<Point> ball){
		double cubV=Math.abs(cuboid.get(0).x-cuboid.get(1).x)*Math.abs(cuboid.get(0).y-cuboid.get(1).y)*Math.abs(cuboid.get(0).z-cuboid.get(1).z);
		dfs(id,0);
		System.out.println(Math.round(cubV-maxboV));	
	}
		
}
