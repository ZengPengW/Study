import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public  class Statistics {
	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);
	        String str=null;
	        String temp=null;
	        int id=1;
	        Integer count=null;
	        Map<String, Integer> map=new HashMap<String,Integer>();
	        while(sc.hasNextLine()) {
	            str=sc.nextLine();
	            
	            if(str.trim().length()!=0) {	             
	            	for (int i = 1; i <=str.length(); i++) {
	            		temp=str.substring(0,i);
	            		if(!map.containsKey(temp)){
	            			map.put(temp, 1);
	            		}else {
							map.put(temp, map.get(temp)+1);
						}
						
					}
	                continue;
	            }
	          
	            while (sc.hasNextLine()) {
	                str=sc.nextLine();
	                count=map.get(str);
	                System.out.println(count==null?0:count);
	            }
	            return;
	        }
	}

	
}
