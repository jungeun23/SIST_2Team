import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * 일정을 출력하기 위해 달력을 보여주고, 일정에 대한 정보를 생성하는 메소드
 * 
 * @param DATA        파일이 저장된 위치를 담고있는 멤버변수
 * @param date        Calendar 자료형으로 변환하기 위한 멤버변수
 * @param year,       month, day 년 월 일을 저장하기 위한 멤버변수
 * @param lastDay     해당 월의 마지막 날을 저장하기 위한 멤버변수
 * @param day_of_week 해당 일이 몇 요일인지 저장하기 위한 멤버 변수
 * @param user        사용자를 식별하기 위한 매개변수
 * @param DATA파일을     모두 저장하기 위해 LinkedList<String[]>으로 선언한 멤버 변수
 * 
 *
 */
public class MyCalendar {
	private final String DATA;
	private final String DATA2;
	private final String DATA3;
	private final String DATA4;
	private final String DATA5;
	private Calendar date;
	private int year;
	private int month;
	private int day;
	private int lastDay = 0;
	private int day_of_week = 0;
	private User user;
	LinkedList<String[]> list = new LinkedList<>();
	LinkedList<String[]> listVacation = new LinkedList<>();
	LinkedList<String[]> listCar = new LinkedList<>();
	LinkedList<String[]> listRoom = new LinkedList<>();
	LinkedList<String[]> listTraining = new LinkedList<>();

	/**
	 * 파일의 위치, 오늘 날짜, 파일의 정보를 저장하기 위한 load메소드를 초기화 한다.
	 * 
	 * @param user 사용중인 유저를 식별하기 위한 매개변수
	 */
	public MyCalendar(User user) {
		this.user = user;
		DATA = "data/schedule/schedule.txt";
		DATA2 = "data/schedule/vacation.txt";
		DATA3 = "data/schedule/car.txt";
		DATA4 = "data/schedule/meetingRoom.txt";
		DATA5 = "data/schedule/training.txt";
		date = Calendar.getInstance();
		this.year = date.get(Calendar.YEAR);
		this.month = date.get(Calendar.MONTH) + 1;
		this.day = date.get(Calendar.DATE);
		load();
	}

