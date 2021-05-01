import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;​

​

public class ShowMenu {

	private static Scanner scan;
	private final static String DATA;
	private static ArrayList<Memo> list; // memo.dat 의 대리자

	static {
		scan = new Scanner(System.in);
		DATA = "data/contact.txt";
		list = new ArrayList<Memo>();
	}

	public void start() {

		/*
		 * 
		 * 4.29 :파일 입출력만 사용 1. 정보 쓰기 - BufferedWriter 2. 정보 읽기 - BufferedReader 3. 정보
		 * 삭제하기 - BufferedReader -> BufferedReader -> BufferedWriter
		 * 
		 * 
		 * 4.30 : 파일입출력 + 컬렉션 사용 - 프로그램 시작 : 파일 모든 내용 읽기 -> 메모리 저장(컬렉션) - 프로그램 사용 : 파일을
		 * 건드리지않고-> 배열 메모리 조작(컬렉션) - 프로그램 종료 : 메모리 저장(컬렉션) -> 파일에 모든 내용 쓰기
		 * 
		 * 
		 * [콘솔 메모장]
		 * 
		 * 기능 1. 메모 쓰기 2. 메모 읽기 3. 메모 삭제
		 * 
		 * 데이터 1. 메모 e. 번호(숫자) - 유일한값 (식별용) a. 작성(문자열) b. 메모내용(문자열..) - 한줄메모 - 여러줄 메모 ->
		 * c. 날짜(2021-04-30 12:00:00) d. 중요도(A,B,C) 2. 파일 - memo.dat 3. 형식 - 이름,날짜, 중요도
		 * - 메모내용 - ======== -> 구분자
		 * 
		 */

		load(); // 텍스트파일 -> 배열

		// System.out.println(list);

		System.out.println("[메모장]");

		boolean loop = true;

		while (loop) {
			// 메뉴
			// 선택
			// 분기
			String sel = menu();

			if (sel.equals("1")) {
				add();
			} else if (sel.equals("2")) {
				list();
			} else if (sel.equals("3")) {
				delete();
			} else {
				loop = false;
			}

		} // while

		System.out.println("메모장 종료");

		// memo.dat 내용은 지우고 ArrayList 의 내용을 덮어쓰기 해줘야함

		save(); // ArrayList<Memo> -> memo.dat

	}// main
	​

	}​ ​ ​

	private static String menu() {
​
		System.out.println("===================");
		System.out.println("1. 메모 쓰기");
		System.out.println("2. 메모 읽기");
		System.out.println("3. 메모 삭제하기");
		System.out.println("4. 종료");
		System.out.println("===================");
		System.out.print("선택: ");
​
		String sel = scan.nextLine();
​
		return sel;
​
	}

	private static void pause() {
		System.out.println("엔터를 누르시면 다음으로 진행합니다.");
		scan.nextLine();//Block 
	}
}​ ​