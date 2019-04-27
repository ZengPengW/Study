import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Link {
	static class Node{
		int p;
		Node next=null;
		Node Previous=null;
		public Node() {
			
		}
		public Node(int p) {
			this.p=p;
		}
	}
	public static  Node root=null;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String []op=null;
		op=br.readLine().split(" ");
		int n =Integer.parseInt(op[0]);
		int m= Integer.parseInt(op[1]);
		
		int v;
		
		
		op=br.readLine().split(" ");
		for (int i = 0; i <n; i++) {
			v=Integer.parseInt(op[i]);
			append(v);
		}
		
	
		int p,q;
		while(m-->0){
			op=br.readLine().split(" ");
			
			if(op[0].charAt(0)=='D'){
				p=Integer.parseInt(op[1]);
				delete(p);
			}else{
				p=Integer.parseInt(op[1]);
				q=Integer.parseInt(op[2]);
				add(p, q);
			}
		}
		
		System.out.println(size);
		printNode();
	}
	
	public static  void printNode() {
		Node x=root;
		while(x!=null){
			System.out.print(x.p+" ");
			x=x.next;
			
		}
		
		
		
	}

	private static  int size=0;
	public static void append(int p){
		Node node=new Node(p);
		if(root==null){
			root=new Node(p);			
			size++;
			return;
		}else {
			Node current=root;
			Node pre=null;
			while(current!=null){
				pre=current;
				current=current.next;
			}
			
			pre.next=node;
			node.Previous=pre;
			size++;
		}
	}
	
	public static  void add(int p,int q){
		
		Node x=root;
		while(x!=null){
			if(x.p==p)break;
			x=x.next;
			
		}
		
		if(x.p==p&&x.Previous!=null){
			Node node=new Node(q);
			node.next=x;
			Node pre=x.Previous;
			pre.next=node;
			node.Previous=pre;
			x.Previous=node;
			size++;
			return;
		}
		if(x.p==p&&x.Previous==null){
			Node node=new Node(q);
			node.next=x;
			
			x.Previous=node;
			root=node;
			size++;
			return;
			
			
		}
		
		
	}
	public static  void delete(int p){
		Node x=root;
		while(x!=null){
			if(x.p==p)break;
			x=x.next;
			
		}
		
		if(x.p==p&&x.Previous!=null){
			Node pre=x.Previous;
			Node next=x.next;
			pre.next=next;
			if(next!=null)next.Previous=pre;
			size--;
			return;
		}
		if(x.p==p&&x.Previous==null){
			Node next=x.next;
			next.Previous=null;
			root=next;
			size--;
			return;
		
		}
		
		
	}
	
}
