import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ElecApproval {
	private static ArrayList<Elec> elec = new ArrayList<Elec>();
	private final String DATA = "data\\ElecDoc\\ElecDocDB";
	private User user;
	private static Scanner sc = new Scanner(System.in);

	public ElecApproval(User user) throws IOException {
		this.user = user;
		load();
	}

	private void load() throws IOException {
		try {
			BufferedReader read = new BufferedReader(new FileReader(DATA));
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				Elec t = new Elec();
				t.setSeq(Integer.parseInt(temp[0]));
				t.setTitle(temp[1]);
				t.setDocuPW(temp[2]);
				t.setPosition(temp[3]);
				t.setName(temp[4]);
				String line2 = "";
				while (!(line = read.readLine()).equals("-----")) {
					line2 += line + "\n";
				}
				t.setContent(line2);
				elec.add(t);
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

	}

	public void createElecApproval() throws Exception {
		String title = "";
		String content = "";
		String docuPW = "";

		title = Util.get("제목");
		content = Util.get("내용");
		docuPW = Util.get("전자결제 비밀번호 ");
		String path = "data\\ElecDoc\\ElecDocDB";
		FileWriter fw = new FileWriter(path);
		Elec temp = new Elec();
		temp.setTitle(title);
		temp.setContent(content);
		temp.setDocuPW(docuPW);
		temp.setName(this.user.getName());
		temp.setPosition(this.user.getPosition());
		temp.setSeq(temp.getSeq() + 1);
		elec.add(temp);
		// 1,this is title,this is password,직급
//		전자문서
//		내
//		용
//		-----

		fw.write(temp.getSeq() + ",");
		fw.write(temp.getTitle() + ",");
		fw.write(temp.getDocuPW() + ",");
		fw.write(temp.getName() + ",");
		fw.write(temp.getPosition() + "\n");
		fw.write(temp.getContent() + "\n");
		fw.write("-----");
		fw.close();
	}

	public void readElecApproval() {

		int cnt = 1;
		for (int i = 0; i < elec.size(); i++, cnt++) {
			System.out.printf("[%d] %s", elec.get(i).getSeq(), elec.get(i).getTitle());
		}

		if (cnt == 0)
			System.out.println("전자 결재 문서가 존재하지 않습니다.");

		int choice = Integer.parseInt(Util.get("읽을 결재 문서 번호를 입력해주세요"));
		choice--;
		System.out.println();
		System.out.println();
		System.out.printf("제목 : %s\n", elec.get(choice).getTitle());
		System.out.println();
		System.out.printf("직급 : %s\n", elec.get(choice).getPosition());
		System.out.println();
		System.out.printf("글쓴이 : %s\n", elec.get(choice).getName());
		System.out.println();
		System.out.printf("내용\n====================\n%s", elec.get(choice).getContent());
		System.out.println();
//		File f = new File("data\\ElecDoc\\");
//		File[] fl = f.listFiles();
//		for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
////			System.out.println(cnt + " " +fl[i].getName());
//			System.out.printf("[%d] %s\n", cnt, fl[i].getName());
//		}
//		int n = Integer.parseInt(Util.get("읽은 문서의 번호를 입력하세요"));
//		try {
//			BufferedReader read = new BufferedReader(new FileReader(fl[n - 1]));
//			String line = "";
//			while ((line = read.readLine()) != null) {
//				System.out.println(line);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}

	public void deleteElecApproval() {
		for (int i = 0; i < elec.size(); i++) {
			System.out.printf("[%d] %s\n", elec.get(i).getSeq(), elec.get(i).getTitle());
		}
		int choice = Integer.parseInt(Util.get("삭제할 문서의 번호를 입력하세요"));
		choice--;
		String getPW = Util.get("전자결재문서 비밀번호를 입력하세요");
		if (elec.get(choice).getDocuPW().equals(getPW)) {
			System.out.println(elec.get(choice).getTitle() + " 파일을 삭제했습니다.");
			elec.remove(choice);
		}

//		File f = new File("data\\ElecDoc\\");
//		File[] fl = f.listFiles();
//		for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
//			System.out.printf("[%d] %s\n", cnt, fl[i].getName());
//		}
//		int n = Integer.parseInt(Util.get("삭제할 문서의 번호를 입력하세요"));
//		System.out.println(fl[n - 1].getName() + "파일을 삭제했습니다.");
//		fl[n - 1].delete();
	}

	// @ TODO : 코멘트를 어떻게 구현할지 미정. 추후 구현 예정.
//	public void addCommnetElecApproval() { 
//		File f = new File("data\\ElecDoc\\");
//		File[] fl = f.listFiles();
//		for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
//			System.out.printf("[%d] %s\n", cnt, fl[i].getName());
//		}
//		int n = Integer.parseInt(Util.get("댓글을 남길 문서의 번호를 입력하세요"));
//		try {
//			BufferedReader read = new BufferedReader(new FileReader(fl[n - 1]));
//			String line = "";
//			String result = "";
//			while ((line = read.readLine()) != null) {
//				result += line + "\r\n";
//			}
//
//			String comment = Util.get("코멘트를 입력하세요 : ");
//			result += String.format("코멘트 : %s\n", comment);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	public void myElecApproval() {
		for (int i = 0; i < elec.size(); i++) {
			if (elec.get(i).getName().equals(this.user.getName()))
				System.out.printf("[%d] %s\n", elec.get(i).getSeq(), elec.get(i).getTitle());
		}
		int choice = Integer.parseInt(Util.get("읽을 문서의 번호를 입력하세요"));
		choice--;
		readElecApproval(choice);
	}

//		File f = new File("data\\ElecDoc\\");
//		File[] fl = f.listFiles();
//		ArrayList<File> fal = new ArrayList<File>();
//		for (int i = 0, cnt = 1; i < fl.length; i++) {
//			if (fl[i].getName().contains(this.user.getName())) {
//				System.out.printf("[%d] %s\n", cnt, fl[i].getName());
//				fal.add(fl[i]);
//				cnt++;
//			}
//		}
//		int n = Integer.parseInt(Util.get("읽은 문서의 번호를 입력하세요"));
//		try {
//			BufferedReader read = new BufferedReader(new FileReader(fal.get(n - 1)));
//			String line = "";
//			while ((line = read.readLine()) != null) {
//				System.out.println(line);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	public void readElecApproval(int choice) {
		System.out.println();
		System.out.println();
		System.out.printf("제목 : %s\n", elec.get(choice).getTitle());
		System.out.println();
		System.out.printf("직급 : %s\n", elec.get(choice).getPosition());
		System.out.println();
		System.out.printf("글쓴이 : %s\n", elec.get(choice).getName());
		System.out.println();
		System.out.printf("내용\n====================\n%s", elec.get(choice).getContent());
		System.out.println();
	}

	public void setElecApprovalCondition() throws IOException {
		if (this.user.getPosition().equals("부장")) {
			for (int i = 0, cnt = 0; i < elec.size(); i++, cnt++) {
				System.out.printf("[%d] %s\n", cnt, elec.get(i).getTitle());
			}
//			for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
//				System.out.printf("[%d] %s\n", cnt, fl[i].getName());
//			}
			int n = Integer.parseInt(Util.get("처리할 문서의 번호를 입력하세요 :"));
			int choice = Integer.parseInt(Util.get("1.삭제 2.수정코멘트 3.통과 :"));
			choice--;
			if (choice == 1) {
				String getPW = Util.get("전자결재문서 비밀번호를 입력하세요");
				if (elec.get(choice).getDocuPW().equals(getPW)) {
					System.out.println(elec.get(choice).getTitle() + " 파일을 삭제했습니다.");
					elec.remove(choice);
				}
			} else if (choice == 2) {
//				addCommnetElecApproval();
				System.out.println("추후 구현 예정");
			} else if (choice == 3) {
//				FileWriter fw = new FileWriter(fl[n - 1]);
//				fw.write("상태 : 통과\r\n");
//				fw.close();
				System.out.println("추후 구현 예정");
			}
		}

//		if (this.user.getPosition().equals("차장")) {
//			File f = new File("data\\ElecDoc\\");
//			File[] fl = f.listFiles();
//			for (int i = 0, cnt = 1; i < fl.length; i++, cnt++) {
//				System.out.printf("[%d] %s\n", cnt, fl[i].getName());
//			}
//			int n = Integer.parseInt(Util.get("처리할 문서의 번호를 입력하세요 :"));
//			String choice = Util.get("1.수정코멘트 2.통과 : ");
//			if (choice.equals("1")) {
////				addCommnetElecApproval();
//				System.out.println("추후 구현 예정");
//			} else if (choice.equals("2")) {
//				FileWriter fw = new FileWriter(fl[n - 1]);
//				fw.write("상태 : 통과\r\n");
//				fw.close();
	}

}
