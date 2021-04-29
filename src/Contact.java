import java.util.Scanner;

public class Contact {
	private User ID;
	
	private String name;
	private String email;
	private String phone;
	private int position;
	
	
	public Contact() {
		
	}
	
	public Contact(User iD, String name, String email, String phone, int position) {
		super();
		ID = iD;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.position = position;
	}
	
	public void regist_user() {
//		System.out.println("아이디, 이름, 이메일, 핸드폰 번호, 직급을 입려");
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		this.ID.setID(id);
		
		System.out.print("이름 : ");
		this.name = sc.nextLine();
		
		System.out.print("이메일 : ");
		this.email = sc.nextLine();
		
		System.out.print("핸드폰 번호 : ");
		this.phone = sc.nextLine();
		
		System.out.print("직급 : ");
		this.position = sc.nextLine();
		
		
	}

	public User getID() {
		return ID;
	}
	public void setID(User iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
