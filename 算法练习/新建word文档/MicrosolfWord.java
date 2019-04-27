
import java.util.Scanner;
import java.util.TreeSet;

public class MicrosolfWord {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n =sc.nextInt();
		sc.nextLine();
		String op=null;
		int deleteId=0;
		TreeSet<Integer> minv=new TreeSet<Integer>();
		for (int i = 1; i <=1480; i++) 
			minv.add(i);	
		
		while(n-->0){
			op=sc.nextLine().trim();
			if(op.charAt(0)=='N') {
				System.out.println(minv.pollFirst());
				
			}else {
				deleteId=Integer.parseInt(op.split(" ")[1]);
				if(minv.contains(deleteId))System.out.println("Failed");
				else {
					System.out.println("Successful");
					minv.add(deleteId);
				}
				
			}
		}

	}

}
