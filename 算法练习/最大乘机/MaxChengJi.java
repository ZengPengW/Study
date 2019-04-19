import java.util.Scanner;

public class MaxChengJi {
public static void main(String[] args) {
	Scanner scanner=new Scanner(System.in);
	int group=scanner.nextInt();
	
	int count,teke;
	while (group-->0) {
		count=scanner.nextInt();
		teke=scanner.nextInt();
		int []num=new int[count];
		for (int i = 0; i <count; i++) {
			num[i]=scanner.nextInt();
		}
		max=Integer.MIN_VALUE;
		f(num, teke, 0, 1);
		System.out.println(max);
	}
}
private static int max=Integer.MIN_VALUE;
public static void f(int []num,int n,int index,int v) {
	if(n==0){
		if(v>max)max=v;
		return;
	}
	if(index>=num.length){
		return;
	}
	
	
		for (int j = 0; j <2; j++) {
			if(j==0){
				f(num, n, index+1,v);
			}else{
				f(num, n-1, index+1, v*num[index]);
			}
			
		}
		
	
}
}
