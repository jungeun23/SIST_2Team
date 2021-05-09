import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class PayRoll {

	private final String DATA;
	private ArrayList<String[]> list;
	private Scanner scan;
	private User user;
	private String mentBonus;
	private String  mentExtraWork;
	private String mentSalary;
	private Main mainMenu;
	public PayRoll() {
		
		this.DATA = "data\\HR.txt";
		//송월승,대리,마케팅,E,7,3200000
		this.list = new ArrayList<String[]>();
		this.scan = new Scanner(System.in);
		this.user = new User();
		this.mentBonus = "지급된 성과급";
		this.mentExtraWork = "지급된 연장 수당";
		this.mentSalary = "지급된  급여";
		this.mainMenu = new Main();
	}
	
	
	////////////////             개인 User 성과급, 연장근무 수당 조회          ///////////////////// 
	
	// 개인 - 성과급 조회
	public void bonus(User user) {
		////송월승,대리,마케팅,E,7,3200000
		//일반 유저 접근
		this.user = user; //main에서 받아온 유저 PayRoll 변수에 저장해줌

		int index = userIndex(this.user); // user의 list index값을 알려줌
		
		int bonus = bonusPay(index); // index 값 보내서 bonus 수당 값을 데려옴
		
		printPerson(mentBonus,index,bonus);
		
		System.out.println(getWorkday()); ///&&&&&&&&&&&&&&&&&
		
		pause();
	}
	
	

	//개인 - 연장 근무 수당 조회
	public void extraWork(User user) {
		//일반 유저 접근
		this.user = user;

		int index = userIndex(this.user); // user의 list index값을 알려줌

		int extraWork = extraWork(index);
		
		printPerson(mentExtraWork,index,extraWork);
		
		pause();
	}
	

	// 개인 - user index 넘버 구하기
	private int userIndex(User user) {
	
		int index = -1;
		for(int i=0; i<list.size();i++) {
			if(user.getName().equals(list.get(i)[0])) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	

	
/////////////////////////             인사부  전용  급여관리  메뉴             //////////////////////////	
	
	//인사부 급여관리 메뉴 접근
	public void HrAccess() {
		System.out.println();
		System.out.println();
		System.out.println(" [5. HR - 인사부 급여관리]");
		System.out.println();
		System.out.println();
		String hrPw = (Util.get("인사부 권한 비밀번호 입력"));
		
		if(hrPw.equals("1234")) {
			
			approved();
		} else {
			System.out.println();
			System.out.println("※ 올바르지 않은 입력입니다 ※");
			System.out.println();
			System.out.println("[1. 비밀번호 다시 입력하기]");
			System.out.println("[2. HR 수당관리 목록으로 돌아가기]");
			String num = (Util.get("번호를 입력해주세요"));
			if(num.equals("1")) {
				HrAccess();
			} else if (num.equals("2")){
				Main.showExtraPay();
			}
		}
		
	}
	
	//인사부 전용 급여관리 메뉴 
	private void approved() {
		System.out.println();
		System.out.println();
		
		System.out.println("==================================================");
		System.out.println("|| 1. 연장 근무 || 2.  성과급 || 3.   급여 || 4.   목록 ||");
		System.out.println("||    수당 조회 ||      조회 ||      조회 ||    돌아가기 ||");
		System.out.println("==================================================");
	
		System.out.println();
		int num = Integer.parseInt(Util.get("번호를 입력해주세요"));
		System.out.println();
		System.out.println();
	
		if(num == 1 || num == 2 || num == 3) {
			selSearch(num);
		} else if(num == 4) {
			Main.showExtraPay(); // 처음 HR 수당관리 선택 화면
		} else {
			System.out.println("※ 올바르지 않은 입력입니다 ※");
			approved();
		}
		
	}


	
	// 인사부 검색 기능 메뉴
	
	private void selSearch(int num) {
		
		System.out.println();
		System.out.println("===================================");
		System.out.println("|| 1.  부서 || 2.  직원 || 3.   목록 ||");
		System.out.println("||     검색 ||     검색 ||   돌아가기 ||");
		System.out.println("===================================");
		System.out.println();
		String n = (Util.get("번호를 입력해주세요"));
		System.out.println();
		System.out.println();
		
		if(n.equals("1")) {// 부서검색
			
			if(num == 1) { //연장 근무 수당 조회
				searchBuseoExtraWork();
			} else if(num == 2) { //성과급 조회
				searchBuseoBonus();
			} else if(num == 3) { // 월급 조회
				searchBuseoSalary();
			}
			
		} else if (n.equals("2")) { //직원검색
			
			if(num == 1) { //연장 근무 수당 조회
				searchPersonExtraWork();
			} else if(num == 2) { //성과급 조회
				searchPersonBonus();
			} else if(num == 3) { // 월급 조회
				searchPersonSalary();
			}
			
		} else if (n.equals("3")) {
			approved(); // 인사부 전용 화면으로 돌아가기
		} else {
			System.out.println("※ 올바르지 않은 입력입니다 ※");
			selSearch(num);
		}
		
	}

	private void pause() {
		
		System.out.println("엔터를 누르면 HR 수당 관리 목록으로 돌아갑니다.");
		scan.nextLine();
		Main.showExtraPay();
	}
	
	
	private void backToScreen(String search, int num) { // 인사부 검색기능에서 이어서 검색 / 목록 돌아가기
		
		System.out.println("[ 1. 이어서 검색하기 ]");
		System.out.println("[ 2. HR 인사부 전용 목록으로 돌아가기 ]");
		
		int n = Integer.parseInt(Util.get("번호를 입력해주세요."));
		
		if(n == 1) {
			if(search.equals("buseo")) {
				if(num == 1) { //연장 근무 수당 조회
					searchBuseoExtraWork();
				} else if(num == 2) { //성과급 조회
					searchBuseoBonus();
				} else if(num == 3) { // 월급 조회
					searchBuseoSalary();
				}
			} else if(search.equals("person")) {
				if(num == 1) { //연장 근무 수당 조회
					searchPersonExtraWork();
				} else if(num == 2) { //성과급 조회
					searchPersonBonus();
				} else if(num == 3) { // 월급 조회
					searchPersonSalary();
				}
			}
		} else if(n == 2) {
			approved();
		} else {
			System.out.println("※ 올바르지 않은 입력입니다 ※");
			backToScreen(search, num);
		}
		
	}
	
	
	/////////////////////////////       인사부  급여  (부서검색, 직원검색)      /////////////////////
	

	//인사부 - 급여 - 부서검색
	private void searchBuseoSalary() {
		
		String result = searchBuseo("salary");
		if(!result.equals("잘못된 부서 입력입니다.")) {
		printBuseo(mentSalary,result);
		backToScreen("buseo",3);
		} else {
			searchBuseoSalary();
		}
	}

	//인사부 - 급여 - 직원검색
	private void searchPersonSalary() {
		
		int index = searchPersonIndex();
		if(index >0) {
		int salary = Integer.parseInt(list.get(index)[5]);
		printPerson(mentSalary,index,salary);
		backToScreen("person",3);
		} else {
			System.out.println("존재하지 않는 이름입니다.");
			System.out.println("[1. 다시 검색하기]");
			System.out.println("[2. 직전 목록으로 돌아가기]");
			String n = (Util.get("번호를 입력해주세요."));
			
			if(n.equals("1")) {
				searchPersonSalary();
			} else  {
				approved();
			}
		}
	}
	
	//////////////////////////         인사부   성과급   (부서검색, 직원검색)      ///////////////////

	
	// 인사부 - 성과급 - 부서검색
	private void searchBuseoBonus() {
		
		String result = searchBuseo("bonus");
		if(!result.equals("잘못된 부서 입력입니다.")) {
			printBuseo(mentBonus,result);
			backToScreen("buseo",2);
		} else {
			searchBuseoBonus();
		}
	}

	// 인사부 - 성과급 - 직원검색
	private void searchPersonBonus() {
		
		int index = searchPersonIndex();
		if(index >0) {
		int bonus = bonusPay(index);
		printPerson(mentBonus,index,bonus);
		backToScreen("person",2);
		
		} else {
			System.out.println("존재하지 않는 이름입니다.");
			System.out.println("[1. 다시 검색하기]");
			System.out.println("[2. 직전 목록으로 돌아가기]");
			String n = (Util.get("번호를 입력해주세요."));
			
			if(n.equals("1")) {
				searchPersonBonus();
			} else {
				approved();
			}
			
		}
	}
	
	
	////////////////////////////        인사부 연장근무   (부서검색, 직원검색)    //////////////////////////
	

	// 인사부 - 연장근무 - 부서 검색
	private void searchBuseoExtraWork() {
		
		String result = searchBuseo("extraWork");
		if(!result.equals("잘못된 부서 입력입니다.")) {
			printBuseo(mentExtraWork,result);
			backToScreen("buseo",1);
		} else {
			searchBuseoExtraWork();
		}
	}

	// 인사부 - 연장근무 - 직원검색
	private void searchPersonExtraWork() {
		
		int index = searchPersonIndex();
		if(index >0) {
		int extraWork = extraWork(index);
		printPerson(mentExtraWork,index,extraWork);
		backToScreen("person",1);
		
		} else {
			System.out.println("존재하지 않는 이름입니다.");
			System.out.println("[1. 다시 검색하기]");
			System.out.println("[2. 직전 목록으로 돌아가기]");
			String n = (Util.get("번호를 입력해주세요."));
			
			if(n.equals("1")) {
				searchPersonExtraWork();
			} else {
				approved();
			}
		}
	}
	
	
	////////////////////////////     성과급 , 연장근무 수당 계산 메소드      ////////////////////////
	
	// 성과급 계산 메소드
	private int bonusPay(int index) {
		
		double productivityIncentive = 0.0;
		
		int salary = Integer.parseInt(list.get(index)[5]);
		
		//고과 점수에 따라 월급의 pi배가 성과급으로 차등 지급
		if(list.get(index)[3].equals("A") || list.get(index)[3].equals("B") ) { 
			productivityIncentive = 1.0;
		} else if(list.get(index)[3].equals("C") || list.get(index)[3].equals("D")) {
			productivityIncentive = 0.8;
		} else {
			productivityIncentive = 0.6;
		}
		
		int bonus = (int)(salary * productivityIncentive);
		
		return bonus;
	}
	
	
	// 연장 근무 계산 메소드
	private int extraWork(int index) {
	
		int salary = Integer.parseInt(list.get(index)[5]);// 기본급
		
		int hourlyRate = salary / 209; // 통상 시급 (기본급 / 209H)
		
		int workday = getWorkday();
		
		int extraTime =  Integer.parseInt(list.get(index)[6])/60; //> 분 데이터임 > 시간으로 만듦 한달 일한 시간
		
		int legalWorkingTime = workday * 8; //workday * 8시간 근무 = 법적 근무시간
		
		if(extraTime - legalWorkingTime > 0) {
			
			int extraWorkPay = (int)((extraTime - legalWorkingTime) * hourlyRate * 1.5); // 연장 근무 시간 * 통상시급 * 1.5
			
			return extraWorkPay;
			
		} else {
			return 0;
		}

	}

	

	
	private int getWorkday() {
		
		  Calendar startCal = Calendar.getInstance();
		  startCal.set(2021, 04, 01);

		  Calendar endCal = Calendar.getInstance();
		  endCal.set(2021, 04, 31);

		  int workDays = 0;
		  
		  while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
			  
			  if(startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				  workDays++;
			  }
			  startCal.add(Calendar.DAY_OF_MONTH, 1);
		  }

		  return workDays;
		
	}
	
	
	///////////////////////////////     직원  &  부서 검색 및 정렬    /////////////////////////////////////

	
	// 직원 이름 검색 index값 리턴
	private int searchPersonIndex() {
		
		String name = Util.get("이름 검색: ");
		int index = -1;
		for(int i = 0; i<list.size(); i++) {
			
			if(list.get(i)[0].equals(name)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	
	// 부서 검색 반복 메소드!! >> 직급, 이름 오름차순 정렬 
	private String searchBuseo(String what) {
		
		System.out.println("[1.사업] [2.인사] [3.재무] [4.회계] [5.영업] [6.개발] [7.마케팅] [8.디자인] ");
		System.out.println();
		String buseo = Util.get("[부서 검색] 부서를 입력하세요");
		String result = "";

		if(buseo.equals("사업") || buseo.equals("인사") ||buseo.equals("재무") 
				||buseo.equals("회계") ||buseo.equals("영업") ||buseo.equals("개발") 
				||buseo.equals("마케팅") ||buseo.equals("디자인")) {
			
			
		
		
		ArrayList<String[]> buseo2 = new ArrayList<String[]>();
		
		
		for(int i = 0; i<list.size(); i++) {	
			
			if(list.get(i)[2].equals(buseo)) {
	
				int pay = 0;
				if(what.equals("salary")) { //급여면 급여 돈
				
					pay = Integer.parseInt(list.get(i)[5]);
				
				} else if(what.equals("bonus")) { //보너스면 보너스 돈
					pay = bonusPay(i);
				} else if(what.equals("extraWork")) { // 연장근무 수당
					pay = extraWork(i);
				}

				String line = "";
				
				line = String.format("%s,%s,%s,%d"
										,list.get(i)[2], list.get(i)[1], list.get(i)[0],pay);
				
				String[] temp = line.split(",");
				
				buseo2.add(temp);
			}
		}
		
		for(int i=0; i<buseo2.size(); i++) { //직급을 숫자로 환산한 것 일단 문자열로 넣어주고
			buseo2.get(i)[1] =  ""+level(buseo2.get(i)[1]);
		}
		
		
		buseo2.sort(new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
//				return o1[1].compareTo(o2[1]);//숫자
				
				if(Integer.parseInt(o1[1])-Integer.parseInt(o2[1]) > 0) {
					return 1;
				} else if (Integer.parseInt(o1[1])-Integer.parseInt(o2[1]) < 0) {
					return -1;
				} else {
					return o1[2].compareTo(o2[2]);
				}
				
			}
	
		});
		
		for(int i=0; i<buseo2.size();i++) {
			
			if(buseo2.get(i)[1].equals("1")) {
				buseo2.get(i)[1] = "사장";
			} else if(buseo2.get(i)[1].equals("2")) {
				buseo2.get(i)[1] = "전무";
			} else if(buseo2.get(i)[1].equals("3")) {
				buseo2.get(i)[1] = "상무";
			} else if(buseo2.get(i)[1].equals("4")) {
				buseo2.get(i)[1] = "부장";
			} else if(buseo2.get(i)[1].equals("5")) {
				buseo2.get(i)[1] = "차장";
			} else if(buseo2.get(i)[1].equals("6")) {
				buseo2.get(i)[1] = "과장";
			} else if(buseo2.get(i)[1].equals("7")) {
				buseo2.get(i)[1] = "대리";
			} else if(buseo2.get(i)[1].equals("8")) {
				buseo2.get(i)[1] = "사원";
			} else if(buseo2.get(i)[1].equals("9")) {
				buseo2.get(i)[1] = "인턴";
			}
			
			result += String.format("||  %s  ||  %s  ||  %s  ||  %,11d원 ||\n"
	
												, buseo2.get(i)[0]
												, buseo2.get(i)[1]
												, buseo2.get(i)[2]
												,Integer.parseInt(buseo2.get(i)[3]));
			
		}
		
		return result;
		} else {
			result = "잘못된 부서 입력입니다.";
			return result;
		}
		
	}
	
	
	//직급별 정렬용 문자열 -> 숫자 변환
	private int level(String jik) {
		
		if(jik.equals("사장")) {
			return 1;
		} else if(jik.equals("전무")) {
			return 2;
		} else if(jik.equals("상무")) {
			return 3;
		} else if(jik.equals("부장")) {
			return 4;
		} else if(jik.equals("차장")) {
			return 5;
		} else if(jik.equals("과장")) {
			return 6;
		} else if(jik.equals("대리")) {
			return 7;
		} else if(jik.equals("사원")) {
			return 8;
		}    else if(jik.equals("인턴")) {
			return 9;
		}
		return 0;
	}
	
	
	///////////////////////////////////      검색 목록 프린트          ///////////////////////////
	
	// 1명 검색 프린트 (user / 직원 검색시 사용)
		private void printPerson(String ment ,int index, int pay) {
			
			System.out.println();
			System.out.println();
			System.out.println("===============================================");  
			System.out.printf("||   이름  ||  부서  ||  직급  ||   %9s ||  \n",ment);
			System.out.println("-----------------------------------------------");  
			System.out.printf("||  %s  ||  %s  ||  %s  ||  %,11d원 ||\n",list.get(index)[0], list.get(index)[2], list.get(index)[1],pay);
			System.out.println("===============================================");  
			System.out.println();
			System.out.println();
			
			
		}
		
		// 부서 검색 프린트 
		private void printBuseo(String ment, String result) {
			System.out.println();
			System.out.println();
			System.out.println("===============================================");  
			System.out.printf("||  부서  ||  직급  ||  이름   ||   %9s ||  \n",ment);
			System.out.println("-----------------------------------------------");
			System.out.println(result);
			System.out.println("===============================================");  
			System.out.println();
			System.out.println();
			
		}
		

	
	
	//////////////////    HR 파일 Load 
	
	// HR 데이터 불러오기 > 프로그램 시작 전
	public void load() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			
			String line = "";
			
			while((line = reader.readLine())!=null) {
				//석박남,대리,인사,D,5,월급,근무시간(월)
				String [] temp = line.split(",");
//				System.out.println(Arrays.toString(temp));
				list.add(temp);

			}

//			for(int i =0; i<list.size(); i++) {
//			System.out.println(Arrays.toString(list.get(i)));
//			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	
	
	
}
