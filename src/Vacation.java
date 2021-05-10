import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Vacation {
	private final String DATA;
	private User user;
	MyCalendar mc;
	private Calendar date;
	private int year;
	private int month;
	private int day;
	
	LinkedList<String[]> listVacation = new LinkedList<>();
	private static Scanner scan;

	public Vacation(User user) {
		this.user = user;
		this.mc = new MyCalendar(user);
		DATA = "data\\schedule\\vacation.txt";
		date = Calendar.getInstance();
		this.year = date.get(Calendar.YEAR);
		this.month = date.get(Calendar.MONTH) + 1;
		this.day = date.get(Calendar.DATE);
		scan = new Scanner(System.in);
		load();
	}

	private void load() {
		try {
			BufferedReader readVacation = new BufferedReader(new FileReader(DATA));
			String line = "";
			while ((line = readVacation.readLine()) != null) {
				String[] temp = line.split(",");
				listVacation.add(temp);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}
	
	
	/**
	 * 사용자가 휴가관리에 진입하여 처음으로 보게 되는 카테고리.
	 * 여기에서 원하는 카테고리 번호를 입력받는다.
	 */
	public void firstScreen() {

		System.out.println("=================================================");
		System.out.println("|| 1.휴가 일정  || 2. 휴가 일정 || 3. 휴가 일정 ||");
		System.out.println("||        신청  ||         확인 ||         취소 ||");
		System.out.println("=================================================");
		System.out.println();
		System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요.");
		System.out.println("목차로 돌아가려면 0번을 누르세요.\n");
		int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
		
		
		try {
			
			if (n == 0) {
				return;
			} else if (n == 1) {
				createScheduleVacation();
			} else if (n == 2) {
				readVacation();
			} else if (n == 3) {
				deleteVacation();
			} else {
				System.out.println("잘못된 번호가 선택되었습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	/**
	 * 휴가날짜와 휴가사유를 입력받고, 그걸 휴가일정 파일(DATA2)에 저장하는 메소드
	 */
	public void createScheduleVacation() {
		System.out.println("휴가를 등록합니다. 달력을 확인해주세요");
		mc.showCanlendar(this.year, this.month);
		String s = Util.get("\n날짜를 입력해주세요(yyyy-mm-dd): ");
		String[] temp = s.split("-");
		Calendar newTask = Calendar.getInstance();
		newTask.set(Util.toInt(temp[0]), Util.toInt(temp[1]), Util.toInt(temp[2]));
		String title = Util.get("휴가 사유를 입력해주세요");
		String ymd = newTask.get(Calendar.YEAR) + "-" + newTask.get(Calendar.MONTH) + "-"
				+ newTask.get(Calendar.DAY_OF_MONTH);
		// 홍길동,2021-5-4,t,testaaa
		String position = null;
		String depart = null;
		String randNum = null;

		try {
			BufferedReader read = new BufferedReader(new FileReader("data\\HR.txt"));
			long seed = System.currentTimeMillis();
			Random rand = new Random(seed);
			rand.setSeed(seed);
			randNum = Integer.toString(rand.nextInt(15) + 1);

			String line = "";
			while ((line = read.readLine()) != null) {
				String[] t = line.split(",");
				if (t[0].equals(this.user.getName())) {
					position = t[1];
					depart = t[2];
					String[] sl = { this.user.getName(), position, depart, randNum, ymd, title };
					listVacation.add(sl);
				}
			}

			FileWriter fw = new FileWriter(DATA, true);
			fw.write(this.user.getName() + ",");
			fw.write(position + ",");
			fw.write(depart + ",");
			fw.write(randNum + ",");
			fw.write(ymd + ",");
			fw.write(title + "\n");
			fw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("\n▶ 휴가 일정 등록이 완료됐습니다.");
		System.out.println("=====================================================");
		System.out.print("목차로 돌아가려면 엔터를 누르세요");
		scan.nextLine();
		Util.cls();
		firstScreen();
	}//create()
	
	/**
	 * 입력했던 휴가날짜를 모두 출력하고, 거기에서 취소할 일정을 입력받는다.
	 * 입력받은 취소 일정을 휴가일정 파일(DATA2)에서 지우는 메소드.
	 */
	public void deleteVacation() {
		 ArrayList<int[]> t = new ArrayList<>();
	      for (int i = 0; i < listVacation.size(); i++) {
	         if (listVacation.get(i)[0].equals(this.user.getName())) {
	            String[] temp = listVacation.get(i)[4].split("-");
	            int year = Util.toInt(temp[0]);
	            int month = Util.toInt(temp[1]);
	            int day = Util.toInt(temp[2]);
	            int[] g = { year, month, day };
	            t.add(g);

	         }
	      } // exit for
	      System.out.println("삭제할 일정을 선택해주세요");
	      int[] c = mc.showCanlendar(t);
	      for (int i = 0; i < listVacation.size(); i++) {
	         if (listVacation.get(i)[0].equals(this.user.getName())) {
	            String[] temp = listVacation.get(i)[4].split("-");
	            int year = Util.toInt(temp[0]);
	            int month = Util.toInt(temp[1]);
	            int day = Util.toInt(temp[2]);
	            if (c[0] == year && c[1] == month && c[2] == day) {
	               System.out.println();
	               System.out.println(listVacation.get(i)[4] + " 일정을 삭제했습니다.");
	               listVacation.remove(i);
	            }
	         }
	      } // exit for
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA));
			
			for(int i =0; i<listVacation.size(); i++) {
				
				writer.write(String.format("%s,%s,%s,%s,%s,%s\n", listVacation.get(i)[0], listVacation.get(i)[1],
						listVacation.get(i)[2], listVacation.get(i)[3], listVacation.get(i)[4], listVacation.get(i)[5]));
			
			}
			writer.close();
			System.out.println("\n▶ 휴가 일정 삭제가 완료됐습니다.");
			System.out.println("=====================================================");
			System.out.print("목차로 돌아가려면 엔터를 누르세요");
			scan.nextLine();
			Util.cls();
			firstScreen();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//delete()
	
	/**
	 * 입력했던 휴가날짜를 모두 출력하고, 거기에서 확인할 일정을 입력받는다.
	 * 확인할 일정은 휴가 사유와 함께 출력하는 메소드. 
	 */
	public void readVacation() {
		
		ArrayList<int[]> t = new ArrayList<>();
	      for (int i = 0; i < listVacation.size(); i++) {
	         if (listVacation.get(i)[0].equals(this.user.getName())) {
	            String[] temp = listVacation.get(i)[4].split("-");
	            int year = Util.toInt(temp[0]);
	            int month = Util.toInt(temp[1]);
	            int day = Util.toInt(temp[2]);
	            int[] g = { year, month, day };
	            t.add(g);
	         }
	      }
	      System.out.println("휴가일정을 선택해주세요");
	      int[] c = mc.showCanlendar(t);
	      
	      for (int i = 0; i < listVacation.size(); i++) {
	    	  
	         if (listVacation.get(i)[0].equals(this.user.getName())) {
	            String[] temp = listVacation.get(i)[4].split("-");
	            int year = Util.toInt(temp[0]);
	            int month = Util.toInt(temp[1]);
	            int day = Util.toInt(temp[2]);
	            
	            if (c[0] == year && c[1] == month && c[2] == day) {
	               System.out.println();
	               System.out.println("휴가일정을 출력합니다");
	               System.out.println("사유 : " + listVacation.get(i)[5] + "\n");
	            }
	         }
	      }
			System.out.println("=====================================================");
			System.out.print("목차로 돌아가려면 엔터를 누르세요");
			scan.nextLine();
			Util.cls();
			firstScreen();
		
	}//read()
	
	
}//class



