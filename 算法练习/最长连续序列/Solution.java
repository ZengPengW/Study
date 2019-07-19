class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set =new HashSet<>();
		for (int i : nums) {
			set.add(i);
		}
		int max=0;
		for (Integer num : set) {
			if(!set.contains(num-1)){
				int len=1;
				int curr=num;
				
				while (set.contains(curr+1)) {
					curr+=1;
					++len;
				}
				max=Math.max(len, max);
			}
			
			
		}
		return max;
    }
}