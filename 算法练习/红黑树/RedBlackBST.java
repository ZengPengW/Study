import java.security.Key;


public class RedBlackBST {

	private static final boolean RED=true;
	private static final boolean BLACK=false;
	

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
		
		Node x= min(root);
		if(x==null) return -1;
		return x.key;
	}

	private Node min(Node x) {
		if(x==null)return null;
		if (x.left==null) return x;
		return min(x.left);
		
	}
	public int max(){
		Node x=max(root);
		if(x==null) return -1;
		return x.key;
	}
	private Node max(Node x) {
		if(x==null)return null;
		if(x.right==null)return x;
		
		return max(x.right);
	}

	public int floor(int key){
		Node x=floor(root,key);
		if(x==null)return -1;
		return x.key;
		
	}

	private Node floor(Node x, int key) {
		if(x==null)return null;
		int cmp=key>(x.key)?1:key==(x.key)?0:-1;
		if (cmp<0)return floor(x.left, key);
		if (cmp==0)return x;
		Node t=floor(x.right, key);
		if(t!=null) return t;
		else return x;
		
	
	}
	public int select(int k){
		if(k>size()-1)return -1;
		return select(root,k).key;
	}

	private Node select(Node x, int k) {
		
		if(x==null)return null;
		int t=size(x.left);
		if(t>k)return select(x.left, k);
		else if(t<k) return select(x.right, k-t-1);
		else return x;
	}
	
	public  int  rank(int key) {
		
		return rank(key,root);
		
	}

	private int rank(int key, Node x) {
		if(x==null)return 0;
		int cmp=key>(x.key)?1:key==(x.key)?0:-1;
		if(cmp<0)return rank(key,x.left);
		else if(cmp>0) return 1+size(x.left)+rank(key, x.right);
		else return size(x.left);
		
	}
	public String get(int key){
		return get(key,root);
	}
	private String get(int key, Node x) {
		if (x==null)return null;
		int cmp=key>(x.key)?1:key==(x.key)?0:-1;
		if(cmp<0)return get(key, x.left);
		else if(cmp>0)return get(key, x.right);
		else return x.val;
		
	}
	

	

	public static void main(String[] args) {
		RedBlackBST rbt=new RedBlackBST();
		rbt.put(9, "A");
		rbt.put(4, "B");
		rbt.put(7, "C");
		rbt.put(8, "D");
		rbt.put(2, "E");
		rbt.put(5, "F");
		rbt.put(3, "G");
		
		System.out.println("查询二叉树大小:"+rbt.size());
		System.out.println("查询指定key的value:"+rbt.get(2));
		System.out.println("查询最小key:"+rbt.min());
		System.out.println("查询最大key:"+rbt.max());
		System.out.println("查询比key小的最大key:"+rbt.floor(6));
		System.out.println("查询第n大的key:"+rbt.select(1));
		System.out.println("查询key小于指定key的个数:"+rbt.rank(6));
		
	}

}
