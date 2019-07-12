package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Node525 {

	public static void main(String[] args) {
		int[] nums={0,1,0};
		System.out.println(findMaxLength(nums));;
	}
	public static int findMaxLength(int[] nums) {
		int max=0;
		Integer sum=0;
		Map<Integer, Integer> map=new HashMap<Integer,Integer>();
		map.put(0, -1);
		for (int i = 0; i < nums.length; i++) {
			sum+=nums[i]==0?-1:1;
			if(map.containsKey(sum)){
				max=Math.max(max, i-map.get(sum));
			}else {
				map.put(sum, i);
			}
			
		}
		
		return max;
		
	}

}
