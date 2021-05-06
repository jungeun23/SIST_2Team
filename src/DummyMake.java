import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DummyMake {
	public static void main(String[] args) {
		try {
//			ContactDummy();
			HRDummy();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void HRDummy() throws IOException {
//		ehumber0,EcxPfdAl,반남석,ssellar0@bloglovin.com,010-3091-7832,과장,디자인
		LinkedList<String[]> list = new LinkedList<>();
		LinkedList<String[]> list2 = new LinkedList<>();

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
		String[] goga = { "A", "B", "C", "D", "E" };

		Random rand = new Random();

		int rnd = rand.nextInt(5);

		for (int i = 0; i < list.size(); i++) {
			String year = "";
			
			if(list.get(i)[1].equals("인턴")) {
				year = (rand.nextInt(2)+1) +""; //인턴 1~2년 > 
			} else if(list.get(i)[5].equals("사원")) {
				year = (rand.nextInt(3)+1) + ""; //사원 1~3년  
			} else if(list.get(i)[5].equals("대리")) {
				year = (rand.nextInt(4)+4) + ""; //대리 4~7년
			} else if(list.get(i)[5].equals("과장")) {
				year = (rand.nextInt(4)+8) + ""; //과장 8~11년
			} else if(list.get(i)[5].equals("차장")) {
				year = (rand.nextInt(5)+12) + ""; //차장 12~16년
			} else if(list.get(i)[5].equals("부장")) { 
				year = (rand.nextInt(5)+17) + ""; //부장 17~21년
			} else if(list.get(i)[5].equals("상무")) {
				year = 23 +"";
			} else if(list.get(i)[5].equals("전무")) {
				year = 25 +"";
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
			String[] t = { list.get(i)[2], list.get(i)[5], list.get(i)[6], goga[rnd], year, Integer.toString(salary), Integer.toString(rand.nextInt(100)+500)};
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



	private static String workyear(LinkedList<String[]> list) {
		// TODO Auto-generated method stub
		return null;
	}

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
