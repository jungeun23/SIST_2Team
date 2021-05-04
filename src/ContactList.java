import java.io.BufferedReader;
import java.io.FileReader;
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
		DATA2 = "data\\MyContactList";
		name = "";
		
	}
	
	
	// temp[0] = 아이디, temp[1] = 비밀번호, temp[2] = 이름 
	// temp[3] = 이메일, temp[4] = 전화번호, temp[5] = 직급
	
	
	//주소록 첫 화면 
	public void firstScreen()  {
				
				load();
				
				System.out.println("================================================");
				System.out.println("|| 1.이름  || 2. 직급 || 3. 부서 || 0. 주소록 ||");
				System.out.println("||   검색  ||    검색 ||    검색 ||      추가 ||");
				System.out.println("================================================");
				System.out.println();
				System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요. ");
				System.out.print("카테고리 번호 : ");
				this.num = scan.nextInt();
				scan.nextLine(); // 입력 받으면서 들어온 \r\n을 지워주고 가야함.
				
				
				try {
					
					if (num == 0) {
						inputContact();
					} else if (num == 1) {
						searchName();
					} else if (num == 2) {
						searchPosition();
					} else if (num == 3) {
						searchBuseo();
					} else {
						System.out.println("잘못된 번호가 선택되었습니다.");
					}
					
				} catch (Exception e) {
					System.out.println(e);
				}

			
	}//clScreen()

	private void inputContact() {
		
		try {
			
		
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}//inputContact()


	private void searchBuseo() {
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println();
		System.out.println("");
		System.out.print("부서: ");
		this.buseo = scan.nextLine(); 
		System.out.println();
		System.out.println("--------------------------");
		
		String line = "";
		
		for (int i=0; i<list.size(); i++) {
			
			if((list.get(i)[6]).equals(this.buseo)) {
				
				line += String.format("[부서]: %s팀\n[이름]: %s\n[아이디]: %s\n[이메일]: %s\n[전화번호]: %s\n[직급]:%s\n\n"
						,list.get(i)[6] ,list.get(i)[2] ,list.get(i)[0] ,list.get(i)[3] ,list.get(i)[4] ,list.get(i)[5]);
				
			}
			
		}
		
		if (line.equals("")) {
			System.out.println("잘못된 부서입니다.");
		} else {
			System.out.println(line);
		}
		
		System.out.println("=============================================================");
		scan.nextLine();
		
	}//buseo()


	private void searchPosition() {
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println();
		System.out.print("직급: ");
		this.position = scan.nextLine(); 
		System.out.println();
		System.out.println("--------------------------");
		
		String line = "";
			
		for (int i=0; i<list.size(); i++) {
			
			if((list.get(i)[5]).equals(this.position)) {
					
				line += String.format("[직급]: %s\n[이름]: %s\n[아이디]: %s\n[이메일]: %s\n[전화번호]: %s\n[부서]:%s팀\n\n"
							,list.get(i)[5] ,list.get(i)[2] ,list.get(i)[0] ,list.get(i)[3] ,list.get(i)[4] ,list.get(i)[6]);
				}
		}
		
		if (line.equals("")) {
			System.out.println("잘못된 직급입니다.");
		} else {
			System.out.println(line);
		}
		
		System.out.println("=============================================================");
		scan.nextLine();
		
	}//position()


	private void searchName() {
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println();
		System.out.println("검색 하고자 하는 이름을 입력하세요. ");
		System.out.print("이름: ");
		this.name = scan.nextLine(); 
		System.out.println();
		
		String line = "";
		
		
		for (int i=0; i<list.size(); i++) {
			
				
			//list.get(i)[2] = temp[2] / Array접근->배열접근->값접근 : 이게 내가 찾는 값. (string) 
			if ((list.get(i)[2]).equals(this.name)){
				
				System.out.println("==========================================================");
				System.out.println("|| 이름 || 아이디 || 이메일 || 전화번호 || 직급 || 부서 ||                                                   ||");
				System.out.println("==========================================================");
					
				line += String.format("|| %s || %s || %s || %s || %s || %s ||"
							,list.get(i)[2] ,list.get(i)[0] ,list.get(i)[3] ,list.get(i)[4] ,list.get(i)[5] ,list.get(i)[6]);
				}
		}
		
		//데이터에 없는 값이 입력되면 그냥 아무 것도 출력 XXX -> 그래서 빈 문자열이 나오면 그걸 걸러주는 것. 
		if ((line.equals("")) ) {
			System.out.println("잘못된 이름입니다");
		} else {
			System.out.println(line);
		}
		
		System.out.println("=============================================================");
		scan.nextLine();
		
	}//Name()
	
	
	
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


}//class





