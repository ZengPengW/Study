import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Airport_Configuration {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		while(true) {
			int n=scan.nextInt();
			if(n==0)break;
			int [][]num=new int[n+1][n+1];
			
			for (int i = 1; i <=n; i++) {
				int g=scan.nextInt();
				int count=scan.nextInt();
				
				for (int j = 0; j <count; j++) {
					int g2=scan.nextInt();
					num[g][g2]=scan.nextInt();
				}
				
			}
			
			HashMap<Integer, Integer> hm=new HashMap<Integer, Integer>();
			while(true) {
				int i=scan.nextInt();
				if(i==0)break;
				int [][]Method=new int [2][n+1];
				
				for (int j = 0; j <n; j++) {
					Method[0][scan.nextInt()]=j;
				}
				for (int j = 0; j <n; j++) {
					Method[1][scan.nextInt()]=j;
				}
				
				Statistics(i, num, Method, hm);
				
			}
			
			ArrayList<Entry<Integer, Integer>> al=new ArrayList<Entry<Integer,Integer>>(hm.entrySet());
			
			Collections.sort(al, new Comparator<Entry<Integer, Integer>>() {

				@Override
				public int compare(Entry<Integer, Integer> o1,
						Entry<Integer, Integer> o2) {
					if(o1.getValue()<o2.getValue())return -1;
					else if (o1.getValue()>o2.getValue())return 1;
					else {
						if(o1.getKey()<o2.getKey())return -1;
						else return 1;
					}
				}
			});
			
			System.out.println("Configuration Load");
			for (Entry<Integer, Integer> entry : al) {
				//if(String.valueOf(entry.getKey()).length()==2)System.out.println("   "+entry.getKey()+"         "+entry.getValue());
				//else System.out.println("    "+entry.getKey()+"         "+entry.getValue());
				System.out.println(String.format("%5d%9s%d",entry.getKey(),"",entry.getValue()));
			}
			
		}
	}
	
	
	public static void Statistics(int name,int [][]num,int [][]method,HashMap<Integer, Integer> hm) {
		int sum=0;
		for (int i = 1; i < num.length; i++) {
			for (int j = 1; j <num.length; j++) {
				if(num[i][j]!=0) {
					int x=method[0][i];
					int y=method[1][j];
					if(x==y){
						sum+=num[i][j];
						continue;
					}
					int v1=(1+Math.abs(x-y));
								
					sum+=v1*num[i][j];
					}
				}
			}
			
		hm.put(name, sum);	
			
		}
		
		
	}

