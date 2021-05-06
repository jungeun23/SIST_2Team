import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Vacation {
	private static Scanner scan = new Scanner(System.in);
	User user;
	MyCalendar_jiwon mc;
	int num;
	private final String DATA;
	
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	Vacation(User user){
		DATA = "data\\schedule\\vacation.txt";
		this.user = user;
		this.mc = new MyCalendar_jiwon(user);
	}
	
	
	public void firstScreen() {
		load();
		
		System.out.println("=================================================");
		System.out.println("|| 1.휴가 일정  || 2. 휴가 일정 || 3. 휴가 일정 ||");
		System.out.println("||        신청  ||         확인 ||         취소 ||");
		System.out.println("=================================================");
		System.out.println();
		System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요.");
		System.out.println("목차로 돌아가려면 0번을 누르세요.\n");
		System.out.print("카테고리 번호 : ");
		this.num = scan.nextInt();
		scan.nextLine(); // 입력 받으면서 들어온 \r\n을 지워주고 가야함.
		
		try {
			
			if (num == 0) {
				return;
			} else if (num == 1) {
				createVacation();
			} else if (num == 2) {
				readVacation();
			} else if (num == 3) {
				deleteVacation();
			} else {
				System.out.println("잘못된 번호가 선택되었습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}//FirstScreen()
	
	 
	public void createVacation(){
		mc.createScheduleVacation();
	}//create()
	
	
	public void deleteVacation() {
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				String[] temp = list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				System.out.println("취소할 휴가 일정을 선택해주세요");
				int[] c = mc.showCanlendar(year, month, day);
				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println(list.get(i)[2] + "휴가 일정을 취소했습니다.");
					list.remove(i);
				}
			}
		}
		
	}//delete()
	
	public void readVacation() {
		
		
		
	}//read()
	
	
	private void load() {
		
		try {
			BufferedReader read = new BufferedReader(new FileReader(DATA));
			String line ="";
			while((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				list.add(temp);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}//load()
}//Class








