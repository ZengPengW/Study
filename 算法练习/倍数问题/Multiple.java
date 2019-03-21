
import java.util.Arrays;
import java.util.Scanner;

public class Multiple {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		int[] num = new int[n];
		for (int i = 0; i <n; i++) {
			num[i]=scan.nextInt();
		}
		Arrays.sort(num);

		for (int i = n - 1; i >= 0; i--)
			for (int j = i - 1; j >= 0; j--)
				for (int l = j - 1; l >= 0; l--) {
					if((num[i]+num[j]+num[l])%k==0) {
						System.out.println(num[i]+num[j]+num[l]);
						System.exit(0);
					}
						
				}

	}
}
