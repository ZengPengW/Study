

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FrogJump {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		String pre=sc.next();
		
		System.out.println(f(str, pre));
	}
	
	public static int f(String str,String pre) {
	Queue<String> que=new LinkedList<String>();
	que.offer(str);
	HashMap<String, Integer> hm=new HashMap<String, Integer>();
	hm.put(str, 0);
	while (!que.isEmpty()) {
		String s=que.poll();
		
		int index=s.indexOf("*");
		
		  if (index+1<s.length()) {
			  String n=swap(index, index+1, s);
			  
			  if (!hm.containsKey(n)) {
				hm.put(n, hm.get(s)+1);
				que.offer(n);
				if (n.equals(pre)) {
					return hm.get(n);
					
				}				
			} 
			  
			
		}	
		  
		  if (index+2<s.length()) {
			  String n=swap(index, index+2, s);
			  
			  if (!hm.containsKey(n)) {
				hm.put(n, hm.get(s)+1);
				que.offer(n);
				if (n.equals(pre)) {
					return hm.get(n);
					
				}				
			} 
			 
			
		}	
		  
		  
		  if (index+3<s.length()) {
			  String n=swap(index, index+3, s);
			  
			  if (!hm.containsKey(n)) {
				hm.put(n, hm.get(s)+1);
				que.offer(n);
				if (n.equals(pre)) {
					return hm.get(n);
					
				}				
			} 
			  
			
		}	
		  
		  
		  if (index-1>=0) {
			  String n=swap(index, index-1, s);
			  
			  if (!hm.containsKey(n)) {
				hm.put(n, hm.get(s)+1);
				que.offer(n);
				if (n.equals(pre)) {
					return hm.get(n);
					
				}				
			} 
			 
			
		}
		  
		  if (index-2>=0) {
			  String n=swap(index, index-2, s);
			  
			  if (!hm.containsKey(n)) {
				hm.put(n, hm.get(s)+1);
				que.offer(n);
				if (n.equals(pre)) {
					return hm.get(n);
					
				}				
			} 
			
		}	
		  
		  if (index-3>=0) {
			  String n=swap(index, index-3, s);
			  
			  if (!hm.containsKey(n)) {
				hm.put(n, hm.get(s)+1);
				que.offer(n);
				if (n.equals(pre)) {
					return hm.get(n);
					
				}				
			} 
			
		}	
		
			
		}
	return 0;
		
	}
	public static String swap (int a,int b,String s) {
		String c;
		char[] ch=s.toCharArray();
		char temp=ch[a];
		ch[a]=ch[b];
		ch[b]=temp;
		c=String.valueOf(ch);
		
		
		return c;
		
	}
}
