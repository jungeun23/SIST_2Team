import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		//Contact user = new Contact();
//		user.regist_user();
		//user.updateDB();
//		System.out.println(user);
		
		Mail mail = new Mail();
		mail.getReceiverEmail(mail.name);
		System.out.println(mail.receiveEmail);
		
	}
}
