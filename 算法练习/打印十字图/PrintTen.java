import java.util.Scanner;

public class PrintTen {
private static String[] mid={
	"$$$.$.$$$",
	"$...$...$",
	"$.$$$$$.$",
	"$...$...$",
	"$$$.$.$$$"
};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		print(n);
		
	}
	
	public static void print(int n) {
		int h=9+(n-1)*4;
		int hf=(h-5)/2;
		
		int hd=n*4+1;
		int co=0;
		for (int i = 0; i <hf; i++) {
			if(i%2==0){
				if(co==0){
					System.out.print("..");
					System.out.print(String.format("%"+hd+"s", "").replace(" ", "$"));
					System.out.print("..");
				}else {
					for (int j = 0; j <co-1; j++) 
						System.out.print("$.");
					System.out.print("$$$.");	
					System.out.print(String.format("%"+hd+"s", "").replace(" ", "$"));
					System.out.print(".$$$");	
					for (int j = 0; j <co-1; j++) 
						System.out.print(".$");
				}
				
				
			}else {
				if(co==0){
					System.out.print("..$");
					System.out.print(String.format("%"+(hd-2)+"s", "").replace(" ", "."));
					System.out.print("$..");
				}else {
					for (int j = 0; j <co; j++) 
						System.out.print("$.");
					System.out.print("..$");
					System.out.print(String.format("%"+(hd-2)+"s", "").replace(" ", "."));
					System.out.print("$..");
					for (int j = 0; j <co; j++) 
						System.out.print(".$");
				}
				co++;
				hd-=4;
				}
			System.out.println();
			}
		
		StringBuilder s1=new StringBuilder();
		StringBuilder s2=new StringBuilder();
		for (int i = 0; i <n-1; i++){ 
			s1.append("$.");
			s2.append(".$");
		}
		for (int i = 0; i <5; i++) {
			System.out.printf("%s%s%s",s1,mid[i],s2);
			System.out.println();
		}
		
		
		
		hd+=4;
		co--;
		for (int i =hf-1; i >=0; i--) {
			if(i%2==0){
				if(co==0){
					System.out.print("..");
					System.out.print(String.format("%"+hd+"s", "").replace(" ", "$"));
					System.out.print("..");
				}else {
					for (int j = 0; j <co-1; j++) 
						System.out.print("$.");
					System.out.print("$$$.");	
					System.out.print(String.format("%"+hd+"s", "").replace(" ", "$"));
					System.out.print(".$$$");	
					for (int j = 0; j <co-1; j++) 
						System.out.print(".$");
				}
				co--;
				hd+=4;
				
			}else {
				if(co==0){
					System.out.print("..$");
					System.out.print(String.format("%"+(hd-2)+"s", "").replace(" ", "."));
					System.out.print("$..");
				}else {
					for (int j = 0; j <co; j++) 
						System.out.print("$.");
					System.out.print("..$");
					System.out.print(String.format("%"+(hd-2)+"s", "").replace(" ", "."));
					System.out.print("$..");
					for (int j = 0; j <co; j++) 
						System.out.print(".$");
				}
				
				}
			System.out.println();
			}
		}
		
	}

