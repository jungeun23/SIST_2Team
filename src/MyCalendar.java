import java.util.Calendar;

public class MyCalendar {
	private Calendar date;
	private int year;
	private int month;
	private int lastDay = 0; 
	private int day_of_week = 0; 
	
	public void output() {

		this.date = Calendar.getInstance();
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH);
		month++;
		
		// 마지막일?
		lastDay = getLastDay(year, month);

		// 해당 월의 1일의 요일?
		day_of_week = getDayOfWeek(year, month); // 4

		// System.out.println(day_of_week);

		// 달력 출력하기
		System.out.println();
		System.out.println("===================================================");
		System.out.printf("                     %d년 %d월\n", year, month);
		System.out.println("===================================================");
		System.out.println("[일]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]");

		// 1일의 요일을 맞추기 위해서..
		for (int i = 0; i < day_of_week; i++) {
			System.out.print("\t");
		}

		// 날짜 출력
		for (int i = 1; i <= lastDay; i++) {
			System.out.printf("%3d\t", i);
			int a = i % 7;
			int b = 7 - day_of_week;

			b = 7 - day_of_week == 7 ? 0 : b;
			if ( i % 7 == b) {
//			if ((day_of_week + i - 1) % 7 == 0) {
				System.out.println();
			}
		}

		System.out.println();
		System.out.println();

	}// output

	public static int getDayOfWeek(int year, int month) {

		int totalDays = 0;

		for (int i = 1; i < year; i++) { // 전년도까지

			if (isLeafYear(i)) {
				totalDays += 366;
			} else {
				totalDays += 365;
			}

		}

		// 2021.1.1 ~ 2021.3.31
		for (int i = 1; i < month; i++) { // 전월까지
			totalDays += getLastDay(year, i);
		}
		// 2021.4.1 ~ 2021.4.1
		totalDays++;
		return totalDays % 7;
	}
	public static int getLastDay(int year, int month) {

		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31; // return문: 메소드를 종료하는 역할(break 유사) + 값 반환
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			return isLeafYear(year) ? 29 : 28;
		}
		return 0;
	}// getLastDay
	
	public static boolean isLeafYear(int year) {

		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true; // 윤년
				} else {
					return false; // 평년
				}
			} else {
				return true; // 윤년
			}
		} else {
			return false; // 평년
		}

	}
}
