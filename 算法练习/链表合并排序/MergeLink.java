/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class MergeLink {
  public ListNode Merge(ListNode list1,ListNode list2) {
	        ListNode list=null;
	        list=dfs(list, list1, list2);
	        return list;
	   
	  }
	  public ListNode dfs(ListNode list,ListNode list1,ListNode list2){
		  	if (list1==null&&list2==null) 
		  		return null;
	       
	        	if(list1==null){
	        		list=list2;
	        		list.next=dfs(list, list1, list2.next);
	        	}else if (list2==null) {
					list=list1;
					list.next=dfs(list, list1.next, list2);
				}else if (list1.val<list2.val) {
					list=list1;
					list.next=dfs(list, list1.next, list2);
				}else if (list1.val>=list2.val) {
					list=list2;
					list.next=dfs(list, list1, list2.next);
				}
	        
	        return list;
	  }
}