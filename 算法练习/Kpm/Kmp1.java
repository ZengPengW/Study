import java.util.Scanner;

public class Kmp1 {
	private static int[][]num;
	private static int [][]pw1=new int[3][2];
	private static int [][]pw2=new int[1][2];
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		String s=scan.next();
		num=opMID(s.toCharArray());
		for (int i = 0; i <s.length(); i++) {
			f2(s, i);
		}
		if((pw1[0][1]+pw1[1][1]+pw1[2][1])>pw2[0][1]){
			System.out.println(3);
			System.out.println(pw1[0][0]+" "+pw1[0][1]);
			System.out.println(pw1[1][0]+" "+pw1[1][1]);
			System.out.println(pw1[2][0]+" "+pw1[2][1]);
		}else {
			System.out.println(1);
			System.out.println(pw2[0][0]+" "+pw2[0][1]);
		}

	}
	public static void f2(String s,int md) {
		int len =s.length();
		char [] c=s.toCharArray();
		
		int index=len-1;
		String pr=""+c[index];
		int mark=KmpSearch(s,pr);
		boolean ismid=mark==-1||mark==index||mark>=num[md][0]?true:false;
		if(ismid) {
			if(num[md][1]>pw2[0][1]){
				pw2[0][0]=num[md][0]+1;
				pw2[0][1]=num[md][1];
			}
		}
		else {
			int lo=num[md][0],hi=num[md][0]+num[md][1]-1;
			int upst1 = 0;
			while (index>hi) {
				int st1=KmpSearch(s,pr);
				int ed1=st1+(len-index)-1;
				if(st1==-1||ed1>=lo) {
					if((pw1[0][1]+pw1[1][1]+pw1[2][1])<(num[md][1]+2*(len-(index+1)))){
						pw1[0][0]=upst1+1;pw1[0][1]=len-(index+1);
						pw1[1][0]=lo+1;pw1[1][1]=num[md][1];
						pw1[2][0]=index+2;pw1[2][1]=len-(index+1);
					}
					return;
				}
				 upst1=st1;
				pr=pr+c[--index];
				
			}
			
		}	
	}
	public static int [][] opMID(char [] c) {
		int len =c.length;
		//int id = 0;
		//int count=0;
		int mid=len/2;
		int lo,hi;
		lo=mid-1;
		hi=mid+1;
		
		int[][] numm=new int[len][2];
		
		while(mid>=0) {
			while(true) {
				
				if(lo>=0&&hi<len&&c[lo]==c[hi]) {
					lo--;
					hi++;
				}else {
					lo++;
					hi--;
					break;
				}
			}
			numm[mid][1]=(hi-lo)+1;
			numm[mid][0]=lo;
//			if(count<=(hi-lo)+1) {
//				count=(hi-lo)+1;
//				//id=lo;
//			}
			mid--;
			lo=mid-1;
			hi=mid+1;
		}
	
		mid=len/2+1;
		
		lo=mid-1;
		hi=mid+1;
		while(mid<len) {
			while(true) {
				
				if(lo>=0&&hi<len&&c[lo]==c[hi]) {
					lo--;
					hi++;
				}else {
					lo++;
					hi--;
					break;
				}
			}
			numm[mid][1]=(hi-lo)+1;
			numm[mid][0]=lo;
//			if(count<(hi-lo)+1) {
//				count=(hi-lo)+1;
//				//id=lo;
//			}
			mid++;
			lo=mid-1;
			hi=mid+1;
		}
		
	
		return numm;
	}
	
	public static int[] getnext(String s) {
		int [] next=new int[s.length()];
		char[]p=s.toCharArray();
		
		int k=-1;
		int j=0;
		next[0]=-1;
		while (j<next.length-1) {
			if(k==-1||p[j]==p[k]){
				++j;
				++k;
				if(p[j]==p[k]) {
					next[j]=next[k];
				}
				else {
					next[j]=k;
				}
			}
				
			else k=next[k];	
			
		}
		
		return next;
		
	}
	
	public static int KmpSearch(String str,String pr) {
		int[]next=getnext(pr);
		char [] s=str.toCharArray();
		char [] p=pr.toCharArray();
		int i=0,j=0;
		int slen=s.length;
		int plen=p.length;
		
		while (i<slen&&j<plen) {
			
			if(j==-1||s[i]==p[j]){
				i++;
				j++;
			}else {
				j=next[j];
			}
		}
		if(j==plen)return i-j;
		return -1;
		
	}
}
