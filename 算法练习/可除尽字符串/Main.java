
public class Main{

	public static void main(String[] args) {
		System.out.println(gcdOfStrings("UETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZ", "UETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZUETKZ"));
	}

	public static String gcdOfStrings(String str1, String str2) {
		//if(str1.length()==str2.length()&&str1.equals(str1))return str1;
		if (str1.length() < str2.length()) {
			String temp=str1;
			str1=str2;
			str2=temp;
		}
			String x = str2;
			while (x.length()>0) {
				
				int id=0;
				int add=0;
				boolean flag=false;
				while (true) {
					id=str2.indexOf(x, id);
					if (id==add) {
						id+=x.length();
						add+=x.length();
					}else {
						break;
					}
					if ((id)==str2.length()) {
						flag=true;
						break;
					}
				}
				if (flag) {
					 id=0;
					 add=0;
					 flag=false;
					 while (true) {
							id=str1.indexOf(x, id);
							if (id==add) {
								id+=x.length();
								add+=x.length();
							}else {
								
								break;
							}
							if ((id)==str1.length()) {
								flag=true;
								break;
							}
						} 
					 if (flag) {
						return x;
					}
				}

				int len=x.length();
				int len1=str2.length();
				while(--len>0){
					if(len1%len==0){
						x=x.substring(0, len);
						break;
					}
				}
				if(len<=0)break;
			
			}
		
		return "";
	}
}
