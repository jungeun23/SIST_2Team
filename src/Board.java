import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Board {
	private static LinkedList<String[]> list = new LinkedList<>();
	private final String DATA = "data\\board\\board.txt";
	private User user;
	private static int seq;

	public Board(User user) {
		this.user = user;
		load();
	}

	public void load() {

	}

	public void createBoard() {
		// 번호 제목 내용 글쓴이 비밀번호
		String title = Util.get("제목을 입력하세요");
		String pw = Util.get("비밀번호를 입력하세요");
		System.out.println("내용을 입력하세요 (종료 : q)");
//		String content = Util.get("내용을 입력하세요");
		String content = "";
		while (true) {
			Scanner sc = new Scanner(System.in);
			String t = sc.nextLine();
			if (t.equals("q"))
				break;
			content += t;
		}
		seq++;
		String[] temp = { Integer.toString(seq), title, content, pw, this.user.getName() };
		list.add(temp);
		
		try {
			FileWriter fw = new FileWriter(DATA);
			fw.write(seq + "\n");
			fw.write(title + "\n");
			fw.write(pw + "\n");
			fw.write(this.user.getName() + "\n");
			fw.write(content + "\n");
			fw.write("-----\n");
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		
		System.out.println("글 작성을 완료했습니다.");
		
	}

}
