import java.util.Scanner;
import java.util.Stack;
public class FlightPlanning {
private static final double GPHOPT=2000;
private static final double VCRUSITE =400;
private static final double AOPT=30;
private static final double GPHEXTRA=10;
private static final double CLIMBCOST=50;
private static double min;
private static int ans=1;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		double n=sc.nextInt();
		int k,a,b,c;
		double [][]dp;
		int [][]dir;
		while(n-->0) {
			k=sc.nextInt();
			int tempk=k;
			dp=new double[k+1][42];
			for (int i = 0; i <=k; i++) {
				for (int j = 0; j <42; j++) {
					dp[i][j]=999999999;
				}
			}
			int index=1;		
			dir=new int[k+1][41];
			while(tempk-->0) {
				
				a=sc.nextInt();
				if(a==95201){
					System.out.println();
				}
				b=sc.nextInt();
				c=sc.nextInt();
				double fs=(c-b)/20.0;
				
				double ss=VCRUSITE+(b);//20k 时的时速
				
				
				if(index==1) {
					for (int h = 20; h <=40; h++) {					
					  dp[index][h]=h*CLIMBCOST+(Math.abs(AOPT-h)*GPHEXTRA+GPHOPT)*(a/((h-20.0)*fs+ss));				  
					}
				}else {
					double bsv=0;//改变高度的消耗
					double temp;
					for (int h = 20; h <=40; h++) {
						for (int i = 20; i <=40; i++) {
							bsv=0;
							if(h>i)bsv=(h-i)*CLIMBCOST;	
							temp=dp[index-1][i]+(bsv+(Math.abs(AOPT-h)*GPHEXTRA+GPHOPT)*(a/((h-20)*fs+ss)));
							if(dp[index][h]>temp){
								dp[index][h]=temp;
								dir[index][h]=i;
							}
							
						
						}
						
					}
				}
				index++;			
			}
			
			min=Integer.MAX_VALUE;
			int minIndex=0;
			for (int i = 20; i <=40; i++) {
				if(dp[k][i]<min){
					minIndex=i;
					min=dp[k][i];
				}
			}
			
			Stack<Object> stack=new Stack<Object>();
			stack.push((int)Math.ceil(min));
			stack.push(minIndex);			
			while (dir[k--][minIndex]>0) {			
				stack.push(dir[k+1][minIndex]);
				minIndex=dir[k+1][minIndex];
			}
			
			System.out.print("Flight "+(ans++)+":");
			while(!stack.isEmpty()){
				System.out.print(" "+stack.pop());
			}
			System.out.println();
		}
		
		
	}


}
