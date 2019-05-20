
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Offspring {

	private static class Node{
		List<Integer> next=new ArrayList<Integer>(20);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));	
		
		//Scanner sc=new Scanner(System.in);
		int n,p,f,g;
		Node[] node;
		String[] read=null;
		while (true) {
			read=sc.readLine().split(" ");
			n=Integer.valueOf(read[0]);
			p=Integer.valueOf(read[1]);
			if(n==0&&p==0)break;
			node=new Node[n+1];
			
			for (int i = 1; i <n; i++) {
				read=sc.readLine().split(" ");
				f=Integer.valueOf(read[0]);
				g=Integer.valueOf(read[1]);
				if(node[f]==null)node[f]=new Node();
				node[f].next.add(g);
			}
			

			for (int i = 1; i <=n; i++) {
//				if(node[i]==null) {
//					if(i<n)System.out.print("0 ");
//					else System.out.print(0);					
//					continue;
//				}
//				count=0;
//				dfs(node,i,i);				
//				if(i<n)System.out.print(count+" ");
//				else System.out.print(count);
				if(i<n)System.out.print(getChildCount(node, i)+" ");
				else System.out.print(getChildCount(node, i));
			}	
			
		}
		

	}
//	private static int count;
	
//	private static void dfs(Node[] node, int current,int index) {
//		
//		if(node[index]==null)return;
//		List<Integer> list=node[index].next;
//		for (Integer integer : list) {
//			if(current>integer)count++;
//			dfs(node, current,integer);
//		}
//		
//	}
	
	private static int getChildCount(Node[] node, int current) {
		if(node[current]==null)return 0;
		int count=0;
		Queue<Integer> que=new LinkedList<Integer>();
		List<Integer> list=node[current].next;
		for (Integer i : list) {
			que.offer(i);
		}
		int index;
		while (!que.isEmpty()) {
			index=que.poll();
			if(index<current)count++;
			if(node[index]==null)continue;
			list=node[index].next;
			for (Integer i : list) {
				que.offer(i);
			}
			
		}
		return count;
		
	}
}
