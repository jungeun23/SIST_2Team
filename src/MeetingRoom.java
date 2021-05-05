import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MeetingRoom {
    private String[] roomNumber = {"501","502","503","504","505","506","601","602","603","701","702","703","704","815","816","817","819","820","821"};
    private User user;
    private MyCalendar_subin mc;
    private Scanner scan;
    private final String DATA4;
    private Calendar date;
	private int year;
	private int month;
	private int day;
	private int lastDay = 0;
	private int day_of_week = 0;
	ArrayList<String[]> listRoom = new ArrayList<String[]>();
    
    public MeetingRoom(User user) {
    	this.user = user;
    	this.mc = new MyCalendar_subin(user);
    	scan = new Scanner(System.in);
    	DATA4 = "data\\schedule\\meetingRoom.txt";
    }
    
    //1.회의실 예약, 취소, 목록 확인 (날짜순 정렬)
    
    public void MeetingRoomScreen() {
    
    System.out.println("                ▣ 회의실 예약 목록 ▣");
	System.out.println("====================================================");
	System.out.println("|| 1. 회의실  || 2. 회의실 예약  || 3. 회의실 예약 ||");
	System.out.println("||      예약  ||           취소  ||      현황 확인 ||");
	System.out.println("====================================================");
	int num = Integer.parseInt(Util.get("카테고리(번호)를 선택하세요"));
	
	cls();
	
	if(num == 1) {
		createRoomReservation();
	} else if(num == 2) {
		deleteRoom();
	} else if(num == 3) {
		readRoom();
	} else {
		System.out.println("잘못된 번호를 입력하셨습니다.");
	}

    }//MeetingRoomScreen()
    
    private void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}//cls() 

	public void createRoomReservation() {
    	mc.createRoomReservation(roomNumber);
    }//createRoomReservation()
    
    public void deleteRoom() {
    	for (int i = 0; i < listRoom.size(); i++) {
			if (listRoom.get(i)[0].equals(this.user.getName())) {
				String[] temp = listRoom.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				System.out.println("취소할 회의실 예약을 선택해주세요");
				int[] c = mc.showCanlendar(year, month, day);
				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println(listRoom.get(i)[2] + "회의실 예약을 취소했습니다.");
					listRoom.remove(i);
				}
			}
		}
	}//deleteVacation()
	
	public void readRoom() {
		while (true) {
			lastDay = mc.getLastDay(year, month);

			// 해당 월의 1일의 요일?
			day_of_week = mc.getDayOfWeek(year, month); // 4
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
		
	}//readVacation()
	
    
}//MeetingRoom
