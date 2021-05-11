package Contact;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import ASAP.Util;

/**
 * 주소록 구현 클래스
 * 사용자는 주소록을 통해 사원들의 인적정보를 검색하거나, 개인 주소록을 만들 수 있다.
 * @param scan 사용자로부터 입력 받는 Scanner 변수
 * @param list DATA에 저장한 정보를 모두 불러들여 저장
 * @param DATA 파일의 정보가 저장된 위치를 저장
 * @param DATA2 파일의 정보가 저장된 위치를 저장
 
 * @param num 입력받는 카테고리 번호
 * @param name 입력받는 이름
 * @param position 입력받는 직급
 * @param buseo 입력받는 부서
 */
public class ContactList {
	private static Scanner scan = new Scanner(System.in);
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	private final String DATA;
	private final String DATA2;
	
	private int num;
	private String name;
	private String position;
	private String buseo;
	
	//오름차순
	
	public ContactList() {
		DATA = "data\\Contact.txt";
		DATA2 = "data\\MyContactList.txt";
		name = "";
		load();
	}
	
	/**
	 * DATA에 있는 정보를 모두 읽어 list에 저장한다. 
	 */
	private void load() {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			String line = "";
			String temp2 = "";
			
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				list.add(temp);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}//load()
	
	
	/**
	 * 사용자가 주소록에 진입하여 가장 처음 보게 되는 카테고리
	 * 여기서 원하는 카테고리 번호를 입력받는다.
	 */
	public void firstScreen()  {
				
				System.out.println("===============================================");
				System.out.println("|| 1.이름  || 2. 직급 || 3. 부서 || 4.  개인 ||");
				System.out.println("||   검색  ||    검색 ||    검색 ||   주소록 ||");
				System.out.println("===============================================");
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
						searchName();
					} else if (num == 2) {
						searchPosition();
					} else if (num == 3) {
						searchBuseo();
					} else if (num == 4) {
						makeIndividual();
					} else {
						System.out.println("잘못된 번호가 선택되었습니다.");
					}
					
				} catch (Exception e) {
					System.out.println(e);
				}
	}//clScreen()
	
	
	/**
	 * 개인 주소록 카테고리로 들어와서
	 * 원하는 카테고리 번호를 입력 받고 해당 메소드로 넘겨주는 메소드. 
	 */
	private void makeIndividual() {
		
		System.out.println("\n\n=======================================");
		System.out.println("|| 1. 개인 주소록  || 2. 개인 주소록 ||");
		System.out.println("||           확인  ||           추가 ||");
		System.out.println("=======================================");
		System.out.println();
		System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요.");
		System.out.println("▶ 목차로 돌아가려면 0번을 누르세요.\n");
		System.out.print("카테고리 번호 : ");
		this.num = scan.nextInt();
		scan.nextLine(); // 입력 받으면서 들어온 \r\n을 지워주고 가야함.
		
		try {
			if (num == 0) {
				cls();
				firstScreen();
			} else if (num == 1) {
				checkIndivisual();
			} else if (num == 2) {
				inputIndividual();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * 개인주소록 파일(DATA2)에 존재하는 정보를 모두 읽어서 출력하는 메소드.
	 */
	private void checkIndivisual() {

			try {
				BufferedReader reader = new BufferedReader(new FileReader(DATA2));
				String line = "";
				System.out.println("==========================\n");
				System.out.println("개인 주소록\n");
				System.out.println(" [이름]  [아이디]  [이메일]  [전화번호]  [직급]  [부서]");
				System.out.println("=====================================================");
				
				while ((line = reader.readLine()) != null) {
					String[] temp = line.split(",");
					
					System.out.printf("\n|| %3s || %7s || %s || %s || %s || %s ||"
							, temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
					
				}
				reader.close();
				
				System.out.println("\n=====================================================");
				System.out.print("▶ 목차로 돌아가려면 0번을 누르세요 : ");
				this.num = scan.nextInt();
				
				//다시 목차로 돌려주기
				if (num == 0) {
					cls();
					firstScreen();
				}
				scan.nextLine();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}//check()
	
	/**
	 * 사용자에게 입력받은 정보를 개인주소록 파일(DATA2)에 저장하는 메소드. 
	 */
	private void inputIndividual() {
		
		Util juso = new Util();
		
		System.out.println("==========================\n");
		System.out.println("[개인주소록 추가]\n");
		
		System.out.print("이름: ");
		String name = scan.nextLine();
		
		String id = Util.get("아이디");
		String email = Util.get("이메일");
		String phone = Util.get("전화번호");
		String position = Util.get("직급");
		String buseo = Util.get("부서");
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA2, true));
			writer.write(String.format("%s,%s,%s,%s,%s,%s\n", name, id, email, phone, position, buseo));
			writer.close();
			
			System.out.println("==========================\n");
			System.out.print("▶ 목차로 돌아가려면 0번을 누르세요 : ");
			this.num = scan.nextInt();
			
			//다시 목차로 돌려주기
			if (num == 0) {
				cls();
				firstScreen();
			}
			scan.nextLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}//input()
	
	
	/**
	 * 사용자에게 부서명을 입력받고, 그와 동일한 부서명들이 가진 정보를 모두 출력하는 메소드
	 */
	private void searchBuseo() {
		
		System.out.println("\n==========================\n");
		System.out.println("검색 하고자 하는 부서 명을 입력하세요. ");
		System.out.println("[회계] [재무] [인사] [영업] [마케팅] [개발] [디자인] [사업]\n");
		System.out.print("부서: ");
		this.buseo = scan.nextLine(); 
		System.out.println();
		
		String line = "";
		
		System.out.println("================================================================================================");
		System.out.println("||부서\t||이름  \t||아이디\t||    \t이메일         \t  ||전화번호\t||직급\t||");
		System.out.println("================================================================================================");
		
		for (int i=0; i<list.size(); i++) {
			
			if((list.get(i)[6]).equals(this.buseo)) {
				
				line = String.format("||%s\t||%s\t||%s\t||%23s\t||%s\t||%s||"
						,list.get(i)[6] ,list.get(i)[2] ,list.get(i)[0] ,list.get(i)[3] ,list.get(i)[4] ,list.get(i)[5]);
				
				System.out.println(line);
			}
		}//for
		if (line.equals("")) {
			System.out.println("잘못된 부서입니다.");
		}
		System.out.println("============================================================================================\n");
		System.out.print("▶ 목차로 돌아가려면 0번을 누르세요 : ");
		this.num = scan.nextInt();
		
		//다시 목차로 돌려주기
		if (num == 0) {
			cls();
			firstScreen();
		}
		scan.nextLine();
		
	}//buseo()
	
	
	/**
	 * 사용자에게 직급명을 입력받고, 그와 동일한 직급명들이 가진 정보를 모두 출력하는 메소드
	 */
	private void searchPosition() {
		
		System.out.println();
		System.out.println("==========================\n");
		System.out.println("검색 하고자 하는 직급 명을 입력하세요. ");
		System.out.println("[인턴] [사원] [대리] [과장] [차장] [부장] [상무] [전무] [사장]\n");
		System.out.print("직급: ");
		this.position = scan.nextLine(); 
		System.out.println();
		
		String line = "";
		
		System.out.println("=================================================================================================");
		System.out.println("||직급\t||이름  \t||아이디 \t||이메일                    \t||전화번호\t||부서\t||");
		System.out.println("==================================================================================================");

		
		for (int i=0; i<list.size(); i++) {
			
			if((list.get(i)[5]).equals(this.position)) {
				
				line = String.format("||%s\t||%s\t||%s\t||%23s\t||%s\t||%4s||"
							,list.get(i)[5] ,list.get(i)[2] ,list.get(i)[0] ,list.get(i)[3] ,list.get(i)[4] ,list.get(i)[6]);
				
				System.out.println(line);
				}
		}//for
		if (line.equals("")) 
			System.out.println("잘못된 직급입니다.");
		
		System.out.println("================================================================================================\n");
		System.out.print("▶ 목차로 돌아가려면 0번을 누르세요 : ");
		this.num = scan.nextInt();
		
		//다시 목차로 돌려주기
		if (num == 0) {
			cls();
			firstScreen();
		}
		scan.nextLine();
		
	}//position()
	
	
	/**
	 * 사용자에게 이름을 입력받고, 그와 동일한 이름이 가진 정보를 모두 출력하는 메소드
	 */
	private void searchName() {
		
		System.out.println();
		System.out.println("==========================\n");
		System.out.println("검색하고자 하는 이름을 입력하세요.\n");
		System.out.print("이름: ");
		this.name = scan.nextLine(); 
		System.out.println();
		
		String line = "";
		
		System.out.println("============================================================================================");
		System.out.println("||   이름   ||   아이디  ||       이메일       ||     전화번호    ||   직급   ||   부서   ||");
		System.out.println("============================================================================================");
		
		
		for (int i=0; i<list.size(); i++) {
				
			//list.get(i)[2] = temp[2] / Array접근->배열접근->값접근 : 이게 내가 찾는 값. (string) 
			if ((list.get(i)[2]).equals(this.name)){
				
				line += String.format("||  %s  ||   %s   || %s  ||  %s  ||   %s   ||   %s   ||"
							,list.get(i)[2] ,list.get(i)[0] ,list.get(i)[3] ,list.get(i)[4] ,list.get(i)[5] ,list.get(i)[6]);
				}
		}//for
		
		//데이터에 없는 값이 입력되면 그냥 아무 것도 출력 XXX -> 그래서 빈 문자열이 나오면 그걸 걸러주는 것. 
		if ((line.equals("")) ) {
			System.out.println("잘못된 이름입니다");
		} else {
			System.out.println(line);
		}
		
		System.out.println("============================================================================================\n");
		System.out.print("▶ 목차로 돌아가려면 0번을 누르세요 : ");
		this.num = scan.nextInt();
		
		//다시 목차로 돌려주기
		if (num == 0) {
			cls();
			firstScreen();
		}
		scan.nextLine();
	}//Name()
	
	/**
	 * 콘솔창에 출력되는 내용의 가독성을 높이기 위해 줄을 바꿔주는 메소드.
	 */
	private static void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}//cls()


}//class


