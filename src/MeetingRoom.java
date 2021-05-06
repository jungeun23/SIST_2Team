import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;

public class MeetingRoom {
	private String[] roomNumber = { "501", "502", "503", "504", "505", "506", "601", "602", "603", "701", "702", "703",
			"704", "815", "816", "817", "819", "820", "821" };
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
	LinkedList<String[]> listRoom;

	public MeetingRoom(User user) {
		this.user = user;
		this.mc = new MyCalendar_subin(user);
		scan = new Scanner(System.in);
		DATA4 = "data\\schedule\\meetingRoom.txt";
	}

	// 1.회의실 예약, 취소, 목록 확인 (날짜순 정렬)

	public void MeetingRoomScreen() {

		System.out.println("                ▣ 회의실 예약 목록 ▣");
		System.out.println("====================================================");
		System.out.println("|| 1. 회의실  || 2. 회의실 예약  || 3. 회의실 예약 ||");
		System.out.println("||      예약  ||           취소  ||      현황 확인 ||");
		System.out.println("====================================================");
		int num = Integer.parseInt(Util.get("카테고리(번호)를 선택하세요"));

		load();
		cls();

		if (num == 1) {
			createRoomReservation();
		} else if (num == 2) {
			deleteRoom();
		} else if (num == 3) {
			readRoom();
		} else {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}

	}// MeetingRoomScreen()

	private void load() {
//		/*
//		 * 
//		 * 홍길동,차장,인사,501,2021-5-5,t 홍길동,차장,인사,501,2021-5-30,dc
//		 * 홍길동,차장,인사,505,2021-5-1,sdf
//		 * 
//		 */
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader(DATA4));
//
//			String line = "";
//			while ((line = reader.readLine()) != null) {
//				String[] temp = line.split(",");
//				listRoom.add(temp);
//			}
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
	}// load()

	private void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}// cls()

	public void createRoomReservation() {
		mc.createRoomReservation(roomNumber);
	}// createRoomReservation()

	public void deleteRoom() {
//		// 홍길동,차장,인사,505,2021-5-1,sdf
//		ArrayList<int[]> t = new ArrayList<>();
//		for (int i = 0; i < listRoom.size(); i++) {
//			if (listRoom.get(i)[0].equals(this.user.getName())) {
//				String[] temp = listRoom.get(i)[4].split("-");
//				int year = Util.toInt(temp[0]);
//				int month = Util.toInt(temp[1]);
//				int day = Util.toInt(temp[2]);
//				int[] g = { year, month, day };
//				t.add(g);
//
//			}
//		} // exit for
//		System.out.println("삭제할 일정을 선택해주세요");
//		int[] c = mc.showCanlendar(t);
//		for (int i = 0; i < listRoom.size(); i++) {
//			if (listRoom.get(i)[0].equals(this.user.getName())) {
//				String[] temp = listRoom.get(i)[4].split("-");
//				int year = Util.toInt(temp[0]);
//				int month = Util.toInt(temp[1]);
//				int day = Util.toInt(temp[2]);
//				if (c[0] == year && c[1] == month && c[2] == day) {
//					System.out.println();
//					System.out.println(listRoom.get(i)[3] + " 회의실 예약을 취소했습니다.");
//					listRoom.remove(i);
//				}
//			}

		mc.deleteRoom();

//		
//		} // exit for
//			// Filewrite로 list 모두 쓰기
//		FileWriter fw = null;
//		try {
//			fw = new FileWriter(DATA4);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		for (int i = 0; i < listRoom.size(); i++) {
//			try {
//				fw.write(String.format("%s,%s,%s,%s,%s,%s\n", 
//						listRoom.get(i)[0], listRoom.get(i)[1], listRoom.get(i)[2],
//						listRoom.get(i)[3], listRoom.get(i)[4], listRoom.get(i)[5]));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		try {
//			fw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
	}// deleteVacation()

	public void readRoom() {
		// 홍길동,차장,인사,821,2021-5-18,회의
//	      ArrayList<int[]> t = new ArrayList<>();
//	      for (int i = 0; i < listRoom.size(); i++) {
//	         if (listRoom.get(i)[0].equals(this.user.getName())) {
//	            String[] temp = listRoom.get(i)[4].split("-");
//	            int year = Util.toInt(temp[0]);
//	            int month = Util.toInt(temp[1]);
//	            int day = Util.toInt(temp[2]);
//	            int[] g = { year, month, day };
//	            t.add(g);
//	         }
//	      }
//	      System.out.println("일정을 선택해주세요");
//	      int[] c = mc.showCanlendar(t);
//	      for (int i = 0; i < listRoom.size(); i++) {
//	         if (listRoom.get(i)[0].equals(this.user.getName())) {
//	            String[] temp = listRoom.get(i)[4].split("-");
//	            int year = Util.toInt(temp[0]);
//	            int month = Util.toInt(temp[1]);
//	            int day = Util.toInt(temp[2]);
//	            if (c[0] == year && c[1] == month && c[2] == day) {
//	               System.out.println();
//	               System.out.println("예약 일정을 출력합니다");
//	               System.out.println();
//	               System.out.println("이름 : " + listRoom.get(i)[0]);
//	               System.out.println("직급 : " + listRoom.get(i)[1]);
//	               System.out.println("부서 : " + listRoom.get(i)[2]);
//	               System.out.println("회의실 번호 : " + listRoom.get(i)[3]);
//	               System.out.println("예약 날짜 : " + listRoom.get(i)[4]);
//	               System.out.println("내용 : " + listRoom.get(i)[5]);
//	               System.out.println();
//	            }
//	         }
//	      }
//	      System.out.println("남은 일정이 없습니다.");
//	   }
		mc.readRoom();

//	public void readRoom() {
//		// 홍길동,차장,인사,501,2021-5-5,t
//		for (int i = 0; i < listRoom.size(); i++) {
//			if (listRoom.get(i)[0].equals(this.user.getName())) {
//				String[] temp = listRoom.get(i)[4].split("-");
//				int year = Util.toInt(temp[0]);
//				int month = Util.toInt(temp[1]);
//				int day = Util.toInt(temp[2]);
//				System.out.println("일정을 선택해주세요");
//				int[] c = mc.showCanlendar(year, month, day);
//				if (c[0] == year && c[1] == month && c[2] == day) {
//					System.out.println();
//					System.out.println("예약 일정을 출력합니다");
//					System.out.println();
//					System.out.println("이름 : " + listRoom.get(i)[0]);
//					System.out.println("직급 : " + listRoom.get(i)[1]);
//					System.out.println("부서 : " + listRoom.get(i)[2]);
//					System.out.println("회의실 번호 : " + listRoom.get(i)[3]);
//					System.out.println("예약 날짜 : " + listRoom.get(i)[4]);
//					System.out.println("내용 : " + listRoom.get(i)[5]);
//					System.out.println();
//				}
//			}
//		}
//		System.out.println("남은 일정이 없습니다.");
//
//	}// readVacation()
	}
}// MeetingRoom
