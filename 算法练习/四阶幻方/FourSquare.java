
public class FourSquare {
	public static void main(String[] args) {
		int[] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		f(num, 1, 0);
		System.out.println(count);
	}

	private static int count = 0;

	/*
	 * �÷���ʽ
	 * ��1�ӵ�n ���� ���� �õ���ֵ���� �÷� �� �� б ��ֵ��
	 * ��1+....+16��/4=34
	 */
	public static void f(int[] num, int index, int v) {
		 if (index % 4 == 0) {
			if (num[index - 1] + num[index - 2] + num[index - 3] + num[index - 4] != 34) {
				return;
			}
		}
		if (index == 16) {
			if (num[0] + num[4] + num[8] + num[12] != 34)
				return;
			if (num[1] + num[5] + num[9] + num[13] != 34)
				return;
			if (num[2] + num[6] + num[10] + num[14] != 34)
				return;
			if (num[3] + num[7] + num[11] + num[15] != 34)
				return;
			if (num[0] + num[5] + num[10] + num[15] != 34)
				return;
			if (num[3] + num[6] + num[9] + num[12] != 34)
				return;
			// System.out.println(count);
			count++;
			return;
		}
		for (int i = index; i < num.length; i++) {
			int temp = num[index];
			num[index] = num[i];
			num[i] = temp;
			f(num, index + 1, v);
			temp = num[index];
			num[index] = num[i];
			num[i] = temp;
		}
	}
}
