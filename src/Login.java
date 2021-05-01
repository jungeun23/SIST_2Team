import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Login {
	private final String DATA;

	
	private Scanner scan;
	private String id;
	private String pw;
	private Contact ct;
	
	
	public Login() {
		DATA = "data\\Contact.txt";
		scan = new Scanner(System.in);
		id = "";
		pw = "";
	}
	
	//temp[0]= 아이디, temp[1] = 비밀번호 temp[2] = 이름 , temp[3] = 이메일,temp[4] = 전화번호, temp[5] = 직급
	
	public Contact loginScreen() {
			
		System.out.println("=======================");
		System.out.println("        [로그인]");
		System.out.println("=======================");
		System.out.print("ID : ");
		this.id = scan.nextLine();
		System.out.print("PW : ");
		this.pw = scan.nextLine();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			
			String line = "";
			
			boolean id = false;
			boolean pw = false;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				if(temp[0].equals(this.id)) { //id 비교
					id = true;
					
					if(temp[1].equals(this.pw)) {// 비밀번호 비교
						pw = true;
						System.out.println("로그인 성공");
						ct.setId(this.id);
						ct.setPw(this.pw);
						ct.setName(temp[2]);
						ct.setEmail(temp[3]);
						ct.setPhone(temp[4]);
						ct.setPosition(Integer.parseInt(temp[5]));
						
						
						break;
					}
				} 
			}
			
			if(id == false) {
				System.out.println("ID를 다시 입력하세요.");
			} else if(id == true && pw == false) {
				System.out.println("PW를 다시 입력하세요.");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return ct;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
	
}
