public class Jump{
//f(n)=f(n-1)+f(n-2)+f(n-3)........
//f(0)=1 f(1)=1 f(2)=2;
    public int JumpFloorII(int target) {
   	    if(target==0)return 1;
		else if (target==1)return 1;
		else if (target==2)return 2;
		int a=1,b=1,c=2;
		int temp=a+b+c;
		if(target-3>0)temp*=Math.pow(2, (target-3));
		return temp;
    }
}