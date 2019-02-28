
import java.text.ParseException;

public class NineNumbers {

	public static void main(String[] args) throws ParseException {
		int [] num={1,2,3,4,5,6,7,8,9};
		dfs(num, 0);
	}
	public static void dfs(int[]num,int index) {
		if(index==8) {
			int a=((num[0]*10+num[1])*10+num[2]);
			int b=((num[3]*10+num[4])*10+num[5]);
			int c=((num[6]*10+num[7])*10+num[8]);
			if(b==2*a&&c==3*a) {
				System.out.println(a);
			}
		}
		for (int i = index; i <9; i++) {
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
