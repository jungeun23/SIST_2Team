import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author 2조 통합 Mail 기능 클래스 1. 메일 쓰기 - 수신자 검색 -> 해당하는 mail주소 가져오기 -> 내용입력 후 새로운
 *         txt 파일로 저장. 2. 메일 읽기 - 내 메일을 찾아 내용 읽기
 */
public class Mail {
	//
	private final String DATA;

	private Scanner scan;
	private String receiverEmail;
	private String senderName;
	private String receiverName;
	private User user;

	public Mail() {

		DATA = "data\\Contact.txt"; // Window
		// DATA = "data/Contact.txt"; // Mac
		scan = new Scanner(System.in);
		receiverEmail = "";
		receiverName = "";
		senderName = "";
	}

	public Mail(User user) {

		DATA = "data\\Contact.txt"; // Window
		// DATA = "data/Contact.txt"; // Mac
		scan = new Scanner(System.in);
		receiverEmail = "";
		receiverName = "";
		user = new User();
	}

	/**
	 * @author 2
	 * @param receiverName : 수신자(메일 받을 사람) 이름
	 * @throws IOException 수신자의 이름을 찾아서 mail 주소를 저장하는 메소드
	 */
	public void getReceiverEmail(String receiverName) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(DATA));
		String line = "";
		String result = "";
		while ((line = read.readLine()) != null) {
			String[] temp = line.split(",");
			if (temp[2].equals(this.receiverName)) {
				this.receiverEmail = temp[3];
				break;
			}
		} // while

	}

	/**
	 * @throws IOException
	 * 
	 */
	public void writeMail() throws IOException {
		String title = "";
		String content = "";
		System.out.print("제목을 입력하세요.");
		title = scan.nextLine();
		System.out.print("내용을 입력하세요.");
		content = scan.nextLine();
		
		String path = "data\\mail\\" + this.user.getName() + "_" + this.receiverName;
//	      path = "data\\mail\\" ;

		FileWriter fw = new FileWriter(path, true);
		fw.write(title);
		fw.write("-----");
		fw.write(content);
		fw.write("*****");
		fw.close();

	}

	public void readMail() throws IOException {
		String path = "data\\mail\\";
		File f = new File(path);
		File[] fl = f.listFiles();
		String result = "";

		for (int i = 0; i < fl.length; i++) {
			if (fl[i].isFile()) {
				String s = fl[i].getName();
				String t = "";
				t = s.substring(s.indexOf("_"));
				if (t.equals(this.user.getName())) {
					BufferedReader read = new BufferedReader(new FileReader(path + t));
					String line = "";
					while ((line = read.readLine()) != null) {
						result += line + "\r\n";
					}
				}
				System.out.println(result);
			}
		}
	}
}// Mail
