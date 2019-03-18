import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class FlightTime {
	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		String[][] time = new String[n][4];
		String s;
		for (int i = 0; i < n; i++) {
			s = sc.nextLine();
			time[i][0] = s.substring(0, 8);
			time[i][1] = s.substring(9);

			s = sc.nextLine();
			time[i][2] = s.substring(0, 8);
			time[i][3] = s.substring(9);
			System.out.println(getTime(time[i][0], time[i][1], time[i][2], time[i][3]));

		}

	}

	// 推导公式
	// (e1+e2-s1-s2)/(2)=time /s

	public static String getTime(String s1, String e1, String s2, String e2)
			throws ParseException {
		int time1, time2, time3, time4;

		time1 = Integer.parseInt(s1.substring(0, 2)) * 3600
				+ Integer.parseInt(s1.substring(3, 5)) * 60
				+ Integer.parseInt(s1.substring(6, 8));
		time2 = Integer.parseInt(e1.substring(0, 2)) * 3600
				+ Integer.parseInt(e1.substring(3, 5)) * 60
				+ Integer.parseInt(e1.substring(6, 8));
		if (e1.contains("+1"))
			time2 += (24 * 3600);
		else if (e1.contains("+2"))
			time2 += (24 * 3600 * 2);

		time3 = Integer.parseInt(s2.substring(0, 2)) * 3600
				+ Integer.parseInt(s2.substring(3, 5)) * 60
				+ Integer.parseInt(s2.substring(6, 8));
		time4 = Integer.parseInt(e2.substring(0, 2)) * 3600
				+ Integer.parseInt(e2.substring(3, 5)) * 60
				+ Integer.parseInt(e2.substring(6, 8));
		if (e2.contains("+1"))
			time4 += (24 * 3600);
		else if (e2.contains("+2"))
			time4 += (24 * 3600 * 2);

		long t = (time4 + time2 - time1 - time3) / (2);

		long hh, mm, ss;
		hh = t / 3600;
		t %= 3600;
		mm = t / 60;
		t %= 60;
		ss = t;

		String h, m, s;
		h = String.valueOf(hh).length() < 2 ? String.valueOf("0" + hh) : String
				.valueOf(hh);
		m = String.valueOf(mm).length() < 2 ? String.valueOf("0" + mm) : String
				.valueOf(mm);
		s = String.valueOf(ss).length() < 2 ? String.valueOf("0" + ss) : String
				.valueOf(ss);
		
		return h + ":" + m + ":" + s;

	}
}
