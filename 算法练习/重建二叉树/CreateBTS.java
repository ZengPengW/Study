import java.util.HashMap;

public class CreateBTS {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return "TreeNode [val=" + val + ", left=" + left + ", right="
					+ right + "]";
		}

	}

	public static void main(String[] args) {
		int[] pre = { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] in = { 4, 7, 2, 1, 5, 3, 8, 6 };
		System.out.println(reConstructBinaryTree(pre, in));
	}

	public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (pre.length == 0 || in.length == 0)
			return null;
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0, len = in.length; i < len; i++)
			hm.put(in[i], i);

		return op(pre, 0, pre.length - 1, in, 0, in.length - 1, hm);

	}

	public static TreeNode op(int[] pre, int ps, int pd, int[] in, int is,
			int id, HashMap<Integer, Integer> hm) {
		if (ps > pd)
			return null;

		TreeNode tn = new TreeNode(pre[ps]);
		int index = hm.get(pre[ps]);
		tn.left = op(pre, ps + 1, ps + index - is, in, is, index - 1, hm);
		tn.right = op(pre, ps + index - is + 1, pd, in, index + 1, id, hm);
		return tn;
	}
}
