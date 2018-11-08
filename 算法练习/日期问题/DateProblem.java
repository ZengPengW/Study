import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TreeSet;

public class DateProblem {
	private static int[] date = new int[3];
	private static String datestr[] = new String[3];

	// 采用年/月/日的，有采用月/日/年的，还有采用日/月/年的
	
	// 1960年1月1日至2059年12月31日
	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		datestr = sc.nextLine().trim().split("/");
		for (int i = 0; i < date.length; i++) {
			date[i] = Integer.parseInt(datestr[i]);
		}
		f();
	}

	public static void f() throws ParseException {
		TreeSet<String> treeSet = new TreeSet<String>();
		for (int i = 0; i < 3; i++) {
			if (i == 1)
				i++;
			for (int j = 0; j < 2; j++) {
				if (j != i) {
					for (int k = 0; k < 3; k++) {
						if (k != i && k != j) {
							if (date[i] >= 60 && date[i] <= 99 && date[j] >= 1
									&& date[j] <= 12 && date[k] >= 1
									&& date[k] <= 31) {
								String format = "19" + datestr[i] + "-"
										+ datestr[j] + "-" + datestr[k];
								if (dateformat(format).equals(format)) {
									treeSet.add(format);
								}

							} else if (date[i] >= 00 && date[i] <= 59
									&& date[j] >= 1 && date[j] <= 12
									&& date[k] >= 1 && date[k] <= 31) {
								String format = "20" + datestr[i] + "-"
										+ datestr[j] + "-" + datestr[k];
								if (dateformat(format).equals(format)) {
									treeSet.add(format);
								}
							}
						}
					}
				}
			}
		}
		for (String sg : treeSet) {
			System.out.println(sg);
		}
	}

	public static String dateformat(String dat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long time = sdf.parse(dat).getTime();
		return sdf.format(time);
	}

}
