import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ElecApproval {
	private User user;
	private Scanner sc = new Scanner(System.in);

	public ElecApproval(User user) {
		this.user = user;
	}

	public void createElecApproval(User user) throws Exception {
		String title = "";
		String content = "";
		String docuPW = "";

		title = Util.get("제목: ");
		content = Util.get("제목: ");
		docuPW = Util.get("전자결제 비밀번호 : ");

		String path = "data\\ElecDoc\\";
		path += title + "_" + this.user.getName() + ".txt";
		FileWriter fw = new FileWriter(path);

		fw.write("제목 : ");
		fw.write(title + "\r\n");

		fw.write("내용 : ");
		fw.write(content + "\r\n");

		fw.write("전자 결제 비밀번호 : ");
		fw.write(docuPW + "\r\n");

		fw.close();
	}

	public void readElecApproval() {
		File f = new File("data\\ElecDoc\\");
		File[] fl = f.listFiles();
		for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
//			System.out.println(cnt + " " +fl[i].getName());
			System.out.printf("[%d] %s\n", cnt, fl[i].getName());
		}
		int n = Integer.parseInt(Util.get("읽은 문서의 번호를 입력하세요"));
		try {
			BufferedReader read = new BufferedReader(new FileReader(fl[n - 1]));
			String line = "";
			while ((line = read.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteElecApproval() {
		File f = new File("data\\ElecDoc\\");
		File[] fl = f.listFiles();
		for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
			System.out.printf("[%d] %s\n", cnt, fl[i].getName());
		}
		int n = Integer.parseInt(Util.get("삭제할 문서의 번호를 입력하세요"));
		System.out.println(fl[n - 1].getName() + "파일을 삭제했습니다.");
		fl[n - 1].delete();
	}

	public void addCommnetElecApproval() {
		File f = new File("data\\ElecDoc\\");
		File[] fl = f.listFiles();
		for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
			System.out.printf("[%d] %s\n", cnt, fl[i].getName());
		}
		int n = Integer.parseInt(Util.get("댓글을 남길 문서의 번호를 입력하세요"));
		try {
			BufferedReader read = new BufferedReader(new FileReader(fl[n - 1]));
			String line = "";
			String result = "";
			while ((line = read.readLine()) != null) {
				result += line + "\r\n";
			}

			String comment = Util.get("코멘트를 입력하세요 : ");
			result += String.format("코멘트 : %s\n", comment);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void myElecApproval() {
		File f = new File("data\\ElecDoc\\");
		File[] fl = f.listFiles();
		ArrayList<File> fal = new ArrayList<File>();
		for (int i = 0, cnt = 1; i < fl.length; i++) {
			if (fl[i].getName().contains(this.user.getName())) {
				System.out.printf("[%d] %s\n", cnt, fl[i].getName());
				fal.add(fl[i]);
				cnt++;
			}
		}
		int n = Integer.parseInt(Util.get("읽은 문서의 번호를 입력하세요"));
		try {
			BufferedReader read = new BufferedReader(new FileReader(fal.get(n - 1)));
			String line = "";
			while ((line = read.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void setElecApprovalCondition(User user) throws IOException {
		if (user.getPosition().equals("부장")) {
			File f = new File("data\\ElecDoc\\");
			File[] fl = f.listFiles();
			for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
				System.out.printf("[%d] %s\n", cnt, fl[i].getName());
			}
			int n = Integer.parseInt(Util.get("처리할 문서의 번호를 입력하세요 :"));
			String choice = Util.get("1.삭제 2.수정코멘트 3.통과 :");
			if (choice.equals("1")) {
				System.out.println(fl[n - 1].getName() + "파일을 삭제했습니다.");
				fl[n - 1].delete();
			} else if (choice.equals("2")) {
				addCommnetElecApproval();
			} else if (choice.equals("3")) {
				FileWriter fw = new FileWriter(fl[n - 1]);
				fw.write("상태 : 통과\r\n");
				fw.close();
			}
		}
		if (user.getPosition().equals("차장")) {
			File f = new File("data\\ElecDoc\\");
			File[] fl = f.listFiles();
			for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
				System.out.printf("[%d] %s\n", cnt, fl[i].getName());
			}
			int n = Integer.parseInt(Util.get("처리할 문서의 번호를 입력하세요 :"));
			String choice = Util.get("1.수정코멘트 2.통과 : ");
			if (choice.equals("1")) {
				addCommnetElecApproval();
			} else if (choice.equals("2")) {
				FileWriter fw = new FileWriter(fl[n - 1]);
				fw.write("상태 : 통과\r\n");
				fw.close();
			}
		}
	}

}