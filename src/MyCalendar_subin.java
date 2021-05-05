import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class MyCalendar_subin {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	private final String DATA;
	private final String DATA2;
	private final String DATA3;
	private final String DATA4;
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

	public MyCalendar_subin(User user) {
		this.user = user;
		DATA = "data\\schedule\\schedule.txt";
		DATA2 = "data\\schedule\\vacation.txt";
		DATA3 = "data\\schedule\\car.txt";
		DATA4 = "data\\schedule\\meetingRoom.txt";
		date = Calendar.getInstance();
		this.year = date.get(Calendar.YEAR);
		this.month = date.get(Calendar.MONTH) + 1;
		this.day = date.get(Calendar.DATE);
		load();
	}

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
			BufferedReader readMeetingRoom = new BufferedReader(new FileReader(DATA4));
			while((line = readMeetingRoom.readLine()) != null) {
				String[] temp = line.split(",");
				listRoom.add(temp);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void createSchedule() {
		System.out.println("일정을 등록합니다. 달력을 확인해주세요");
		showCanlendar(this.year, this.month);
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

	public void showSchedule() {
		// 홍길동,2021-5-4,t,testaaa
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				String[] temp = list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				System.out.println("일정을 선택해주세요");
				int[] c = showCanlendar(year, month, day);
				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println("일정을 출력합니다");
					System.out.println("제목 : " + list.get(i)[2]);
					System.out.println("내용 : " + list.get(i)[3]);
				}
			}
		}
		System.out.println("남은 일정이 없습니다.");

	}

	public void deleteSchedule() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				String[] temp = list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				System.out.println("삭제할 일정을 선택해주세요");
				int[] c = showCanlendar(year, month, day);
				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println(list.get(i)[2] + " 일정을 삭제했습니다.");
					list.remove(i);
				}
			}
		}
		
	}

	public void updateSchedule() {
		// 홍길동,2021-5-4,t,testaaa
		for (int i = 0; i < this.list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				String[] temp = this.list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				System.out.println("수정할 일정을 선택해주세요");
				int[] c = showCanlendar(year, month, day);
				if (c[0] == year && c[1] == month && c[2] == day) {
					String title = Util.get("일정의 제목을 입력해주세요");
					String content = Util.get("일정의 내용을 입력해주세요");
					String[] t = { this.user.getName(), this.list.get(i)[1], title, content };
					this.list.set(i, t);
					System.out.println("일정 수정이 완료됐습니다.");
				}
			}
		}
	}

	public int[] showCanlendar(int year, int month, int day) {
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
				if (i == day && this.month == month && this.year == year)
//							System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
					System.out.printf("%3d*\t", i);
				else
					System.out.printf("%3d\t", i);

				int a = i % 7;
				int b = 7 - day_of_week;

				b = 7 - day_of_week == 7 ? 0 : b;
				if (i % 7 == b) {
//					if ((day_of_week + i - 1) % 7 == 0) {
					System.out.println();
				}
			}

			System.out.println();
			System.out.println();
			String s = Util.get("월 이동(a or d) 끝내기(q) 날짜선택(요일입력)");
			if (s.equals("q"))
				break;
//					KeyEvent event = new KeyEvent();
//					if(Event.getKeycode() == KeyEvent.VK_LEFT)
			else if (s.equals("a")) {
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
				int[] t = { year, month, Util.toInt(s) };
				return t;
			}

		}
		return null;

	}

//	public void output() {
//
//		this.date = Calendar.getInstance();
//		year = date.get(Calendar.YEAR);
//		month = date.get(Calendar.MONTH);
//		month++;
//
//		// System.out.println(day_of_week);
//
//		showCanlendar(year, month);
//
//	}// output

	private void showCanlendar(int year, int month) {
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
//					System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
					System.out.printf("%3d*\t", i);
				else
					System.out.printf("%3d\t", i);

				int a = i % 7;
				int b = 7 - day_of_week;

				b = 7 - day_of_week == 7 ? 0 : b;
				if (i % 7 == b) {
//			if ((day_of_week + i - 1) % 7 == 0) {
					System.out.println();
				}
			}

			System.out.println();
			System.out.println();
			String s = Util.get("월 이동(a or d) 끝내기(q)");
			if (s.equals("q"))
				break;
