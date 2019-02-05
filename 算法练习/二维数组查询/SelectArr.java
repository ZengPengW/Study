public class SelectArr {
   public boolean Find(int target, int[][] array) {
	for (int i = 0,len=array.length; i < len; i++) {
		if(array[i].length==0||array[i][0]>target)break;
		for (int j = 0,len1=array[i].length; j < len1; j++) {
			if(array[i][j]==target)
			return true;
			if(array[i][j]>target)break;
		}
			
	}
	    return false;

    }
}