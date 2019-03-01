public class Zixulie
{
	public static int f(String x, String y)
	{
		if(x.length()==0) return 0;
		if(y.length()==0) return 0;
		
		String x1 = x.substring(1);
		String y1 = y.substring(1);	
		
		if(x.charAt(0)==y.charAt(0)) return f(x1,y1)+1;
		
		return Math.max(f(x, y1), f(x1, y));
	}
	
	public static void main(String[] args)
	{
		System.out.println(f("ac","abcd")); //2
		System.out.println(f("acebbcde1133","xya33bc11de")); //5
	}
}