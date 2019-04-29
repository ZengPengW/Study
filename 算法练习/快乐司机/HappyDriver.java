import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HappyDriver {
	private static  class Node{
		double gi,pi;
		double wi;
		public Node(double gi,double pi,double wi) {
			this.gi=gi;
			this.pi=pi;
			this.wi=wi;
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n =sc.nextInt();
		int w =sc.nextInt();
		double gi;
		double pi;
		ArrayList<Node> al=new ArrayList<Node>();
		for (int i = 1; i <=n; i++) {
			gi=sc.nextInt();
			pi=sc.nextInt();
			al.add(new Node(gi, pi, pi/gi));
			
		}
		f(al,w);
	}

	public static void f(ArrayList<Node> al, int w) {
		Collections.sort(al, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if(o1.wi>o2.wi)return -1;
				else if (o1.wi<o2.wi)return 1; 
				return 0;
			}
		});
		
		double gi,pi,wi;
		double value=0;
		for (Node node : al) {
			if(w<=0)break;
			gi=node.gi;
			pi=node.pi;
			wi=node.wi;
			
			if(w-gi>0) {
				value+=pi;
				w-=gi;
			}else{
				value+=(w*wi);
				break;
			}
		
		}
		System.out.println(String.format("%.1f", value));
		
		
	}

}