	/**
	 * 해당 파일에 존재하는 정보를 list에 저장한다.
	 */
	private void load() {
		try {
			BufferedReader read = new BufferedReader(new FileReader(DATA));
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				list.add(temp);
			}
			BufferedReader readVacation = new BufferedReader(new FileReader(DATA2));
			while ((line = readVacation.readLine()) != null) {
				String[] temp = line.split(",");
				listVacation.add(temp);
			}
			BufferedReader readCar = new BufferedReader(new FileReader(DATA3));
			while ((line = readCar.readLine()) != null) {
				String[] temp = line.split(",");
				listCar.add(temp);
			}
			BufferedReader readTraining = new BufferedReader(new FileReader(DATA5));
			while ((line = readTraining.readLine()) != null) {
				String[] temp = line.split(",");
				listTraining.add(temp);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}
	
	

	/**
	 * 일정을 등록하기 위해 달력을 보여주고, 사용자로부터 날짜, 제목, 내용을 입력받아 일정을 등록한다.
	 */
	public void createSchedule() {
		System.out.println("일정을 등록합니다. 달력을 확인해주세요");
		ArrayList<int[]> t = new ArrayList<int[]>();
		int[] t2 = { this.year, this.month, this.day };
		t.add(t2);
		showCanlendar(t);
		String s = Util.get("날짜를 입력해주세요(yyyy-mm-dd): ");
		String[] temp = s.split("-");
		Calendar newTask = Calendar.getInstance();
		newTask.set(Util.toInt(temp[0]), Util.toInt(temp[1]), Util.toInt(temp[2]));
		String title = Util.get("일정 제목을 입력해주세요");
		String content = Util.get("일정의 내용을 입력해주세요");

		String ymd = newTask.get(Calendar.YEAR) + "-" + newTask.get(Calendar.MONTH) + "-"
				+ newTask.get(Calendar.DAY_OF_MONTH);
		// 홍길동,2021-5-4,t,testaaa
		String[] sl = { this.user.getName(), ymd, title, content };
		list.add(sl);

		try {
			FileWriter fw = new FileWriter(DATA, true);
			fw.write(this.user.getName() + ",");
			fw.write(ymd + ",");
			fw.write(title + ",");
			fw.write(content + "\n");
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 일정을 삭제하기 위해 모든 일정을 보여주고 선택된 일정을 삭제하는 메소드
	 * 
	 * @throws IOException
	 */
	public void deleteSchedule() throws IOException {
		ArrayList<int[]> t = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				String[] temp = list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				int[] g = { year, month, day };
				t.add(g);
			}
		}
		System.out.println("삭제할 일정을 선택해주세요");
		int[] c = showCanlendar(t);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println(list.get(i)[2] + " 일정을 삭제했습니다.");
					list.remove(i);
				}
			}

		}

		FileWriter fw = new FileWriter(DATA);
		for (int i = 0; i < list.size(); i++) {
			fw.write(String.format("%s,%s,%s,%s\n", list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3]));
		}
		fw.close();
	}

	/**
	 * 일정을 수정하기 위해 모든 일정을 보여주고 선택된 일정을 수정하는 메소드
	 */
	public void updateSchedule() throws IOException {
		// 홍길동,2021-5-4,t,testaaa
		ArrayList<int[]> t = new ArrayList<>();
		for (int i = 0; i < this.list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				String[] temp = this.list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				int[] g = { year, month, day };
				t.add(g);
			}
		}
		System.out.println("수정할 일정을 선택해주세요");
		int[] c = showCanlendar(t);
		for (int i = 0; i < list.size(); i++) {
			if (c[0] == year && c[1] == month && c[2] == day) {
				String title = Util.get("일정의 제목을 입력해주세요");
				String content = Util.get("일정의 내용을 입력해주세요");
				String[] t2 = { this.user.getName(), this.list.get(i)[1], title, content };
				this.list.set(i, t2);
				System.out.println("일정 수정이 완료됐습니다.");
			}
		}

		FileWriter fw = new FileWriter(DATA);
		for (int i = 0; i < list.size(); i++) {
			fw.write(String.format("%s,%s,%s,%s\n", list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3]));
		}
		fw.close();
	}
	
	  public void showCanlendar(int year, int month) {
	      // 마지막일?
	      while (true) {
	         lastDay = getLastDay(year, month);

	         // 해당 월의 1일의 요일?
	         day_of_week = getDayOfWeek(year, month); // 4
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
	            if (i == 9999)
//	               System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
	               System.out.printf("%3d*\t", i);
	            else
	               System.out.printf("%3d\t", i);

	            int a = i % 7;
	            int b = 7 - day_of_week;

	            b = 7 - day_of_week == 7 ? 0 : b;
	            if (i % 7 == b) {
//	         if ((day_of_week + i - 1) % 7 == 0) {
	               System.out.println();
	            }
	         }

	         System.out.println();
	         System.out.println();
	         String s = Util.get("월 이동(a or d) 일정/예약 날짜 입력(q)");
	         if (s.equals("q"))
	            break;
//	         KeyEvent event = new KeyEvent();
//	         if(Event.getKeycode() == KeyEvent.VK_LEFT)
	         if (s.equals("a")) {
	            if (month - 1 != 0)
	               month--;
	            else {
	               month = 12;
	               year--;
	            }
	         }
	         if (s.equals("d")) {
	            if (month + 1 != 12)
	               month++;
	            else {
	               month = 1;
	               year++;
	            }
	         }

	      }
	   }
	   

	/**
	 * 일정이 등록된 달력을 출력하기 위한 메소드 사용자로부터 등록된 일정 날짜 뒤에 *을 붙여 일정을 표시한다.
	 * 
	 * @param t year-month-day를 int[]로 입력받아 ArrayList에 추가된 t값을 가지고 모든 일정을 출력한다.
	 * @return 사용자로부터 입력받은 날짜(year, month, day)를 돌려주기 위해 int[] 자료형으로 반환한다.
	 */
	public int[] showCanlendar(ArrayList<int[]> t) {
//	      System.out.println(Arrays.toString(t.get(0)));
//	      System.out.println(this.year);
//	      System.out.println(this.month);
//	      System.out.println(this.day);
		// 마지막일?
		while (true) {
			lastDay = getLastDay(year, month);
			// 해당 월의 1일의 요일?
			day_of_week = getDayOfWeek(year, month); // 4
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
			boolean isStar = false;
			for (int i = 1; i <= lastDay; i++) {
				for (int j = 0; j < t.size(); j++) {
					if (t.get(j)[0] == this.year && t.get(j)[1] == this.month && t.get(j)[2] == i) {
						System.out.printf("%3d*\t", i);
						isStar = true;
						break;
					} else {
						isStar = false;
					}
				}
				if (!isStar) {
					System.out.printf("%3d\t", i);
				}
				int a = i % 7;
				int b = 7 - day_of_week;
				b = 7 - day_of_week == 7 ? 0 : b;
				if (i % 7 == b) {
					System.out.println();
				}
			}
			System.out.println();
			System.out.println();
			String s = Util.get("월 이동(a or d) 끝내기(q) 날짜선택(요일입력)");
			if (s.equals("q")) {
				int[] t2 = { year, month, day };
				return t2;
			} else if (s.equals("a")) {
				if (month - 1 != 0)
					month--;
				else {
					month = 12;
					year--;
				}
			} else if (s.equals("d")) {
				if (month + 1 != 12)
					month++;
				else {
					month = 1;
					year++;
				}
			} else {
				int[] t2 = { year, month, Util.toInt(s) };
				return t2;
			}
		}
	}// showCalendar

	/**
	 * 요일을 반환하기 위한 메소드
	 * 
	 * @param year  년도를 입력받는다.
	 * @param month 월을 입력받는다.
	 * @return 해당 년, 월의 날짜를 1/1/1부터 모두 더한 뒤 7로 나눈 값이 요일이 된다.
	 */
	public static int getDayOfWeek(int year, int month) {

		int totalDays = 0;

		for (int i = 1; i < year; i++) {

			if (isLeafYear(i)) {
				totalDays += 366;
			} else {
				totalDays += 365;
			}

		}

		for (int i = 1; i < month; i++) {
			totalDays += getLastDay(year, i);
		}
		totalDays++;
		return totalDays % 7;
	}

	/**
	 * 윤년인지 아닌지에 따라 해당 월의 마지막 날을 반환한다.
	 * 
	 * @param year  년도
	 * @param month 월
	 * @return 해당 년도와 월의 마지막 날을 반환한다.
	 */
	public static int getLastDay(int year, int month) {

		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			return isLeafYear(year) ? 29 : 28;
		}
		return 0;
	}

	/**
	 * 해당 년도가 윤년인지 판단하는 메소드
	 * 
	 * @param year 년도을 입력으로 받는다.
	 * @return 해당년도가 윤년인지 아닌지 boolean값으로 return한다.
	 */
	public static boolean isLeafYear(int year) {

		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			return false;
		}

	}


	
}
