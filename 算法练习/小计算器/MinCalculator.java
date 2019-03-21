
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MinCalculator
{
	private static String[][]operate ;
	private static long numb=0;
	private static int radix=10;
	public static void main(String[] args) throws IOException
	{
		//Scanner scan=new Scanner(System.in);
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine().trim());
		//scan.nextLine();
		operate=new String[n][];
		for (int i = 0; i <n; i++) {
			String []str=in.readLine().split(" ");
			operate[i]=new String[str.length];
			for (int j = 0; j < str.length; j++) {
				operate[i][j]=str[j];
			}
		}
		
		Calculator();
		
	}
	
	public static void Calculator() {
		
		int index = 0,count=0;
		for (int i = 0; i < operate.length; i++) {
			int key=0;
			if(operate[i][0].equals("ADD")
					||operate[i][0].equals("SUB")
					||operate[i][0].equals("MUL")
					||operate[i][0].equals("DIV")
					||operate[i][0].equals("MOD"))
				key=1;
			else if (operate[i][0].equals("CHANGE"))key=2; 
			else if (operate[i][0].equals("EQUAL"))key=3;
			else if (operate[i][0].equals("CLEAR"))key=4;
			else key=5;
			
			switch (key) {
			case 1:index=i;break;
			case 2:Change(operate[i][1]);break;
			case 3:Equal();break;
			case 4:
				Clear();
				count=0;
				break;
			case 5:
				if(count==0){
					numb=NUM(operate[i][1]);
				 	count++;
				}
				else MyMath(NUM(operate[i][1]), operate[index][0]);
				
				break;
			default: break;
			}
			
		}	
	}
	
	public static void Clear() {
		numb=0;
	}
	public static long NUM(String s){
	
		return Long.valueOf(s, radix);
		
//		char [] ch=s.toCharArray();
//		long sum=0;
//		long currad=1;
//		for (int i =  ch.length-1; i >=0; i--) {
//			
//			if (ch[i]>='A'&&ch[i]<='Z') {
//				sum+=(ch[i]-'A'+10)*currad;
//			}else {
//				sum+=(ch[i]-'0')*currad;
//			}
//			
//			currad*=radix;
//		}
//		
//		return sum;	
		
	}
	public static void Equal() {
		System.out.println(Long.toString(numb, radix).toUpperCase());
	}
	public static void Change(String rdx) {
		radix=Integer.valueOf(rdx);
	}
	
	public static void MyMath(long num,String sigh) {
	//'ADD','SUB','MUL','DIV','MOD'	
		int sigh1 = 0;
		if (sigh.equals("ADD")) sigh1=1;
		else if (sigh.equals("SUB"))sigh1=2; 
		else if (sigh.equals("MUL"))sigh1=3; 
		else if (sigh.equals("DIV"))sigh1=4; 
		else if (sigh.equals("MOD"))sigh1=5; 
		switch (sigh1) {
		case 1:numb+=num ;break;
		case 2:numb-=num ;break;
		case 3:numb*=num ;break;
		case 4:numb/=num ;break;
		case 5:numb%=num ;break;
		default:
			break;
		}	
	}
}
