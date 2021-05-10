import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 유저의 정보를 관리하기위한 메소드
 * 
 * @param ContactPath 파일의 위치를 저장하는 멤버변수
 * @param id          사용자의 아이디를 저장하는 멤버변수
 * @param name        사용자의 이름을 저장하는 멤버변수
 * @param pw          사용자의 이름을 저장하는 멤버변수
 * @param email       사용자의 이메일을 저장하는 멤버변수
 * @param phone       사용자의 핸드폰 번호를 저장하는 멤버변수
 * @param position    사용자의 직급을 저장하는 멤버변수
 * @param depart사용자의  부서를 저장하는 멤버변수
 */
public class User {
	private static final String DATA = "data/Contact.txt";
	ArrayList<String[]> list = new ArrayList<>();
//	private User ID;

	private String id;
	private String name;
	private String pw;
	private String email;
	private String phone;
	private String position; // TODO :Contact.txt 직급 자료형 문자열로 바꿈에 따라 int > String 변경 요망 //TODO complete 확인
	private String depart;

	/**
	 * ""으로 초기화
	 */
	public User() {
//		ID = new User();
//		this.ID.setID("");
//		this.ID.setPassWord("");
		this.id = "";
		this.pw = "";
		this.name = "";
		this.email = "";
		this.phone = "";
		this.position = "";
		this.depart = "";
	}

	/**
	 * 생성자.
	 * 
	 * @param id
	 * @param name
	 * @param pw
	 * @param email
	 * @param phone
	 * @param position
	 * @param depart
	 */
	public User(String id, String name, String pw, String email, String phone, String position, String depart) {
		super();
//		ID = new User(id);
//		this.ID.setID(name);
//		this.ID.setPassWord(password);

		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.position = position;
		this.depart = depart;
		load();
	}

	
	/**
	 * DATA에 저장된 파일 정보를 읽어 와 list에 저장하는 메소드
	 */
	public void load() {
		try {
			BufferedReader read = new BufferedReader(new FileReader(DATA));
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				String[] t = { temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6] };
				list.add(t);
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 유저의 정보를 저장하기 위한 메소드
	 * 사용자로부터 id pw name email phone position depart를 입력으로 받는다.
	 */
	public void regist_user() {
//		System.out.println("아이디, 이름, 이메일, 핸드폰 번호, 직급을 입려");
//		Scanner sc = new Scanner(System.in);
//		System.out.print("아이디 : ");
//		String id = sc.nextLine();
//		String id = "asdfqwer1234";
		String id = Util.get("아이디 : ");
//		this.ID.setID(id); **

//		System.out.print("비밀번호 : ");
//		String pw = sc.nextLine();
		String pw = Util.get("비밀번호 : ");
//		this.ID.setPassWord("pw");**

//		System.out.print("이름 : ");
//		this.name = sc.nextLine();
//		this.name = "홍길동";
		this.name = Util.get("이름 : ");

//		System.out.print("이메일 : ");
//		this.email = sc.nextLine();
//		this.email = "abc@naver.com";
		this.email = Util.get("이메일 : ");

//		System.out.print("핸드폰 번호 : ");
//		this.phone = sc.nextLine();
//		this.phone = "010-1234-1234";
		this.phone = Util.get("핸드폰 번호 : ");

//		System.out.print("직급 : ");
//		this.position = sc.nextInt();
//		this.position = 1;
		this.position = Util.get("직급 : ");

		// id pw name email phone position

		String[] temp = { id, pw, name, email, phone, position, depart };
		list.add(temp);

		try {
			FileWriter fw = new FileWriter(DATA, true);
			fw.write(id + ",");
			fw.write(pw + ",");
			fw.write(name + ",");
			fw.write(email + ",");
			fw.write(phone + ",");
			fw.write(depart + ",");
			fw.write(position + "\n");
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.println("글 작성을 완료했습니다.");
	}

	/**
	 * 모든 사용자의 정보를 출력해주는 메소드
	 */
	public void readUser() {
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%s %s %s %s %s %s %s\n", list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3],
					list.get(i)[4], list.get(i)[5], list.get(i)[6]);
		}
	}

