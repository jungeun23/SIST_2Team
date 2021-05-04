import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HR {

	private final String DATA;
	private final String DATA2;
	private Scanner scan;
	ArrayList<String[]> contact = new ArrayList<String[]>();
	ArrayList<String[]> hr = new ArrayList<String[]>();

	public HR() {

		DATA = "data//HR.txt";
		DATA2 = "data//Contact.txt";
		scan = new Scanner(System.in);

	}

	public void hrScreen() {

		// 관리자 전용 비밀번호 -> 권한 있는 사용자에게만 배포
		cls();
		int pw = 1234;

		System.out.println("관리자 비밀번호를 입력하세요.");
		System.out.println("=======================");
		System.out.print("      PW: ");
		pw = scan.nextInt();
		System.out.println("=======================");
		System.out.println();
		
		if (pw == 1234) {

			System.out.println("     [로그인 성공]");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			
			read();
			
			// cls();
			System.out.println("                       ▣ 인사 관리 항목 ▣");
			System.out.println("=======================================================================");
			System.out.println("|| 1. 부서  || 2. 직급 || 3. 신입사원 || 4. 퇴사직원 || 5. 고가데이터 ||");
			System.out.println("||    변경  ||    변경 ||    정보추가 ||    정보삭제 ||        관리   ||");
			System.out.println("=======================================================================");
			System.out.print(" 카테고리(번호)를 선택하세요: ");	
			int num = scan.nextInt();
			 
			cls();
			
			if (num == 1) {
				buseoAlter();

			} else if (num == 2) {
				positionAlter();

			} else if (num == 3) {
				add();

			} else if (num == 4) {
				delete();

			} else if(num == 5) {
				goga();
				
			} else {

				System.out.println("카테고리(번호)를 다시 입력하세요.");
			}
			
			

		} else {
			System.out.println("권한이 있는 사용자가 아닙니다.");
			System.out.println("비밀번호를 다시 입력해주세요.");
		}

	}// hrScreen()

	private void goga() {
		
		System.out.println(" ▣ 고가 데이터 관리 화면 ▣");
		System.out.println("====================================");
		System.out.println("||1. 고가 데이터 || 2. 고가 데이터 ||");
	    System.out.println("||          출력 ||         변경   ||");
		System.out.println("====================================");
		int num = Integer.parseInt(Util.get("카테고리(번호)를 선택하세요"));
		
		cls();
		
		if(num == 1) {
			gogaPrint();
			
		} else if(num == 2) {
			gogaAlter();
			
		} else {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}
			
		}//goga()
		

	private void gogaAlter() {

		System.out.println("         ▣ 고가 데이터 변경 화면 ▣");
		System.out.println("====================================");
		System.out.println("||1. A ||2. B ||3. C ||4. D ||5. E ||");
		System.out.println("====================================");
		String name = (Util.get("변경할 직원의 이름을 입력하세요"));
		System.out.println();
		
		int index = -1;
		for (int i = 0; i < hr.size(); i++) {

			// list 방 찾기
			if (hr.get(i)[0].equals(name)) {
				index = i;
				break;
			}
		}
		
		int num = Integer.parseInt(Util.get("변경할 데이터를 선택하세요(번호)"));
		
		if(hr.get(index)[0].equals(name)) {
			
			if(num == 1) {
				hr.get(index)[3] = "A";
				
			} else if(num == 2) {
				hr.get(index)[3] = "B";
				
			} else if(num == 3) {
				hr.get(index)[3] = "C";
				
			} else if(num == 4) {
				hr.get(index)[3] = "D";
				
			} else if(num == 5) {
				hr.get(index)[3] = "E";
					
				} else {
					System.out.println("잘못된 번호를 입력하셨습니다.");
				}
		}
		
		hrsave();
		
		
	}//gogaAlter()

	private void gogaPrint() {

		System.out.println("▣ 고가 데이터 출력 화면 ▣");
		String name = (Util.get("출력할 직원의 이름을 입력하세요"));
		
		int index = -1;
		for (int i = 0; i < hr.size(); i++) {

			// list 방 찾기
			if (hr.get(i)[0].equals(name)) {
				index = i;
				break;
			}
		}
		
		if(hr.get(index)[0].equals(name)) {
			
			String s = String.format("[이름] %s\n[직급] %s\n[고가 데이터] %s\n"
							, hr.get(index)[0]
							, hr.get(index)[1]
							, hr.get(index)[3]);
			
			System.out.println(s);
		}
		
	}//gogaPrint() 

	private void delete() {
		System.out.println("▣ 퇴사 직원 삭제 화면 ▣");
		String name = (Util.get("삭제할 직원의 이름을 입력하세요"));
		
		for(int i=0; i<contact.size(); i++) {
			if(contact.get(i)[2].equals(name)) {
				
				contact.remove(i);
			}
		}
		contactsave();
		
	}//delete()

	private void add() {
        //t,t,홍길동,a@sis2.co.kr,010-1234-1234,인턴,인사
		
		System.out.println("▣ 신입 사원 추가 화면 ▣");
		String name = (Util.get("이름"));
		String id = (Util.get("아이디"));
		String pw = (Util.get("비밀번호"));
		String email = (Util.get("이메일"));
		String phone = (Util.get("전화번호"));
		String position = (Util.get("직급"));
		String buseo = (Util.get("부서"));
		
		String[] newPerson = new String[7];
		
		newPerson[0] = name;
		newPerson[1] = id;
		newPerson[2] = pw;
		newPerson[3] = email;
		newPerson[4] = phone;
		newPerson[5] = position;
		newPerson[6] = buseo;
		
		contact.add(newPerson);
		
		contactsave();
		
	}//add()

	private void positionAlter() {
		// t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사 -> temp[5]
		
		System.out.println("                           ▣ 직급 변경 화면 ▣");
		System.out.println("=================================================================================");
		System.out.println("||1.인턴 ||2.사원 ||3.대리 ||4.과장 ||5.차장 ||6.부장 ||7.상무 ||8.전무 ||9.사장||");
		System.out.println("=================================================================================");
		String name = (Util.get("변경할 직원의 이름을 입력하세요"));
		System.out.println();

		int index = -1;
		for (int i = 0; i < contact.size(); i++) {

			// list 방 찾기
			if (contact.get(i)[2].equals(name)) {
				index = i;
				break;
			}
			//System.out.println(list);
		}

		System.out.printf("■ %s님의 정보 ■\n", contact.get(index)[2]);
//		System.out.println(list.get(index));
//		System.out.println();
	// 입력된 사원 정보 보여주기
      String s = String.format("현재 직급: %s", contact.get(index)[5]);
           System.out.println(s); 
           System.out.println();
     
		int num = Integer.parseInt(Util.get("변경할 직급을 선택하세요(번호)"));

	// "인턴","사원","대리","과장","차장","부장" + 상무, 전무, 사장
	if(contact.get(index)[2].equals(name)) {
		
		if (num == 1) {

			contact.get(index)[5] = "인턴";
			
		} else if (num == 2) {
			
			contact.get(index)[5] = "사원";

		} else if (num == 3) {
			
			contact.get(index)[5] = "대리";

		} else if (num == 4) {

			contact.get(index)[5] = "과장";
			
		} else if (num == 5) {

			contact.get(index)[5] = "차장";
			
		} else if (num == 6) {

			contact.get(index)[5] = "부장";
			
		} else if (num == 7) {

			contact.get(index)[5] = "상무";
			
		} else if (num == 8) {

			contact.get(index)[5] = "전무";
			
		} else if (num == 9) {

			contact.get(index)[5] = "사장";
			
		} else {

			System.out.println("잘못된 번호를 입력하셨습니다.");
		}

	}
	//System.out.println(Arrays.toString(list.get(index)));
	contactsave();
	
}//positionAlter()

	private void buseoAlter() {
		// t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사 -> temp[6]
		
		System.out.println("                          ▣ 부서 변경 화면 ▣");
		System.out.println("=============================================================================");
		System.out.println("||1.회계 ||2.재무 ||3.인사 ||4.영업 ||5.마케팅 ||6.개발 ||7.디자인 ||8.사업 ||");
		System.out.println("=============================================================================");
		String name = (Util.get("변경할 직원의 이름을 입력하세요"));
		System.out.println();
		
		int index = -1;
		for (int i = 0; i < contact.size(); i++) {

			// list 방 찾기
			if (contact.get(i)[2].equals(name)) {
				index = i;
				break;
			}
			//System.out.println(list);
		}
		
		System.out.printf("■ %s님의 정보 ■\n", contact.get(index)[2]);
		
		 String s = String.format("현재 부서: %s", contact.get(index)[6]);
         System.out.println(s); 
         System.out.println();
   
		int num = Integer.parseInt(Util.get("변경할 부서를 선택하세요(번호)"));
		
	// "회계","재무","인사","영업","마케팅","개발","디자인"
	if(contact.get(index)[2].equals(name)) {
	
		if (num == 1) {
			
			contact.get(index)[6] = "회계";

		} else if (num == 2) {
			
			contact.get(index)[6] = "재무";

		} else if (num == 3) {
			
			contact.get(index)[6] = "인사";

		} else if (num == 4) {
			
			contact.get(index)[6] = "영업";

		} else if (num == 5) {
			
			contact.get(index)[6] = "마케팅";

		} else if (num == 6) {
			
			contact.get(index)[6] = "개발";

		} else if (num == 7) {
			
			contact.get(index)[6] = "디자인";

		} else if(num == 8) {
			
			contact.get(index)[6] = "사업";
		
		} else {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}
	}
	contactsave();
}//buseoAlter()

	private static void cls() {

		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}//cls()

	private void read() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA2));

			String line = "";
			while ((line = reader.readLine()) != null) {

				String[] temp = line.split(",");
//				System.out.println(Arrays.toString(temp));
				this.contact.add(temp);
			}
