import java.util.Scanner;

public class PrintBTS {

	public static void main(String[] args) {

		PrintBTS bs=new PrintBTS();
		Scanner sc=new Scanner(System.in);
		String[] str=sc.nextLine().split(" ");
		for (int i = 0,len=str.length; i < len; i++) {
			bs.put(Integer.parseInt(str[i]), str[i]);	
		}
		
		print(bs.root, 1,"");
		
		
	}
	
	public static void print(Node x,int id,String str) {
		if(x==null)return;
		int key=x.key;
		int h=(int) (Math.log(id)/Math.log(2)+1);//所在高度
		
		
		if(h==1){
			str=key+"-|";	
		}
		else if(h==2){
			str=str.replaceAll("\\d|\\-", ".");
			if(x.left==null&&x.right==null)str+="-"+key;
			else str+="-"+key+"-|";
		}else {
			str=str.replaceAll("\\d|\\-", ".");
			if((x.fa.fa.left!=null&&x.fa.fa.left.left==x)||(x.fa.fa.right!=null&&x.fa.fa.right.right==x))
			{
				int index=str.lastIndexOf("|", str.length()-2);
				str=str.substring(0, index)+"."+str.substring(index+1, str.length());
				
			}
			if(x.left==null&&x.right==null)str+="-"+key;
			else str+="-"+key+"-|";
			
			
		}
		print(x.right,id*2+1,str);
		System.out.println(str);
		print(x.left,id*2,str);
		
		
		
	}
	private Node root;
	/*
	 * 判断条件为该结点父结点的父结点存在且该父节点的左孩子的左孩子或者右孩子的右孩子为当前结点时
	 * “|”位置替换为“.”其他情况下“|”的位置不变。
	 */
	private class Node {
		private int key;
		private String val;
		private Node left, right,fa;
		private int N;

		private Node(int key, String val, int N,Node fa) {
			this.key = key;
			this.N = N;
			this.val = val;
			this.fa=fa;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", val=" + val + ", left=" + left
					+ ", right=" + right + ", N=" + N + "]";
		}

		
	}

	public int size() {
		return size(root);
	}

	public int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}


	public void put(int key, String val) {
		root = put(root, key, val,null);
	}

	public Node put(Node x, int key, String val,Node fa) {
		if (x == null)
			return new Node(key, val, 1,fa);
		int cmp = key > x.key ? 1 : key == x.key ? 0 : -1;
		if (cmp < 0)
			x.left = put(x.left, key, val,x);
		else if (cmp > 0)
			x.right = put(x.right, key, val,x);
		else
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	

	
	
}
