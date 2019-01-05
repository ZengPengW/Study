import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class JiuGongChongPai {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		String mat=sc.next();
		bfs(s, mat);
		
	}
	public static void bfs(String s,String mat) {
		Queue<String> que=new LinkedList<String>();
		que.offer(s);
		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		hm.put(s, 0);
		while (!que.isEmpty()) {
			String str=que.poll();
			
			int id=str.indexOf(".");
			//ио
			if(id>2){
				String tempStr=swap(id, id-3,str.toCharArray());
				if(!hm.containsKey(tempStr)){
					hm.put(tempStr, hm.get(str)+1);
					que.offer(tempStr);
				}
				if(tempStr.equals(mat)){
					System.out.println(hm.get(tempStr));
					return;
				}
					
			}
			
			//об
			if(id<6){
				String tempStr=swap(id, id+3,str.toCharArray());
				if(!hm.containsKey(tempStr)){
					hm.put(tempStr, hm.get(str)+1);
					que.offer(tempStr);
				}
				if(tempStr.equals(mat)){
					System.out.println(hm.get(tempStr));
					return;
				}
					
			}
			
			//вС
			if(id!=0&&id!=3&&id!=6){
				String tempStr=swap(id, id-1,str.toCharArray());
				if(!hm.containsKey(tempStr)){
					hm.put(tempStr, hm.get(str)+1);
					que.offer(tempStr);
				}
				if(tempStr.equals(mat)){
					System.out.println(hm.get(tempStr));
					return;
				}
					
			}
			
			//ср
			if(id!=2&&id!=5&&id!=8){
				String tempStr=swap(id, id+1,str.toCharArray());
				if(!hm.containsKey(tempStr)){
					hm.put(tempStr, hm.get(str)+1);
					que.offer(tempStr);
				}
				if(tempStr.equals(mat)){
					System.out.println(hm.get(tempStr));
					return;
				}
					
			}
			
		}
		
	}
	
	private static String swap(int i,int j,char[]arr) {
		char temp =arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
		return new String(arr);
	}
}
