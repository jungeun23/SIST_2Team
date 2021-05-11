package HR;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import HSJ.User;



public class Attendance {
	private User user;
	private String DATA;
	private String attendancePath;
	private String memberPath;

	private ArrayList<Calendar> chulgeun; // 출근 시간
	private ArrayList<Calendar> toegeun; // 퇴근 시간
	private ArrayList<String> memberName;
	private int lastDay = 0;
	private int day_of_week = 0;
	private static Calendar c;
	private static Scanner scan;

	public Attendance(User user) {
		DATA = "data/Contact.txt";
		attendancePath = "data/attendance/memberWorkingTimeDummy.txt";
		memberPath = "data/attendance/memberTime.txt";
		chulgeun = new ArrayList<Calendar>();
		toegeun = new ArrayList<Calendar>();
		memberName = new ArrayList<String>();
		scan = new Scanner(System.in);
		c = Calendar.getInstance();
		this.user = user;

	}

	/*
	 * 근태관리 항목 나타내주는 메뉴
	 */
	private static String menu() {

		System.out.println("                       ▣ 근태 관리 항목 ▣");
		System.out.println("==============================================================================");
		System.out.println("||  1. 출근  ||  2. 퇴근  ||  3. 월간 근무 ||  4. 월간 근무시간  || 5. 뒤로 ||" );
		System.out.println("||     등록  ||     등록  ||     시간 조회 ||     조회 관리자용  ||    가기 || ");
		System.out.println("==============================================================================");
		System.out.print(" 카테고리(번호)를 선택하세요: ");

		String sel = scan.nextLine();

		return sel;

	}

	/**
	 * 근태 관리 항목 선택시 해당 메소드로 연결 해주는 메소드
	 * 
	 * @throws IOException
	 */
	public void attendanceScreen() {

		boolean loop = true;

		String sel = menu();

		if (sel.equals("1")) {
			inTime();
		} else if (sel.equals("2")) {
			outTime();
		} else if (sel.equals("3")) {
			searchWorkingTime();
		} else if (sel.equals("4")) {
			if (this.user.getName().equals("홍길동")) { // t,t 를 임의의 관리자로 지정
				adminSearchWorkingTime();

			} else {
				System.out.println("관리자만 접근 가능합니다.");
				pause();
				attendanceScreen();
			}
		} else if(sel.equals("5")) {
			//Main.menu();
			
		} else {
			System.out.println("번호를 다시 입력해주세요.");
			loop = true;
			
		}

	}

