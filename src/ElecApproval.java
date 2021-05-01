import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ElecApproval {
	
	
	public void CreateElecApproval(User user) throws Exception {
		String title="";
		String content="";
		String docuPW = "";
		
		Scanner sc = new Scanner(System.in);
		System.out.print("제목 : ");
		title = sc.nextLine();
		
		System.out.print("내용 : ");
		content = sc.nextLine();
		
		System.out.print("전자결제 비밀번호 : ");
		docuPW = sc.nextLine();
		
		String path = "data\\ElecDcou";
		FileWriter fw = new FileWriter(path);
		
		fw.write("제목 : ")
		fw.write(title);
		
		fw.write("내용 : ");
		fw.write(content);
		
		fw.write("전자 결제 비밀번호 : ");
		docuPW
		
		
	
	}
}
