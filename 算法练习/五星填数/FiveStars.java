public class FiveStars
{
	private static int [] num={1,2,3,4,5,6,8,9,10,12};
	private static int count=0;
	public static void main(String[] args)
	{		
		dfs(0);
		System.out.println(count/10);
	}
	public static void dfs(int index) {
		if (index>=10) {
			if (check(num)) {
				count++;
			}
		}
		
		for (int i = index; i < num.length; i++) {
			
			int temp = num[index];
			num[index]=num[i];
			num[i]=temp;
			dfs(index+1);
		    temp = num[index];
			num[index]=num[i];
			num[i]=temp;
		}
	}
	public static boolean check(int[] num) {
		
		int a=num[0]+num[2]+num[5]+num[8];
		int b=num[0]+num[3]+num[6]+num[9];
		int d=num[1]+num[2]+num[3]+num[4];	
		int e=num[1]+num[5]+num[7]+num[9];
		int f=num[4]+num[6]+num[7]+num[8];
		return a==b&&b==d&&d==e&&e==f;
	}
}