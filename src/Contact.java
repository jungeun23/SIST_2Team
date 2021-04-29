import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Contact {
	private User ID;

	private String name;
	private String email;
	private String phone;
	private int position;

	public Contact() {
		ID = new User();
		this.ID.setID("");
		this.ID.setPassWord("");
		this.name = "";
		this.email = "";
		this.phone = "";
		this.position = 0;
	}

	public Contact(User iD, String name, String email, String phone, int position) {
		super();
		ID = new User();
		this.setID(iD);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.position = position;
	}

	public void regist_user() {
//		System.out.println("아이디, 이름, 이메일, 핸드폰 번호, 직급을 입려");
		Scanner sc = new Scanner(System.in);
//		System.out.print("아이디 : ");
//		String id = sc.nextLine();
		String id = "asdfqwer1234";
		this.ID.setID(id);

//		System.out.print("비밀번호 : ");
//		String pw = sc.nextLine();
		this.ID.setPassWord("1234qwer");

//		System.out.print("이름 : ");
//		this.name = sc.nextLine();
		this.name = "홍길동";

//		System.out.print("이메일 : ");
//		this.email = sc.nextLine();
		this.email = "abc@naver.com";

//		System.out.print("핸드폰 번호 : ");
//		this.phone = sc.nextLine();
		this.phone = "010-1234-1234";

//		System.out.print("직급 : ");
//		this.position = sc.nextInt();
		this.position = 1;
		generateDBCreate();
	}

	@Override
	public String toString() {
		return String.format("id: %s\npassword: %s\nname: %s\nemail: %s\nphone: %s\nposition: %s\n", this.ID.getID(),
				this.ID.getPassWord(), this.name, this.email, this.phone, this.position);
	}

	// TODO : Contact 변수에 입력된 값을 .txt에 저장한다.
	public void generateDBCreate() {
		String path = "data\\Contact.txt";

		try {
			FileWriter fw = new FileWriter(path, true);
			fw.write(String.format("%s,%s,%s,%s,%s,%d\n", 
					this.ID.getID(), this.ID.getPassWord(), this.name, this.email, this.phone, this.position));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// TODO : dump로 Contact에 여러 자료를 채운다.

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
