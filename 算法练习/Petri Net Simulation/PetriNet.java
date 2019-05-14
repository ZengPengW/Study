import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetriNet {
private static class Node{
	List<Integer> from =new ArrayList<Integer>();
	List<Integer> to =new ArrayList<Integer>();
	int []mark=new int[102];
	public Node(List<Integer> from, List<Integer> to) {	
		this.from = from;
		this.to = to;
	}
	
}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int np,nt,nf;
		int []token;
		List<Integer> from;
		List<Integer> to;
		
		
		while (true) {
			
			np=sc.nextInt();
			if(np==0)break;
			
			token=new int[np+1];
			for (int i = 1; i <=np; i++) {
				token[i]=sc.nextInt();
			}
			
				nt=sc.nextInt();
				Node[]node=new Node[nt+1];
				init(node);
				for (int i = 1; i <=nt; i++) {
					
					while (true) {
						int n=sc.nextInt();
						if(n==0)break;
						int temp=Math.abs(n);
						if(n<0)node[i].mark[temp]++;
						addPoint(node[i],n);
					}
					
				}
			
			
			nf=sc.nextInt();
			petriNet(node,token,nf);
			
		}

	}
	private static int ans=1;
	public static void petriNet(Node[] node,int []token,int nf) {
		int np=node.length;
		List<Integer> to;
		List<Integer> from;
		Node no=null;
		boolean flag;
		int count=0;
		while (nf>count) {
			flag=true;
			for (int i = 1; i < np; i++) {
				no=node[i];
				to=no.to;
				from=no.from;
				boolean isgo=checkfire(no.mark,from,token);
				if(isgo) {
					++count;
					
					flag=false;
					for (int j = 0; j <from.size(); j++) {
						int n=from.get(j);						
						token[n]-=1;
						
					}
					for (int j = 0; j <to.size(); j++) {
						int n=to.get(j);					
						token[n]+=1;
						
					}
					if(nf==count)break;
				}
				
			}
			if(flag)break;
			
			
		}
		String status=null;
		if(count==nf)status=": still live after ";
		else status=": dead after ";
		System.out.print("Case "+(ans++)+status+count+" transitions\r\n" + 
				"Places with tokens: ");
		for (int i=1; i<token.length;i++) {
			if(token[i]>0) {
				System.out.print(i+" ("+token[i]+") ");
			}
		}
		System.out.println();
		System.out.println();
		
	}
	
	private static boolean checkfire(int[] mark, List<Integer> from,int []token) {
		for (int j = 0; j <from.size(); j++) {
			int n=from.get(j);
			int use=mark[n];
			if(token[n]<use)
				return false;
			
			
		}
		return true;
	}
	private static void addPoint(Node node, int n) {
		if(n>0) 
			node.to.add(n);
		else 
			node.from.add(n*-1);
		
	}
	private static void init(Node[] node) {
		for (int i = 0; i < node.length; i++) {
			node[i]=new Node(new ArrayList<Integer>(), new ArrayList<Integer>());
		}
		
	}

}
