

public class Main{

	public static void main(String[] args) {
		int[][] grid = {{0,1,1,1,1,1},
				 	    {1,1,1,0,1,1},
				 	    {0,0,1,0,0,0},
				 	    {0,0,0,0,0,0},
				 	    {0,0,0,0,0,0}};
		int[][] hits = { { 0, 3 } };
		int[] bricks = hitBricks(grid, hits);
		for (int i : bricks) {
			System.out.println(i);
		}
		//[[0, 1, 1, 0, 1, 1], [1, 1, 1, 0, 0, 0], [0, 0, 1, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0]]
		 
	}

	public static int[] hitBricks(int[][] grid, int[][] hits) {
		int x = grid.length;
		int y = grid[0].length;
		int[] v = new int[hits.length];
		int id = 0;
		for (int[] del : hits) {
			if(grid[del[0]][del[1]]==0)continue;
			grid[del[0]][del[1]] = 0;
			int[][] mark = new int[x][y];
			for (int i = 0; i < grid[0].length; i++) {
				if (grid[0][i] == 1 && mark[0][i] == 0) {
					mark[0][i] = 1;
					dfs(grid, 0, i, mark);
				}
			}

			int count = 0;
			for (int i = 1; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j] != mark[i][j]) {
						grid[i][j] = 0;
						count++;
					}
				}
			}
			v[id++] = count;
		}
		return v;
	}

	private static void dfs(int[][] grid, int x, int y, int[][] mark) {

		int lenx = grid.length;
		int leny = grid[0].length;
		// ио
		if (x - 1 >= 0 && x - 1 < lenx && y >= 0 && y < leny && grid[x - 1][y] == 1 && mark[x - 1][y] == 0) {
			mark[x - 1][y] = 1;
			dfs(grid, x - 1, y, mark);
		}
		// об
		if (x + 1 < lenx && x + 1 >= 0 && y >= 0 && y < leny && grid[x + 1][y] == 1 && mark[x + 1][y] == 0) {
			mark[x + 1][y] = 1;
			dfs(grid, x + 1, y, mark);
		}
		// вС
		if (y - 1 >= 0 && x < lenx && x >= 0 && y - 1 < leny && grid[x][y - 1] == 1 && mark[x][y - 1] == 0) {
			mark[x][y - 1] = 1;
			dfs(grid, x, y - 1, mark);
		}
		// ср
		if (y + 1 < leny && y + 1 >= 0 && x >= 0 && x < lenx && grid[x][y + 1] == 1 && mark[x][y + 1] == 0) {
			mark[x][y + 1] = 1;
			dfs(grid, x, y + 1, mark);
		}
	}

}
