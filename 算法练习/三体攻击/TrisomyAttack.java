
import java.util.Scanner;

public class TrisomyAttack
{
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int A=in.nextInt();
		int B=in.nextInt();
		int C=in.nextInt();
		int m=in.nextInt();
		
		//ÉúÃüÖµ
		int [][][]life=new int[A+1][B+1][C+1];
		for (int i =1; i <=A; i++) 
		for (int j =1; j <=B; j++) 
		for (int k =1; k <=C; k++) {
			life[i][j][k]=in.nextInt();
		}	
		
		//¹¥»÷
		int lat,rat,lbt,rbt,lct,rct,ht;
		for (int i = 1; i <=m; i++) {
			lat=in.nextInt();
			rat=in.nextInt();
			lbt=in.nextInt();
			rbt=in.nextInt();
			lct=in.nextInt();
			rct=in.nextInt();
			ht=in.nextInt();
			
			if (attack(life,lat, rat, lbt, rbt, lct, rct, ht)) {
				System.out.println(i);
				System.exit(0);
			}
		}
		
	}
	public static boolean attack(int[][][]life,int lat,int rat,int lbt,int rbt,int lct,int rct,int ht) {
		
		for (int i =lat; i <=rat; i++) 
		for (int j =lbt; j <=rbt; j++) 
		for (int k =lct; k <=rct; k++) {
			life[i][j][k]-=ht;
			if(life[i][j][k]<0)return true;
		}	
		return false;
	}
}
