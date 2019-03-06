
public class PutBlocks {
	public static void main(String[] args) {
		int[]num={1,2,3,4,5,6,7,8,9,0};
		dfs(num, 0);
		System.out.println(count);
	}
	private static long count=0;
	public static void dfs(int []num,int index) {
		if(index==9) {
			if(num[0]<num[1]&&num[0]<num[2]
			&&num[1]<num[3]&&num[1]<num[4]
			&&num[2]<num[4]&&num[2]<num[5]
			&&num[3]<num[6]&&num[3]<num[7]
			&&num[4]<num[7]&&num[4]<num[8]
			&&num[5]<num[8]&&num[5]<num[9]) {
				count++;
			}
			return;
		}
		for (int i = index; i < num.length; i++) {
			int temp=num[index];
			num[index]=num[i];
			num[i]=temp;
			dfs(num, index+1);
			temp=num[index];
			num[index]=num[i];
			num[i]=temp;
		}
	}
}