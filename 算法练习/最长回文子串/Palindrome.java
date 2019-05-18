
import java.util.Scanner;
public  class Palindrome {
	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);
	     String s=null;
	     char[]ch;
	     while(sc.hasNextLine()){
	    	 s=sc.nextLine();
	    	 if(sc.hasNextLine())sc.nextLine();
	    	
	    	 ch=init(s);
	    	 System.out.println(op(ch));;
	    	 
	    	 
	    	 
	     }
	}

	private static int op(char[] ch) {
		int mx=0,id=0;
		int len=ch.length-2;
		int longest=0;
		int []p=new int[len];
		for (int i =1; i <len; i++) {
			if(mx>=i){
				p[i]=Math.min(p[2*id-i], mx-i);
			}else p[i]=1;
			while((i+p[i]<=len)&&ch[i+p[i]]==ch[i-p[i]]){
				p[i]++;
			}
			if(p[i]+i>mx){
				mx=p[i]+i;
				id=i;
			}
			longest=Math.max(longest, p[id]);
		}
		
		
		return longest-1;
	}

	private static char[] init(String s) {
		char [] ch=new char[s.length()*2+3];
		ch[0]='@';
		int len=s.length();
		for (int i = 1; i <2*len; i+=2) {
			ch[i]='#';
			ch[i+1]=s.charAt(i/2);
		}
		ch[2*len+1]='#';
		ch[2*len+2]='@';
		
		
		
		return ch;
	}

	
}
