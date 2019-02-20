import java.util.Stack;

public class StackMin {

 private  Stack<Integer> st=new Stack<Integer>();
	private  Stack<Integer> min=new Stack<Integer>();
    public void push(int node) {
        st.push(node);
        if(min.isEmpty()){
        	min.push(node);
        }else {
			min.push(min.peek()>node?node:min.peek());
		}
    }
    
    public void pop() {
        st.pop();
        min.pop();
    }
    
    public int top() {
        return st.peek();
    }
    
    public int min() {
        return min.peek();
    }
}