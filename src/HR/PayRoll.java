package HR;
import ASAP.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;



/**
 * 수당관리 구현
 * 사용자는 자신의 연장근무 수당과 성과급을 확인할 수 있다.
 * 권한이 있는 사용자(인사부)는 직원들의 연장근무 수당, 성과급, 월급을 확인할 수 있다.
 * 인사부 전용 메뉴는 비밀번호 입력을 통해 접근이 가능하다.
 * 
 *@param DATA 전직원 정보인 HR의 정보가 저장된 위치  
 *@param list DATA에 위치한 파일을 윍어 전부 저장
 *@param scan 이용자의 입력받기
 *@param user 현재 접속중인 유저의 정보
 *@param mentBonus 성과급 출력 공통 멘트
 *@param mentExtraWork 연장 근무 수당 출력 공통 멘트
 *@param mentSalary 월급 출력 공통 멘트
 *@param mainMenu 메인 목록으로 돌아가기
 *
 */
public class PayRoll {

	private final String DATA;
	private ArrayList<String[]> list;
	private Scanner scan;
	private User user;
	private String mentBonus;
	private String  mentExtraWork;
	private String mentSalary;
	private Main mainMenu;
	
	/**
	 *  사용자가 메뉴창에서 수당관리를 선택 시 실행되는 생성자와 메소드
	 */
	public PayRoll() {
		
		this.DATA = "data\\HR.txt";
		this.list = new ArrayList<String[]>();
		this.scan = new Scanner(System.in);
		this.user = new User();
		this.mentBonus = "지급된 성과급";
		this.mentExtraWork = "지급된 연장 수당";
		this.mentSalary = "지급된  급여";
		this.mainMenu = new Main();
	
	}
	
	
	////////////////             개인 User 성과급, 연장근무 수당 조회          ///////////////////// 
	
	/**
	 * 사용자의 성과급 조회 메소드
	 * 사용자의 정보인 user를 매개변수로 받아 PayRoll의 멤버 변수 user에 저장해준다.
	 * user의 list index 값을 이용해서 성과급 수당 계산을 통해 성과급을 return 받고, 표를 출력한다.
	 * 
	 * @param user
	 */
	public void bonus(User user) {
		
		this.user = user;

		int index = userIndex(this.user); 
		
		int bonus = bonusPay(index); 
		
		printPerson(mentBonus,index,bonus);
		
		
		pause();
	}
	
	/**
	 * 사용자의 연장 근무 수당 조회 메소드
	 * 사용자의 정보인 user를 매개변수로 받아 PayRoll의 멤버 변수 user에 저장해준다.
	 * user의 list index 값을 이용해서 연장근무 수당 계산을 통해 수당 금액을 return 받고, 표를 출력한다.
	 * @param user
	 */
	public void extraWork(User user) {
		
		this.user = user;

		int index = userIndex(this.user); 

		int extraWork = extraWork(index);
		
		printPerson(mentExtraWork,index,extraWork);
		
		pause();
	}
	


	/**
	 * 현재 접속한 user의 list에서의 index값을 구하는 메소드
	 * @param user
	 * @return int index user의 list index값을 return
	 */
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
	

