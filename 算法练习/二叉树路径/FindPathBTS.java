
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class FindPathBTS {
     public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
		  ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
         if(root==null)return al;
		  ArrayList<Integer> temp=new ArrayList<Integer>();
		  dfs(root, al, target, temp, root.val);
		  Collections.sort(al, new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				if(o1.size()>o2.size())return -1;
				else if(o1.size()<o2.size())return 1;
				return 0;
			}
		});
		  return al;
	  }
	  public void dfs(TreeNode x,ArrayList<ArrayList<Integer>> al,int target,ArrayList<Integer> temp,int val){
		  temp.add(x.val);
		     if(x.left==null&&x.right==null&&val==target){
			  al.add((ArrayList<Integer>) temp.clone());
		  }
		 
		  
		  if(x.left!=null)dfs(x.left, al, target, temp, val+x.left.val);
		  
		  if(x.right!=null)dfs(x.right, al, target, temp, val+x.right.val);
		  temp.remove(temp.size()-1);
	  }
}