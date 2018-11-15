
public class RedBlackBST {

	private static final boolean RED=true;
	private static final boolean BLACK=false;
	
	public static void main(String[] args) {
		

	}
	private Node root;
	private class Node{
		private int key ;
		private String val;
		private Node left,right;
		private int N;
		private boolean color;
		
		private Node(int key,String val,int N,boolean color) {
			this.key=key;
			this.val=val;
			this.color=color;
			this.N=N;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", val=" + val + ", left=" + left
					+ ", right=" + right + ", N=" + N + ", color=" + color
					+ "]";
		}
		
		
	}
	public void put(int key ,String val){
		root=put(root,key,val);
		root.color=BLACK;
	}
	
	private Node put(Node h, int key, String val) {
		if(h==null) return new Node(key, val, 1, RED);
		
		int cmp=key>h.key?1:key<h.key?-1:0;
		if(cmp<0) h.left=put(h.left,key, val);
		else if(cmp>0)h.right=put(h.right, key, val);
		else h.val=val;
		
		if(!isRed(h.left)&&isRed(h.right)) h=rotateLeft(h);
		if(isRed(h.left)&&isRed(h.left.left))h=rotateRight(h);
		if(isRed(h.left)&&isRed(h.right))flipColors(h);
		
		h.N=1+size(h.left)+size(h.right);
		return h;
	}

	public int size() {
		return size(root);
	}

	public int size(Node x) {
		if(x==null)return 0;
		return x.N;
	}
	
	public boolean isRed(Node x){
		if(x==null)return false;
		return x.color==RED;
	}
	
	public Node rotateLeft(Node h){
		Node x=h.right;
		h.right=x.left;
		x.left=h;
		x.color=h.color;
		h.color=RED;
		x.N=h.N;
		h.N=1+size(h.left)+size(h.right);
		return x;
		
	}
	public Node rotateRight(Node h){
		Node x=h.left;
		h.left=x.right;
		x.right=h;
		x.color=h.color;
		h.color=RED;
		x.N=h.N;
		h.N=size(h.left)+size(h.right)+1;
		return x;
	}
	public void flipColors(Node h){
		h.color=RED;
		h.left.color=BLACK;
		h.right.color=BLACK;
	}
	

	public int min(){
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left==null) return x;
		return min(x.left);
		
	}

}