//			System.out.println(contect);

			BufferedReader reader2 = new BufferedReader(new FileReader(DATA));

			 line = "";
			while ((line = reader2.readLine()) != null) {

				String[] temp2 = line.split(",");
//				System.out.println(Arrays.toString(temp2));
				this.hr.add(temp2);
			}
			
			
		} catch (Exception e) {

			System.out.println(e);
		}
	}//read()

	private void contactsave() {// 바뀐 내용 저장하는 작업
//		t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사

		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(DATA2));
		
		//[bgentery9j, Fgziy3, 민천석, gbrik9j@sist2.co.kr, 010-7894-9146, 대리, 디자인]
		for(int i=0; i<contact.size(); i++) {
			//System.out.println(Arrays.toString(save));
			
			writer.write(String.format("%s,%s,%s,%s,%s,%s,%s\n"
											 , contact.get(i)[0]
											 , contact.get(i)[1]
											 , contact.get(i)[2]
											 , contact.get(i)[3]
											 , contact.get(i)[4]
											 , contact.get(i)[5]
											 , contact.get(i)[6]));
		}
		
		writer.close();
		
		
		System.out.println("[작업이 완료되었습니다.]");

		} catch (Exception e) {
			System.out.println(e);
		}

	}//Contactsave()
	
	private void hrsave() {
		
		try {
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(DATA));
			
			for(int i=0; i<hr.size(); i++) {
				//System.out.println(Arrays.toString(save));
				
				writer2.write(String.format("%s,%s,%s,%s,%s\n"
												 , hr.get(i)[0]
												 , hr.get(i)[1]
												 , hr.get(i)[2]
												 , hr.get(i)[3]
												 , hr.get(i)[4]));
			}
			
			writer2.close();
			
			System.out.println("[작업이 완료되었습니다.]");
			
	} catch (Exception e) {
		System.out.println(e);
	}
}//hrsave()
	

}// class
