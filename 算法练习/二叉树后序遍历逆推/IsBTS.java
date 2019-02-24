public class IsBTS{
 public boolean VerifySquenceOfBST(int [] sequence) {
		 int len=sequence.length;
		 if (len==0) 
			return false;
		
	    
		 int st=0,ed;
	   while (--len>0) {
		   ed=len;
		while(st<ed&&sequence[st++]<sequence[ed]);
		while(st<ed&&sequence[st++]>sequence[ed]);
		
		if(st<ed)return false;
		st=0;
	   }
	   return true;
	  }
	
}