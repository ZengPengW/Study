public class BST {

	public static void main(String[] args) {

		BST bs=new BST();
		
		
		bs.put(3, "c");
		bs.put(2, "b");
		bs.put(5, "d");
		bs.put(11, "a");
		bs.put(4, "d");
		bs.put(9, "d");
		bs.put(8, "a");
		
		System.out.println(bs.size());
		System.out.println(bs.get(2));
		System.out.println(bs.min());
		System.out.println(bs.floor(6));
		System.out.println(bs.select(3));
		System.out.print(bs.root);
		
		
	}

	private Node root;

	private class Node {
		private int key;
		private String val;
		private Node left, right;
		private int N;

		public Node(int key, String val, int N) {
			this.key = key;
			this.N = N;
			this.val = val;
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

	public String get(int key) {
		return get(root, key);
	}

	public String get(Node x, int key) {
		if (x == null)
			return null;
		int cmp = key > x.key ? 1 : key == x.key ? 0 : -1;
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;

	}

	public void put(int key, String val) {
		root = put(root, key, val);
	}

	public Node put(Node x, int key, String val) {
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key > x.key ? 1 : key == x.key ? 0 : -1;
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public int min(){
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left==null) return x;
		return min(x.left);
		
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
		return select(root,k).key;
	}

	private Node select(Node x, int k) {
		
		if(x==null)return null;
		int t=size(x.left);
		if(t>k)return select(x.left, k);
		else if(t<k) return select(x.right, k-t-1);
		else return x;
	}

}