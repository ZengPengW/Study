import java.util.Scanner;


public class AntColds {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		int []num=new int[n];
		int Pathogen=sc.nextInt();
		num[0]=Pathogen;
		for (int i = 1; i < n; i++) 
			num[i]=sc.nextInt();
		
		quickSort(num, 0, n-1);
		/*
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i]+" ");	
		}
		System.out.println();
		*/
		f(num, Pathogen);

	}
	
	public static void f(int []num,int Pathogen) {
		
		String dir;
		String s=Integer.toString(Pathogen);
		if(s.indexOf("-")==0)dir="L";
		else dir="R";
		
		int id = 0;
		for (int i = 0,len=num.length; i < len; i++) {
			if(num[i]==Pathogen){
				id=i;
				break;
			}		
		}
		
		int count=0;
		int ans=1;
		if(dir.equals("R")){
			
			for (int i = 0; i < id; i++) {
			   if (Integer.toString(num[i]).indexOf("-")==-1) {
					count++;
				}	
			}
			for (int i =id+1,len=num.length; i < len; i++) {
				if(Integer.toString(num[i]).indexOf("-")==0){
					ans++;
				}
			}
			if(ans>1)ans+=count;
			System.out.println(ans);
		}else {
			for (int i = id+1,len=num.length; i < len; i++) {
				if(Integer.toString(num[i]).indexOf("-")==0){
					count++;
				}	
			}
			
			for (int i =id-1; i >=0; i--) {
				if(Integer.toString(num[i]).indexOf("-")==-1){
					ans++;
				}
			}
			if(ans>1)ans+=count;
			System.out.println(ans);
			
		}
	}
	public static void quickSort(int []num,int left,int right) {
		if(left>=right)return;
		
		int index=cut(num, left, right);
		quickSort(num, 0, index-1);
		quickSort(num, index+1, right);
	}
	private static int cut(int []num,int lo,int hi) {
		int te=num[lo];
		int temp=Math.abs(te);
		while (lo<hi) {
			while (lo<hi&& Math.abs(num[hi])>=temp)  hi--;
			num[lo]=num[hi];
			
			while (lo<hi&& Math.abs(num[lo])<=temp)  lo++;
			num[hi]=num[lo];
		}
		num[hi]=te;
		return hi;
	}
}
