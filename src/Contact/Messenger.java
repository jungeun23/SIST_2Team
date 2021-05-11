package Contact;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 메신저 구현 이용자는 메신저를 생성, 읽기, 수정, 삭제등을 할 수 있다.
 * 
 * @param list DATA에 위치한 파일을 읽어 전부 저장
 * @param seq  게시물의 번호
 * @param user 현재 접속중인 유저의 정보
 * @param DATA 게시물의 정보가 저장된 위치
 */
public class Messenger {
	private static LinkedList<String[]> list = new LinkedList<>();
	private static int seq;
	private User user;
	private final String DATA = "data\\Messenger\\Messenger.txt";

	/**
	 * 현재 유저를 식별하고 DATA 파일의 정보를 list에 저장하는 생성자
	 * 
	 * @param user 현재 유저를 식별하기 위한 매개변수
	 */
	public Messenger(User user) {
		this.user = user;
		load();
	}

	/**
	 * DATA파일의 정보를 list에 저장하기 위한 메소드
	 */
	public void load() {
		try {
			BufferedReader read = new BufferedReader(new FileReader(DATA));
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				String content = "";
				while (!(line = read.readLine()).equals("-----")) {
					content += line + "\n";
				}

				String[] t = { temp[0], temp[1], temp[2], content };
				list.add(t);
			}
			seq = Util.toInt(list.get(list.size() - 1)[0]);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 사용자로부터 제목과 내용을 입력받아 메세지를 생성하는 메소드
	 */
	public void createMessenger() {
		// 번호 제목 받는사람 내용
		String title = Util.get("제목을 입력하세요");
		seq++;
		String receiverName = Util.get("보낼 사람 이름을 입력하세요");
		String content = "";
		System.out.println("내용을 입력해주세요 (종료 : exit)");
		while (true) {
			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine();
			if (line.equals("exit"))
				break;
			content += line + "\n";
		}

		String[] temp = { Integer.toString(seq), title, receiverName, content };
		list.add(temp);

		try {
			FileWriter fw = new FileWriter(DATA, true);
			fw.write(seq + ",");
			fw.write(title + ",");
			fw.write(receiverName + "\n");
			fw.write(content);
			fw.write("-----");
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.println("글 작성을 완료했습니다.");
	}

	/**
	 * 메세지를 읽기 위해 자신에게 온 메세지를 출력하고 선택된 메세지를 출력하는 메소드
	 */
	public void readMessenger() {
		// 번호 제목 보내는사람 내용
		for (int i = 0; i < list.size(); i++) {
			if (this.user.getName().equals(list.get(i)[2]))
				System.out.printf("[%s] %s\n", list.get(i)[0], list.get(i)[1]);
		}

		int c = Util.toInt(Util.get("읽을 메세지를 선택하세요"));
		for (int i = 0; i < list.size(); i++) {
			if (Util.toInt(list.get(i)[0]) == c) {
				System.out.println();
				System.out.println("메세지를 출력합니다.");
				System.out.printf("[%s] %s\n%s :\n%s", list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3]);
			}
		}
	}

	/**
	 * 메소드를 수정하기 위해 자신에게 온 메세지 중 수정할 메세지를 선택하여 수정된 내용을 저장하는 메소드
	 */
	public void updateMessenger() {
		// 번호 제목 보내는사람 내용
		for (int i = 0; i < list.size(); i++) {
			if (this.user.getName().equals(list.get(i)[2]))
				System.out.printf("[%s] %s\n", list.get(i)[0], list.get(i)[1]);
		}

		int c = Util.toInt(Util.get("수정할 메세지를 선택하세요"));
		for (int i = 0; i < list.size(); i++) {
			if (Util.toInt(list.get(i)[0]) == c) {
				String title = Util.get("제목을 입력하세요");
				seq++;
				String content = "";
				System.out.println("내용을 입력해주세요 (종료 : exit)");
				while (true) {
					Scanner sc = new Scanner(System.in);
					String line = sc.nextLine();
					if (line.equals("exit"))
						break;
					content += line + "\n";
				}
				String[] temp = { Integer.toString(seq), title, this.user.getName(), content };
				list.remove(i);
				list.add(temp);
			}
		} // exit for
		FileWriter fw = null;
		try {
			fw = new FileWriter(DATA);
		} catch (IOException e1) {
			System.out.println(e1);
		}
		for (int i = 0; i < list.size(); i++) {
			try {
				fw.write(list.get(i)[0] + ",");
				fw.write(list.get(i)[1] + ",");
				fw.write(list.get(i)[2] + "\n");
				fw.write(list.get(i)[3]);
				fw.write("-----");
				fw.write("\n");
			} catch (IOException e) {
				System.out.println(e);
			}
		} // exit for
		try {
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 메세지를 삭제하기 위하여 자신에게 온 메세지중 선택하여 메세지를 삭제하는 메소드
	 * @throws IOException 
	 */
	public void deleteMessenger() throws IOException {
		// 번호 제목 보내는사람 내용
		for (int i = 0; i < list.size(); i++) {
			if (this.user.getName().equals(list.get(i)[2]))
				System.out.printf("[%s] %s\n", list.get(i)[0], list.get(i)[1]);
		}

		int c = Util.toInt(Util.get("삭제할 메세지를 선택하세요"));
		for (int i = 0; i < list.size(); i++) {
			if (Util.toInt(list.get(i)[0]) == c) {
				list.remove(i);
			}
		}

		FileWriter fw = null;
		fw = new FileWriter(DATA);
		for (int i = 0; i < list.size(); i++) {
			fw.write(list.get(i)[0] + ",");
			fw.write(list.get(i)[1] + ",");
			fw.write(list.get(i)[2] + "\n");
			fw.write(list.get(i)[3]);
			fw.write("-----");
			fw.write("\n");
		} // exit for

		fw.close();

	}
}
