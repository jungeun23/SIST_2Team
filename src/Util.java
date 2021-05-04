import java.util.Scanner;

public class Util {
	
	public static String get(String label) {
		Scanner scan = new Scanner(System.in);
		System.out.print(label + ": ");
		return scan.nextLine();		
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}

}