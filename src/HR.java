import java.util.Scanner;

public class HR {

	private final String DATA;
//	private String assigned;
//	private int position;
	private Scanner scan;
	
	public HR() {

		DATA = "data//HR.txt";
		scan = new Scanner(System.in);
		
	}
	
	public void hrScreen() {//이건가요?네네
	
		//관리자 전용 비밀번호 -> 권한 있는 사용자에게만 배포
		int pw = 1234; 
		
		System.out.println("관리자 비밀번호를 입력하세요.");
		System.out.println("=======================");
		System.out.print("      PW: ");
		pw = scan.nextInt();
		System.out.println("=======================");
		
		if(pw == 1234) {
			
			System.out.println("[로그인 성공]");
			
			cls();
			
			System.out.println("=======================================");
			System.out.println("|| 1. 사원정보 || 2. 사원정보 || 3. 사원정보  ||");
			System.out.println("||      추가  ||      변경  ||      삭제  ||");
			System.out.println("=======================================");
			System.out.println();
			System.out.print(" 카테고리(번호)를 선택히세요: ");
			int num = scan.nextInt();
			
			if(num == 1) {
				add();
				
			} else if(num == 2) {
				alter();
				
			} else if(num == 3) {
				delete();
				
			} else {
				
				System.out.println("카테고리(번호)를 다시 입력하세요.");
			}
			
			
		} else {
			System.out.println("권한이 있는 사용자가 아닙니다.");
			System.out.println("비밀번호를 다시 입력해주세요.");
		}
	
		
		
		
	
	
   }

	private void delete() {

		
		
	}

	private void alter() {
		
		
		
		
	}

	private void add() {
		
		System.out.println("추가할 사원의 정보를 입력해주세요.");
		System.out.print("이름: ");
		String name = scan.nextLine();
		
		System.out.print("아이디: ");
		String id = scan.nextLine();
		
		System.out.print("비밀번호: ");
		String pw = scan.nextLine();
		
		System.out.print("이메일: ");
		String email = scan.nextLine();
		
		System.out.print("전화번호: ");
		String phone = scan.nextLine();
		
		System.out.print("");
		
		
		
		
		
		
	}
	
	private static void cls() {
		
		for(int i=0; i<100; i++) {
			System.out.println();
		}
	}
	
	
	
}//class












