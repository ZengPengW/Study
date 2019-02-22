import java.util.ArrayList;

public class PopOrder {
 public boolean IsPopOrder(int [] pushA,int [] popA) {
      ArrayList<Integer> al=new ArrayList<Integer>(pushA.length);
      
      int index=0;
      for (int i = 0; i < pushA.length; i++) {
		al.add(pushA[i]);
		if(pushA[i]==popA[index]){
			int id=i;
			while((id>=0&&index<popA.length)&&pushA[id]==popA[index]){
				al.remove(al.size()-1);
				index++;
				id--;
			}
		}	
      }
      int id=al.size()-1;
      for (int i = index; i < popA.length; i++) {
		if(popA[i]!=al.get(id--)){
			return false;
		}
		
	}
    return true;	
    }
}