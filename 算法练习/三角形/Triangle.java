
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

 
public class Triangle {
	public static void main(String[] args) throws IOException {
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//		String []read=br.readLine().trim().split(" ");
		Scanner sc=new Scanner(System.in);
		int [][]point=new int[3][2];
		for (int i = 0; i <3; i++) {
			point[i][0]=sc.nextInt();
			point[i][1]=sc.nextInt();
		}
		System.out.println(String.format("%.2f", C(point[0],point[1],point[2])));
		System.out.println(String.format("%.2f", S(point[0],point[1],point[2])));
		double []poi=O(point[0],point[1],point[2]);
		System.out.println(String.format("%.2f %.2f",poi[0],poi[1]));
		 poi=F(point[0],point[1],point[2]);
		System.out.println(String.format("%.2f %.2f",poi[0],poi[1] ));
	}

	//距离公式
	private static double D(int[]p1,int[]p2) {
		return Math.sqrt(Math.pow(p1[0]-p2[0], 2)+Math.pow(p1[1]-p2[1], 2));
	}
	
	//周长
	public static double C(int[]p1,int []p2,int []p3) {
		double sum=0;
		sum+=D(p1, p2);
		sum+=D(p1, p3);
		sum+=D(p2, p3);
		return sum;
	}
	
	//面积 用海伦公式:
	/*
	 * 	假设三边长为a,b,c
		p=(a+b+c)/2 
		则面积的平方s^2=p*(p-a)*(p-b)*(p-c) 
	 */
	public static double S(int[]p1,int []p2,int []p3) {
		double p,s=0;
		
		double a=D(p1, p2);
		double b=D(p1, p3);
		double c=D(p2, p3);
		p=(a+b+c)/2;
		s=p*(p-a)*(p-b)*(p-c);
		return Math.sqrt(s);
	}
	
	//重心 横坐标：(X1+X2+X3)/3 纵坐标：(Y1+Y2+Y3)/3
	public static double[] F(int[]p1,int []p2,int []p3) {
		double X,Y;
		X=(p1[0]+p2[0]+p3[0])/3.0;
		Y=(p1[1]+p2[1]+p3[1])/3.0;
		double [] point=new double[2];
		point[0]=X;point[1]=Y;
		return point;
	}
	
	//外心 
	public static double[] O(int[]p1,int []p2,int []p3) {
		double X,Y;

		
		
		Y=-(p1[1]*p1[1]*(p2[0]-p3[0])+p2[1]*p2[1]*(p3[0]-p1[0])+p3[1]*p3[1]*(p1[0]-p2[0]))+(p1[0]-p2[0])*(p2[0]-p3[0])*(p3[0]-p1[0]);
		Y=Y/(2*(p1[0]*(p2[1]-p3[1])+p2[0]*(p3[1]-p1[1])+p3[0]*(p1[1]-p2[1])));
		
		X=(p1[0]*p1[0]*(p2[1]-p3[1])+p2[0]*p2[0]*(p3[1]-p1[1])+p3[0]*p3[0]*(p1[1]-p2[1]))-(p1[1]-p2[1])*(p2[1]-p3[1])*(p3[1]-p1[1]);
		X=X/(2*(p1[0]*(p2[1]-p3[1])+p2[0]*(p3[1]-p1[1])+p3[0]*(p1[1]-p2[1])));

		
		
		double [] point=new double[2];
		point[0]=X;point[1]=Y;
		return point;
	}
}
