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

public class Vacation {
	User user;
	MyCalendar_jiwon mc;
	
	public Vacation(User user) {
		this.user =user;
		this.mc = new MyCalendar_jiwon(user);
	}
	
	
	//첫 화면 출력
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








