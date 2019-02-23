import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class  PrintBT {
   private ArrayList<Integer> al=new ArrayList<Integer>();
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
    	if(root==null)return al;
    	al.add(root.val);
    	Queue<TreeNode> que=new LinkedList<TreeNode>();
    	que.offer(root);
    	TreeNode node=null;
    	while (!que.isEmpty()) {
    		node=que.poll();
			if(node.left!=null){
				al.add(node.left.val);
				que.offer(node.left);
			}
			if(node.right!=null){
				al.add(node.right.val);
				que.offer(node.right);
			}
		}
    	
        return al;
    }
}