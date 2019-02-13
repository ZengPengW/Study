public class Solution {
    public int NumberOf1(int n) {
     	//System.out.println(Integer.toString(n, 2));
		int count = 0;
        while(n!= 0){
            count++;
            n = n & (n - 1);
        //	System.out.println(Integer.toString(n, 2)+" "+n);
         }
        return count;
		
    }
}