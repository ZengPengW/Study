import java.util.Scanner;


public class NinePalace {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int []arr=new int[10];
		for (int i = 1; i < 10; i++) 
			arr[i]=i;
			
		int []num=new int [10];
		int count=0;
		for (int i = 1; i <10; i++) {
			num[i]=sc.nextInt();
			
			if(num[i]!=0){
				arr[num[i]]=0;
				count++;
			}
		}
		int []select=new int[9-count];
		
		int index=0;
		for (int i = 1; i < 10; i++) {
			if(arr[i]!=0)select[index++]=arr[i];
		}
		
		int []ID=new int[10-count];
		index=1;
		for (int i =1; i < 10; i++) {
			if(num[i]==0)ID[index++]=i;
		}
		count=9-count;
		dfs(num, select, ID, count, 1);
	}
	private static int[]mark=new int[10];
	public static void dfs(int []num,int []select,int[] ID,int count,int id) {
		if(id>=ID.length){
			int a=num[1]+num[2]+num[3];
			int b=num[4]+num[5]+num[6];
			int c=num[7]+num[8]+num[9];
			int d=num[1]+num[4]+num[7];
			int e=num[2]+num[5]+num[8];
			int f=num[3]+num[6]+num[9];
			int g=num[1]+num[5]+num[9];
			int h=num[3]+num[5]+num[7];
			if(a==b&&b==c&&c==d&&d==e&&e==f&&f==g&&g==h){
				System.out.println(num[1]+" "+num[2]+" "+num[3]);
				System.out.println(num[4]+" "+num[5]+" "+num[6]);
				System.out.println(num[7]+" "+num[8]+" "+num[9]);
				System.exit(0);
			}
			return;
		}
		for (int i = 0; i < count; i++) {
			if(mark[select[i]]==0){
				num[ID[id]]=select[i];
				mark[select[i]]=1;
				dfs(num, select, ID, count, id+1);
				mark[select[i]]=0;
			}
			
		}
	}
}
