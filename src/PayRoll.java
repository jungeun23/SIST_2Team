import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class PayRoll {

	private final String DATA;
	private ArrayList<String[]> list;
	private Scanner scan;
	private User user;
	
	public PayRoll() {
		
		DATA = "data\\HR.txt";
		//송월승,대리,마케팅,E,7,3200000
		list = new ArrayList<String[]>();
		scan = new Scanner(System.in);
		user = new User();
	}
	
	
	// 개인 - 성과급 
	public void bonus(User user) {
		////송월승,대리,마케팅,E,7,3200000
		//일반 유저 접근
		this.user = user; //main에서 받아온 유저 PayRoll 변수에 저장해줌

		int index = userIndex(this.user); // user의 list index값을 알려줌
		
		int bonus = bonusPay(index); // index 값 보내서 bonus 수당 값을 데려옴
		
		String ment = "지급된 성과급";
		
		printPerson(ment,index,bonus);
	}
	
	
	// 개인 - user index 넘버 구하기
	private int userIndex(User user) {
		this.user = user;
		int index = -1;
		for(int i=0; i<list.size();i++) {
			if(user.getName().equals(list.get(i)[0])) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	
	// 성과급 계산
	private int bonusPay(int index) {
		
		double pi = 0.0;
		
		int salary = Integer.parseInt(list.get(index)[5]);
		
		//고과 점수에 따라 월급의 ps배가 성과급으로 차등 지급
		if(list.get(index)[3].equals("A") || list.get(index)[3].equals("B") ) { 
			pi = 1.0;
		} else if(list.get(index)[3].equals("C") || list.get(index)[3].equals("D")) {
			pi = 0.8;
		} else {
			pi = 0.6;
		}
		
		int bonus = (int)(salary * pi);
		
		
		return bonus;
	}
	
	//개인 - 연장 근무 수당
	public void extraWork(User user) {
		//일반 유저 접근
		this.user = user;

		int index = userIndex(this.user); // user의 list index값을 알려줌
		
		
	}
	
	//인사부 급여관리 메뉴 접근
	public void HrTeam() {
		System.out.println();
		System.out.println();
		System.out.println(" [5. HR - 인사부 급여관리]");
		
		System.out.println();
		System.out.println();
		String hrPw = (Util.get("인사부 권한 비밀번호 입력"));
		
		if(hrPw.equals("1234")) {
			
			approved();
		} else {
			System.out.println("올바르지 않은 입력입니다.");
		}
		
	}
	
	//인사 급여관리 메뉴 
	private void approved() {
		System.out.println();
		System.out.println();
		System.out.println("1. 직원 연장 근무 수당 조회");
		System.out.println("2. 직원 성과급 조회");
		System.out.println("3. 직원 급여 조회");
		System.out.println("4. HR 수당관리 목록으로 돌아가기");
		System.out.println();
		int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
		System.out.println();
		System.out.println();
	
		
		if(n == 1) {
			
			allExtraWork();
			
		} else if(n == 2) {
			
			allBonus();
			
		}else if(n == 3) {
			
			allSalary();
			
		} else {
			
			//목록 돌아가기
		}
	}

	// 인사부 검색 기능 메뉴
	private int allNum() {
		System.out.println();
		System.out.println("===============");
		System.out.println("1. 부서 검색");
		System.out.println("2. 직원 검색");
		System.out.println("3. HR 수당관리 목록으로 돌아가기");
		System.out.println();
		int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
		System.out.println();
		System.out.println();
		
		return n;
	}
	
	// 인사부 - 급여 선택 메뉴 
	private void allSalary() {
		
		int n = allNum();
		
		if(n == 1) {
			
			searchBuseoSalary();
			
		} else if (n ==2) {
			
			searchPersonSalary();
			
		} else if (n == 3) {
			//목록 돌아가기
			
		} else {
			System.out.println("잘못된 입력입니다.");
		}
		
		
	}

	

	//인사부 - 급여 - 부서검색
	private void searchBuseoSalary() {
		
		String result = searchBuseo("salary");
		
		String ment = "지급된  급여";
		printBuseo(ment,result);
		
	}

	// 부서 검색 반복 메소드!!
	private String searchBuseo(String what) {
		
		System.out.println("[1.사업] [2.인사] [3.재무] [4.회계] [5.영업] [6.개발] [7.마케팅] [8.디자인] ");
		String buseo = Util.get("[부서 검색] 부서를 입력하세요");

		String result = "";
		
		ArrayList<String[]> buseo2 = new ArrayList<String[]>();
		
		
		for(int i = 0; i<list.size(); i++) {	
			
			if(list.get(i)[2].equals(buseo)) {
	
				int pay = 0;
				if(what.equals("salary")) { //급여면 급여 돈
				
					pay = Integer.parseInt(list.get(i)[5]);
				
				} else if(what.equals("bonus")) { //보너스면 보너스 돈
					pay = bonusPay(i);
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
			
			result += String.format("||  %s  ||  %s  ||  %s  ||  %,d원 ||\n"
	
												, buseo2.get(i)[0]
												, buseo2.get(i)[1]
												, buseo2.get(i)[2]
												,Integer.parseInt(buseo2.get(i)[3]));
			
		}
		
		return result;
		
	}
	
	
	
	//인사부 - 급여 - 직원검색
	private void searchPersonSalary() {
		
		int index = searchPersonIndex();
		String ment = "지급된  급여";
		int salary = Integer.parseInt(list.get(index)[5]);
		printPerson(ment,index,salary);
		
	}

	// 인사부 - 성과급 선택 메뉴
	private void allBonus() {
		int n = allNum();
		
		if(n == 1) {
			
			searchBuseoBonus();
			
		} else if (n ==2) {
			
			searchPersonBonus();
			
		} else if (n == 3) {
			//목록 돌아가기
			
		} else {
			System.out.println("잘못된 입력입니다.");
		}
		
	}
	
	// 인사부 - 성과급 - 부서검색
	private void searchBuseoBonus() {
		String result = searchBuseo("bonus");
		String ment = "지급된  급여";
		
		printBuseo(ment,result);
		
	}


	// 인사부 - 성과급 - 직원검색
	private void searchPersonBonus() {
		
		int index = searchPersonIndex();
		String ment = "지급된 성과급";
		int bonus = bonusPay(index);
		printPerson(ment,index,bonus);
		
	}//searchPersonBonus()

	
	// 직원 이름 검색
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
	
	
	

	//인사부 - 연장근무 선택 메뉴
	private void allExtraWork() {
		int n = allNum();
		
		if(n == 1) {
			
			//searchBuseoExtraWork();
			
		} else if (n ==2) {
			
			//searchPersonExtraWork();
			
		} else if (n == 3) {
			//목록 돌아가기
			
		} else {
			System.out.println("잘못된 입력입니다.");
		}
		System.out.println("전직원 연장근무");
		
	} //allExtraWork()

	//직급별 정렬
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
	
	
	
	
	// 1명 검색 프린트 (user / 직원 검색시 사용)
		private void printPerson(String ment ,int index, int pay) {
			
			System.out.println();
			System.out.println();
			System.out.println("=============================================");  // UI 피드백 필요 
			System.out.printf("||   이름  ||  부서  ||  직급  ||   %s  ||  \n",ment);
			System.out.println("----------------------------------------------");  
			System.out.printf("||  %s  ||  %s  ||  %s  ||  %,d원 ||\n",list.get(index)[0], list.get(index)[2], list.get(index)[1],pay);
			System.out.println("=============================================");  
			System.out.println();
			System.out.println();
			scan.nextLine();
			
		}
		
		// 부서 검색 프린트 
		private void printBuseo(String ment, String result) {
			System.out.println();
			System.out.println();
			System.out.println("==============================================");  // UI 피드백 필요 
			System.out.printf("||  부서  ||  직급  ||  이름   ||   %s  ||  \n",ment);
			System.out.println("----------------------------------------------");
			System.out.println(result);
			System.out.println("==============================================");  
			System.out.println();
			System.out.println();
			scan.nextLine();
		}
		

	
	
	
	
	// HR 데이터 불러오기 > 프로그램 시작 전
	public void load() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			
			String line = "";
			
			while((line = reader.readLine())!=null) {
				//석박남,대리,인사,D,5,월급,근무시간(월)
				String [] temp = line.split(",");
				
				list.add(temp);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	
	
	
}
