public class RotateArr {
	public int minNumberInRotateArray(int [] array) {
		if(array.length==0)return 0;
		int min=array[0];
		int id=0;
	    for (int i =1,len=array.length; i < len; i++) {
	    	
			if(array[i]>=array[i-1]){
				if(id>0)break;
				continue;
			}
			else {
				min=array[i];
				id++;
			}
			
		}
	    return min;
    }
}