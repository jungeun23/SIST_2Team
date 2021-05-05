import java.util.ArrayList;
import java.util.HashMap;
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
	private MyCalendar mc;
	private User user;
//    private HashMap<String, Integer> list = new HashMap<>();
	private ArrayList<String[]> list = new ArrayList<>();

	public CoperationCar(User user) {
		this.user = user;
		this.mc = new MyCalendar(user);
		Long seed = System.currentTimeMillis();
		Random rand = new Random(seed);
		rand.setSeed(seed);
		for (int i = 0; i < model.length; i++) {
			String[] s = { model[i], Integer.toString(rand.nextInt(5) + 1) };
			list.add(s);
		}
	}

	public void createCopCarSchedule() {
		mc.createCopCarReseravtion(list);
	}
	
	public void readCopCarSchedule() {
		
	}
	public void updateCopCarSchedule() {
		
	}
	public void deleteCopCarSchedule() {
		
	}
}
