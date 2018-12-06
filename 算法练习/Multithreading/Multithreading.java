import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Multithreading{

	private static int count = 0;
	private static int min=1000000001;
	private static ArrayList<Entry<Integer, Integer>> al;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int W = scan.nextInt();

		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

		for (int i = 1; i <=N; i++) {
			int a = scan.nextInt();
			if(min>a)min=a;
			count += a;
			hm.put(i, a);
		}

		 al = new ArrayList<Entry<Integer, Integer>>(hm.entrySet());
		Collections.sort(al, new Comparator<Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				if (o1.getValue() < o2.getValue())
					return -1;
				else if (o1.getValue() > o2.getValue())
					return 1;
				else
					return 0;
			}
		});
		
		int[] num = new int[count*2];
		int id = 0;
		
		for (int j = 0; j < al.size(); j++) {
			int key = al.get(j).getKey();
			int val = al.get(j).getValue();
			
			for (int i = 0; i < val; i++) {
				
				num[id++] = key;
				num[id++] = key;
			}
		}

		f(num, W);

	}

	public static void f(int[] num, int w) {
		int sum = count;
		if (sum < w||(sum>w&&al.size()==1)||(w==1&&min>1)) {
			System.out.println("No");
			return;
		}

		if (sum == w) {
			System.out.println("Yes");
			for (int i : num) {
				System.out.print(i + " ");
			}
			return;
		}

		if (sum > w) {
			int []group=new int [w];
			System.out.println("Yes");
			int k=0, j=0;
			int co=0;
			for (int i = 0; i < group.length; i++) {
				if(al.get(j).getValue()>0&&co<sum){
					group[k++]=al.get(j).getKey();
					al.get(j).setValue(al.get(j).getValue()-1);
					co++;
				}else {
					j++;
					group[k++]=al.get(j).getKey();
					al.get(j).setValue(al.get(j).getValue()-1);
					co=1;
				}
				
			}
			
			ArrayList<Integer> als=new ArrayList<Integer>();
			
			for (int i = 0; i < group.length; i++) {
				als.add(group[i]);
				
			  for (int l = 0; l < al.size();l++) {
				  int kk=al.get(l).getKey();
				if(kk!=group[i]){
					int vv=al.get(l).getValue();
					while(vv>0) {
						als.add(kk);
						als.add(kk);
						vv--;
						al.get(l).setValue(vv);
					}
				}	  
			  }
				
				als.add(group[i]);	
			}
			
			for (int i : als) {
				System.out.print(i+" ");
			}
			
			return;
			
			
		}

	}

}