	/**
	 * 사용자의 정보를 수정하는 메소드
	 */
	public void updateUser() {
		// id pw name email phone position depart
		String c = Util.get("수정할 id를 입력해 주세요");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0] == c) {
				String id = Util.get("제목을 입력하세요");
				String pw = Util.get("비밀번호을 입력하세요");
				String name = Util.get("이름을 입력하세요");
				String email = Util.get("이메일을 입력하세요");
				String phone = Util.get("핸드폰번호를 입력하세요");
				String position = Util.get("직급을 입력하세요");
				String depart = Util.get("부서를 입력하세요");
				String[] temp = { id, pw, name, email, phone, position, depart};
				list.set(i, temp);
			}
		} // exit for
		try {
			FileWriter fw = new FileWriter(DATA, true);
			fw.write(id + ",");
			fw.write(pw + ",");
			fw.write(name + ",");
			fw.write(email + ",");
			fw.write(phone + ",");
			fw.write(depart + ",");
			fw.write(position + "\n");
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 사용자의 정보를 삭제하는 메소드
	 */
	public void deleteUser() {
		// id pw name email phone position depart
		String c = Util.get("삭제할 id를 입력해 주세요");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0] == c) {
				list.remove(i);
			}
		} // exit for
		try {
			FileWriter fw = new FileWriter(DATA, true);
			fw.write(id + ",");
			fw.write(pw + ",");
			fw.write(name + ",");
			fw.write(email + ",");
			fw.write(phone + ",");
			fw.write(depart + ",");
			fw.write(position + "\n");
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public String toString() {
		return String.format("id: %s\npassword: %s\nname: %s\nemail: %s\nphone: %s\nposition: %s\n", this.id, this.pw,
				this.name, this.email, this.phone, this.position);
	}

//	public void updateDB() throws IOException {
//		// TODO : updateDB 불러오기 금지... 형식지금은 맞춰져서 이후 확정 이후 재구현 예정
//		BufferedReader reader = new BufferedReader(new FileReader(DATA));
//
//		String result = "";
//		String line = "";
//
//		try {
//			while ((line = reader.readLine()) != null) {
//
//				String[] temp = line.split(",");
//				if (temp.length == 5) {
////				System.out.println(temp);
//					String temp2 = String.format("%s,%s,%s,%s,%s,%s\n", temp[0], temp[1], randName(), temp[2], temp[3],
//							temp[4]);
////				System.out.println(temp2);
//					result += temp2;
//				} else if (temp.length == 6) {
////					System.out.println(temp.length);
//					String temp2 = String.format("%s,%s,%s,%s,%s,%s\n", temp[0], temp[1], randName(), temp[3], temp[4],
//							temp[5]);
//					result += temp2;
//				}
//
//			}
//			FileWriter writer = new FileWriter(new File(DATA));
//			writer.write(result);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	
	/**
	 * 더미데이터를 만들기 위하여 성과 이름을 랜덤으로 반환하는 메서드
	 * @return 성+이름
	 */
	public static String randName() {
		List<String> firstName = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신",
				"권", "황", "안", "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하",
				"곽", "성", "차", "주", "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강",
				"현", "변", "염", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "표", "명", "기", "반",
				"왕", "금", "옥", "육", "인", "맹", "모", "장", "남", "탁", "진", "은", "구");
		List<String> lastName = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노",
				"누", "다", "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미",
				"민", "바", "박", "백", "범", "별", "병", "보", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔",
				"수", "숙", "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우",
				"원", "월", "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "자", "잔", "장", "재", "전", "정", "제", "조", "종",
				"주", "준", "중", "지", "진", "찬", "창", "천", "철", "초", "춘", "충", "치", "태", "택", "판", "하", "한", "해", "혁", "현",
				"형", "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "봉", "혼", "황", "량", "린", "을",
				"비", "솜", "공", "면", "탁", "온", "디", "항", "후", "송", "욱", "휴", "견", "추", "걸", "삼", "열", "웅", "분", "양", "출",
				"타", "곤", "번", "식", "란", "더", "손", "술", "반", "빈", "실", "직", "흔", "권", "복", "심", "헌", "학", "개", "늘", "랑",
				"향");
		Collections.shuffle(firstName);
		Collections.shuffle(lastName);
		return firstName.get(0) + lastName.get(0) + lastName.get(1);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public static String getContactpath() {
		return DATA;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

}
