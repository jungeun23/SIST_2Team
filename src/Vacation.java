import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 휴가관리 구현 클래스
 * 사용자는 휴가를 신청, 취소하거나 신청한 휴가일정을 확인할 수 있다. 
 * @param user 현재 사용중인 유저의 정보를 식별하기 위한 변수
 * @param mc 캘린더 구현부를 가져오는 변수
 */
public class Vacation {
	User user;
	MyCalendar_jiwon mc;
	
	public Vacation(User user) {
		this.user =user;
		this.mc = new MyCalendar_jiwon(user);
	}
	

	public void firstScreen() {
		mc.firstScreen();
	}
	
	/**
	 * 휴가날짜와 휴가사유를 입력받고, 그걸 휴가일정 파일(DATA2)에 저장하는 메소드
	 */
	public void createVacation(){
		mc.createScheduleVacation();
	}
	
	/**
	 * 입력했던 휴가날짜를 모두 출력하고, 거기에서 취소할 일정을 입력받는다.
	 * 입력받은 취소 일정을 휴가일정 파일(DATA2)에서 지우는 메소드.
	 */
	public void deleteVacation() {
		mc.deleteVacation();
	}
	
	
	/**
	 * 입력했던 휴가날짜를 모두 출력하고, 거기에서 확인할 일정을 입력받는다.
	 * 확인할 일정은 휴가 사유와 함께 출력하는 메소드. 
	 */
	public void readVacation() {
		mc.readVacation();
	}
	
	
}//Class


