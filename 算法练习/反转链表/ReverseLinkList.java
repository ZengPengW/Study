/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
import java.util.Stack;
public class ReverseLinkList {
    public ListNode ReverseList(ListNode head) {
     if(head==null)return null;
		Stack<ListNode> stk=new Stack<ListNode>();
		while (head!=null) {
			stk.push(head);
			head=head.next;
			
		}
		head=null;
		head=Reverse(head, stk);
		
		return head;
    }
    public  ListNode Reverse(ListNode head,Stack<ListNode> stk) {
		if(!stk.isEmpty()){
			head=stk.pop();
			head.next=Reverse(head.next, stk);
		}else {
			head=null;
		}
		return head;

	}
}