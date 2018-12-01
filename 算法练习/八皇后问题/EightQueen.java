

import java.util.Scanner;


public class EightQueen {
	public static int[] map=new int [9];
	public static String[] s=new String[94];
	private static int count=1;
	public static void main(String[] args) {
	Scanner scan =new Scanner(System.in);
	int n=scan.nextInt();
	int []num=new int[n];
	for (int i = 0; i < num.length; i++) 
		 num[i]=scan.nextInt();
	int []col=new int[9];	
	int [][] ob=new int [9][9];
	dfs(1, col, ob);
	for (int i = 0; i < num.length; i++) {
		System.out.println(s[num[i]]);	
	}
	}
	public static void dfs(int index,int[]col,int[][] ob) {
		
		if(index>=9){
			String str="";
			for (int i = 1; i <map.length; i++) {
				str+=map[i];
			}
			System.out.println(str);
			s[count++]=str;
			return;
		}
		
		
		for (int i = 1; i <9; i++) {
			if(col[i]==0&&ob[index][i]==0){
			 map[index]=i;
			 col[i]=1;
			 int l=i,r=i;
			 ob[index][i]+=1;
			 for (int j = index-1; j >=1&&l-1>=1; j--) {
				ob[j][l-1]+=1;
				l-=1;
			}
			 l=i;r=i;
			 for (int j = index-1; j >=1&&r+1<=8; j--) {
					ob[j][r+1]+=1;
					r+=1;
				}
			 l=i;r=i;
			 for (int j = index+1; j <=8&&l-1>=1; j++) {
					ob[j][l-1]+=1;
					l-=1;
				}
			 l=i;r=i;
			 for (int j = index+1; j <=8&&r+1<=8; j++) {
					ob[j][r+1]+=1;
					r+=1;
				}
			 dfs(index+1,col,ob);
			 l=i;r=i;
			 ob[index][i]-=1;
			 for (int j = index-1; j >=1&&l-1>=1; j--) {
					ob[j][l-1]-=1;
					l-=1;
				}
				 l=i;r=i;
				 for (int j = index-1; j >=1&&r+1<=8; j--) {
						ob[j][r+1]-=1;
						r+=1;
					}
				 l=i;r=i;
				 for (int j = index+1; j <=8&&l-1>=1; j++) {
						ob[j][l-1]-=1;
						l-=1;
					}
				 l=i;r=i;
				 for (int j = index+1; j <=8&&r+1<=8; j++) {
						ob[j][r+1]-=1;
						r+=1;
					}
			 
			 
			 col[i]=0;
			 
			}
			 
		}
		
		
	}
	
}

