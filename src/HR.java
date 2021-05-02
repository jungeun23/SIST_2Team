import java.util.Scanner;

public class HR {

	private final String DATA;
	private String assigned;
	private int position;
	private Scanner scan;
	
	public HR() {

		DATA = "data//HR.txt";
		scan = new Scanner(System.in);
		
	}
	
	public void hrScreen() {
	
		//관리자 전용 비밀번호 -> 권한 있는 사용자에게만 배부
		int pw = 1234; 
		
		System.out.println("관리자 비밀번호를 입력하세요.");
		System.out.println("=======================");
		System.out.print("      PW: ");
		pw = scan.nextInt();
		System.out.println("=======================");
		
		if(pw == 1234) {
			
			System.out.println("[로그인 성공]");
			
			cls();
			
			System.out.println(" 검색하고자 하는 카테고리(번호)를 선택히세요. ");
			System.out.println("=======================================");
			System.out.println("|| 1. 사원정보  || 2. 사원정보 || 3. 사원정보 ||");
			System.out.println("||      추가   ||      변경 ||       삭제 ||");
			System.out.println("---------------------------------------");
			
			
			
			
			
			
		
			System.out.println("||                                    ||");
			
			
			
			
			System.out.println("=======================================");
							
				
			
		} else {
			System.out.println("권한이 있는 사용자가 아닙니다.");
			System.out.println("비밀번호를 다시 입력해주세요.");
		}
	
		
		
		
	
	
   }
	
	private static void cls() {
		for(int i=0; i<100; i++) {
			System.out.println();
		}
	}
	
}//class












