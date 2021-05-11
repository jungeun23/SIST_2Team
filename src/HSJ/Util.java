package HSJ;
import java.util.Scanner;
/**
 * 코드의 재사용을 줄이기 위한 메소드 
 */
public class Util {
	public static Scanner scan = new Scanner(System.in);
	
	/**
	 * 사용자로부터 입력을 받기 위한 메소드
	 * @param label 어떤 값을 나타낼지 문장을 print해주는 값
	 * @return 사용자로부터 입력된 값을 반환
	 */
	public static String get(String label) {
		System.out.print(label + ": ");
		return scan.nextLine();		
	}
	/**
	 * String을 int로 바꾸는 메소드
	 * @param s String자료형 s
	 * @return s를 int로 바꾸어 반환
	 */
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
	
	/**
	 * 메뉴 선택에서 일시정지를 위한 메소드
	 * @param string 추가적으로 출력할 내용이 있을때 사용
	 */
	public static void puase(String string) {
		System.out.println(string);
		System.out.println("계속하시려면 엔터를 입력해주세요.");
		scan.nextLine();
	}

	/**
	 * 화면을 지우기 위한 메소드
	 */
	public static void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}