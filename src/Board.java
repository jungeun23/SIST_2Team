import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
		try {
			BufferedReader read = new BufferedReader(new FileReader(DATA));
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				list.add(temp);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void createBoard() {
		// 번호 제목 내용 글쓴이 비밀번호
		String title = Util.get("제목을 입력하세요");
		String pw = Util.get("비밀번호를 입력하세요");
		String content = Util.get("내용을 입력하세요");
		seq++;
		String[] temp = { Integer.toString(seq), title, pw, this.user.getName(), content, };
		list.add(temp);

		try {
			FileWriter fw = new FileWriter(DATA, true);
			fw.write(seq + ",");
			fw.write(title + ",");
			fw.write(pw + ",");
			fw.write(this.user.getName() + ",");
			fw.write(content + "\n");
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.println("글 작성을 완료했습니다.");

	}

	public void readBoard() {
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("[%s] %s %s\n\n%s", list.get(i)[0], list.get(i)[1], list.get(i)[3], list.get(i)[4]);
		}
	}

	public void deleteBoard() {
		readBoard();
		int choice = Util.toInt(Util.get("삭제할 번호를 입력하세요 : "));
		choice--;
		for (int i = 0; i < list.size(); i++) {
			if (i == choice) {
				System.out.println(list.get(i)[1] + " 게시물을 삭제했습니다.");
				list.remove(i);
			}
		}
		try {
			FileWriter fw = new FileWriter(DATA);
			for (int i = 0; i < list.size(); i++) {
				fw.write(seq + ",");
				fw.write(list.get(i)[1] + ",");
				fw.write(list.get(i)[3] + ",");
				fw.write(this.user.getName() + ",");
				fw.write(list.get(i)[4] + "\n");
				fw.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void updateBoard() {
		readBoard();
		int choice = Util.toInt(Util.get("수정할 번호를 입력하세요 : "));
		choice--;
		for (int i = 0; i < list.size(); i++) {
			if (i == choice) {
				System.out.println(list.get(i)[1] + " 게시물을 삭제했습니다.");
				list.remove(i);
			}
		}
		String title = Util.get("제목을 입력하세요");
		String pw = Util.get("비밀번호를 입력하세요");
		String content = Util.get("내용을 입력하세요");
		seq++;
		String[] temp = { Integer.toString(seq), title, pw, this.user.getName(), content, };
		list.add(temp);

		try {
			FileWriter fw = new FileWriter(DATA);
			for (int i = 0; i < list.size(); i++) {
				fw.write(seq + ",");
				fw.write(list.get(i)[1] + ",");
				fw.write(list.get(i)[3] + ",");
				fw.write(this.user.getName() + ",");
				fw.write(content + "\n");
				fw.close();
				System.out.println("게시물 수정 완료 됐습니다.");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
