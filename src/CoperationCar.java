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

public class CoperationCar {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private final String DATA3;

	private final String DATA5;
	private Calendar date;
	private int year;
	private int month;
	private int day;
	private int lastDay = 0;
	private int day_of_week = 0;
	private User user;

	LinkedList<String[]> listCar = new LinkedList<>();
	private ArrayList<String[]> carList = new ArrayList<>();
	private Scanner scan;
	private MyCalendar mc;
	public CoperationCar(User user) {
		this.user = user;
		DATA3 = "data\\schedule\\car.txt";
		this.DATA5 = "data\\schedule\\listCar.txt";
		date = Calendar.getInstance();
		this.year = date.get(Calendar.YEAR);
		this.month = date.get(Calendar.MONTH) + 1;
		this.day = date.get(Calendar.DATE);
		scan = new Scanner(System.in);
		load();
		mc = new MyCalendar(this.user);
	}

	private void load() {
		try {
			String line = "";
			
			BufferedReader readCar = new BufferedReader(new FileReader(DATA3));
			while ((line = readCar.readLine()) != null) {
				String[] temp = line.split(",");
				listCar.add(temp);
			}
			
			BufferedReader readCarList = new BufferedReader(new FileReader(DATA5));
			line = "";
			while((line = readCarList.readLine()) != null) {
				String[] temp = line.split(",");
				this.carList.add(temp);
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}

	}


	/**
	 * 법인 차량 예약 구현
	 * 이용자는 차량 예약을 등록, 확인, 삭제등을 할 수 있다.
	 * 법인 차량 예약 선택 창 출력 메소드
	 * 사용자의 선택에 따라 예약 신청, 예약 확인, 예약 취소, 목록으로 돌아가기를 구현한다.
	 */
	
	public void copCarScreen() {
		
		System.out.println();
		System.out.println();
		System.out.println("                ▣ 법인 차량 예약 ▣");
		System.out.println("==========================================================");
		System.out.println("||1. 법인차량 | 2. 법인차량 | 3. 법인차량 | 0. 목록으로 ||");
		System.out.println("||  예약 신청 |   예약 확인 |   예약 취소 |    돌아가기 ||");
		System.out.println("==========================================================");
		String n = (Util.get("번호를 입력해주세요"));
		if(n.equals("1")) {
			createCopCarReseravtion();
		} else if(n.equals("2")) {
			readCopCarSchedule();
		} else if(n.equals("3")) {
			deleteCopCarSchedule();
		} else if(n.equals("0")) {
			return;
		} else {
			System.out.println("잘못된 입력입니다.");
			copCarScreen();
		}
	}
	
	/**
	 * 법인 차량 예약 등록 메소드
	 * 사용자가 원하는 예약 날짜를 입력하여 예약 사유를 입력 받는다.
	 * 법인차량 목록을 출력하여 사용자가 원하는 법인차량을 입력받은 후 예약이 완료된다.
	 * 만약 사용자가 선택한 차량의 남은 재고가 없을 시 예약 불가능으로 출력이 된다.
	 */
	
	public void createCopCarReseravtion() {
		System.out.println("법인차량 예약을 등록합니다. 달력을 확인해주세요");
		mc.showCanlendar(this.year, this.month);
		String s = Util.get("날짜를 입력해주세요(yyyy-mm-dd): ");
		String[] temp = s.split("-");
		Calendar newTask = Calendar.getInstance();
		newTask.set(Util.toInt(temp[0]), Util.toInt(temp[1]), Util.toInt(temp[2]));
		System.out.println("-------------------------------------");
		String reason = Util.get("예약 사유를 입력해주세요");
		String ymd = newTask.get(Calendar.YEAR) + "-" + newTask.get(Calendar.MONTH) + "-"
				+ newTask.get(Calendar.DAY_OF_MONTH);

		System.out.println();
		System.out.println("법인 차량 목록입니다.");
		System.out.println("----------------------------------");
		for (int i = 0; i < carList.size(); i++) {
			System.out.printf("[%d] : %s\n", i + 1, carList.get(i)[0]);
		}

		int selectedCar = Util.toInt(Util.get("번호를 입력해주세요"));
		selectedCar--;
		if (Util.toInt(carList.get(selectedCar)[1]) == 0) {
			System.out.println(selectedCar+1 + " 차량의 재고가 존재하지 않습니다.");
			try {
				Main.showReservation();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			int t = Util.toInt(carList.get(selectedCar)[1]);
			carList.get(selectedCar)[1] = Integer.toString(t - 1);
		}
		
		String position = null;
		String depart = null;
		String randNum = null;

		try {
			BufferedReader read = new BufferedReader(new FileReader("data\\HR.txt"));
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] t = line.split(",");
				if (t[0].equals(this.user.getName())) {
					position = t[1];
					depart = t[2];
					String[] sl = { this.user.getName(), position, depart, carList.get(selectedCar)[0],
							(carList.get(selectedCar)[1]), ymd, reason };
					listCar.add(sl);
				}
			}

			FileWriter fw = new FileWriter(DATA3, true);
			fw.write(this.user.getName() + ",");
			fw.write(position + ",");
			fw.write(depart + ",");
			fw.write(carList.get(selectedCar)[0] + ",");
			fw.write(carList.get(selectedCar)[1] + ",");
			fw.write(ymd + ",");
			fw.write(reason +"\n");
			fw.close();

			
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("------------------------------------");
		System.out.println("차량 예약 등록이 완료됐습니다.");
		
		saveCarList();
		System.out.println("엔터를 누르면 목록으로 돌아갑니다.");
		scan.nextLine();
		copCarScreen();
	}

	/**
	 * 법인 차량 예약 확인 메소드
	 * 사용자의 정보로 예약된 일정들을 달력에 *로 전체 표시되어 나타난다.
	 * 사용자는 특정 일정의 날짜를 입력하여 자세한 일정 보기가 가능하다.
	 * 이때 예약자명, 예약 일자, 예약 목적을 확인할 수 있다. 
	 */
	
	public void readCopCarSchedule() {
		
		 ArrayList<int[]> t = new ArrayList<>();
	      for (int i = 0; i < listCar.size(); i++) {
	         if (listCar.get(i)[0].equals(this.user.getName())) {
	            String[] temp = listCar.get(i)[5].split("-");
	            int year = Util.toInt(temp[0]);
	            int month = Util.toInt(temp[1]);
	            int day = Util.toInt(temp[2]);
	            int[] g = { year, month, day };
	            t.add(g);
	         }
	      }
	      System.out.println("일정을 선택해주세요");
	      int[] c = mc.showCanlendar(t);
	      for (int i = 0; i < listCar.size(); i++) {
	         if (listCar.get(i)[0].equals(this.user.getName())) {
	            String[] temp = listCar.get(i)[5].split("-");
	            int year = Util.toInt(temp[0]);
	            int month = Util.toInt(temp[1]);
	            int day = Util.toInt(temp[2]);
	            if (c[0] == year && c[1] == month && c[2] == day) {
	               System.out.println();
	               System.out.println("일정을 출력합니다");
	               System.out.println("예약자명 : " + listCar.get(i)[0]);
	               System.out.println("예약 일자 : "+ listCar.get(i)[5]);
	               System.out.println("예약 목적 : " + listCar.get(i)[6]);
	            }
	         }
	      }

	    System.out.println();
		System.out.println("모든 예약을 출력했습니다.");
		System.out.println("엔터를 누르면 목록으로 돌아갑니다.");
		scan.nextLine();
		copCarScreen();
		
	}
	
	/**
	 * 법인 차량 예약 삭제 메소드
	 * 사용자의 정보로 예약된 일정들이 달력에 *로 전체 표시되어 나타난다.
	 * 사용자는 예약 취소할 날짜를 선택하고, 예약이 취소된다. 
	 */
	
	public void deleteCopCarSchedule() {
		
		ArrayList<int[]> t = new ArrayList<>();
	      for (int i = 0; i < listCar.size(); i++) {
	         if (listCar.get(i)[0].equals(this.user.getName())) {
	            String[] temp = listCar.get(i)[5].split("-");
	            int year = Util.toInt(temp[0]);
	            int month = Util.toInt(temp[1]);
	            int day = Util.toInt(temp[2]);
	            int[] g = { year, month, day };
	            t.add(g);

	         }
	      } 
	      System.out.println("삭제할 일정을 선택해주세요");
	      int[] c = mc.showCanlendar(t);
	      for (int i = 0; i < listCar.size(); i++) {
	         if (listCar.get(i)[0].equals(this.user.getName())) {
	            String[] temp = listCar.get(i)[5].split("-");
	            int year = Util.toInt(temp[0]);
	            int month = Util.toInt(temp[1]);
	            int day = Util.toInt(temp[2]);
	            if (c[0] == year && c[1] == month && c[2] == day) {
	               System.out.println();
	               System.out.println(listCar.get(i)[5] + " 일정을 삭제했습니다.");
	               for(int j=0; j<carList.size(); j++) {
						
						if(listCar.get(i)[3].equals(carList.get(j)[0])) {
							
							String[] tempp = {carList.get(j)[0], (Integer.parseInt(carList.get(j)[1]) +1)+""};
							
							carList.set(j, tempp);
							break;
						}
					}
	               listCar.remove(i);
	            }
	         }
	      } 

		
		
		saveCarList();
		System.out.println();
		System.out.println("엔터를 누르면 목록으로 돌아갑니다.");
		scan.nextLine();
		copCarScreen();
		
	}

	/**
	 * 법인 차량 목록 및 재고 파일 저장 메소드
	 * 사용자가 법인 차량 목록을 선택시 재고가 하나씩 줄고, 예약 취소시 하나 늘어나게 된다.
	 * 그 리스트를 별도의 listCar.txt 파일로 저장한다.
	 */
	
	public void saveCarList() {
	
	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(DATA3));
		
		for(int i=0; i<listCar.size();i++) {
			writer.write(String.format("%s,%s,%s,%s,%s,%s,%s\n"
					                    , listCar.get(i)[0]
					                    , listCar.get(i)[1]
					                	, listCar.get(i)[2]
					                	, listCar.get(i)[3]
					                	, listCar.get(i)[4]
					                	, listCar.get(i)[5]
					                	, listCar.get(i)[6]));
		}
		
		writer.close();
		
		BufferedWriter writerCar = new BufferedWriter (new FileWriter(DATA5));
		
		for(int i=0; i<carList.size(); i++) {
			writerCar.write(String.format("%s,%s\n", carList.get(i)[0]
					                                , carList.get(i)[1]));
		}
		
		writerCar.close();
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
}
	

}
