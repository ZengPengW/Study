import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class PasswordSuspects {
	private static String[] str=null;
	private static int n;
	private static int m;
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		 n=sc.nextInt();
		 m=sc.nextInt();
		
		int len=0;
		if(m>0) {
			str=new String[m];
			for (int i = 0; i <m; i++) {
				str[i]=sc.next();
				len+=str[i].length();
			}
		}
		long count =f(len, n, m);
		System.out.println(count);
		if(count<=42) {
			
			if(len<n) {
				String [] s=new String[n-len];
				dfs(n-len,s,0);
			}
			else if (len==n) {
				int []mark=new int[m];
				dfs("",mark,0);
			}
			Collections.sort(al, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					if(o1.compareTo(o2)>0)return 1;
					else if (o1.compareTo(o2)<0)return -1;
					return 0;
				}
			});
			for (String i : al) {
				System.out.println(i);
			}
			
		}
		
	}
	public static void dfs(String s,int []mark,int index) {
		if(index==m) {
			al.add(s);
			return;
		}
		for (int i = 0; i <m; i++) {
			if(mark[i]==0) {
				mark[i]=1;
				dfs(s+str[i],mark,index+1);
				mark[i]=0;
			}
		}
	}
	//97
	public static void dfs(int len,String[] s,int index) {
		if(index==len) {
			String mat="";
			int []mark=new int[m];
			op(s,mat, mark, m, 0);
			return;
		}
		for (int i = 0; i <26; i++) {
			s[index]=String.valueOf((char)(i+97));
			dfs(len, s, index+1);
		}
	}
	public static void op(String[] s,String  mat,int[]mark,int strcount,int index) {
		if(mat.length()==n) {
//			for (String i : mat) {
//				System.out.print(i);
//			}
//			System.out.println();
			al.add(mat);
			return;
		}
		for (int i = 0; i <2; i++) {
			if(i==0&&strcount>0) {
				for (int j = 0; j < str.length; j++) {
					if(mark[j]==0) {
						mark[j]=1;
						
						--strcount;
						op(s,mat+str[j], mark, strcount-1, index);
						mark[j]=0;
						++strcount;
					}
				}
				
				
			}else  {
				
				op(s,mat+s[index],mark, strcount, index+1);
			}
			
		}
		
	}
	public static long f(int len,int n,int m) {
		long count=0;
	
		if(len==n){
			count=1;
			for (long i =2; i <=m; i++) {
				count*=i;
			}
			return count;
		}else if (len<n) {
			long slen=n-len;//还需要选多少个字母
			slen+=1;
			count=1;
			for (long i = 0; i <m; i++) {
				count*=(slen+i);//用已知子串往未知字母串里插入的方案数
			}
			long sslen=1;//自选的字母的方案数
			for (long i = 1; i <slen; i++) {
				sslen*=26;
			}
			return sslen*count;
		}else {
			int []mark=new int[m];
			dfsCheck("", mark, 0, -1);
			System.out.println(ans);
			if(ans<=42) {
				Collections.sort(al, new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						if(o1.compareTo(o2)>0)return 1;
						else if (o1.compareTo(o2)<0)return -1;
						return 0;
					}
				});
				for (String i : al) {
					System.out.println(i);
				}
			}
			System.exit(0);
		}
		return count;
		
	}
	private static long ans=0;
	private static ArrayList<String> al=new ArrayList<String>();
	public static void dfsCheck(String s,int []mark,int index,int last) {
		if(index==m) {
			if(s.length()==n) {
				al.add(s);
				ans++;
			}
			return;
		}
		int subid=0;
		for (int i = 0; i <m; i++) {
			if(mark[i]==0) {
				mark[i]=1;
				if(last==-1)dfsCheck(s+str[i],mark, index+1, i);
				else {
					for (int j = 0; j <=1; j++) {
						 if(j==0) {//合并
								
								
								for (int k = 0; k < str[last].length(); k++) {
									int p = 0;
									while(k+p<str[last].length()&&str[last].charAt(k+p)==str[i].charAt(p++));
									if(k+p==str[last].length()&&str[last].charAt(k+p-1)==str[i].charAt(p-1)) {
											subid=p;
											break;
									}
										
									
								}
								dfsCheck(s+str[i].substring(subid),mark, index+1, i);
							}else {//不合并
								if(subid==0)continue;
								dfsCheck(s+str[i],mark, index+1, i);
							}
					
					
				}	
				}
				
				
				mark[i]=0;
			}
		}
	}
}