//			KeyEvent event = new KeyEvent();
//			if(Event.getKeycode() == KeyEvent.VK_LEFT)
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

	public void createScheduleVacation() {
		System.out.println("휴가를 등록합니다. 달력을 확인해주세요");
		showCanlendar(this.year, this.month);
		String s = Util.get("날짜를 입력해주세요(yyyy-mm-dd): ");
		String[] temp = s.split("-");
		Calendar newTask = Calendar.getInstance();
		newTask.set(Util.toInt(temp[0]), Util.toInt(temp[1]), Util.toInt(temp[2]));
		String title = Util.get("일정 제목을 입력해주세요");
		String content = Util.get("일정의 내용을 입력해주세요");
		String ymd = newTask.get(Calendar.YEAR) + "-" + newTask.get(Calendar.MONTH) + "-"
				+ newTask.get(Calendar.DAY_OF_MONTH);
		// 홍길동,2021-5-4,t,testaaa
		String position = null;
		String depart = null;
		String randNum = null;

		try {
			BufferedReader read = new BufferedReader(new FileReader("data\\HR.txt"));
			long seed = System.currentTimeMillis();
			Random rand = new Random(seed);
			rand.setSeed(seed);
			randNum = Integer.toString(rand.nextInt(15) + 1);

			String line = "";
			while ((line = read.readLine()) != null) {
				String[] t = line.split(",");
				if (t[0].equals(this.user.getName())) {
					position = t[1];
					depart = t[2];
					String[] sl = { this.user.getName(), position, depart, randNum, ymd, title, content };
					listVacation.add(sl);
				}
			}

			FileWriter fw = new FileWriter(DATA2, true);
			fw.write(this.user.getName() + ",");
			fw.write(position + ",");
			fw.write(depart + ",");
			fw.write(randNum + ",");
			fw.write(ymd + ",");
			fw.write(title + ",");
			fw.write(content + "\n");
			fw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("휴가 일정 등록이 완료됐습니다.");
	}

	public void createCopCarReseravtion(ArrayList<String[]> carList) {
		System.out.println("법인차량 예약을 등록합니다. 달력을 확인해주세요");
		showCanlendar(this.year, this.month);
		String s = Util.get("날짜를 입력해주세요(yyyy-mm-dd): ");
		String[] temp = s.split("-");
		Calendar newTask = Calendar.getInstance();
		newTask.set(Util.toInt(temp[0]), Util.toInt(temp[1]), Util.toInt(temp[2]));
		String title = Util.get("일정 제목을 입력해주세요");
		String content = Util.get("일정의 내용을 입력해주세요");
		String ymd = newTask.get(Calendar.YEAR) + "-" + newTask.get(Calendar.MONTH) + "-"
				+ newTask.get(Calendar.DAY_OF_MONTH);

		System.out.println("법인 차량 목록입니다.");
		for (int i = 0; i < carList.size(); i++) {
			System.out.printf("[%d] : %s\n", i + 1, carList.get(i)[0]);
		}
//		for (String ss : carList.keySet()) {
//			System.out.printf("[%d] : %s\n", cnt++ , ss);
//		}
//		String selecetedCar = Util.get("차량이름을 입력하세요");
		int selectedCar = Util.toInt(Util.get("번호를 입력해주세요"));
		selectedCar--;
		if (Util.toInt(carList.get(selectedCar)[1]) == 0) {
			System.out.println(selectedCar + " 차량의 재고가 존재하지 않습니다.");
		} else {
			int t = Util.toInt(carList.get(selectedCar)[1]);
			carList.get(selectedCar)[1] = Integer.toString(t - 1);
		}
		// 홍길동,2021-5-4,t,testaaa
		String position = null;
		String depart = null;
		String randNum = null;

		try {
			BufferedReader read = new BufferedReader(new FileReader("data\\HR.txt"));
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] t = line.split(",");
				if (t[0].equals(this.user.getName())) {
					position = t[1];
					depart = t[2];
					String[] sl = { this.user.getName(), position, depart, carList.get(selectedCar)[0],
							(carList.get(selectedCar)[1]), ymd, title, content };
					listCar.add(sl);
				}
			}

			FileWriter fw = new FileWriter(DATA3, true);
			fw.write(this.user.getName() + ",");
			fw.write(position + ",");
			fw.write(depart + ",");
			fw.write(carList.get(selectedCar)[0] + ",");
			fw.write(carList.get(selectedCar)[1] + ",");
			fw.write(ymd + ",");
			fw.write(title + ",");
			fw.write(content + "\n");
			fw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("차량 예약 등록이 완료됐습니다.");
	}

	public void createRoomReservation(String[] roomNumber) {
		System.out.println("회의실 예약을 등록합니다. 달력을 확인해주세요");
		showCanlendar(this.year, this.month);
		String s = Util.get("날짜를 입력해주세요(yyyy-mm-dd): ");
		String[] temp = s.split("-");
		Calendar newTask = Calendar.getInstance();
		newTask.set(Util.toInt(temp[0]), Util.toInt(temp[1]), Util.toInt(temp[2]));
		String content = Util.get("예약 목적을 입력해주세요");
		String ymd = newTask.get(Calendar.YEAR) + "-" + newTask.get(Calendar.MONTH) + "-"
				+ newTask.get(Calendar.DAY_OF_MONTH);
		// 홍길동,2021-5-4,t,testaaa
		for (int i = 0; i < roomNumber.length; i++) {
			System.out.printf("[%d] %s\n", i + 1, roomNumber[i]);
		}
		int choiceRoom = Util.toInt(Util.get("방 번호를 선택해주세요"));
		String selectedRoom = roomNumber[choiceRoom-1];
		String position = null;
		String depart = null;
		String randNum = null;
		
		try {
			BufferedReader readRoom = new BufferedReader(new FileReader(DATA4));
			String line ="";
			while((line = readRoom.readLine())!=null) {
				String[] t = line.split(","); 
				if(selectedRoom == t[3] && ymd == t[4]){
					Util.puase("이미 예약된 방과 같은 날짜 입니다. 다시 선택해주세요");
					return;
				}
			}
			BufferedReader read = new BufferedReader(new FileReader("data\\HR.txt"));
//			long seed = System.currentTimeMillis();
//			Random rand = new Random(seed);
//			rand.setSeed(seed);
//			randNum = Integer.toString(rand.nextInt(15) + 1);

			while ((line = read.readLine()) != null) {
				String[] t = line.split(",");
				if (t[0].equals(this.user.getName())) {
					position = t[1];
					depart = t[2];
					String[] sl = { this.user.getName(), position, depart, selectedRoom, ymd, content };
					listRoom.add(sl);
				}
			}

			FileWriter fw = new FileWriter(DATA4, true);
			fw.write(this.user.getName() + ",");
			fw.write(position + ",");
			fw.write(depart + ",");
			fw.write(selectedRoom + ",");
			fw.write(ymd + ",");
			fw.write(content + "\n");
			fw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("회의실 예약이 완료됐습니다.");

	}
}
