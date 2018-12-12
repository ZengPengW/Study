import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.naming.directory.SearchControls;

public class TrieST {
	private static int R=256;//基数
	private Node root;
	
	private class Node{
		private Object val;
		private Node[] next=new Node[R];
		
	}
	
	//get value
	public Object get(String key) {
		Node x=get(root,key,0);
		if(x==null)return null;
		return x.val;
		
	}
	
	private Node get(Node x, String key, int d) {
		if(x==null)return null;
		if(d==key.length())return x;
		char c=key.charAt(d);
		return get(x.next[c], key, d+1);
	}
	
	//put
	private void put(String key,Object val){
		root=put(root,key,val,0);
	}
	
	private Node put(Node x, String key, Object val, int d) {
		if(x==null)x=new Node();
		if(d==key.length()) {x.val=val;return x;}
		char c=key.charAt(d);
		x.next[c]=put(x.next[c], key, val, d+1);
		return x;
	}

	//查找个数
	public int size() { return size(root);}
	private int size(Node x) {
		if(x==null)return 0;
		int cnt=0;
		if(x.val!=null)cnt++;
		for (char c = 0; c <R; c++) {
			cnt+=size(x.next[c]);
		}
		return cnt;
	}
	
   //查找所有key
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}
	//查找匹配开头串
	private Iterable<String> keysWithPrefix(String pre) {
		Queue<String> queue=new LinkedList<String>();
		collect(get(root, pre, 0),pre,queue);
		return queue;
	}

	private void collect(Node x, String pre, Queue<String> queue) {
		if(x==null)return;
		if(x.val!=null)queue.offer(pre);
		for (char i = 0; i <R; i++) {
			collect(x.next[i], pre+i, queue);
		}	
	}
	
	//通配查找
	public Iterable<String> keysThatMatch(String pat){
		Queue<String> q=new LinkedList<String>();
		collect(root,"",pat,q);
		return q;
	}

	private void collect(Node x, String key, String pat, Queue<String> q) {
		int d=key.length();
		if(x==null)return;
		if(d==pat.length()&&x.val!=null)q.offer(key);
		if(d==pat.length())return;
		
		char next=pat.charAt(d);
		for (char c = 0; c < R;c++) {
			if(next=='.'||next==c)
				collect(x.next[c], key+c, pat, q);
			
		}
		
	}
	
		
	//匹配给定字符串的最长前缀
	public String longestPrefixOf(String s) {
			int length=search(root,s,0,0);
			return s.substring(0,length);
		}
		
	private int search(Node x, String s, int d, int length) {
		if(x==null)return length;
		if(x.val!=null)length=d;
		if (d==s.length())return length;
		
		char c=s.charAt(d);
		return search(x.next[c], s, d+1, length);
		
	}
	
	//删除操作
	public void delete(String key) {
		root=delete(root,key,0);
	}
	private Node delete(Node x, String key, int d) {
		if(x==null)return null;
		if(d==key.length())x.val=null;
		else {
			char c=key.charAt(d);
			x.next[c]=delete(x.next[c], key, d+1);
		}
		if(x.val!=null)return x;
		for (char c = 0; c <R; c++) {
			if(x.next[c]!=null)return x;
		}
		return null;
	}
//
	public static void main(String[] args) {
		TrieST ts=new TrieST();
		ts.put("by", 1);
		ts.put("sea", 2);
		ts.put("sells", 3);
		ts.put("she", 4);
		ts.put("shells", 5);
		ts.put("the", 6);
		
		ts.delete("by");
		Iterator<String> iterator=ts.keys().iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());	
		}
		System.out.println("树大小:"+ts.size());
		System.out.println("key的value:"+ts.get("by"));
		System.out.println("最长前缀匹配:"+ts.longestPrefixOf("shel"));
		System.out.println("匹配首头查找:"+ts.keysWithPrefix("se"));
		System.out.println("通配查找:"+ts.keysThatMatch("s...."));
	}
	

}
