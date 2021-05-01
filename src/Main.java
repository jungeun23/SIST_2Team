import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		User user = new User();
//		user.regist_user();
		user.updateDB();
//		System.out.println(user);
		
		//ialbutt0,qdf5bG
		Login login = new Login();
		
//		user = login.loginScreen();
//		ShowMenu menu = new ShowMenu();
//		menu.start();
//		Mail mail = new Mail(user);
		System.out.println("dd");
//		Runtime.getRuntime().exec("cls");
//	    System.out.print("\033[H\033[2J");  
//	    System.out.flush();
//	    try
//	    {
//	        final String os = System.getProperty("os.name");
//
//	        if (os.contains("Windows"))
//	        {
//	            Runtime.getRuntime().exec("cls");
//	        }
//	        else
//	        {
//	            Runtime.getRuntime().exec("clear");
//	        }
//	    }
//	    catch (final Exception e)
//	    {
//	        //  Handle any exceptions.
//	    }
	    try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
		
	}
}
