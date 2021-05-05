import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Login {
	private final String DATA;

	private Scanner scan;
	private String id;
	private String pw;
	private User user;

	public Login() {
		DATA = "data/Contact.txt";
		scan = new Scanner(System.in);
		id = "";
		pw = "";
		user = new User();
	}

	// temp[0]= 아이디, temp[1] = 비밀번호 temp[2] = 이름 , temp[3] = 이메일,temp[4] = 전화번호,
	// temp[5] = 직급

	public User loginScreen() {

//		System.out.println("=======================");
//		System.out.println("        [로그인]");
//		System.out.println("=======================");
//		System.out.print("ID : ");
//		this.id = scan.nextLine();
//		System.out.print("PW : ");
//		this.pw = scan.nextLine();

		try {

			System.out.println("       그룹웨어");
			System.out.println("=======================");
			System.out.println("        [로그인]");
			System.out.println();
			System.out.print("     ▶ ID : ");
			this.id = scan.nextLine();
			System.out.print("     ▶ PW : ");
			this.pw = scan.nextLine();
			System.out.println("=======================");

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";

			boolean id = false;
			boolean pw = false;

			while ((line = reader.readLine()) != null) {

				String[] temp = line.split(",");

				if (temp[0].equals(this.id)) { // id 비교
					id = true;

					if (temp[1].equals(this.pw)) {// 비밀번호 비교
						pw = true;
						System.out.println();
						System.out.println("     [로그인 성공]");
						user.setId(this.id);
						user.setPw(this.pw);
						user.setName(temp[2]);
						user.setEmail(temp[3]);
						user.setPhone(temp[4]);
						user.setPosition(temp[5]);
						user.setDepart(temp[6]);

						break;
					}
				}
			}
			System.out.println();
			if (id == false) {
				System.out.println("       ID를 다시 입력하세요.");
				show();
			} else if (id == true && pw == false) {
				System.out.println("       PW를 다시 입력하세요.");
				show();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}

	public void show() {
		try {

			System.out.println("       그룹웨어");
			System.out.println("=======================");
			System.out.println("        [로그인]");
			System.out.println();
			System.out.print("     ▶ ID : ");
			this.id = scan.nextLine();
			System.out.print("     ▶ PW : ");
			this.pw = scan.nextLine();
			System.out.println("=======================");

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";

			boolean id = false;
			boolean pw = false;

			while ((line = reader.readLine()) != null) {

				String[] temp = line.split(",");

				if (temp[0].equals(this.id)) { // id 비교
					id = true;

					if (temp[1].equals(this.pw)) {// 비밀번호 비교
						pw = true;
						System.out.println();
						System.out.println("     [로그인 성공]");
						user.setId(this.id);
						user.setPw(this.pw);
						user.setName(temp[2]);
						user.setEmail(temp[3]);
						user.setPhone(temp[4]);
						user.setPosition(temp[5]);
						user.setDepart(temp[6]);

						break;
					}
				}
			}
			System.out.println();
			if (id == false) {
				System.out.println("       ID를 다시 입력하세요.");
				show();
			} else if (id == true && pw == false) {
				System.out.println("       PW를 다시 입력하세요.");
				show();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
