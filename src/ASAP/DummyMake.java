package ASAP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/**
 * 더미 데이터를 생성하기 위한 메소드
 */
public class DummyMake {
	static Random rnd = new Random();

	public static void main(String[] args) {
		try {
//			ContactDummy();
//			HRDummy();
//			for (int i = 0; i < 100; i++) {
//				System.out.println(makeTitle());
//				System.out.println(makeContent());
//			}
//			boardDummy();
			ElecAppDummy();
//			EmailDummy();
//			editEmail();
//			MessengerDummy();
//			System.out.println(makeContent());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @뒤를 사내 메일로 맞추기 위하여 실행하는 메소드  
	 */
	public static void editEmail() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data\\Contact.txt"));
		String line = "";
		String result = "";
		while ((line = reader.readLine()) != null) {
			String[] temp = line.split(",");
			temp[3] = temp[3].substring(0, temp[3].indexOf("@") + 1);
			String add = "sist2.co.kr";
			temp[3] = temp[3].concat(add);
			result += String.format("%s,%s,%s,%s,%s,%s,%s\n", temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("data\\Contact.txt"));
		writer.write(result);
		writer.close();
	}

	/**
	 * 메신저 더미 데이터 생성하는 메서드
	 */
	public static void MessengerDummy() throws IOException {
		// 번호 제목 보내는사람이름 내용
		int cnt = 1;
		FileWriter fw = new FileWriter("data\\Messenger\\Messenger.txt");
		for (int i = 0; i < 10000; i++) {
			String s = getNameFromContact();
			fw.write(String.format("%s,%s,%s\n", cnt++, getTitleDummy(), s));
			fw.write(getContentDummy() + "\n");
			fw.write("-----\n");
		}
		fw.close();
	}

	/**
	 * email 더미 데이터를 생성하는 메서드
	 */
	public static void EmailDummy() throws IOException {
//		번호 제목 보낸이메일 받는이메일 보낸이름 받는이름 내용
		int cnt = 1;
		FileWriter fw = new FileWriter("data\\Email\\Email.txt");
		for (int i = 0; i < 30000; i++) {
			String s = getNameFromContact();
			String s2 = getNameFromContact();
			fw.write(String.format("%s,%s,%s,%s,%s,%s\n", cnt++, getTitleDummy(), getEmail(s), getEmail(s2), s, s2));
			fw.write(getContentDummy() + "\n");
			fw.write("-----\n");
		}
		fw.close();
	}

	/**
	 * 전자결재 더미 데이터를 생성하기 위한 메서드 
	 */
	public static void ElecAppDummy() throws IOException {
		int cnt = 1;
		FileWriter fw = new FileWriter("data\\ElecDoc\\ElecDoc.txt");
		for (int i = 0; i < 10000; i++) {
			String s = getNameFromContact();
//			1036,전월세 사직서,gcdXIdqIq#DXy!nJZt396,허신량,과장
			fw.write(String.format("%s,%s,%s,%s,%s\n", cnt++, getTitleDummy(), getPasswordDummy(), s,
					getPosition(s)));
			fw.write(getContentDummy() + "\n");
			fw.write("-----\n");
		}
		fw.close();
	}

	/**
	 * 이메일 정보를 가져오기 위한 메서드
	 * @param ,로 나누어진 Contact 형식 문자열
	 * @return contact에서 같은 이름을 찾고 이메일을 반환한다.
	 */
	private static String getEmail(String s) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("data\\Contact.txt"));
		String line = "";
		String res = "";
		while ((line = read.readLine()) != null) {
			String[] temp = line.split(",");
			if (temp[2].equals(s)) {
				res = temp[3];
				break;
			}
		}
		return res;
	}

