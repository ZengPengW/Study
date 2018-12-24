

public class Main {
	
	public static void main(String[] args) {
		int count=0;
		for (int i = 0; i <2000; i++) {
			for (int j = 0; j <2000; j++) {
				if (dis(i, j,1000,1000)<=1000.0
						&&dis(i+1, j,1000,1000)<=1000.0
						&&dis(i, j+1,1000,1000)<=1000.0
						&&dis(i+1, j+1,1000,1000)<=1000.0) {
					count++;
				}
				
			}
			
		}
		System.out.println(count);
	
	}
	private static double dis(int x1,int y1,int x2,int y2){
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}



}
