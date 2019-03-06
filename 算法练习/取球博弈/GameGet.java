
import java.util.Arrays;
import java.util.Scanner;

public class GameGet {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] n = new int[3];
		int[] x = new int[5];
		for (int i = 0; i < 3; i++) {
			n[i] = sc.nextInt();
		}
		for (int i = 0; i < 5; i++) {
			x[i] = sc.nextInt();
		}
		pk(n, x);
	}

	public static void pk(int[] n, int[] x) {
		Arrays.sort(n);

		for (int i = 0; i < 5; i++) {
			int num = x[i];
			int p1 = 0;
			int p2 = 0;
			while (num > 0) {
				//Íæ¼Ò1
				boolean bl = false;
				for (int j = 2; j >= 0; j--) {
					if ((n[j] % 2 == 0 && p1 % 2 == 0) || (p1 % 2 != 0 && n[j] % 2 != 0))
						continue;
					if (num - n[j] >= 0) {
						p1 += n[j];
						num -= n[j];
						bl = true;
						break;
					}
				}

				if (!bl) {
					for (int j = 2; j >= 0; j--) {
						if (num - n[j] >= 0) {
							p1 += n[j];
							num -= n[j];
							bl = true;
							break;
						}
					}
				}
				if(bl!=true)break;
				if(num<=0)break;
				//Íæ¼Ò2
				bl = false;
				for (int j = 2; j >= 0; j--) {
					if ((n[j] % 2 == 0 && p2 % 2 == 0) || (p2 % 2 != 0 && n[j] % 2 != 0))
						continue;
					if (num - n[j] >= 0) {
						p2 += n[j];
						num -= n[j];
						bl = true;
						break;
					}
				}

				if (!bl) {
					for (int j = 2; j >= 0; j--) {
						if (num - n[j] >= 0) {
							p2 += n[j];
							num -= n[j];
							bl = true;
							break;
						}
					}
				}
				if(bl!=true)break;
				if(num<=0)break;	
			}
			if((p1%2==0&&p2%2==0)||(p1%2==1&&p2%2==1))
				System.out.print("0 ");
			else if ((p1%2==1&&p2%2==0)) 
				System.out.print("+ ");
			else System.out.print("- ");
			
		}

	}
}