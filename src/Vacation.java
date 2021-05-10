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
	
	public void createVacation(){
		mc.createScheduleVacation();
	}
	
	public void deleteVacation() {
		mc.deleteVacation();
	}
	
	public void readVacation() {
		mc.readVacation();
	}
}//Class


