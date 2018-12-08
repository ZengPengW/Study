
import java.util.Scanner;

public class Throw{
	private static int [][]role;
	private static int max=0;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		role=new int[3][3];
		
		for (int i = 0; i < role.length; i++) {
			role[i][0]=scan.nextInt();
			role[i][1]=scan.nextInt();
			role[i][2]=scan.nextInt();
		}
		int[]op=new int[9];
		for (int i = 0; i < op.length; i++) {
			op[i]=i;
		}
		
		dfs(op, 0);
		System.out.println(max);
	}
	
	public static void dfs(int[]op,int id) {
	
		if(id==8) {
			int [][]state=new int[3][4];//是否移动过，是否举着人，是否被举着，是否举过人
			state[0][1]=-1;
			state[1][1]=-1;
			state[2][1]=-1;
			int [][]ro=role.clone();
			int []pos=new int [100];
			pos[ro[0][0]]=1;
			pos[ro[1][0]]=1;
			pos[ro[2][0]]=1;
			check(op, 0, state, ro, pos);
			//max=max<v?v:max;
			return;
		}
		for (int i =id; i < op.length; i++) {
			if(id==0){
			if((op[i]%3)!=2){
			int temp=op[id];
			op[id]=op[i];
			op[i]=temp;
			dfs(op, id+1);
			temp=op[id];
			op[id]=op[i];
			op[i]=temp;	
				}
			}else {
				int temp=op[id];
				op[id]=op[i];
				op[i]=temp;
				dfs(op, id+1);
				temp=op[id];
				op[id]=op[i];
				op[i]=temp;	
			}
			
		}
		
		
	}

	private static void check(int[] op,int index,int [][]state,int [][]ro,int []pos) {
		
		if(index>8)return;
		
			int i=index;
			int o=op[i]%3;//0 1 2
			int w=op[i]/3;	
				if(o==0) {
					//0=是否移动过，1=是否举着某个人，2=是否被举着，3=是否举过人
					
					if(state[w][0]==1||state[w][1]!=-1||state[w][2]==1)return;
					int len=1;
					if(i==8) {
						len=ro[w][1];
					}
					else {
						for (int j =1; j <ro[w][0]; j++) {
							if(pos[j]==1){
								int l=-(ro[w][0]-j-1);
								len=l<len?l:len;
								break;
							}
						}
						len=len<-ro[w][1]?-ro[w][1]:len;
					}
					
					for (; len <=ro[w][1]; len++) {
						if(pos[ro[w][0]+len+1]==1||pos[ro[w][0]+len-1]==1||len==ro[w][1]){
							if(ro[w][0]+len>0&&pos[ro[w][0]+len]==0){
							//if(len==0)continue;	
								
								pos[ro[w][0]]=0;
								ro[w][0]+=len;
								pos[ro[w][0]]=1;
								state[w][0]=1;
								max=max<ro[w][0]?ro[w][0]:max;
								
								check(op, i+1, state, ro, pos);
								//回溯
								state[w][0]=0;
								pos[ro[w][0]]=0;
								ro[w][0]-=len;
								pos[ro[w][0]]=1;
								
							}	
						}	
					}
					
				}
				else if (o==1) {
					//0=是否移动过，1=是否举着某个人，2=是否被举着，3=是否举过人
					if(state[w][1]!=-1||state[w][2]==1||state[w][3]==1)return;
					for (int j = 0; j <3; j++) {
						if(w!=j&&Math.abs(ro[j][0]-ro[w][0])==1){
							if(state[j][2]==1)continue;
							
							state[j][2]=1;
							state[w][1]=j;
							state[w][3]=1;
							
							int temp=ro[j][0];
							pos[temp]=0;
							ro[j][0]=ro[w][0];
							
							if(state[j][1]!=-1){
								int k=state[j][1];
								ro[k][0]=ro[j][0];
							}
							
							check(op, i+1, state, ro, pos);
							//回溯
							state[w][3]=0;
							state[w][1]=-1;
							state[j][2]=0;
							
							ro[j][0]=temp;
							pos[temp]=1;
							if(state[j][1]!=-1){
								int k=state[j][1];
								ro[k][0]=ro[j][0];
							}						
						}					
					}
					
				}else if (o==2) {
				//0=是否移动过，1=是否举着某个人，2=是否被举着，3=是否举过人
					if(state[w][2]==1||state[w][1]==-1)return;
					
					int len=1;
					
					if(i==8) {
						len=ro[w][2];					
					}
					else {
						for (int j =1; j <ro[w][0]; j++) {
							if(pos[j]==1){
								int l=-(ro[w][0]-j-1);
								len=l<len?l:len;
								break;
							}
						}
						len=len<-ro[w][2]?-ro[w][2]:len;
					}
					
					for (; len <=ro[w][2]; len++) {
						if(ro[w][0]+len>0&&pos[ro[w][0]+len]==0){
						if(pos[ro[w][0]+len+1]==1||pos[ro[w][0]+len-1]==1||len==ro[w][2]){
							//if(len==0)continue;
							//0=是否移动过，1=是否举着某个人，2=是否被举着，3=是否举过人
								int k=state[w][1];
								ro[k][0]+=len;
								state[w][1]=-1;
								state[k][2]=0;
								pos[ro[k][0]]=1;
								max=max<ro[k][0]?ro[k][0]:max;
								
								if(state[k][1]!=-1){
									int l=state[k][1];
									ro[l][0]=ro[k][0];
								}
								
								check(op, i+1, state, ro, pos);
								//回溯
								pos[ro[k][0]]=0;
								ro[k][0]-=len;
								state[k][2]=1;
								state[w][1]=k;
								if(state[k][1]!=-1){
									int l=state[k][1];
									ro[l][0]=ro[k][0];
								}							
							}	
						}	
					}														
				}						
			
	}
}

