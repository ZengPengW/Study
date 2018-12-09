import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class Computation{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		f(s);

	}

	// 1-2+3*(4-5)
	//表达式计算
	//Expression computation
	public static void f(String s) {
		char[] c = s.toCharArray();
		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		hm.put("+", 1);
		hm.put("-", 1);
		hm.put("*", 2);
		hm.put("/", 2);
		Stack<String> op = new Stack<String>();
		Queue<String> que = new LinkedList<String>();
		String snum="";
		
		for (int i = 0; i < c.length; i++) {
			snum="";
			if ((i==0&&c[i]=='-')||c[i] >= '0' && c[i] <= '9') {
				snum+=c[i];
				while(i<c.length){
					if (i+1<c.length&&c[i+1] >= '0' && c[i+1] <= '9'){
						i++;
						snum+=c[i];
					}else {
						que.offer(snum);
						break;
					}
						
				}
				
				
			} else if (c[i] == ')') {

				while (!op.isEmpty()) {
					String str = op.pop();
					if (str.equals("("))
						break;
					que.offer(str);
				}
				
			} else {
				if(c[i]!='('){
					int mk=hm.get(""+c[i]);
					while (!op.isEmpty()) {
						
						String ck=op.peek();
						if(!ck.equals("(")&&hm.get(ck)>=mk){
							que.offer(op.pop());
						}else {
							break;
						}	
					}
				}
				
				op.push("" + c[i]);
			}
		}
		
		while (!op.isEmpty()) {
			String str = op.pop();
			que.offer(str);
		}				
		//System.out.println(que);
		Stack<Integer> stk=new Stack<Integer>();
		while (!que.isEmpty()) {
			String sq=que.poll();
		boolean isNumber=	Pattern.matches("\\-*[0-9]+", sq);
		if(isNumber){
			stk.push(Integer.parseInt(sq));
		}else {
			int a=stk.pop();
			int b=stk.pop();
			int key=0;
			
			if(sq.equals("+"))key=1;
			else if(sq.equals("-"))key=2;
			else if(sq.equals("*"))key=3;
			else key=4;
			
			switch (key) {
			case 1:stk.push(b+a);break;
			case 2:stk.push(b-a);break;
			case 3:stk.push(b*a);break;
			case 4:stk.push(b/a);break;
			default:break;
			}
			
		}
		
		}
		System.out.println(stk.pop());

	}

}