package shopee;

import java.util.Scanner;

public class Main3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String p = sc.next();
		String str = sc.next();

		f(p, str);

	}

	private static void f(String p, String str) {
		int x=p.indexOf("*");
		if (x==0&&p.length()==1) {
			
			int len=str.length();
			for (int i = 0; i <str.length(); i++) {
				System.out.println(i + " "+(len-i));
			}
			return;
		}
		
		boolean flag=false;
		if (x==0) {
			
		try {
			p=p.substring(x+1);
			int id=-1;
			
			while(id+1<str.length()&&(id=str.indexOf(p, id+1))!=-1){
				flag=true;
				int len=id+p.length();
				
				for (int i = 0; i <=id; i++) {
					System.out.println(i + " "+(len-i));
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
			
			
		}else if (x>0&&x==p.length()-1) {
			try {
				p=p.substring(0,x);
				int id=-1;
				while(id+1<str.length()&&(id=str.indexOf(p, id+1))!=-1){
					flag=true;
					int len=str.length()-id;
					System.out.println(id + " "+(len));
					
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			
		}else {
			try {
				String qian=p.substring(0,x);
				String hou=p.substring(x+1);
				int id=-1;
				while(id+1<str.length()&&(id=str.indexOf(qian, id+1))!=-1){
					int id2=id+qian.length()-1;
					while(id2+1<str.length()&&(id2=str.indexOf(hou, id2+1))!=-1){
						flag=true;
						int len=id2+hou.length()-id;
						System.out.println(id + " "+(len));
						
						
					}
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if (!flag) {
			System.out.println("-1 0");
		}
		
		
	}
}
