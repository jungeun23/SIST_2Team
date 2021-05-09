import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * 게시판 구현
 * 이용자는 게시글을 생성, 읽기, 수정, 삭제등을 할 수 있다.
 * @param list DATA에 위치한 파일을 읽어 전부 저장
 * @param seq 게시물의 번호
 * @param user 현재 접속중인 유저의 정보
 * @param DATA 게시물의 정보가 저장된 위치
 */
public class Board {
	private static LinkedList<String[]> list = new LinkedList<>();
	private static int seq;
	private User user;
	private final String DATA = "data\\board\\board.txt";

	public Board(User user) {
		this.user = user;
		load();
	}
	
	/**
	 * load()메소드를 생성자에 넣어, 게시물 DB를 읽어 전부 저장한다. 
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

				String[] t = { temp[0], temp[1], temp[2], temp[3], content };
				list.add(t);
			}
			seq = Util.toInt(list.get(list.size()-1)[0]);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * 사용자로부터 제목, 비밀번호, 내용을 입력받고 list에 추가한다.
	 * 추가된 내용은 FileWriter append모드로 추가한다.
	 */
	public void createBoard() {
		// 번호 제목 글쓴이 비밀번호 내용 
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

	/**
	 * list에 있는 번호와 제목을 출력 후 선택된 게시물을 출력한다.
	 */
	public void readBoard() {
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
	
	/**
	 * list의 번호와 제목을 출력한 뒤 선택된 게시물을 수정한다.
	 */
	public void updateBoard() {
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
	
	/**
	 * list의 번호와 제목을 출력한 뒤 선택된 게시물을 삭제한다.
	 */
	public void deleteBoard() {
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
