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
public class IsChildBTS {
       public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root2==null||root1==null)return false;
        StringBuilder sb1=new StringBuilder();
        StringBuilder sb2=new StringBuilder();
        dfs(root1, sb1);
        dfs(root2, sb2);
        return sb1.toString().contains(sb2.toString());
        
    }
    public void dfs(TreeNode root,StringBuilder sb){
    	sb.append(root.val);
    	if(root.left!=null)dfs(root.left, sb);
    	if(root.right!=null)dfs(root.right, sb);
    }
}