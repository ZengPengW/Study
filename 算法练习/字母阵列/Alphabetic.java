import java.util.ArrayList;
import java.util.Scanner;

public class Alphabetic {
private static int [][]fx={
	{-1,0},//上
	{1,0},//下
	{0,-1},//左
	{0,1},//右边
	{-1,-1},//左上
	{-1,1},//右上
	{1,-1},//左下
	{1,1}//右下
};
private static int[][]mark=new int[100][100];
private static int sum=0;
	public static void main(String[] args) {
		char [][]str=new char[100][100];
		
		Scanner sc=new Scanner(System.in);
		for (int i = 0; i <100; i++) {
			str[i]=sc.next().toCharArray();	
		}
		
		
		for (int i = 0; i <100; i++) {
			ArrayList<Integer> al=new ArrayList<Integer>();
			for (int j = 0; j <100; j++) {
				if(str[i][j]=='L'){
					al.add(j);
				}
			}
			
			for (int j = 0; j < al.size(); j++) {
				int a=al.get(j);
				mark[i][a]=1;
				dfs(str, 1, i, a, "L", 0);
				
			}
			
		}
		System.out.println(sum);
	}
	
	public static void dfs(char [][]str,int index,int idx,int idy,String s,int last) {
		if(index==7){
			if(s.equals("LANQIAO"))sum++;
			return;
		}
		for (int i = 0; i <8; i++) {
			if(((idx+fx[i][0])>=0&&(idx+fx[i][0])<100)&&((idy+fx[i][1])>=0&&(idy+fx[i][1])<100)&&mark[idx+fx[i][0]][idy+fx[i][1]]==0){
				if(index==1){
					dfs(str, index+1, idx+fx[i][0], idy+fx[i][1], s+str[idx+fx[i][0]][idy+fx[i][1]], i);
				}else if(i==last) {
					dfs(str, index+1, idx+fx[i][0], idy+fx[i][1], s+str[idx+fx[i][0]][idy+fx[i][1]], i);
				}
			}
			
		}
		
	}
}
