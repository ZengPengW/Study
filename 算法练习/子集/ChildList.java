import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ChildList {
public static void main(String[] args) {
	int [] num= {1,2,3,4,5};
	ArrayList<Integer> al=null;
	
	for (int i = 0,len=num.length; i < len; i++) {//以每个数为开头
		for (int j = 1,len2=len-i; j <=len2; j++) {//len2 表示最大可以凑的个数
			al=new ArrayList<Integer>();
			al.add(num[i]);
			f(num, i, j,al);
		}
	}
}
/*
 * num 是要算子集 的数组
 * x 是当前值在num 中的索引
 * count 是要凑的个数
 * al 是存子集的容器
 */
public static void f(int []num,int x,int count,ArrayList<Integer> al) {
	if(al.size()==count) {
		System.out.print("{");
		for (Iterator iterator = al.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			System.out.print(integer+",");
		}
		System.out.print("}");
		System.out.println();
		return;
	}
	for (int i = x+1,len=num.length; i < len; i++) {
		
			al.add(num[i]);
			f(num, i, count, al);
			al.remove(al.size()-1);
		
		
	}
}
}