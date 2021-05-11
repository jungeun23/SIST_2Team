package ASAP;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


/**
 * 사용자임을 확인하기위해 로그인하는 클래스
 * @param DATA 정보가 저장된 위치
 * @param scan 이용자의 입력받기
 * @param id 사용자에게 입력받는 id
 * @param pw 사용자에게 입력받는 pw
 * @param user 현재 접속중인 유저의 정보
 */

public class Login {
	private final String DATA;

	private Scanner scan;
	private String id;
	private String pw;
	private User user;

	/**
	 *  사용자가 로그인시 실행되는 생성자와 메소드
	 */
	
	public Login() {
		DATA = "data/Contact.txt";
		scan = new Scanner(System.in);
		id = "";
		pw = "";
		user = new User();
	}


	/**
	 * 사용자가 프로그램 실행시 보여지는 로그인 창
	 * @return user 로그인 후 입력받은 id,pw를 user 클래스로 돌려준다.
	 */
	public User loginScreen() {


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
				loginScreen();
			} else if (id == true && pw == false) {
				System.out.println("       PW를 다시 입력하세요.");
				loginScreen();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}

	/**
	 * 입력받은 id를 Login 클래스에 저장
	 * @param id  입력받은 id를 Login 클래스에 저장
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 입력받은 pw를 Login 클래스에 저장
	 * @param pw 입력받은 pw를 Login 클래스에 저장
	 */
	
	public void setPw(String pw) {
		this.pw = pw;
	}

}
