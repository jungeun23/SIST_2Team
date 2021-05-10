import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * @author 방수빈
 * 
 * 회의실 예약 구현
 * 이용자는 회의실 예약, 예약 취소, 예약 현황 확인을 할 수 있다.
 * @param roomNumber 회의실 방 번호
 * @param user 현재 접속 중인 유저의 정보
 * @param mc MyCalendar 클래스
 * @param DATA4 회의실 예약 정보가 저장된 경로
 * @param year 회의실 예약 년도
 * @param month 회의실 예약 달
 * @param day 회의실 예약 일
 * @param lastDay 달 일수 
 * @param day of week 해당 월의 1일의 요일
 * 
 *
 */
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
	/**
	 * 회의실 예약 관리 각 항목으로 넘어가도록 int값을 입력받아 각 메소드로 이동한다.
	 */
	public void MeetingRoomScreen() {

		System.out.println("                ▣ 회의실 예약 관리 목록 ▣");
		System.out.println("====================================================");
		System.out.println("|| 1. 회의실  || 2. 회의실 예약  || 3. 회의실 예약 ||");
		System.out.println("||      예약  ||           취소  ||      현황 확인 ||");
		System.out.println("====================================================");
		System.out.println("목차로 돌아가려면 0번을 누르세요.\n");
		int num = Integer.parseInt(Util.get("카테고리(번호)를 선택하세요"));

		cls();

		if(num == 0) {
			return;
		} else if (num == 1) {
			createRoomReservation();
		} else if (num == 2) {
			deleteRoom();
		} else if (num == 3) {
			readRoom();
		} else {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}

	}// MeetingRoomScreen()
	/**
	 * 출력화면에서 화면이 넘어간 것처럼 표현하기 위해 빈줄 100줄 출력
	 */
	private void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}// cls()
	/**
	 * roomNumber를 가지고 mc의 createRoomReservation()로 넘어가서, 회의실 예약을 한다. 
	 */
	public void createRoomReservation() {
		
		mc.createRoomReservation(roomNumber);
		
		
		System.out.println();
		int num2 = Integer.parseInt(Util.get("▶ 목차로 돌아가려면 0번을 누르세요."));
		if(num2 == 0) {
			cls();
			MeetingRoomScreen();
		}
	}// createRoomReservation()
	/**
	 * mc의 deleteRoom()으로 넘어가서, 회의실 예약을 취소한다.
	 */
	public void deleteRoom() {

		mc.deleteRoom();
		
		
		System.out.println();
		int num2 = Integer.parseInt(Util.get("▶ 목차로 돌아가려면 0번을 누르세요."));
		if(num2 == 0) {
			cls();
			MeetingRoomScreen();
		}

	}// deleteVacation()
	/**
	 * mc의 readRoom으로 넘어가서, 회의실 예약 현황을 확인한다.
	 */
	public void readRoom() {
		
		mc.readRoom();
		
		
		System.out.println();
		int num2 = Integer.parseInt(Util.get("▶ 목차로 돌아가려면 0번을 누르세요."));
		if(num2 == 0) {
			cls();
			MeetingRoomScreen();
		}


	}
}// MeetingRoom
