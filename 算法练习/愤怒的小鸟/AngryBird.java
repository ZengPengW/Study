public class AngryBird{

	public static void main(String[] args) {

		double jl = 1000;
		double a = 10;
		double b = 10;
		double xn = 50;
		int index = 0;
		int flag = 0;
		while (jl > 1) {

			jl = jl - (jl / (xn + b) * a) * 2;
			if (jl >= 1 && flag == 0) {
				index++;
				flag = 1;
			} else if (jl >= 1 && flag == 1) {
				flag = 0;
			}

		}
		System.out.println(index);

	}

}