	/**
	 * 원하는 년,월을 입력하여 해당 근무 시간을 조회하는 메소드 로그인한 본인의 근무시간 조회
	 */
	public void searchWorkingTime() {

		try {
			BufferedReader read = new BufferedReader(new FileReader(memberPath));
			System.out.print("근무 시간을 확인하고 싶은 년도와 월을 입력해주세요.(YYYY-M): ");
			String yearMonth = scan.nextLine();

			String[] temp1 = yearMonth.split("-"); // 입력받은 2021-5 쪼개기

//			ArrayList<Integer> list = new ArrayList<Integer>();

			String line = "";
			// String date = "2021-05-02";
			String str = "";
			int sum = 0;
			int workingMin = 0;
			ArrayList<String> list = new ArrayList<String>();

			while ((line = read.readLine()) != null) {
				// System.out.println(line);
				String temp[] = line.split(",");
				list.add(temp[0]);
				workingMin = Integer.parseInt(temp[2]);
//				System.out.println(workingMin);

				if (temp[0].equals(this.user.getName())) {
					String workingDay = temp[1];
					String[] yearMonthDay = temp[1].split("-"); // 2021-5-3 쪼개기
					if (yearMonthDay[0].equals(temp1[0]) && yearMonthDay[1].equals(temp1[1])) {

						sum += workingMin;
					}
				}
			} // while
			System.out.println();
			System.out.println();
			System.out.println("        ▣ 월간 근무 시간 조회(개인) ▣");
			System.out.println("====================================");
			System.out.println();
			System.out.printf("%s님의 %s년 %s월 근무시간은 %d시간입니다.\n ", this.user.getName(), temp1[0], temp1[1], sum / 60);
			System.out.println();
			System.out.println("====================================");

			pause();
			attendanceScreen();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// searchWorkingTime

	/**
	 * contact.txt 명단에 있는 인원들마다 각각 근무시간을 생성하기 위한 더미 메소드
	 * @param String path 생성된 더미를 저장할 경로 
	 */
	public void dummy() {
		// ialbutt0,qdf5bG,주반택,nbresland0@sist2.co.kr,010-9965-6848,과장,인사
		String path = "data/Contact.txt";
		String name = "";
		Random r = new Random();
		try {
			BufferedReader read = new BufferedReader(new FileReader(path));

			String line = "";
			ArrayList<String> list = new ArrayList<String>();
			attendancePath = "data/attendance/memberWorkingTimeDummy.txt";
			FileWriter fw = new FileWriter(attendancePath, true);
			String dummy = "";

			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");

				list.add(temp[2]);
			} // while

			for (int i = 0; i < list.size(); i++) {
				name = list.get(i);
				for (int j = 1; j <= 31; j++) {

					dummy = String.format("%s,2021-05-%02d 08:%02d:00,2021-05-%02d 18:%02d:00\n", name, j,
							r.nextInt(30) + 30, j, r.nextInt(50));

					fw.write(dummy);
				}
			} // for i

			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// dummy

	/**
	 * 5월 출퇴근 시간이 나와있는 더미 데이터에서 필요한 값을 ArrayList 에 저장하는 메소드 
	 * 관리자의 입장에서 모든 직원의 근무시간 확인하는 기능
	 * 
	 * @author 2조
	 * @throws IOException
	 */
	public void readWorkingTime() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(this.attendancePath));
		String line = "";

		while ((line = read.readLine()) != null) {
			String[] temp = line.split(",");
			this.memberName.add(temp[0]);
			String in = temp[1];
			String out = temp[2];
			String[] inDate = in.split(" ");
			String[] inDate2 = inDate[0].split("-"); // 년월일
			String[] inDate3 = inDate[1].split(":");// 시분초
			int inYear = Integer.parseInt(inDate2[0]);
			int inMonth = Integer.parseInt(inDate2[1]);
			inMonth--;
			int inDay = Integer.parseInt(inDate2[2]);

			int inHour = Integer.parseInt(inDate3[0]);
			int inMin = Integer.parseInt(inDate3[1]);

			String[] outDate = out.split(" ");
			String[] outDate2 = outDate[0].split("-"); // 년월일
			String[] outDate3 = outDate[1].split(":");// 시분초
			int outYear = Integer.parseInt(outDate2[0]);
			int outMonth = Integer.parseInt(outDate2[1]);
			outMonth--;
			int outDay = Integer.parseInt(outDate2[2]);

			int outHour = Integer.parseInt(outDate3[0]);
			int outMin = Integer.parseInt(outDate3[1]);

			Calendar c1 = Calendar.getInstance();
			c1.set(inYear, inMonth, inDay, inHour, inMin, 00);
			Calendar c2 = Calendar.getInstance();
			c2.set(outYear, outMonth, outDay, outHour, outMin, 00);

			this.chulgeun.add(c1);
			this.toegeun.add(c2);

		} // while

	}// readWorkingTime

	/**
	 * 멤버별 근무시간 더미 데이터 이용해 월~금 근무 일자 추출 + 근무 시간을 새로운 더미로 만드는 메소드
	 *  ex)홍길동,2021-5-3,521
	 * 
	 * @throws IOException
	 */
	public void dayWorkingTime() throws IOException {

		lastDay = getLastDay(this.chulgeun.get(this.chulgeun.size() - 1).get(Calendar.YEAR),
				this.chulgeun.get(this.chulgeun.size() - 1).get(Calendar.MONTH));
		int sum = 0;
		int i = 0;
		int cnt = 1;
		String testPath = "data/attendance/memberTime.txt";
		FileWriter fw = new FileWriter(testPath);

		for (i = 0; i < this.chulgeun.size(); i++) {
			if ((this.chulgeun.get(i).get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
					&& (chulgeun.get(i).get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)) {
				sum = (int) ((this.toegeun.get(i).getTimeInMillis() - this.chulgeun.get(i).getTimeInMillis()) / 1000
						/ 60);
				cnt++;

				String dayWorkingTime = String.format("%d", sum - 60);
				fw.write(this.memberName.get(i));
				fw.write(",");
				fw.write(String.format("%s-%s-%s", this.chulgeun.get(i).get(Calendar.YEAR),
						this.chulgeun.get(i).get(Calendar.MONTH) + 1, this.chulgeun.get(i).get(Calendar.DATE)));
				fw.write(",");
				fw.write(dayWorkingTime);
				fw.write("\r\n");
			}
		} // i for
		fw.close();

	}// DayWorkingTime

	/**
	 * 멤버 이름 , 년, 월 검색으로 해당 년,월의 근무시간 출력
	 */

	public void adminSearchWorkingTime() {

		try {
			BufferedReader read = new BufferedReader(new FileReader(memberPath));
			System.out.print("근무 시간을 확인하고 싶은 직원의 이름 입력: ");
			String memName = scan.nextLine();
			System.out.print("근무 시간을 확인하고 싶은 년도와 월을 입력해주세요.(YYYY-M): ");
			String yearMonth = scan.nextLine();

			String[] temp1 = yearMonth.split("-"); // 입력받은 2021-5 쪼개기

			String line = "";
			// String date = "2021-05-02";
			String str = "";
			int sum = 0;
			int workingMin = 0;

			while ((line = read.readLine()) != null) {
				// System.out.println(line);
				String temp[] = line.split(",");
				workingMin = Integer.parseInt(temp[2]);
				if (temp[0].equals(memName)) {
					String workingDay = temp[1];
					String[] yearMonthDay = temp[1].split("-"); // 2021-5-3 쪼개기
					if (yearMonthDay[0].equals(temp1[0]) && yearMonthDay[1].equals(temp1[1])) {

						sum += workingMin;
					}
				}
			} // while
			System.out.println();
			System.out.println();
			System.out.println("        ▣ 월간 근무 시간 조회(관리자) ▣");
			System.out.println("====================================");
			System.out.println();
			System.out.printf("%s님의 %s년 %s월 근무시간은 %d시간입니다.\n ", memName, temp1[0], temp1[1], sum / 60);
			System.out.println();
			System.out.println("====================================");

			pause();
			attendanceScreen();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// searchWorkingTime

	/*
	 * 날짜 계산시 필요한 메소드
	 */
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

	/**
	 * 출근 시간 입력하는 메소드 임의의 랜덤한 시간값이 출근시간으로 입력되도록 만듬
	 * @param Random rnd Random 변수 
	 * @param Calendar in 출근날짜,시간 관련 Calendar 변수 
	 */
	public void inTime() {
		Random rnd = new Random();
		Calendar in = Calendar.getInstance();

		try {
			BufferedReader read = new BufferedReader(new FileReader(DATA));
			String line = "";

			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				if (temp[2].equals(this.user.getName())) {
					in.set(in.get(Calendar.YEAR), in.get(Calendar.MONTH), in.get(Calendar.DATE), 8,
							rnd.nextInt(30) + 30, 00);
					
				}
			} // while
			System.out.println();
			System.out.println();
			System.out.println("        ▣ 출근 등록 화면 ▣");
			System.out.println("====================================");
			System.out.println("출근 완료 하셨습니다.");
			System.out.printf("%s님의 출근시간 : %tF %tT\n", this.user.getName(), in, in);
			System.out.println("====================================");

			pause();
			attendanceScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// inTime

	/**
	 * 퇴근시간 입력 메소드
	 * @param Random rnd Random메소드 
	 * @param Calendar out 퇴근 시간 관련 Calendar 변수 
	 */
	public void outTime() {
		Random rnd = new Random();
		// t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사
		Calendar out = Calendar.getInstance();

		try {
			BufferedReader read = new BufferedReader(new FileReader(DATA));
			String line = "";

			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				if (temp[2].equals(this.user.getName())) {
					out.set(out.get(Calendar.YEAR), out.get(Calendar.MONTH), out.get(Calendar.DATE), 18,
							rnd.nextInt(30), 00);
					
				}
			} // while
			System.out.println();
			System.out.println();
			System.out.println("        ▣ 퇴근 등록 화면 ▣");
			System.out.println("====================================");
			System.out.println("퇴근 완료 하셨습니다.");
			System.out.printf("%s님의 퇴근시간 : %tF %tT\n", this.user.getName(), out, out);
			System.out.println("====================================");

			pause();
			attendanceScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// OutTime
	/**
	 * 100줄을 생성해 새로운 창에서 화면이 뜨는 효과를 내기 위한 메소드
	 */
	private static void cls() {

		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}// cls()
	
	/**
	 * 일시정지 기능을 해주는 메소드 
	 * 엔터를 누르면 cls() 메소드 실행 
	 */
	private static void pause() {
		System.out.println();
		System.out.println("엔터를 누르시면 이전 항목으로 돌아갑니다.");
		scan.nextLine();// Block
		cls();

	}

}
