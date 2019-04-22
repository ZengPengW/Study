
import java.util.Scanner;

public class CalculationRank {
private static String[][]p;
	public static void main(String[] args)  {	
		Scanner sc=new Scanner(System.in);
		p=new String[5][3];
		String str=null;
		for (int i = 0; i <5; i++) {
			str=sc.next();
			p[i][0]=String.valueOf(str.charAt(0));
			p[i][1]=str.substring(1, str.length()-1);
			p[i][2]=str.substring(str.length()-1);
			
		}
	
		dfs("", 0);
	System.out.println(count);
	}

	private static int count=0;
	private static int [] mark=new int [100];
	public static void dfs(String s,int index) {
		if (index>=5) {
			int one=s.charAt(0)-65;//j
			int two=s.charAt(1)-65;
			int three=s.charAt(2)-65;//j
			int four=s.charAt(3)-65;
			int five=s.charAt(4)-65;//j
			
		if(isTrue(p[one], s)==false&&isTrue(p[two], s)&&isTrue(p[three], s)==false&&isTrue(p[four], s)&&isTrue(p[five], s)==false) {
			System.out.println(s);
			++count;
		}
		
			return;
		}
		for (int i ='A'; i <='E'; i++) {
			if(mark[i]==0) {
				mark[i]=1;
				dfs(s+((char)i),index+1);
				mark[i]=0;
			}
			
			
			
		}
		
		
		
	}
	public static boolean isTrue(String [] say,String rank) {
		if(say[0]==null||say[0].isEmpty())return false;
		int index=rank.indexOf(say[0])+1;//ÅÅÃû
		if(say[1].equals("<")) {
			return index<Integer.parseInt(say[2]);
		}else if (say[1].equals("<=")) {
			return index<=Integer.parseInt(say[2]);
		}else if (say[1].equals("=")) {
			return index==Integer.parseInt(say[2]);
		}else if (say[1].equals(">=")) {
			return index>=Integer.parseInt(say[2]);
		}else if (say[1].equals(">")) {
			return index>Integer.parseInt(say[2]);
		}else if (say[1].equals("!=")) {
			return index!=Integer.parseInt(say[2]);
		}
		
		
		return false;
		
	}

}









