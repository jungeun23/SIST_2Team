package Calendar;
import ASAP.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;

import ASAP.Util;


public class TrainingCenter {

	private final String DATA5;
	public static Scanner scan;
	private Calendar date;
	private int year;
	private int month;
	private int day;
	private int lastDay = 0;
	private int day_of_week = 0;
	private User user;
	LinkedList<String[]> listTraining = new LinkedList<>();
	
	public TrainingCenter(User user) {
		this.user = user;
		DATA5 = "data/schedule/training.txt";
		date = Calendar.getInstance();
		this.year = date.get(Calendar.YEAR);
		this.month = date.get(Calendar.MONTH) + 1;
		this.day = date.get(Calendar.DATE);
		load();
		scan = new Scanner(System.in);
	}

	private void load() {
		try {
			BufferedReader readTraining = new BufferedReader(new FileReader(DATA5));
			String line = "";
			while ((line = readTraining.readLine()) != null) {
				String[] temp = line.split(",");
				listTraining.add(temp);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	/**
	 * 달력 나타내주는 메소드 
	 * @param t
	 * @return
	 */

	public int[] showCanlendar(ArrayList<int[]> t) {
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
//	               if ((day_of_week + i - 1) % 7 == 0) {
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
	}//showCalendar

	/**
	 * year와 month 값을 받아 해당 달력을 나타내주는 메소드 
	 * @param year
	 * @param month
	 */
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
					System.out.printf("%3d*\t", i);
				else
					System.out.printf("%3d\t", i);

				int a = i % 7;
				int b = 7 - day_of_week;

				b = 7 - day_of_week == 7 ? 0 : b;
				if (i % 7 == b) {
					System.out.println();
				}
			}

			System.out.println();
			System.out.println();
			String s = Util.get("월 이동(a or d) 끝내기(q)");
			if (s.equals("q"))
				break;
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
	/**
	 * 월별 마지막 날짜 구해주는 메소드 
	 * @param year
	 * @param month
	 * @return
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
	/**
	 * 윤년 구분 메소드 
	 * @param year
	 * @return
	 */
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



	/**
	 * 교육센터 일정 예약 메소드
	 * 
	 * @param String title 교육 일정 제목 입력 
	 * @param String content 교육 일정 내용 입력 
	 * @param String s 원하는 교육 날짜 입력 
	 * @param Calendar newTask 입력된 교육 날짜를 Calendar 형식으로 변환 
	 */
	public void createTraining() {
		System.out.println("교육센터 일정을 등록합니다. 달력을 확인해주세요");
		showCanlendar(this.year, this.month);
		String s = Util.get("날짜를 입력해주세요(yyyy-mm-dd): ");
		String[] temp = s.split("-");
		Calendar newTask = Calendar.getInstance();
		newTask.set(Util.toInt(temp[0]), Util.toInt(temp[1]), Util.toInt(temp[2]));
		String title = Util.get("교육 일정의 제목을 입력해주세요");
		String content = Util.get("교육 일정의 내용을 입력해주세요");
		String ymd = newTask.get(Calendar.YEAR) + "-" + newTask.get(Calendar.MONTH) + "-"
				+ newTask.get(Calendar.DAY_OF_MONTH);
		String position = null;
		String depart = null;

		try {
			BufferedReader read = new BufferedReader(new FileReader("data/HR.txt"));

			String line = "";
			while ((line = read.readLine()) != null) {
				String[] t = line.split(",");
				if (t[0].equals(this.user.getName())) {
					position = t[1];
					depart = t[2];
					String[] sl = { this.user.getName(), position, depart, ymd, title, content };
					listTraining.add(sl);
				}
			}

			FileWriter fw = new FileWriter(DATA5, true);
			fw.write(this.user.getName() + ",");
			fw.write(position + ",");
			fw.write(depart + ",");
			fw.write(ymd + ",");
			fw.write(title + ",");
			fw.write(content + "\n");
			fw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("교육 일정 등록이 완료됐습니다.");
		pause();
	}// createTraining

	/**
	 * 예약된 교육 일정을 보여주는 메소드
	 * @author 2조
	 * @param ArrayList<int[]> t 날짜 형식을 int배열로 바꾼 값을 저장해주는 ArrayList
	 * @param int year 입력된 날짜 형식의 값중 year의 String 값을 int로 변환
	 * @param int month 입력된 날짜 형식의 값중 month의 String 값을 int로 변환
	 * @param int day 입력된 날짜 형식의 값중 day의 String 값을 int로 변환
	 */
	public void readTrainingSchedule() {
		ArrayList<int[]> t = new ArrayList<int[]>();
		for (int i = 0; i < listTraining.size(); i++) {
			String[] temp = listTraining.get(i)[3].split("-");
			int year = Util.toInt(temp[0]);
			int month = Util.toInt(temp[1]);
			int day = Util.toInt(temp[2]);
			int[] g = { year, month , day };
			t.add(g);
		}
			System.out.println("조회할 일정을 선택해주세요");
			int[] c = showCanlendar(t);
			for (int i = 0; i < listTraining.size(); i++) {
				String[] temp = listTraining.get(i)[3].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println("일정을 출력합니다");
					System.out.println("제목 : " + listTraining.get(i)[4]);
					System.out.println("내용 : " + listTraining.get(i)[5]);
				}
			}
		System.out.println();
		System.out.println("남은 일정이 없습니다.");
		pause();
	}// showTrainingSchedule

	/**
	 * 교육 일정 수정 메소드
	 * @author 2조
	 * @param String title 수정할 교육 일정 제목 입력 
	 * @param String content 수정할 교육 일정 내용 입력 
 	 * @param ArrayList<int[]> t2 날짜 형식을 int배열로 바꾼 값을 저장해주는 ArrayList
 	 * @param int year 입력된 날짜 형식의 값중 year의 String 값을 int로 변환
	 * @param int month 입력된 날짜 형식의 값중 month의 String 값을 int로 변환
	 * @param int day 입력된 날짜 형식의 값중 day의 String 값을 int로 변환
	 */
	public void updateTrainingSchedule() {
		ArrayList<int[]> t2 = new ArrayList<>();
		for (int i = 0; i < this.listTraining.size(); i++) {
			
			if (listTraining.get(i)[0].equals(this.user.getName())) {
				String[] temp = this.listTraining.get(i)[3].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				int[] g = { year, month, day };
				t2.add(g);
				}
			}
			System.out.println("수정할 일정을 선택해주세요");
			int[] c = showCanlendar(t2);
			for (int i = 0; i < listTraining.size(); i++) {
				if (listTraining.get(i)[0].equals(this.user.getName())) {
					String[] temp = listTraining.get(i)[3].split("-");
					int year = Util.toInt(temp[0]);
					int month = Util.toInt(temp[1]);
					int day = Util.toInt(temp[2]);
					if (c[0] == year && c[1] == month && c[2] == day) {
						String title = Util.get("일정의 제목을 입력해주세요");
						String content = Util.get("일정의 내용을 입력해주세요");
						String[] t = { this.user.getName(), this.listTraining.get(i)[1], this.listTraining.get(i)[2],
								this.listTraining.get(i)[3], title, content };
						this.listTraining.set(i, t);
						System.out.println("일정 수정이 완료됐습니다.");
					}
				}
			} // for
			try {
			FileWriter fw = new FileWriter(DATA5);
			for (int i = 0; i < listTraining.size(); i++) {
				
				fw.write(listTraining.get(i)[0] + ",");
				fw.write(listTraining.get(i)[1] + ",");
				fw.write(listTraining.get(i)[2] + ",");
				fw.write(listTraining.get(i)[3] + ",");
				fw.write(listTraining.get(i)[4] + ",");
				fw.write(listTraining.get(i)[5] + "\n");
			}
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
			pause();

	}// updateTrainingSchedule

	/**
	 * 교육 일정 삭제 메소드
	 * @author 2조 
 	 * @param int year 입력된 날짜 형식의 값중 year의 String 값을 int로 변환
	 * @param int month 입력된 날짜 형식의 값중 month의 String 값을 int로 변환
	 * @param int day 입력된 날짜 형식의 값중 day의 String 값을 int로 변환
	 * 
	 */
	public void deleteTrainingSchedule() {
		ArrayList<int[]> t = new ArrayList<int[]>();
		for (int i = 0; i < listTraining.size(); i++) {
			if(listTraining.get(i)[0].equals(this.user.getName())) {
			String[] temp = listTraining.get(i)[3].split("-");
			int year = Util.toInt(temp[0]);
			int month = Util.toInt(temp[1]);
			int day = Util.toInt(temp[2]);
			int[] g = { year, month, day };
			t.add(g);
			}
		}
		
		System.out.println("삭제할 일정을 선택해주세요");
		int[] c = showCanlendar(t);
		for (int i = 0; i < listTraining.size(); i++) {
			if (listTraining.get(i)[0].equals(this.user.getName())) {

				String[] temp = listTraining.get(i)[3].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);

				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println("'" + listTraining.get(i)[4] + "' 일정을 삭제했습니다.");
					listTraining.remove(i);
					
				}
			}
		} // for

		try {
			FileWriter fw = new FileWriter(DATA5);
			for (int i = 0; i < listTraining.size(); i++) {

				fw.write(listTraining.get(i)[0] + ",");
				fw.write(listTraining.get(i)[1] + ",");
				fw.write(listTraining.get(i)[2] + ",");
				fw.write(listTraining.get(i)[3] + ",");
				fw.write(listTraining.get(i)[4] + ",");
				fw.write(listTraining.get(i)[5] + "\n");
			}
			fw.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		pause();
	}// deleteTrainingSchedule
	
	/**
	 * 교육일정 초기 항목 화면 
	 */
	public void trainingScreen() {
		while(true) {
			System.out.println("                       ▣ 교육 일정 항목 ▣");
			System.out.println("==============================================================================");
			System.out.println("||1.	교육	||2.	교육	||3.	교육	||4.	교육	||5.	뒤로||");
			System.out.println("||  	일정	||  	일정	||  	일정	||  	일정	||  	가기||");
			System.out.println("||  	등록	||  	조회	||  	수정	||  	삭제	||         ||");
			System.out.println("==============================================================================");
			System.out.print(" 카테고리(번호)를 선택하세요: ");
			int num = scan.nextInt();

			if (num == 1) {
				createTraining();

			} else if (num == 2) {
				readTrainingSchedule();
			} else if (num == 3) {
				updateTrainingSchedule();

			} else if (num == 4) {
				deleteTrainingSchedule();

			} else if (num == 5) {

				try {
					Main.menu();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

				System.out.println("카테고리(번호)를 다시 입력하세요.");
			}
		}//while 
	}
	public static void pause() {
		System.out.println();
		System.out.println("엔터를 누르시면 항목으로 돌아갑니다.");
		scan.nextLine();// Block
		scan.nextLine();// Block
		cls();

	}
	
	/**
	 * 100줄을 띄워 새로운 창이 뜨는 효과를 나타내주는 메소드 
	 */
	private static void cls() {

		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}// cls()
	

}
