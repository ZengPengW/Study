import java.util.Scanner;


public class TwoPower{
	
	public static void main(String[] args) {
	Scanner scan =new Scanner(System.in);
	String num=Integer.toString(scan.nextInt(), 2);
	System.out.println(f(num, 0));
	}
	public static String f(String num,int id) {
		if(num.equals("11"))return "2+2(0)";
		if(num.equals("100"))return "2(2)";
		
		String str="";
		while (num.indexOf("1", id)!=-1) {
			id=num.indexOf("1", id);
			String val=Integer.toString((num.length()-id-1),2);
			if(val.equals("0"))str+="+2(0)";
			else if(val.equals("1")) str+="2";
			else str+="2("+f(val, 0)+")+";
			
			id+=1;
		}
	
		str=str.replace("++", "+");
		str=str.replace("+)", ")");
		if(str.substring(str.length()-1, str.length()).equals("+"))str=str.substring(0, str.length()-1);
		return str;
		
	}
	
}
