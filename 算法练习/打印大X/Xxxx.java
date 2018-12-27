

import java.util.Scanner;

public class Xxxx {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int h = sc.nextInt();
		long ks=System.currentTimeMillis();
		dy(w, h);
		System.out.println(System.currentTimeMillis()-ks);
	
	}

	public static void dy(int w, int h) {
		int wd = h - 1 + w;
		for (int i = 0; i < (h - 1) / 2; i++) {

			int midlen=wd-2*w-2*i;
			if(midlen<=0&&i==0){
				System.out.print(String.format("%"+wd+"s","").replace(" ", "*"));
			}else if (midlen<=0&&i>0) {
				System.out.print(String.format("%"+i+"s", "").replace(" ", "."));
				System.out.print(String.format("%"+(wd-2*i)+"s", "").replace(" ", "*"));
				System.out.print(String.format("%"+i+"s", "").replace(" ", "."));
			}else if(midlen>0&&i==0) {
				System.out.print(String.format("%"+w+"s","").replace(" ", "*"));
				System.out.print(String.format("%"+midlen+"s","").replace(" ", "."));
				System.out.print(String.format("%"+w+"s","").replace(" ", "*"));
			}else {
				System.out.print(String.format("%"+i+"s", "").replace(" ", "."));
				System.out.print(String.format("%"+w+"s","").replace(" ", "*"));
				System.out.print(String.format("%"+midlen+"s","").replace(" ", "."));
				System.out.print(String.format("%"+w+"s","").replace(" ", "*"));
				System.out.print(String.format("%"+i+"s", "").replace(" ", "."));
			}
			System.out.println();
		}
		if(h%2==0){
			int n=(h - 1) / 2;
			for (int i = 0; i <2; i++) {
				System.out.print(String.format("%"+n+"s", "").replace(" ", "."));
				System.out.print(String.format("%"+(wd-2*n)+"s", "").replace(" ", "*"));
				System.out.print(String.format("%"+n+"s", "").replace(" ", "."));
				System.out.println();
			}
		}else {
			int n=(h - 1) / 2;
			System.out.print(String.format("%"+n+"s", "").replace(" ", "."));
			System.out.print(String.format("%"+(wd-2*n)+"s", "").replace(" ", "*"));
			System.out.print(String.format("%"+n+"s", "").replace(" ", "."));
			System.out.println();
		}
	

		for (int i = ((h - 1) / 2) - 1; i >= 0; i--) {

			int midlen=wd-2*w-2*i;
			if(midlen<=0&&i==0){
				System.out.print(String.format("%"+wd+"s","").replace(" ", "*"));
			}else if (midlen<=0&&i>0) {
				System.out.print(String.format("%"+i+"s", "").replace(" ", "."));
				System.out.print(String.format("%"+(wd-2*i)+"s", "").replace(" ", "*"));
				System.out.print(String.format("%"+i+"s", "").replace(" ", "."));
			}else if(midlen>0&&i==0) {
				System.out.print(String.format("%"+w+"s","").replace(" ", "*"));
				System.out.print(String.format("%"+midlen+"s","").replace(" ", "."));
				System.out.print(String.format("%"+w+"s","").replace(" ", "*"));
			}else {
				System.out.print(String.format("%"+i+"s", "").replace(" ", "."));
				System.out.print(String.format("%"+w+"s","").replace(" ", "*"));
				System.out.print(String.format("%"+midlen+"s","").replace(" ", "."));
				System.out.print(String.format("%"+w+"s","").replace(" ", "*"));
				System.out.print(String.format("%"+i+"s", "").replace(" ", "."));
			}
			System.out.println();

			
		}

	}
}
