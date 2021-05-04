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
	ArrayList<String[]> contect = new ArrayList<String[]>();
	ArrayList<String[]> hr = new ArrayList<String[]>();

	public HR() {

		DATA = "data//HR.txt";
		DATA2 = "data//Contact.txt";
		scan = new Scanner(System.in);

	}

	public void hrScreen() {

		// 관리자 전용 비밀번호 -> 권한 있는 사용자에게만 배포
		int pw = 1234;

		System.out.println("관리자 비밀번호를 입력하세요.");
		pw = scan.nextInt();
		System.out.println("=======================");
		System.out.print("      PW: ");
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
			System.out.println("=============================================================");
			System.out.println("|| 1. 부서  || 2. 직급 || 3. 신입사원 || 4. 퇴사직원 || 5. 고가데이터 ||");
			System.out.println("||    변경  ||    변경 ||    정보추가 ||    정보삭제 ||        관리 ||");
			System.out.println("=============================================================");
			System.out.print(" 카테고리(번호)를 선택히세요: ");	
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
		//홍길동,차장,인사,A,12
		
		System.out.println(" ▣ 고가 데이터 관리 화면 ▣");
		System.out.println("==============================");
		System.out.println("||1. 고가 데이터 || 2. 고가 데이터 ||");
	    System.out.println("||        출력 ||         변경 ||");
		System.out.println("==============================");
		int num = Integer.parseInt(Util.get("카테고리(번호)를 선택히세요"));
		
		cls();
		
		String name = (Util.get("작업할 직원의 이름을 입력하세요"));
		
		int index = -1;
		for (int i = 0; i < hr.size(); i++) {

			// list 방 찾기
			if (hr.get(i)[0].equals(name)) {
				index = i;
				break;
			}
		}
		
		if(hr.get(index)[0].equals(name)) {
			if(num == 1) {
				//save() 출력할 때 고가데이터만 출력되도록 출력...?
				//hr.get(index); 
				
			} else if(num == 2) {
				
				
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
		
		
		
		
		
		
	}//goga()

	private void delete() {
		System.out.println("▣ 퇴사 직원 삭제 화면 ▣");
		String name = (Util.get("삭제할 직원의 이름을 입력하세요"));
		
		for(int i=0; i<contect.size(); i++) {
			if(contect.get(i)[2].equals(name)) {
				
				contect.remove(i);
			}
		}
		save();
		
	}//delete()

	private void add() {
        //t,t,홍길동,a@sis2.co.kr,010-1234-1234,인턴,인사
		
//		System.out.println("▣ 신입 사원 추가 화면 ▣");
//		String name = (Util.get("이름"));
//		String id = (Util.get("아이디"));
//		String pw = (Util.get("비밀번호"));
//		String email = (Util.get("이메일"));
//		String phone = (Util.get("전화번호"));
//		String position = (Util.get("직급"));
//		String buseo = (Util.get("부서"));
//		
//		User user = new User();
//		
//		user.setName(name);
//		user.setId(id);
//		user.setPw(pw);
//		user.setEmail(email);
//		user.setPhone(phone);
//		user.setPosition(position);
//		user.setDepart(buseo);
//		
//		list.add(user); 
		
		
		
		
	}//add()

	private void positionAlter() {
		// t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사 -> temp[5]
		
		System.out.println("                           ▣ 직급 변경 화면 ▣");
		System.out.println("=====================================================================");
		System.out.println("||1.인턴 ||2.사원 ||3.대리 ||4.과장 ||5.차장 ||6.부장 ||7.상무 ||8.전무 ||9.사장||");
		System.out.println("=====================================================================");
		String name = (Util.get("변경할 직원의 이름을 입력하세요"));
		System.out.println();

		int index = -1;
		for (int i = 0; i < contect.size(); i++) {

			// list 방 찾기
			if (contect.get(i)[2].equals(name)) {
				index = i;
				break;
			}
			//System.out.println(list);
		}

		System.out.printf("■ %s님의 정보 ■\n", contect.get(index)[2]);
//		System.out.println(list.get(index));
//		System.out.println();
	// 입력된 사원 정보 보여주기
      String s = String.format("현재 직급: %s", contect.get(index)[5]);
           System.out.println(s); 
           System.out.println();
     
		int num = Integer.parseInt(Util.get("변경할 직급을 선택하세요(번호)"));

	// "인턴","사원","대리","과장","차장","부장" + 상무, 전무, 사장
	if(contect.get(index)[2].equals(name)) {
		
		if (num == 1) {

			contect.get(index)[5] = "인턴";
			
		} else if (num == 2) {
			
			contect.get(index)[5] = "사원";

		} else if (num == 3) {
			
			contect.get(index)[5] = "대리";

		} else if (num == 4) {

			contect.get(index)[5] = "과장";
			
		} else if (num == 5) {

			contect.get(index)[5] = "차장";
			
		} else if (num == 6) {

			contect.get(index)[5] = "부장";
			
		} else if (num == 7) {

			contect.get(index)[5] = "상무";
			
		} else if (num == 8) {

			contect.get(index)[5] = "전무";
			
		} else if (num == 9) {

			contect.get(index)[5] = "사장";
			
		} else {

			System.out.println("잘못된 번호를 입력하셨습니다.");
		}

	}
	//System.out.println(Arrays.toString(list.get(index)));
	save();
	
}//positionAlter()

	private void buseoAlter() {
		// t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사 -> temp[6]
		
		System.out.println("                          ▣ 부서 변경 화면 ▣");
		System.out.println("===================================================================");
		System.out.println("||1.회계 ||2.재무 ||3.인사 ||4.영업 ||5.마케팅 ||6.개발 ||7.디자인 || 8.사업 ||");
		System.out.println("===================================================================");
		String name = (Util.get("변경할 직원의 이름을 입력하세요"));
		System.out.println();
		
		int index = -1;
		for (int i = 0; i < contect.size(); i++) {

			// list 방 찾기
			if (contect.get(i)[2].equals(name)) {
				index = i;
				break;
			}
			//System.out.println(list);
		}
		
		System.out.printf("■ %s님의 정보 ■\n", contect.get(index)[2]);
		
		 String s = String.format("현재 부서: %s", contect.get(index)[6]);
         System.out.println(s); 
         System.out.println();
   
		int num = Integer.parseInt(Util.get("변경할 부서를 선택하세요(번호)"));
		
	// "회계","재무","인사","영업","마케팅","개발","디자인"
	if(contect.get(index)[2].equals(name)) {
	
		if (num == 1) {
			
			contect.get(index)[6] = "회계";

		} else if (num == 2) {
			
			contect.get(index)[6] = "재무";

		} else if (num == 3) {
			
			contect.get(index)[6] = "인사";

		} else if (num == 4) {
			
			contect.get(index)[6] = "영업";

		} else if (num == 5) {
			
			contect.get(index)[6] = "마케팅";

		} else if (num == 6) {
			
			contect.get(index)[6] = "개발";

		} else if (num == 7) {
			
			contect.get(index)[6] = "디자인";

		} else if(num == 8) {
			
			contect.get(index)[6] = "사업";
		
		} else {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}
	}
	save();
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
				this.contect.add(temp);
			}

			BufferedReader reader2 = new BufferedReader(new FileReader(DATA));

			 line = "";
			while ((line = reader.readLine()) != null) {

				String[] temp = line.split(",");
//				System.out.println(Arrays.toString(temp));
				this.hr.add(temp);
			}
			
		} catch (Exception e) {

			System.out.println(e);
		}
	}//read()

	private void save() {// 바뀐 내용 저장하는 작업
//		t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사
//		데이터읽기
//		데이터작업
//		데이터쓰기 (저장)
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(DATA2));
		
		//[bgentery9j, Fgziy3, 민천석, gbrik9j@sist2.co.kr, 010-7894-9146, 대리, 디자인]
		for(int i=0; i<contect.size(); i++) {
			//System.out.println(Arrays.toString(save));
			
			writer.write(String.format("%s,%s,%s,%s,%s,%s,%s\n"
											 , contect.get(i)[0]
											 , contect.get(i)[1]
											 , contect.get(i)[2]
											 , contect.get(i)[3]
											 , contect.get(i)[4]
											 , contect.get(i)[5]
											 , contect.get(i)[6]));
		}
		
		writer.close();
		
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(DATA));
		
		//홍길동,차장,인사,A,12
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

	}//save()

}// class
