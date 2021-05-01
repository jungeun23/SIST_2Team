import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author 2조 
 * 통합 Mail 기능 클래스 
 * 1. 메일 쓰기 
 * 	- 수신자 검색 -> 해당하는 mail주소 가져오기 -> 내용입력 후 새로운 txt 파일로 
 * 2. 메일 읽기 
 */
public class Mail {
	// 
	


	User user = new User();
	String path = "data/Contact.txt"; // Mac 용 
	//String path = "data\\Contact.txt"; // Window 용 
//	user.getMail;
	String line = "";
	String receiveEmail = "";
	String receiveName = "";
	String senderName = "홍길동"; //  보내는 사람, 발신자 
	
	/**
	 * @author 2
	 * @param receiverName : 수신자(메일 받을 사람) 이름 
	 * @throws IOException
	 * 수신자의 이름을 찾아서 mail 주소를 저장하는 메소드 
	 */
	public void getReceiverEmail(String receiverName) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(path));

		String result = "";
		while ((line = read.readLine()) != null) {
			String[] temp = line.split(",");
			if (temp[2].equals(this.receiveName)) {
				this.receiveEmail = temp[3];
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
		
		path = "data/mail/"+this.senderName+"_"+this.receiveName+"_"+ title ;
		path = "data\\mail\\" ;
		
		FileWriter fw = new FileWriter(path);
		fw.write(title);
		fw.write("-----");
		fw.write(content);
		fw.close();
		
	}
}// Mail







