import java.util.ArrayList;
public class PrintMatrix {
   public ArrayList<Integer> printMatrix(int [][] matrix) {
    	if(matrix==null)return null;
		ArrayList<Integer> al= new ArrayList<Integer>(matrix.length*matrix[0].length);
	
	
    	int [][] mark=new int[matrix.length][matrix[0].length];
    	mark[0][0]=1;
    	int count=0;
    	dfs(matrix,al,mark,0,0,"R",count);
    	return al;
       
    }
    public void dfs(int[][] matrix,ArrayList<Integer> al,int [][]mark,int x,int y,String upstate,int count){
    	
    	al.add(matrix[x][y]);
    	count++;
    	if(count==matrix.length*matrix[0].length)return;
    	
    	if((upstate.equals("R"))&&y+1<=matrix[x].length-1&&mark[x][y+1]==0){
    		mark[x][y+1]=1;
    		dfs(matrix, al, mark, x, y+1, "R",count);
    	}else if ((upstate.equals("R")||upstate.equals("D"))&&x+1<=mark.length-1&&mark[x+1][y]==0) {
			mark[x+1][y]=1;
			dfs(matrix, al, mark, x+1, y, "D",count);
		}else if ((upstate.equals("L")||upstate.equals("D"))&&y-1>=0&&mark[x][y-1]==0) {
			mark[x][y-1]=1;
			dfs(matrix, al, mark, x, y-1, "L",count);
		}else if((upstate.equals("L")||upstate.equals("U"))&&x-1>=0&&mark[x-1][y]==0) {
			mark[x-1][y]=1;
			dfs(matrix, al, mark, x-1, y, "U",count);
		}else {
			mark[x][y+1]=1;
    		dfs(matrix, al, mark, x, y+1, "R",count);
		}
    	
    }
}