	/**
	 * 인사부 전용의 급여관리 메뉴 접근 창을 출력하는 메소드
	 * 비밀번호 입력을 통해 접근을 제한한다.
	 */
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
			System.out.println("[0. HR 수당관리 목록으로 돌아가기]");
			String num = (Util.get("번호를 입력해주세요"));
			if(num.equals("1")) {
				HrAccess();
			} else if (num.equals("0")){
				Main.showExtraPay();
			}
		}
		
	}
	
	
	/**
	 * 인사부 전용의 급여관리 메뉴 선택창을 출력하는 메소드
	 */
	private void approved() {
		System.out.println();
		System.out.println();
		
		System.out.println("===========================================================");
		System.out.println("|| 1. 연장 근무 || 2.  성과급 || 3.   급여 || 0.    목록 ||");
		System.out.println("||    수당 조회 ||       조회 ||      조회 ||    돌아가기||");
		System.out.println("===========================================================");
	
		System.out.println();
		int num = Integer.parseInt(Util.get("번호를 입력해주세요"));
		System.out.println();
		System.out.println();
	
		if(num == 1 || num == 2 || num == 3) {
			selSearch(num);
		} else if(num == 0) {
			Main.showExtraPay(); 
		} else {
			System.out.println("※ 올바르지 않은 입력입니다 ※");
			approved();
		}
		
	}


	
	
	/**
	 * 부서검색, 직원 검색, 목록 돌아가기를 선택하는 메뉴를 출력하는 메소드
	 * @param num 사용자가 approved()에서 선택한 메뉴 번호를 매개변수로 받는다.
	 */
	private void selSearch(int num) {
		
		System.out.println();
		System.out.println("========================================");
		System.out.println("|| 1.  부서 || 2.  직원 || 0.    목록 ||");
		System.out.println("||     검색 ||     검색 ||   돌아가기 ||");
		System.out.println("========================================");
		System.out.println();
		String n = (Util.get("번호를 입력해주세요"));
		System.out.println();
		System.out.println();
		
		if(n.equals("1")) {
			
			if(num == 1) { 
				searchBuseoExtraWork();
			} else if(num == 2) { 
				searchBuseoBonus();
			} else if(num == 3) { 
				searchBuseoSalary();
			}
			
		} else if (n.equals("2")) { 
			
			if(num == 1) { 
				searchPersonExtraWork();
			} else if(num == 2) { 
				searchPersonBonus();
			} else if(num == 3) { 
				searchPersonSalary();
			}
			
		} else if (n.equals("0")) {
			approved(); 
		} else {
			System.out.println("※ 올바르지 않은 입력입니다 ※");
			selSearch(num);
		}
		
	}

	/**
	 * 사용자가 급여 확인 후 목록으로 돌아가는 메소드
	 */
	private void pause() {
		
		System.out.println("엔터를 누르면 HR 수당 관리 목록으로 돌아갑니다.");
		scan.nextLine();
		Main.showExtraPay();
	}
	
	/**
	 * 인사부의 검색 기능에서 이어서 검색할지 목록으로 돌아갈지 선택하는 메소드
	 * @param search buseo 또는 person을 매개변수로 받아 부서 검색, 직원 검색 여부를 판단할 수 있다.
	 * @param num 매개변수 num으로 연장 근무 수당 조회, 성과급 조회, 월급 조회 여부를 판단할 수 있다.
	 */
	private void backToScreen(String search, int num) { 
		
		System.out.println("[ 1. 이어서 검색하기 ]");
		System.out.println("[ 0. HR 인사부 전용 목록으로 돌아가기 ]");
		
		int n = Integer.parseInt(Util.get("번호를 입력해주세요."));
		
		if(n == 1) {
			if(search.equals("buseo")) {
				if(num == 1) { 
					searchBuseoExtraWork();
				} else if(num == 2) { 
					searchBuseoBonus();
				} else if(num == 3) { 
					searchBuseoSalary();
				}
			} else if(search.equals("person")) {
				if(num == 1) {
					searchPersonExtraWork();
				} else if(num == 2) { 
					searchPersonBonus();
				} else if(num == 3) { 
					searchPersonSalary();
				}
			}
		} else if(n == 0) {
			approved();
		} else {
			System.out.println("※ 올바르지 않은 입력입니다 ※");
			backToScreen(search, num);
		}
		
	}
	
	
	/////////////////////////////       인사부  급여  (부서검색, 직원검색)      /////////////////////
	

	
	/**
	 * 사용자에게 부서이름을 입력받아 해당하는 부서의 월급을 전체 출력해주는 메소드
	 */
	private void searchBuseoSalary() {
		
		String result = searchBuseo("salary");
		if(!result.equals("잘못된 부서 입력입니다.")) {
		printBuseo(mentSalary,result);
		backToScreen("buseo",3);
		} else {
			searchBuseoSalary();
		}
	}

	
	/**
	 * 사용자에게 직원 이름을 입력받아 해당하는 직원의 월급을 출력해주는 메소드
	 */
	private void searchPersonSalary() {
		
		int index = searchPersonIndex();
		if(index >0) {
		int salary = Integer.parseInt(list.get(index)[5]);
		printPerson(mentSalary,index,salary);
		backToScreen("person",3);
		} else {
			System.out.println("존재하지 않는 이름입니다.");
			System.out.println("[1. 다시 검색하기]");
			System.out.println("[0. 직전 목록으로 돌아가기]");
			String n = (Util.get("번호를 입력해주세요."));
			
			if(n.equals("1")) {
				searchPersonSalary();
			} else  {
				approved();
			}
		}
	}
	
	//////////////////////////         인사부   성과급   (부서검색, 직원검색)      ///////////////////

	
	/**
	 * 사용자에게 부서이름을 입력받아 해당하는 부서의 성과급을 전체 출력해주는 메소드
	 */
	private void searchBuseoBonus() {
		
		String result = searchBuseo("bonus");
		if(!result.equals("잘못된 부서 입력입니다.")) {
			printBuseo(mentBonus,result);
			backToScreen("buseo",2);
		} else {
			searchBuseoBonus();
		}
	}

	/**
	 * 사용자에게 직원 이름을 입력받아 해당하는 직원의 성과급을 출력해주는 메소드
	 */
	private void searchPersonBonus() {
		
		int index = searchPersonIndex();
		if(index >0) {
		int bonus = bonusPay(index);
		printPerson(mentBonus,index,bonus);
		backToScreen("person",2);
		
		} else {
			System.out.println("존재하지 않는 이름입니다.");
			System.out.println("[1. 다시 검색하기]");
			System.out.println("[0. 직전 목록으로 돌아가기]");
			String n = (Util.get("번호를 입력해주세요."));
			
			if(n.equals("1")) {
				searchPersonBonus();
			} else {
				approved();
			}
			
		}
	}
	
	
	////////////////////////////        인사부 연장근무   (부서검색, 직원검색)    //////////////////////////
	

	/**
	 * 사용자에게 부서이름을 입력받아 해당하는 부서의 연장 근무 수당을 전체 출력해주는 메소드
	 */
	private void searchBuseoExtraWork() {
		
		String result = searchBuseo("extraWork");
		if(!result.equals("잘못된 부서 입력입니다.")) {
			printBuseo(mentExtraWork,result);
			backToScreen("buseo",1);
		} else {
			searchBuseoExtraWork();
		}
	}

	/**
	 * 사용자에게 직원 이름을 입력받아 해당하는 직원의 연장 근무 수당을 출력해주는 메소드
	 */
	private void searchPersonExtraWork() {
		
		int index = searchPersonIndex();
		if(index >0) {
		int extraWork = extraWork(index);
		printPerson(mentExtraWork,index,extraWork);
		backToScreen("person",1);
		
		} else {
			System.out.println("존재하지 않는 이름입니다.");
			System.out.println("[1. 다시 검색하기]");
			System.out.println("[0. 직전 목록으로 돌아가기]");
			String n = (Util.get("번호를 입력해주세요."));
			
			if(n.equals("1")) {
				searchPersonExtraWork();
			} else {
				approved();
			}
		}
	}
	
	
	////////////////////////////     성과급 , 연장근무 수당 계산 메소드      ////////////////////////
	
	/**
	 * 성과급 계산 메소드
	 * 고과 점수에 따라 월급의 pi배가 성과급으로 차등 지급
	 * @param index 입력받은 사용자의 index를 매개변수로 받아 list의 기본급을 이용한다.
	 * @return 계산한 성과급을 return한다.
	 */
	private int bonusPay(int index) {
		
		double productivityIncentive = 0.0;
		
		int salary = Integer.parseInt(list.get(index)[5]);
		
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
	
	

	/**
	 * 연장 근무 수당 계산 메소드
	 * 기본급/209H를 통해 통상 시급을 구한다.
	 * list의 한달치 근로 시간(분)을 법정근로시간으로 빼서 연장 근로 시간을 구한 뒤 통상 시급 * 1.5를 곱한다.
	 * @param index 입력받은 사용자의 index를 매개변수로 받아 list의 기본급을 이용한다.
	 * @return 계산한 연장 근무 수당을 return한다.
	 */
	private int extraWork(int index) {
	
		int salary = Integer.parseInt(list.get(index)[5]);
		
		int hourlyRate = salary / 209; 
		
		int workday = getWorkday();
		
		int extraTime =  Integer.parseInt(list.get(index)[6])/60; 
		
		int legalWorkingTime = workday * 8; 
		
		if(extraTime - legalWorkingTime > 0) {
			
			int extraWorkPay = (int)((extraTime - legalWorkingTime) * hourlyRate * 1.5); 
			
			return extraWorkPay;
			
		} else {
			return 0;
		}

	}

	

	/**
	 * 영업일수 구하는 메소드 
	 * @return 주말을 뺸 평일의 합을 구해 값을 return한다. 
	 */
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

	
	/**
	 * 사용자에게 직원 이름을 입력받아 list의 index를 찾는 메소드
	 * @return list의 index를 return한다.
	 */
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
	
	
	/**
	 * 부서 검색 메소드
	 * 사용자에게 입력받은 부서의 급여 리스트를 만들고, 직급, 이름을 기준으로 오름차순 정렬한다.
	 * @param what 월급,성과급,연장근무 중 무엇인지 매개변수로 입력받아서 출력할 급여를 결정한다.
	 * @return 부서의 리스트를 String으로 만들어 return한다.
	 */
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
				if(what.equals("salary")) { 
				
					pay = Integer.parseInt(list.get(i)[5]);
				
				} else if(what.equals("bonus")) { 
					pay = bonusPay(i);
				} else if(what.equals("extraWork")) {
					pay = extraWork(i);
				}

				String line = "";
				
				line = String.format("%s,%s,%s,%d"
										,list.get(i)[2], list.get(i)[1], list.get(i)[0],pay);
				
				String[] temp = line.split(",");
				
				buseo2.add(temp);
			}
		}
		
		for(int i=0; i<buseo2.size(); i++) { 
			buseo2.get(i)[1] =  ""+level(buseo2.get(i)[1]);
		}
		
		
		buseo2.sort(new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				
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
			
			result += String.format("||  %s  ||  %s  ||  %s  ||  %,14d원 ||\n"
	
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
	

	/**
	 * 직급별 정렬하기 위한 문자열을 숫자로 변환하는 메소드 
	 * @param position list index에 해당하는 직급을 매개변수로 받는다 
	 * @return 직급을 숫자로 변환한 값을 return한다.
	 */
	private int level(String position) {
		
		if(position.equals("사장")) {
			return 1;
		} else if(position.equals("전무")) {
			return 2;
		} else if(position.equals("상무")) {
			return 3;
		} else if(position.equals("부장")) {
			return 4;
		} else if(position.equals("차장")) {
			return 5;
		} else if(position.equals("과장")) {
			return 6;
		} else if(position.equals("대리")) {
			return 7;
		} else if(position.equals("사원")) {
			return 8;
		}    else if(position.equals("인턴")) {
			return 9;
		}
		return 0;
	}
	
	
	///////////////////////////////////      검색 목록 프린트          ///////////////////////////
	
	/**
	 * user 또는 직원 검색 결과를 표로 출력하는 메소드
	 * @param ment 성과급,연장근무수당,월급 중 출력할 문구 
	 * @param index 직원의 list index
	 * @param pay 검색한 급여
	 */
		private void printPerson(String ment ,int index, int pay) {
			
			System.out.println();
			System.out.println();
			System.out.println("=======================================================");  
			System.out.printf("||    이름  ||  부서  ||  직급  ||   %9s ||  \n",ment);
			System.out.println("-------------------------------------------------------");  
			System.out.printf("||  %s  ||  %s  ||  %s  ||  %,14d원 ||\n",list.get(index)[0], list.get(index)[2], list.get(index)[1],pay);
			System.out.println("=======================================================");  
			System.out.println();
			System.out.println();
			
			
		}
		
	
		/**
		 * 부서 검색 결과를 표로 출력하는 메소드
		 * @param ment 성과급, 연장 근무 수당, 월급 중 출력할 문구 
		 * @param result 출력할 표를 저장한 문자열
		 */
		private void printBuseo(String ment, String result) {
			System.out.println();
			System.out.println();
			System.out.println("=====================================================");  
			System.out.printf("||  부서  ||  직급  ||  이름   ||   %s ||  \n",ment);
			System.out.println("-----------------------------------------------------");
			System.out.println(result);
			System.out.println("=====================================================");  
			System.out.println();
			System.out.println();
			
		}
		

	
	
	//////////////////    HR 파일 Load 
	
	// HR 데이터 불러오기 > 프로그램 시작 전
	/**
	 * load()메소드를 생성자에 넣어, HR DB를 읽어 전부 저장한다.
	 */
	public void load() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			
			String line = "";
			
			while((line = reader.readLine())!=null) {
				String [] temp = line.split(",");
				list.add(temp);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	
	
	
}
