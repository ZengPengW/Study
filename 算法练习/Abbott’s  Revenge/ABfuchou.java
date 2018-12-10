import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	LinkedList<String> go=new LinkedList<String>();
	public String route="";//路径
	public ArrayList<Node> ro=new ArrayList<Node>();//经过的路径节点
	public  Node(int x,int y,LinkedList<String> go,String route) {
		this.x=x;
		this.y=y;
		this.go=go;
		this.route=route;
	}
	public Node(){}; 
	
}
class Goal{
	int bgx;
	int bgy;
	int edx;
	int edy;
	public String go;
}
public class ABfuchou {
	public static void main(String[] args) {
		long ks=System.currentTimeMillis();
		Scanner sc=new Scanner(System.in);
		
		while (true) {
			String ne=sc.next();
			sc.nextLine();
			if(ne.equals("END"))break;
			
			String s=sc.nextLine();
			StringTokenizer str=new StringTokenizer(s, " ");
			Goal g1=new Goal();
			g1.bgx=Integer.parseInt(str.nextToken());
			g1.bgy=Integer.parseInt(str.nextToken());
			g1.go=str.nextToken();
			g1.edx=Integer.parseInt(str.nextToken());
			g1.edy=Integer.parseInt(str.nextToken());
			HashMap<String, Node> hm=new HashMap<String, Node>();
			while (true) {
				 String st1=sc.nextLine();
				 StringTokenizer strlist=new StringTokenizer(st1," ");
				 st1=strlist.nextToken();
				 if(st1.equals("0"))break;
				 String st2=strlist.nextToken();
				 String key=st1+st2;
				
				Node n=new Node();
				n.x=Integer.parseInt(st1);n.y=Integer.parseInt(st2);
				n.route="("+n.x+","+n.y+")";
				while (strlist.hasMoreTokens()) {
					String str1=strlist.nextToken();
					if(str1.equals("*")) break;
						for (int j =1; j < str1.length(); j++) {
							switch (str1.charAt(0)) {
							case 'N':
								if(str1.charAt(j)=='F')n.go.add("NN");
								else if(str1.charAt(j)=='L')n.go.add("NE");
								else n.go.add("NW");
								break;
								
							case 'S':
								if(str1.charAt(j)=='F')n.go.add("SS");
								else if(str1.charAt(j)=='L')n.go.add("SW");
								else n.go.add("SE");
								break;
								
							case 'E':
								if(str1.charAt(j)=='F')n.go.add("EW");
								else if(str1.charAt(j)=='L')n.go.add("EN");
								else n.go.add("ES");
								break;
								
							default:
								if(str1.charAt(j)=='F')n.go.add("WE");
								else if(str1.charAt(j)=='L')n.go.add("WS");
								else n.go.add("WN");
								break;
							}
//							if(str1.charAt(0)=='N'){
//								if(str1.charAt(j)=='F')n.go.add("NN");
//								else if(str1.charAt(j)=='L')n.go.add("NE");
//								else n.go.add("NW");
//							}else if (str1.charAt(0)=='S') {
//								if(str1.charAt(j)=='F')n.go.add("SS");
//								else if(str1.charAt(j)=='L')n.go.add("SW");
//								else n.go.add("SE");	
//							}else if (str1.charAt(0)=='E') {
//								if(str1.charAt(j)=='F')n.go.add("EW");
//								else if(str1.charAt(j)=='L')n.go.add("EN");
//								else n.go.add("ES");
//							}else  {
//								if(str1.charAt(j)=='F')n.go.add("WE");
//								else if(str1.charAt(j)=='L')n.go.add("WS");
//								else n.go.add("WN");
//							}
							
						}	
					
				}
				hm.put(key, n);	
			}
			
			bfs(ne, hm, g1);
		}
		System.out.println(System.currentTimeMillis()-ks);
	}
	
	
	public static void bfs(String name,HashMap<String, Node>hm,Goal g1) {
	
		Node begin=new Node();//开始位置
		Queue<Node> q=new LinkedList<Node>();
		int stx=g1.bgx;int sty=g1.bgy;
		int edx=g1.edx;int edy=g1.edy;
		begin.x=stx;begin.y=sty;
		begin.go.add(g1.go.equals("N")?"N":g1.go.equals("E")?"W":"E");
		begin.route="("+stx+","+sty+")";
	
		q.offer(begin);
		while (!q.isEmpty()) {
			Node cur=q.poll();
			for (int i = 0; i <cur.go.size(); i++) {
				if(cur.go.get(i).equals("N")){
					int nx=cur.x-1;
					int ny=cur.y;
					if(nx==edx&&ny==edy){
						System.out.print(name);
						int j=0;
						for (j = 0; j < cur.ro.size(); j++) {
							if((j)%10!=0){
								if((j+1)%10==0)System.out.print(cur.ro.get(j).route);
								else System.out.print(cur.ro.get(j).route+" ");
							}
							else {
								System.out.println();
								System.out.print("  "+cur.ro.get(j).route+" ");
							}
						}
						if((j)%10!=0){
							if((j+1)%10==0)System.out.print("("+cur.x+","+cur.y+")");
							else System.out.print("("+cur.x+","+cur.y+") ");
						}
						else {
							System.out.println();
							System.out.print("  "+"("+cur.x+","+cur.y+") ");
						}
						j++;
						
						if((j)%10!=0){	
							System.out.print("("+nx+","+ny+")\n");
						}
						else {
							System.out.println();
							System.out.print("  "+"("+nx+","+ny+")\n");
						}
						
						return;
					}
					Node getnode=hm.get(nx+""+ny);
					if(getnode==null||getnode.go.size()==0)continue;//不存在这个位置  或 此位置无路可走
					LinkedList<String>nex=new LinkedList<String>();
					
					for (int j = getnode.go.size()-1; j >=0; j--) {
						if(getnode.go.get(j).startsWith("N")){
							String gg=getnode.go.remove(j);//移除访问过的方向
							for (int k = 1; k < gg.length(); k++) {
								nex.add(String.valueOf(gg.charAt(k)));
								
							}	
						}	
					}
					
					
					Node next=new Node(nx, ny, nex,getnode.route);
					next.ro.addAll(cur.ro);
					next.ro.add(cur);
					
					q.offer(next);
					
				}
				else if(cur.go.get(i).equals("S")){
					int nx=cur.x+1;
					int ny=cur.y;
					
					if(nx==edx&&ny==edy){
						System.out.print(name);
						int j=0;
						for (j = 0; j < cur.ro.size(); j++) {
							if((j)%10!=0){
								if((j+1)%10==0)System.out.print(cur.ro.get(j).route);
								else System.out.print(cur.ro.get(j).route+" ");
							}
							else {
								System.out.println();
								System.out.print("  "+cur.ro.get(j).route+" ");
							}
						}
						if((j)%10!=0){
							if((j+1)%10==0)System.out.print("("+cur.x+","+cur.y+")");
							else System.out.print("("+cur.x+","+cur.y+") ");
						}
						else {
							System.out.println();
							System.out.print("  "+"("+cur.x+","+cur.y+") ");
						}
						j++;
						
						if((j)%10!=0){	
							System.out.print("("+nx+","+ny+")\n");
						}
						else {
							System.out.println();
							System.out.print("  "+"("+nx+","+ny+")\n");
						}
						
						return;
					}
					Node getnode=hm.get(nx+""+ny);
					if(getnode==null||getnode.go.size()==0)continue;
					LinkedList<String>nex=new LinkedList<String>();
					for (int j =  getnode.go.size()-1; j >=0; j--) {
						if(getnode.go.get(j).startsWith("S")){
							String gg=getnode.go.remove(j);
							for (int k = 1; k < gg.length(); k++) {
								nex.add(String.valueOf(gg.charAt(k)));
							}	
						}	
					}
					
					
					Node next=new Node(nx, ny, nex,getnode.route);
					next.ro.addAll(cur.ro);
					next.ro.add(cur);
					
					q.offer(next);
					
				}
				else if(cur.go.get(i).equals("W")){
					int nx=cur.x;
					int ny=cur.y+1;
					
					if(nx==edx&&ny==edy){
						System.out.print(name);
						int j=0;
						for (j = 0; j < cur.ro.size(); j++) {
							if((j)%10!=0){
								if((j+1)%10==0)System.out.print(cur.ro.get(j).route);
								else System.out.print(cur.ro.get(j).route+" ");
							}
							else {
								System.out.println();
								System.out.print("  "+cur.ro.get(j).route+" ");
							}
						}
						if((j)%10!=0){
							if((j+1)%10==0)System.out.print("("+cur.x+","+cur.y+")");
							else System.out.print("("+cur.x+","+cur.y+") ");
						}
						else {
							System.out.println();
							System.out.print("  "+"("+cur.x+","+cur.y+") ");
						}
						j++;
						
						if((j)%10!=0){	
							System.out.print("("+nx+","+ny+")\n");
						}
						else {
							System.out.println();
							System.out.print("  "+"("+nx+","+ny+")\n");
						}
						
						return;
					}
					
					Node getnode=hm.get(nx+""+ny);
					if(getnode==null||getnode.go.size()==0)continue;
					LinkedList<String>nex=new LinkedList<String>();
					for (int j = getnode.go.size()-1; j >=0; j--) {
						if(getnode.go.get(j).startsWith("E")){
							String gg=getnode.go.remove(j);
							for (int k = 1; k < gg.length(); k++) {
								nex.add(String.valueOf(gg.charAt(k)));
							}	
						}	
					}
					
					
					Node next=new Node(nx, ny, nex,getnode.route);
					next.ro.addAll(cur.ro);
					next.ro.add(cur);
				
					q.offer(next);
				}
				else {
					
					int nx=cur.x;
					int ny=cur.y-1;
					
					if(nx==edx&&ny==edy){
						System.out.print(name);
						int j=0;
						for (j = 0; j < cur.ro.size(); j++) {
							if((j)%10!=0){
								if((j+1)%10==0)System.out.print(cur.ro.get(j).route);
								else System.out.print(cur.ro.get(j).route+" ");
							}
							else {
								System.out.println();
								System.out.print("  "+cur.ro.get(j).route+" ");
							}
						}
						if((j)%10!=0){
							if((j+1)%10==0)System.out.print("("+cur.x+","+cur.y+")");
							else System.out.print("("+cur.x+","+cur.y+") ");
						}
						else {
							System.out.println();
							System.out.print("  "+"("+cur.x+","+cur.y+") ");
						}
						j++;
						
						if((j)%10!=0){	
							System.out.print("("+nx+","+ny+")\n");
						}
						else {
							System.out.println();
							System.out.print("  "+"("+nx+","+ny+")\n");
						}
						
						return;
					}
					Node getnode=hm.get(nx+""+ny);
					if(getnode==null||getnode.go.size()==0)continue;
					LinkedList<String>nex=new LinkedList<String>();
					for (int j =getnode.go.size()-1; j >=0; j--) {
						if(getnode.go.get(j).startsWith("W")){
							String gg=getnode.go.remove(j);
							for (int k = 1; k < gg.length(); k++) {
								nex.add(String.valueOf(gg.charAt(k)));
								
							}	
						}	
					}
					
					
					Node next=new Node(nx, ny, nex,getnode.route);
					next.ro.addAll(cur.ro);
					next.ro.add(cur);
					
					q.offer(next);
				}
			}
					
		}
		System.out.println(name);
		System.out.println("  No Solution Possible");
		
	}
	
}









