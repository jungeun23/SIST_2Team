import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Board {
	private static LinkedList<String[]> list = new LinkedList<>();
	private static int seq;
	private User user;
	private final String DATA = "data\\board\\board.txt";

	public Board(User user) {
		this.user = user;
		load();
	}

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

				String[] t = { temp[0], temp[1], temp[2], temp[3], content };
				list.add(t);
			}
			seq = Util.toInt(list.get(list.size()-1)[0]);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void createMessenger() {
		// 번호 제목 내용 글쓴이 비밀번호
		String title = Util.get("제목을 입력하세요");
		String pw = Util.get("비밀번호를 입력하세요");
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
		list.add(temp);

		try {
			FileWriter fw = new FileWriter(DATA, true);
			fw.write(seq + ",");
			fw.write(title + ",");
			fw.write(this.user.getName() + "\n");
			fw.write(content);
			fw.write("-----");
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.println("글 작성을 완료했습니다.");
	}

	public void readMessenger() {
		// 번호 제목 보내는사람 내용
		for (int i = 0; i < list.size(); i++) {
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

	public void updateMessenger() {
		// 번호 제목 보내는사람 내용
		for (int i = 0; i < list.size(); i++) {
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

	public void deleteMessenger() {
		// 번호 제목 보내는사람 내용
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("[%s] %s\n", list.get(i)[0], list.get(i)[1]);
		}

		int c = Util.toInt(Util.get("삭제할 메세지를 선택하세요"));
		for (int i = 0; i < list.size(); i++) {
			if (Util.toInt(list.get(i)[0]) == c) {
				list.remove(i);
			}
		}

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
			try {
				fw.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		} // exit for

	}
}
