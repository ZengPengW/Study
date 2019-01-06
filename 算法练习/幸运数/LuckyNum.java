import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class LuckyNum {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int m=sc.nextInt();
		int n=sc.nextInt();
		f(m, n);

	}
	public static void f(int m,int n) {
		LinkedList<Integer> ls=new LinkedList<Integer>();
		ls.add(999);
		
		for (int i = 1; i <=n; i+=2) 
				ls.add(i);
			
		int index=2;
		while (index<ls.size()&&ls.get(index)<=n) {
			int v=ls.get(index);
			for (int i =ls.size()-1; i >=1; i--) 
			if(i%v==0)ls.remove(i);
			index++;
		}
		
		int count=0;
		for (int i =1,len=ls.size(); i <len; i++) {
			if(ls.get(i)>m&&ls.get(i)<n){
				count++;
				//System.out.print(ls.get(i)+" ");
			}
			else if (ls.get(i)>n) break;
			
		}
		
		System.out.println(count);
	
	}

}
