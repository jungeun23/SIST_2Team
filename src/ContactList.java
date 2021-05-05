import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ContactList {
	private static Scanner scan = new Scanner(System.in);
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	private final String DATA;
	private final String DATA2;
	
	private int num;
	private String name;
	private String position;
	private String buseo;
	
	
	public ContactList() {
		DATA = "data\\Contact.txt";
		DATA2 = "data\\MyContactList.txt";
		name = "";
		
	}
	
	//주소록 첫 화면 
	public void firstScreen()  {
				
				load();
				
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
	
	
	
	//개인 주소록 첫 화면
	private void makeIndividual() {
		
		System.out.println("=======================================");
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
	
	
	
	//개인 주소록 - 개인 주소록 확인
	private void checkIndivisual() {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA2));
			String line = "";
			System.out.println("[이름] [아이디] [이메일] [전화번호] [직급] [부서]");
			
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				
				System.out.printf("%s%s%s%s%s%s\n"
						, temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
				
				scan.nextLine();
			}
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//check()


	
	//개인 주소록 - 개인 주소록 추가
	private void inputIndividual() {
		
		Util juso = new Util();
		
		System.out.println("주소록 추가");
		
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		scan.nextLine();
	}//input()
	
	
	
	//주소록 - 부서 검색
	private void searchBuseo() {
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println();
		System.out.println("");
		System.out.print("부서: ");
		this.buseo = scan.nextLine(); 
		System.out.println();
		
		String line = "";
		
		System.out.println("============================================================================================");
		System.out.println("||   부서   ||   이름  ||       아이디       ||     이메일    ||   전화번호   ||   직급   ||");
		System.out.println("============================================================================================");
		
		for (int i=0; i<list.size(); i++) {
			
			if((list.get(i)[6]).equals(this.buseo)) {
				
				line = String.format("||  %s  ||   %s   || %s  ||  %s  ||   %s   ||   %s   ||"
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
	
	
	
	//주소록 - 직급 검색
	private void searchPosition() {
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println();
		System.out.println("검색 하고자 하는 직급 명을 입력하세요. ");
		System.out.println("[인턴] [사원] [대리] [과장] [차장] [부장] [상무] [전무] [사장]");
		System.out.println();
		System.out.print("직급: ");
		this.position = scan.nextLine(); 
		System.out.println();
		
		String line = "";
		
		System.out.println("============================================================================================");
		System.out.println("||  직급  ||   이름   ||    아이디 \t ||     이메일\t ||  전화번호\t ||   부서\t ||");
		System.out.println("============================================================================================");

		
		for (int i=0; i<list.size(); i++) {
			
			if((list.get(i)[5]).equals(this.position)) {
				
				line = String.format("||  %s  ||  %s  || %s\t ||  %s\t  ||   %s   ||   %s   ||"
							,list.get(i)[5] ,list.get(i)[2] ,list.get(i)[0] ,list.get(i)[3] ,list.get(i)[4] ,list.get(i)[6]);
				
				System.out.println("--------------------------------------------------------------------------------------------");
				System.out.println(line);
				}
		}//for
		if (line.equals("")) 
			System.out.println("잘못된 직급입니다.");
		
		System.out.println("============================================================================================\n");
		System.out.print("▶ 목차로 돌아가려면 0번을 누르세요 : ");
		this.num = scan.nextInt();
		
		//다시 목차로 돌려주기
		if (num == 0) {
			cls();
			firstScreen();
		}
		scan.nextLine();
		
	}//position()
	
	
	
	//주소록 - 이름 검색
	private void searchName() {
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println();
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
	
	
	
	// Contact.txt에서 더미 데이터를 읽어오는 작업
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
	
	
	
	//가독성 생각해서 콘솔 내려주기
	private static void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}//cls()


}//class