	/**
	 * 직급 정보를 가져오기 위한 메서드
	 * @param ,로 나누어진 HR 형식 문자열
	 * @return contact에서 같은 이름을 찾고 직급을 반환한다.
	 * @throws IOException
	 */
	private static String getPosition(String s) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("data\\HR.txt"));
		String line = "";
		String res = "";
		while ((line = read.readLine()) != null) {
			String[] temp = line.split(",");
			if (temp[0].equals(s)) {
				res = temp[1];
				break;
			}
		}
		return res;
	}

	/**
	 * 게시물 더미 데이터 생성을 위한 메서드
	 */
	public static void boardDummy() throws IOException {
//		1,title제목,홍길동,qwer1234,content내용
		int cnt = 1;
		FileWriter fw = new FileWriter("data\\board\\board.txt");
		for (int i = 0; i < 10000; i++) {
			fw.write(String.format("%s,%s,%s,%s\n", cnt++, getTitleDummy(), getNameFromContact(),
					getPasswordDummy()));
			fw.write(getContentDummy() + "\n");
			fw.write("-----\n");
		}
		fw.close();
	}

	/**
	 * 패스워드를 생성하기 위한 메서드
	 */
	private static String getPasswordDummy() {
		int length = rnd.nextInt(20) + 8;
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[length];
		String result = "";

		for (int i = 0; i < length; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		for (int i = 0; i < length; i++) {
			result += password[i];
		}

		return result;
	}

	/**
	 * 연락처에서 이름을 랜덤으로 가져오기 위한 메서드 
	 */
	public static String getNameFromContact() throws IOException {

		BufferedReader read = new BufferedReader(new FileReader("data\\Contact.txt"));
		String line = "";
		LinkedList<String> list = new LinkedList<>();
		while ((line = read.readLine()) != null) {
			String[] t = line.split(",");
			list.add(t[2]);
		}

		return list.get(rnd.nextInt(list.size() - 1));
	}

	
	/**
	 * 제목 더미를 생성하는 메서드
	 * @return 제목에 해당하는 데이터를 2개 가져와서 새로운 제목을 반환한다.
	 */
	public static String getTitleDummy() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("data\\dummy\\title.txt"));
		String line = "";
		String result = "";
		LinkedList<String> list = new LinkedList<>();
		while ((line = read.readLine()) != null) {
			list.add(line);
		}
		return list.get(rnd.nextInt(list.size() - 1)) + " " + list.get(rnd.nextInt(list.size() - 1));
	}

	/**
	 * 내용 더미를 생성하는 메서드
	 * @return 내용에 해당하는 데이터를 3개 가져와서 새로운 내용을 반환한다.
	 */
	public static String getContentDummy() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("data\\dummy\\content.txt"));
		String line = "";
		String result = "";
		LinkedList<String> list = new LinkedList<>();
		while ((line = read.readLine()) != null) {
			list.add(line);
		}
		return list.get(rnd.nextInt(list.size() - 1)) + "\n" + list.get(rnd.nextInt(list.size() - 1)) + "\n"
				+ list.get(rnd.nextInt(list.size() - 1));
	}

	/**
	 * HR에 필요한 데이터를 생성하는 메서드
	 */
	public static void HRDummy() throws IOException {
		LinkedList<String[]> list = new LinkedList<>();
		LinkedList<String[]> list2 = new LinkedList<>();

		try {
			BufferedReader read = new BufferedReader(new FileReader("DATA\\Contact.txt"));
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				list.add(temp);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		String[] goga = { "A", "B", "C", "D", "E" };

		Random rand = new Random();


		for (int i = 0; i < list.size(); i++) {
			int rnd = rand.nextInt(5);
//			ehumber0,EcxPfdAl,반남석,ssellar0@sist2.co.kr,010-3091-7832,과장,디자인
			String year = "";
			if (list.get(i)[5].equals("인턴")) {
				year = (rand.nextInt(2) + 1) + ""; // 인턴 1~2년 >
			} else if (list.get(i)[5].equals("사원")) {
				year = (rand.nextInt(3) + 1) + ""; // 사원 1~3년
			} else if (list.get(i)[5].equals("대리")) {
				year = (rand.nextInt(4) + 4) + ""; // 대리 4~7년
			} else if (list.get(i)[5].equals("과장")) {
				year = (rand.nextInt(4) + 8) + ""; // 과장 8~11년
			} else if (list.get(i)[5].equals("차장")) {
				year = (rand.nextInt(5) + 12) + ""; // 차장 12~16년
			} else if (list.get(i)[5].equals("부장")) {
				year = (rand.nextInt(5) + 17) + ""; // 부장 17~21년
			} else if (list.get(i)[5].equals("상무")) {
				year = 23 + "";
			} else if (list.get(i)[5].equals("전무")) {
				year = 25 + "";
			} else {
				year = 30 + "";
			}

			int salary = 0;

			if (list.get(i)[5].equals("인턴")) {
				salary = 2100000;

			} else if (list.get(i)[5].equals("사원")) {
				salary = 2800000;

			} else if (list.get(i)[5].equals("대리")) {
				salary = 3200000;

			} else if (list.get(i)[5].equals("과장")) {
				salary = 4000000;

			} else if (list.get(i)[5].equals("차장")) {
				salary = 5000000;

			} else if (list.get(i)[5].equals("부장")) {
				salary = 6000000;

			} else if (list.get(i)[5].equals("상무")) {
				salary = 7000000;

			} else if (list.get(i)[5].equals("전무")) {
				salary = 8000000;

			} else if (list.get(i)[5].equals("사장")) {
				salary = 10000000;

			}
//			ehumber0,EcxPfdAl,반남석,ssellar0@sist2.co.kr,010-3091-7832,과장,디자인
//			여보전,대리,부서,B,4,3200000,10144
			String[] t = { list.get(i)[2], list.get(i)[5], list.get(i)[6], goga[rnd], year, Integer.toString(salary),
					Integer.toString(rand.nextInt(1000) + 10000) };
			list2.add(t);
		}

		FileWriter fw = new FileWriter("DATA\\HR_dummy.txt");

		for (int i = 0; i < list.size(); i++) {
			fw.write(list2.get(i)[0] + ",");
			fw.write(list2.get(i)[1] + ",");
			fw.write(list2.get(i)[2] + ",");
			fw.write(list2.get(i)[3] + ",");
			fw.write(list2.get(i)[4] + ",");
			fw.write(list2.get(i)[5] + ",");
			fw.write(list2.get(i)[6] + "\n");
		}
		fw.close();
//		ehumber0,EcxPfdAl,,ssellar0@bloglovin.com,010-3091-7832,과장,디자인
	}

	/**
	 * Contact 더미를 생성하는 메서드
	 * @throws IOException
	 */
	public static void ContactDummy() throws IOException {
//		ehumber0,EcxPfdAl,,ssellar0@bloglovin.com,010-3091-7832,과장,디자인
		LinkedList<String[]> list = new LinkedList<>();

		try {
			BufferedReader read = new BufferedReader(new FileReader("DATA\\Contact_dummy.txt"));
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] temp = line.split(",");
				list.add(temp);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		for (int i = 0; i < list.size(); i++) {
			list.get(i)[2] = randName();
		}
		FileWriter fw = new FileWriter("DATA\\Contact_dummy.txt");

		for (int i = 0; i < list.size(); i++) {
			fw.write(list.get(i)[0] + ",");
			fw.write(list.get(i)[1] + ",");
			fw.write(list.get(i)[2] + ",");
			fw.write(list.get(i)[3] + ",");
			fw.write(list.get(i)[4] + ",");
			fw.write(list.get(i)[5] + ",");
			fw.write(list.get(i)[6] + "\n");
		}
		fw.close();
//		ehumber0,EcxPfdAl,,ssellar0@bloglovin.com,010-3091-7832,과장,디자인

	}

	/**
	 * 이름을 랜덤으로 생성하는 메서드
	 * @return 성+이름을 반환한다.
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
}
