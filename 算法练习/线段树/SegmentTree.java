import java.util.Scanner;

public class SegmentTree{
	private static int [] num;
	private static int [] sum;
	private static int [] Maxx;
	private static int n;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		int m=scan.nextInt();
		num=new int[n+1];
		sum=new int[4*n+1];
		Maxx=new int[4*n+1];
		for (int i = 1; i <=n; i++) 
			num[i]=scan.nextInt();
		int [][] op=new int[m][3];
		for (int i = 0; i <m; i++) {
			op[i][0]=scan.nextInt();
			op[i][1]=scan.nextInt();
			op[i][2]=scan.nextInt();
		}
		build(1, n, 1);
		operation(op);	
		
	}
	
	public static void operation (int [][] op) {
		int temp=0;
		for (int i = 0; i < op.length; i++) {
			
			switch (op[i][0]) {
			case 1:{
				 temp=num[op[i][1]];
				 num[op[i][1]]=op[i][2];
				 updeta(1,n, op[i][1],op[i][2]-temp,1);	
			}break;
			case 2:{
				//build(1, n, 1);
				System.out.println(getsum(op[i][1], op[i][2], 1, n,1));break;
			}
			case 3:{
				System.out.println(getMax(op[i][1], op[i][2], 1, n,1));
			}break;
			default:break;
			}
		}
	}
	
	public static void build(int l,int r,int rt) {
		
		if(l==r){
			sum[rt]=num[l];
			Maxx[rt]=num[l];
			return;
		}
		int mid=(l+r)>>1;
		
		build(l, mid, rt<<1);
		build(mid+1,r, rt<<1|1);
		sum[rt]=sum[rt<<1]+sum[rt<<1|1];
		Maxx[rt]=Maxx[rt<<1]>=Maxx[rt<<1|1]?Maxx[rt<<1]:Maxx[rt<<1|1];
	}
	
	public static int  getMax(int L,int R,int l,int r,int rt) {
		
		if(L<=l&&R>=r){
			return Maxx[rt];
		}
		
		int mid=(l+r)>>1;
		int right=0, left=0;
		if(L<=mid)left= getMax(L, R, l, mid,rt<<1);
		if(R>mid)right= getMax(L, R, mid+1, r,rt<<1|1);
		return left>=right?left:right;
	}
	
	public static void updeta(int l,int r,int L,int C,int rt) {
		if(l==r){
			sum[rt]+=C;
			Maxx[rt]=num[l];
			return;
		}
		int mid=(l+r)>>1;
		
		if(L<=mid)updeta(l, mid, L, C, rt<<1);
		else updeta(mid+1, r, L, C, rt<<1|1);
		sum[rt]=sum[rt<<1]+sum[rt<<1|1];
		Maxx[rt]=Maxx[rt<<1]>=Maxx[rt<<1|1]?Maxx[rt<<1]:Maxx[rt<<1|1];
		
	}
	public static int  getsum(int L,int R,int l,int r,int rt) {
		if(L<=l&&R>=r)return sum[rt];
		
		int mid=(l+r)>>1;
		int ans=0;
		if(L<=mid)ans+=getsum(L, R, l, mid, rt<<1);
		if(R>mid)ans+=getsum(L, R, mid+1, r, rt<<1|1);
		return ans;
	}
	
}