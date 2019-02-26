
public class DuiShu2 { 

	public static void main(String[] args) {
		int [] num=new int[15];
		num[1]=7;
		num[2]=4;
		num[9]=7;
		num[7]=4;
		int []mark=new int[8];
		mark[7]=2;
		mark[4]=2;
		f(num, mark, 3);
	}
	public static void f(int []num,int []mark,int index ) {
		if(index==15) {
			if(check(num)) {
					for (int i : num) {
					System.out.print(i);
				}
			System.out.println();
			}
			
			return;
		}
		while(true) {
			if(num[index]!=0)index++;
			else break;
		}
		for (int i = 1; i <=6; i++) {
			if(mark[i]<2) {
				mark[i]+=1;
				num[index]=i;
				f(num, mark,index+1);
				mark[i]-=1;
				num[index]=0;
			}
		}
	}

	public static boolean check(int []num) {
		boolean bl=false;
		for (int i = 3; i <15; i++) {
			int left=i-num[i]-1;
			int right=i+num[i]+1;
			bl=false;
			if((left>=0&&left<15&&num[left]==num[i])||(right>=0&&right<15&&num[right]==num[i]) )					
				bl= true;
			
			if(!bl)break;
		}
		return bl;
		
	}

}
