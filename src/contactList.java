import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactList {
	private static Scanner scan = new Scanner(System.in);
	
	private final String DATA;
	
	private int num;
	private String name;
	private String position;
	private String buseo;
	private String allList;
	
	
	public ContactList() {
		DATA = "data\\Contact.txt";
		name = "";
		position = "";
		buseo = "";
		allList = "";
	}
	
	
	// temp[0] = 아이디, temp[1] = 비밀번호, temp[2] = 이름 
	// temp[3] = 이메일, temp[4] = 전화번호, temp[5] = 직급
	
	
	//주소록 첫 화면 
	public void firstScreen() {
				
				cls();
				System.out.println("=============================================================");
				System.out.println("|| 1.이름  || 2. 직급 || 3. 부서 || 4. 전직원  ||  0. 목차 ||");
				System.out.println("||   검색  ||    검색 ||    검색 ||      검색  || 돌아가기 ||");
				System.out.println("=============================================================");
				System.out.println();
				System.out.println("검색하고자 하는 카테고리(번호)를 선택하세요. ");
				System.out.print("카테고리 번호 : ");
				this.num = scan.nextInt();
				scan.nextLine(); // \r\n을 지워주고 가야함.

				try {
					
					if (num == 0) {
						//여기서 목차로 돌아가게 ... 가능한가?
					} else if (num == 1) {
						searchName();
					} else if (num == 2) {
						searchPosition();
					} else if (num == 3) {
						searchBuseo();
					} else if (num == 4) {
						searchAllList();
					} else {
						System.out.println("잘못된 번호가 선택되었습니다.");
					}
				
				} catch (Exception e) {
					System.out.println(e);
				}
			
	}//clScreen()
	

	private void searchAllList() {
		
	}//allList()


	private void searchBuseo() {
		
	}//buseo()


	private void searchPosition() {
		
	}//position()


	private void searchName() {
		
		System.out.println();
		System.out.println("--------------------------");
		System.out.println();
		System.out.print("이름: ");
		this.name = scan.nextLine(); 
		System.out.println();
		System.out.println("--------------------------");
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			
			String line = "";
			String temp2 = "";
			
			while ((line = reader.readLine()) != null) {
				
				ArrayList<String> temp3 = new ArrayList<String>();
				
				String[] temp = line.split(",");
				
				for (int i=0; i<temp.length; i++) {
					
					temp3.add(temp[i]);
				}
				
				
				if (temp[2].equals(this.name)) {
					
					System.out.println();
					temp2 += String.format("[이름]: %s\n[아이디]: %s\n[이메일]: %s\n[전화번호]: %s\n[직급]: %s\n[부서]:%s팀\n"
							, temp3.get(2)
							, temp3.get(0)
							, temp3.get(3)
							, temp3.get(4)
							, temp3.get(5)
							, temp3.get(6));
					
					System.out.println(temp2);
					System.out.println("--------------------------");
					System.out.println("=============================================================");
					scan.nextLine();
				} 
				
			}//while
		} catch (Exception e) {
			System.out.println(e);
		}

		
		
	}//Name()
	
	
	
	
	private static void cls() {
		for(int i=0; i<100; i++) {
			System.out.println();
		}
	}

}//class























