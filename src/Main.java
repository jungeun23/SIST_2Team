import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Contact user = new Contact();
//		user.regist_user();
		user.updateDB();
//		System.out.println(user);
		
		//ialbutt0,qdf5bG
		Login login = new Login();
		
		user = login.loginScreen();
		

		
	}
}
