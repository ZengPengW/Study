
public class Bisai{
	private static long ks;
	public static void main(String[] args) {
		 ks=System.currentTimeMillis();
		int [] num={1,2,3,4,5,6,7,8,9};
		dfs(num, 0);
		
	}
	public static void dfs(int []num,int id) {
		if(id>=8){
			if (check(num)) {
				for (int i = 0; i < num.length; i++) {
					if(num[i]==1){
						System.out.println(i<3?"张家":i<6?"王家":"李家");
						System.out.println("用时： "+(System.currentTimeMillis()-ks)+"ms");
						System.exit(0);
//						for (int k : num) {
//							System.out.print(k);
//						}
//						System.out.println();
//						return;
					}
				}
			}
			return;
		}
		for (int i =id,len=num.length; i <len; i++) {
			int temp=num[i];
			num[i]=num[id];
			num[id]=temp;
			dfs(num, id+1);
			 temp=num[i];
			 num[i]=num[id];
			 num[id]=temp;
		}
	}
	
	public static boolean check(int[]num) {
		int Zsum=num[0]+num[1]+num[2];
		int Wsum=num[3]+num[4]+num[5];
		int Lsum=num[6]+num[7]+num[8];
		if(Zsum!=Wsum||Zsum!=Lsum)return false;
		
		if((Math.abs(num[0]-num[1])==1||Math.abs(num[0]-num[2])==1||Math.abs(num[1]-num[2])==1))
			return false;
		if((Math.abs(num[3]-num[4])==1||Math.abs(num[3]-num[5])==1||Math.abs(num[4]-num[5])==1))
			return false;
		if((Math.abs(num[6]-num[7])==1||Math.abs(num[6]-num[8])==1||Math.abs(num[7]-num[8])==1))
			return false;
		
		if((num[6]==9||num[7]==9||num[8]==9)&&(num[3]==8||num[4]==8||num[5]==8))return true;
		
		return false;
	}
}
