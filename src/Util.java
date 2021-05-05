import java.util.Scanner;

public class Util {
	private static Scanner scan = new Scanner(System.in);
	
	
	public static String get(String label) {
		System.out.print(label + ": ");
		return scan.nextLine();		
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
	
	public static void puase(String string) {
		System.out.println(string);
		System.out.println("계속하시려면 엔터를 입력해주세요.");
		scan.nextLine();
	}

	public static void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}