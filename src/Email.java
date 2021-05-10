import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 이메일 구현 클래스
 * 이메일의 읽기, 쓰기, 검색, 삭제, 수정이 가능하다.
 * @param list Mail클래스에 속한 멤버객체에 파일에서 읽은 내용을 저장한다.
 * @param scan 사용자로부터 입력을 받기위한 Scanner 멤버변수
 * @param DATA 파일의 위치를 기록하기 위한 멤버변수
 * @param user 현재 사용중인 유저를 식별하기 위한 멤버변수
 * @param receiverName 이메일을 받을 사람의 이름을 저장하기 위한 멤버변수
 * @param receiverEmail 이메일을 받을 사람의 이메일을 저장하기 위한 멤버변수
 */
public class Email {
	private static ArrayList<Mail> list = new ArrayList<Mail>();
	private static Scanner scan;
	private final String DATA;

	private User user;
	private String receiverName;
	private String receiverEmail;

	public Email() {

		DATA = "data\\Email\\Email.txt"; // Window

	}

	/**
	 * 이메일 생성자 DATA, scan, user등을 초기화 해주고 load메소드를 통해 파일을 읽어온다.
	 * @param user 사용자를 식별하기 위해 매개변수로 user를 받아온다.
	 */
	public Email(User user) {
		DATA = "data\\Email\\Email.txt"; // Window
		// DATA = "data/Contact.txt"; // Mac
		scan = new Scanner(System.in);
		this.user = user;
		try {
			load();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * @author 2
	 * @param receiverName : 수신자(메일 받을 사람) 이름
	 * @throws IOException 수신자의 이름을 찾아서 mail 주소를 저장하는 메소드
	 */
//	public void getReceiverEmail(String receiverName) throws IOException {
//		BufferedReader read = new BufferedReader(new FileReader(DATA));
//		String line = "";
//		String result = "";
//		while ((line = read.readLine()) != null) {
//			String[] temp = line.split(",");
//			if (temp[2].equals(this.receiverName)) {
//				this.receiverEmail = temp[3];
//				break;
//			}
//		} // while
//
//	}

	/**
	 * 파일에 저장된 내용을 읽어와 list변수인 ArrayList에 저장한다. 
	 */
	private void load() throws IOException {
		try {
//			System.out.println("load called");
			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";
			
//			번호 제목 보낸이메일 받는이메일 보낸이름 받는이름 내용
			while ((line = reader.readLine()) != null) {
				Mail mail = new Mail();
				String[] temp = line.split(",");
				mail.setSeq(Integer.parseInt(temp[0]));
				mail.setTitle(temp[1]);
				mail.setSenderEmail(temp[2]);
				mail.setReceiverEmail(temp[3]);
				mail.setSenderName(temp[4]);
				mail.setReceiverName(temp[5]);
				String line2 = "";
				while(!(line = reader.readLine()).equals("-----")) {
					line2 += line + "\n"; 
				}
				mail.setContent(line2);
				list.add(mail);
			}

//			list.add(mail);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	/**
	 * 메일을 작성하기 위해 상대방이 이름, 제목, 내용을 입력받아 파일에 저장한다.
	 */
	public void writeMail() throws IOException {
		String title = "";
		String content = "";

//		BufferedReader read = new BufferedReader(new FileReader(DATA));
		this.receiverEmail = Util.get("상대방 이메일을 입력하세요");
		Boolean isExist = false;
		for(int i=0; i<list.size(); i++) {
			if(this.list.get(i).getReceiverEmail().equals(this.receiverEmail)){
				this.receiverName = this.list.get(i).getReceiverName();
				isExist = true;
				break;
			}
		}
		Mail tempMail = new Mail();
		
//		while ((t = read.readLine()) != null) {
//			String[] tl = t.split(",");
//			if (tl[3].equals(this.receiverEmail)) {
//				this.receiverName = tl[2];
//				isExist = true;
//				break;
//			}
//		}
		if (!isExist) {
			System.out.println("존재하지 않는 메일입니다. 사내 메일을 확인해주세요");
			return;
		}
		tempMail.setReceiverEmail(this.receiverEmail);
		tempMail.setReceiverName(this.receiverName);
		title = Util.get("제목을 입력하세요");
		tempMail.setTitle(title);

		System.out.println("내용을 입력하세요 (끝내기 : exit입력): ");
		String line = "\n";
		while (true) {
			line = scan.nextLine();
			if (line.equals("exit"))
				break;
			content += line+"\n";
		}
		tempMail.setContent(content);
		tempMail.setSeq(tempMail.getSeq() + 1);
		tempMail.setSenderName(this.user.getName());
		tempMail.setSenderEmail(this.user.getEmail());
		list.add(tempMail);
		String path = DATA;
//	      path = "data\\mail\\" ;
		// 번호 제목 보낸이메일 받는이메일 보낸이름 받는이름 내용 
		FileWriter fw = new FileWriter(path, true);
		fw.write(tempMail.getSeq() + ",");
		fw.write(title + ",");
		fw.write(this.user.getEmail() + ",");
		fw.write(this.receiverEmail + ",");
		fw.write(this.user.getName() + ",");
		fw.write(this.receiverName + "\n");
		fw.write(content);
		fw.write("-----\n");
		fw.close();

	}

	public void readMail() throws IOException {
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).toString());
			if (list.get(i).getReceiverName().equals(this.user.getName())) {
				System.out.printf("[%s]\t%s\t\n", list.get(i).getSeq(), list.get(i).getTitle());
				cnt++;
			}
		}
		if (cnt == 0) {
			System.out.println("메일이 없습니다.");
			return;
		}
		int choice = Integer.parseInt(Util.get("읽을 메일 번호를 선택해주세요."));
		choice--;
		System.out.println();
		System.out.println();
		System.out.printf("제목 : %s\n내용 : %s", list.get(choice).getTitle(), list.get(choice).getContent());
		System.out.println();
		System.out.println();
		System.out.printf("보낸이 : %s\n 받는이 : %s\n", list.get(choice).getSenderName(),
				list.get(choice).getReceiverName());
		System.out.println();
//		for (int i = 0; i < fl.length; i++) {
//			if (fl[i].isFile()) {
//				String s = fl[i].getName();
//				String t = "";
//				t = s.substring(s.indexOf("_")+1, s.lastIndexOf("."));
//				if (t.equals(this.user.getName())) {
//					BufferedReader read = new BufferedReader(new FileReader(path + s));
//					String line = "";
//					while ((line = read.readLine()) != null) {
//						mail.get(i);
//					}
//				}
//				System.out.println(result);
//			}
//		}

	}

	/**
	 * 검색어를 통해 list에 있는 내용을 검색하여 일치하는 메일을 전부 출력한다. 
	 */
	public void searchMail() throws IOException {
		int cnt = 0;
		String keyword = Util.get("검색어를 입력하세요");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getContent().contains(keyword)) {
				System.out.println(list.get(i).getTitle() + "문서에서 검색어가 발견됐습니다.");
			}
			cnt++;
		}
		if (cnt == 0) {
			System.out.println("메일이 없습니다."); 
			return;
		}
		
		
		
//		String path = "data\\Email\\";
//		File f = new File(path);
//		File[] fl = f.listFiles();
//		String result = "";
//		String keyword = ""; // 사용자로부터 입력받아함
//		for (int i = 0; i < fl.length; i++) {
//			if (fl[i].isFile()) {
//				String s = fl[i].getName();
//				String t = "";
//				t = s.substring(s.indexOf("_"));
//				if (t.equals(this.user.getName())) {
//					BufferedReader read = new BufferedReader(new FileReader(path + t));
//					String line = "";
//					while ((line = read.readLine()) != null) {
//						result += line + "\r\n";
//					}
//					if (result.contains(keyword))
//						System.out.println(s + "에 해당 검색어가 존재합니다.");
//				}
//			}
//
//		} 
	}
	
	/**
	 * list의 내용에 keyword에 해당하는 단어가 존재하면 해당 메일을 출력한다. 
	 */
	public void sapmMailFilter() throws IOException {
		String[] keyword = { "환영", "승부", "도박", "하우스", "포인트", "현출", "슬롯", "베팅" };
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<keyword.length; j++) {
				if(list.get(i).getContent().contains(keyword[j]))	
					System.out.println(list.get(i).getTitle() + " 메일은 스팸일 가능성이 높습니다.");
			}
			
		}
//		String path = "data\\Email\\";
//		File f = new File(path);
//		File[] fl = f.listFiles();
//		String result = "";
//		String[] keyword = { "환영", "승부", "도박", "하우스", "포인트", "현출", "슬롯", "베팅" };
//		for (int i = 0; i < fl.length; i++) {
//			if (fl[i].isFile()) {
//				String s = fl[i].getName();
//				String t = "";
//				t = s.substring(s.indexOf("_"));
//				if (t.equals(this.user.getName())) {
//					BufferedReader read = new BufferedReader(new FileReader(path + t));
//					String line = "";
//					while ((line = read.readLine()) != null) {
//						result += line + "\r\n";
//					}
//					for (int j = 0; j < keyword.length; j++) {
//						if (result.contains(keyword[j]))
//							System.out.println(s + "는 스팸 메일일 가능성이 높습니다.");
//					}
//				}
//			}
//
//		} // Mail
	}
}
