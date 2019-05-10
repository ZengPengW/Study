import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/*
 * 待解决 目前只能算山不重合的情况
 */
public class Sky {
	private static class Mountain{
		double x,h,b;
		public Mountain(double x, double h, double b) {
			this.x = x;
			this.h = h;
			this.b = b;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n= sc.nextInt();
		List<Mountain> mons=new ArrayList<Mountain>();
		int x,h,b;
		while(n-->0) {
			x=sc.nextInt();
			h=sc.nextInt();
			b=sc.nextInt();
			mons.add(new Mountain(x, h, b));
		}
		System.out.println(Math.round(getSkyLimit(mons)));
	}
	
	public static double getSkyLimit(List<Mountain> mons) {
		init(mons);
		double [][]point1;
		double [] poi;
		double sum=0,edge1,edge2 = 0;
		point1=getPoint(mons.get(0),mons.get(0));
		edge1=D(point1[0][0],point1[0][1],point1[0][2], point1[0][3]);
		sum+=edge1;
		for (int i = 1; i < mons.size(); i++) {
			point1=getPoint(mons.get(i-1),mons.get(i));
			edge1=D(point1[0][0],point1[0][1],point1[0][2], point1[0][3]);
			poi=intersection(point1[0][2], point1[0][3], point1[0][4], point1[0][5], point1[1][0], point1[1][1], point1[1][2], point1[1][3]);
			if(poi[0]>=point1[0][2]&&poi[0]<=point1[0][4]&&poi[1]<=point1[0][3]&&poi[1]>=point1[0][5]) {
				sum+=D(point1[0][2], point1[0][3], poi[0],poi[1]);
				sum+=D(poi[0],poi[1],point1[1][2], point1[1][3]);
			}else {
				sum+=edge1;
				edge2=D(point1[1][0],point1[1][1],point1[1][2], point1[1][3]);
				sum+=edge2;
				
			}
			
		}
		
			double h,x,b;
			h=mons.get(mons.size()-1).h;
			x=mons.get(mons.size()-1).x;
			b=mons.get(mons.size()-1).b/2;
			sum+=(Math.sqrt(h*h+b*b));
		
		return sum;
	}
	
	/*
	 *	 4个点(a1,b1) (a2,b2)组成一条线
      	(c1,d1) (c2,d2)组成一条线
		x=((a2-a1)*(c2-c1)*(d2-b2)+(b2-b1)*(c2-c1)*a2-(d2-d1)*(a2-a1)*c2)/((b2-b1)*(c2-c1)-(d2-d1)*(a2-a1)); 
		y=(b2-b1)/(a2-a1)*(x-a2)+b2;
	 */
	private static double[] intersection(double a1,double b1,double a2,double b2,double c1,double d1,double c2,double d2) {
		double [] poi=new double[2];
		poi[0]=((a2-a1)*(c2-c1)*(d2-b2)+(b2-b1)*(c2-c1)*a2-(d2-d1)*(a2-a1)*c2)/((b2-b1)*(c2-c1)-(d2-d1)*(a2-a1)); 
		poi[1]=(b2-b1)/(a2-a1)*(poi[0]-a2)+b2;
		return poi;
	}
	
	private static double D(double x1,double y1,double x2,double y2) {
		//System.out.println(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
	private static double[][] getPoint(Mountain mountain, Mountain mountain2) {
		double [][]point1=new double[2][6];
		point1[0][0]=mountain.x-(mountain.b/2);
		point1[0][1]=0;
		
		point1[0][2]=mountain.x;
		point1[0][3]=mountain.h;
		
		point1[0][4]=point1[0][0]+mountain.b;
		point1[0][5]=0;
		
		//------------------------------
		point1[1][0]=mountain2.x-(mountain2.b/2);
		point1[1][1]=0;
		
		point1[1][2]=mountain2.x;
		point1[1][3]=mountain2.h;
		
		point1[1][4]=point1[1][0]+mountain2.b;
		point1[1][5]=0;
		
		return point1;
	}
	private static int[]mark=new int [102];
	private static void init(List<Mountain> mons) {
		
		Collections.sort(mons, new Comparator<Mountain>() {

			@Override
			public int compare(Mountain o1, Mountain o2) {
				if(o1.x>o2.x)return 1;
				else if(o1.x<o2.x)return -1;
				return 0;
			}
		});
//		Mountain m1,m2;
//		double [][]point;
//		double x1,y1,x2,y2,x3,y3;
//		double x4,y4,x5,y5,x6,y6;
//		for (int i = 0; i < mons.size(); i++) {
//			m1=mons.get(i);
//			for (int j = 0; j < mons.size(); j++) {
//				if(i==j)continue;
//				m2=mons.get(j);
//				point=getPoint(m1,m2);
//				x1=point[0][0];y1=point[0][1];
//				x2=point[0][2];y2=point[0][3];
//				x3=point[0][4];y3=point[0][5];
//				
//				x4=point[1][0];y4=point[1][1];
//				x5=point[1][2];y5=point[1][3];
//				x6=point[1][4];y6=point[1][5];
//				
//				if(x1>=x4&&(x2>=x4&&y2<=y5)&&(x3<=x6)){
//					mark[i]=1;
//					break;
//				}
//				
//			}
//		}
		
		
	}

}
