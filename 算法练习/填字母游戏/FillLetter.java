import java.util.Scanner;


public class FillLetter {

	private static String[] game;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		game=new String[n];
		for (int i = 0; i <n; i++) {
			game[i]=scan.next();
		}
		f();
		scan.close();
	}
	
	public static void f() {
		for (int i = 0; i < game.length; i++) {
			char[] ch=game[i].toCharArray();
			if (one(ch, 0, ch.length)==0) {
			System.out.println(method(ch, 0, ch.length));	
			}else System.out.println(1);
				
		}
	}
	
	public static int one(char[] ch,int index ,int len) {
		int flag=0;
		for (int i = 0; i < len; i++) {
		   if (ch[i]=='*') {
			if ((i+1<len&&i>0&&ch[i+1]=='L'&&ch[i-1]=='L')
				||(i>1&&ch[i-2]=='L'&&ch[i-1]=='O')
				||(i+2<len&&ch[i+2]=='L'&&ch[i+1]=='O')	
					) {
				flag=1;
				return flag;
			}	   
		}		
	}	
		return flag;
}
	public static int method(char[] ch,int index ,int len) {
		int flag=0;
		while (len>index) {
			
			while (true) {
				if (index<len&&ch[index]!='*') 
					index++;	
				else break;
				}
			if (index>=len) {
				break;
			}						
			if (len>index&&ch[index]=='*') {
				if((index>1&&ch[index-2]=='L'&&ch[index-1]=='O')){
					ch[index]='L';
					flag=1;
					index++;
				
				}else if (index+2<=len-1&&ch[index+2]=='L'&&ch[index+1]=='O') {
					ch[index]='L';
					flag=1;
					index++;
					
				}else if (index>0&&index<len-1&&ch[index-1]=='L'&&ch[index+1]=='L') {
					ch[index]='O';
					flag=1;
					index++;
				}else if(index+1<len&&ch[index+1]=='O') {
					ch[index]='O';
					flag=0;
					index++;
				}else {
					ch[index]='L';
					flag=0;
					index++;
				}	
			}
		if (flag==1) {
			break;
		}
		
		while (true) {
			if (index<len&&ch[index]!='*') 
				index++;	
			else break;
			}
		if (index>=len) {
			break;
		}
		if (len>index&&ch[index]=='*') {
			if((index>1&&ch[index-2]=='L'&&ch[index-1]=='O')){
				ch[index]='L';
				flag=-1;
				index++;
			
			}else if (index+2<=len-1&&ch[index+2]=='L'&&ch[index+1]=='O') {
				ch[index]='L';
				flag=-1;
				index++;
				
			}else if (index>0&&index<len-1&&ch[index-1]=='L'&&ch[index+1]=='L') {
				ch[index]='O';
				flag=-1;
				index++;
			}else if(index+1<len&&ch[index+1]=='O') {
				ch[index]='O';
				flag=0;
				index++;
			}else {
				ch[index]='L';
				flag=0;
				index++;
			}	
		}
			
	}
		return flag;
	}

}
