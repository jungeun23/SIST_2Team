import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CoperationCar {
	private String[] model = { "그랜저", "포터", "아반떼", "펠리세이드", "쏘나타", "투싼", "싼타페", "넥쏘", "베뉴", "스타렉스", "코나", "스타리아",
			"아이오닉", "벨로스터-N", "카니발", "봉고", "쏘렌토", "K5", "K8", "레이", "셀토스", "모닝", "K3", "모하비", "니로", "K7", "K9", "스팅어",
			"G80", "GV70", "GV80", "G90", "트레일블레이저", "스파크", "다마스", "트래버스", "트랙스", "말리부", "콜로라도", "볼트", "이쿼녹스", "QM6",
			"XM3", "SM6", "BMW 5 Series", "BMW 3 Series", "BMW 6 Series", "BMW 2 Series", "BMW 7 Series",
			"BMW 1 Series", "BMW X5", "BMW X3", "BMW X7", "BMW X6", "BMW X4", "BMW X1", "MB E-Class", "MB GLC-Class",
			"MB GLB-Class", "MB CLS-Class", "MB GLE-Class", "MB A-Class", "MB GLA-Class", "MB G-Class", "MB C-Class",
			"porsche Cayenne", "porsche Taycan", "porsche Panamera", "porsche 718 Boxster", "porsche Macan",
			"porsche 911", "porsche 718 Cayman", "Lamborghini Urus", "Lamborghini Huracan", "Lamborghini Aventador",
			"Ferrari Portofino Modificata", "Ferrari Roma", "Ferrari SF90 Stradale", "Ferrari F8 Tributo",
			"Ferrari 812 SuperFast", "LaFerrari", "Maserati Levante", "Maserati Ghibli", "Maserati Quattroporte",
			"Maserati Corse 20", "Maserati Levante", "McLaren Artura", "McLaren 720S", "McLaren 765LT",
			"McLaren Speedtail", "McLaren SENNA", "McLaren ELVA" + "McLaren Sabre", "McLaren GT", "Bentley Flying Spur",
			"Bentley Bentayga", "Bentley Continental", "Rolls-Royce Phantom", "Rolls-Royce Ghost", "Rolls-Royce Dawn",
			"Rolls-Royce Cullinan", "Bugatti Chiron", "Bugatti Divo", "Bugatti Centodieci", "Bugatti La Voiture Noire",
			"TESLA Model-S", "TESLA Model-3", "TESLA Model-X", "TESLA Model-Y", "TESLA Roadster", "TESLA Cybertruck",
			"TESLA Semi" };

	private int stock;
	private MyCalendar_je mc;
	private User user;
//    private HashMap<String, Integer> list = new HashMap<>();
	private ArrayList<String[]> listCar = new ArrayList<>();
	private final String DATA;
	
	private ArrayList<String[]> list = new ArrayList<String[]>();
	
	public CoperationCar(User user) {
		this.user = user;
		this.mc = new MyCalendar_je(user);
		Long seed = System.currentTimeMillis();
		Random rand = new Random(seed);
		rand.setSeed(seed);
		for (int i = 0; i < model.length; i++) {
			String[] s = { model[i], Integer.toString(rand.nextInt(5) + 1) };
			listCar.add(s);
		}
		this.DATA = "data\\schedule\\car.txt";
	}

	public void load() {
		try {
			BufferedReader readCar = new BufferedReader(new FileReader(DATA));
			String line = "";
			while ((line = readCar.readLine()) != null) {
				String[] temp = line.split(",");
				list.add(temp);
			}
			
			readCar.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void copCar() {
		load();
		for(int i=0; i<list.size(); i++) {
		System.out.println(Arrays.toString(list.get(i)));
		}
		System.out.println("===================================");
		System.out.println("||1. 법인차량 | 2. 법인차량 | 3. 법인차량||");
		System.out.println("||  예약 신청 |   예약 확인 |   예약 취소||");
		System.out.println("===================================");
		String n = (Util.get("번호를 입력해주세요"));
		if(n.equals("1")) {
			createCopCarSchedule();
		} else if(n.equals("2")) {
			readCopCarSchedule();
		} else if(n.equals("3")) {
			deleteCopCarSchedule();
		} else {
			System.out.println("잘못된 입력입니다.");
			copCar();
		}
	}
	
	public void createCopCarSchedule() {
		mc.createCopCarReseravtion(listCar); // 법인 차량 일정 등록
	}
	
	public void readCopCarSchedule() {
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				String[] temp = list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				System.out.println("일정을 선택해주세요");
				int[] c = mc.showCanlendar(year, month, day);
				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println("일정을 출력합니다");
					System.out.println("제목 : " + list.get(i)[2]);
					System.out.println("내용 : " + list.get(i)[3]);
				}
			}
		}
		System.out.println("남은 일정이 없습니다.");
		
		
	}
	
	
	public void updateCopCarSchedule() {
		for (int i = 0; i < this.list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				String[] temp = this.list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				System.out.println("수정할 일정을 선택해주세요");
				int[] c = mc.showCanlendar(year, month, day);
				if (c[0] == year && c[1] == month && c[2] == day) {
					String title = Util.get("일정의 제목을 입력해주세요");
					String content = Util.get("일정의 내용을 입력해주세요");
					String[] t = { this.user.getName(), this.list.get(i)[1], title, content };
					this.list.set(i, t);
					System.out.println("일정 수정이 완료됐습니다.");
				}
			}
		}
	}
	
	public void deleteCopCarSchedule() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[0].equals(this.user.getName())) {
				String[] temp = list.get(i)[1].split("-");
				int year = Util.toInt(temp[0]);
				int month = Util.toInt(temp[1]);
				int day = Util.toInt(temp[2]);
				System.out.println("취소할 차량 예약 일정을 선택해주세요");
				int[] c = mc.showCanlendar(year, month, day);
				if (c[0] == year && c[1] == month && c[2] == day) {
					System.out.println();
					System.out.println(list.get(i)[2] + " 차량 예약을 삭제했습니다.");
					list.remove(i);
				}
			}
		}
	}
}
