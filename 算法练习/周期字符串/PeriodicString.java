import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PeriodicString {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		
		f2(s);
	}
	
	//只能计算周期串中无重复字符时的情况 60%分 2N
	public static void f(String s) {
		int len =s.length();
		int []ch=new int[Character.MAX_VALUE];
		List<Character> al=new ArrayList<Character>();
		for (int i = 0; i < len; i++) {
			ch[s.charAt(i)]+=1;
			if(!al.contains(s.charAt(i)))al.add(s.charAt(i));
		}
		
		int temp=ch[al.get(0)];
		boolean flag=true;
		for (int j = 1; j <al.size(); j++) {
			if(temp!=ch[al.get(j)]) {
				flag=false;
				break;
			}
		}
			
		if(flag) {
			System.out.println(al.size());
		}else {
			System.out.println(s.length());
		}
		
		
	}
//暴力
	public static void f2(String s) {
		int len =s.length();
		int min=s.length();
		String s1,s2;
		boolean flag=true;
		for (int i = 1; i <=len; i++) {
		
			s1=s.substring(0, i);
			for (int j = i; j <=len-i; j+=i) {
				flag=true;
				s2=s.substring(j,j+i);
				if(!s1.equals(s2)) {
					flag=false;
					break;
				}
			}
			if(flag&&len%i==0) {
				min=min<i?min:i;
				break;
			}
		}
		System.out.println(min);
		
	}
}
