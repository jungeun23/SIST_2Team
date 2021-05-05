import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Vacation {
	User user;
	MyCalendar mc;
	
	private final String DATA;
	
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	Vacation(User user){
		DATA = "data\\schedule\\vacation.txt";
		this.user = user;
		this.mc = new MyCalendar(user);
	}
	
	
	public void vFirstScreen() {
		load();
		
		System.out.println("===============================================");
		System.out.println("|| 1.이름  || 2. 직급 || 3. 부서 || 4.  개인 ||");
		System.out.println("||   검색  ||    검색 ||    검색 ||   주소록 ||");
		System.out.println("===============================================");
		System.out.println();
		System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요.");
		System.out.println("목차로 돌아가려면 0번을 누르세요.\n");
		System.out.print("카테고리 번호 : ");
		//this.num = scan.nextInt();
		//scan.nextLine(); // 입력 받으면서 들어온 \r\n을 지워주고 가야함.
		
	}
	
	 
	public void createVacation(){
		mc.createScheduleVacation();
	}//create()
	
	public void showVacation() {
		
		for(int i=0; i< list.size(); i++) {
			if(list.get(i)[0].equals(this.user.getName())) {
				String[] temp = list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				System.out.println("일정을 선택해주세요");
				int[] c = mc.showCanlendar(year, month, day);
				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println("일정을 출력합니다");
					System.out.println("제목 : " + list.get(i)[2]);
					System.out.println("내용 : " + list.get(i)[3]);
				}
			}
		}
		System.out.println("남은 일정이 없습니다.");
		
	}//show
	
	
	public void deleteVacation() {
		
	}
	
	public void readVacation() {
		
	}
	
	public void updateVacation() {
		
	}
	
	
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



























