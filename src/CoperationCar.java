//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class CoperationCar {
//	private String[] model = { "그랜저", "포터", "아반떼", "펠리세이드", "쏘나타", "투싼", "싼타페", "넥쏘", "베뉴", "스타렉스", "코나", "스타리아",
//			"아이오닉", "벨로스터-N", "카니발", "봉고", "쏘렌토", "K5", "K8", "레이", "셀토스", "모닝", "K3", "모하비", "니로", "K7", "K9", "스팅어",
//			"G80", "GV70", "GV80", "G90", "트레일블레이저", "스파크", "다마스", "트래버스", "트랙스", "말리부", "콜로라도", "볼트", "이쿼녹스", "QM6",
//			"XM3", "SM6", "BMW 5 Series", "BMW 3 Series", "BMW 6 Series", "BMW 2 Series", "BMW 7 Series",
//			"BMW 1 Series", "BMW X5", "BMW X3", "BMW X7", "BMW X6", "BMW X4", "BMW X1", "MB E-Class", "MB GLC-Class",
//			"MB GLB-Class", "MB CLS-Class", "MB GLE-Class", "MB A-Class", "MB GLA-Class", "MB G-Class", "MB C-Class",
//			"porsche Cayenne", "porsche Taycan", "porsche Panamera", "porsche 718 Boxster", "porsche Macan",
//			"porsche 911", "porsche 718 Cayman", "Lamborghini Urus", "Lamborghini Huracan", "Lamborghini Aventador",
//			"Ferrari Portofino Modificata", "Ferrari Roma", "Ferrari SF90 Stradale", "Ferrari F8 Tributo",
//			"Ferrari 812 SuperFast", "LaFerrari", "Maserati Levante", "Maserati Ghibli", "Maserati Quattroporte",
//			"Maserati Corse 20", "Maserati Levante", "McLaren Artura", "McLaren 720S", "McLaren 765LT",
//			"McLaren Speedtail", "McLaren SENNA", "McLaren ELVA" + "McLaren Sabre", "McLaren GT", "Bentley Flying Spur",
//			"Bentley Bentayga", "Bentley Continental", "Rolls-Royce Phantom", "Rolls-Royce Ghost", "Rolls-Royce Dawn",
//			"Rolls-Royce Cullinan", "Bugatti Chiron", "Bugatti Divo", "Bugatti Centodieci", "Bugatti La Voiture Noire",
//			"TESLA Model-S", "TESLA Model-3", "TESLA Model-X", "TESLA Model-Y", "TESLA Roadster", "TESLA Cybertruck",
//			"TESLA Semi" };
//
//	private int stock;
//	private MyCalendar_jungeun mc;
//	private User user;
////    private HashMap<String, Integer> list = new HashMap<>();
//	private ArrayList<String[]> carList = new ArrayList<>();
//	private final String DATA;
//	private final String DATA2;
//	private Scanner scan;
//	private LinkedList<String[]> listCar;
//	
//	public CoperationCar(User user) {
//		this.user = user;
//		this.mc = new MyCalendar_jungeun(user);
//		
//		this.DATA = "data\\schedule\\car.txt";
//		scan = new Scanner(System.in);
//		this.DATA2 = "data\\schedule\\listCar.txt";
//		
//		load();
//		
//		
//	}
//
//	public void listCar() {
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA2));
//			
//			for(int i=0; i<carList.size();i++) {
//				writer.write(String.format("%s,%s\n"
//						                    , carList.get(i)[0]
//						                    , carList.get(i)[1]));
//			}
//			
//			writer.close();
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//	}
//	
//	
//	
//	
//	public void load() {
//		try {
//			BufferedReader readlist = new BufferedReader(new FileReader(DATA));
//			String line = "";
//			while ((line = readlist.readLine()) != null) {
//				String[] temp = line.split(",");
//				this.listCar.add(temp);
//			}
//			
//			readlist.close();
//		
//			BufferedReader readCarList = new BufferedReader(new FileReader(DATA2));
//			line = "";
//			while((line = readCarList.readLine()) != null) {
//				String[] temp = line.split(",");
//				this.carList.add(temp);
//			}
//			
//			
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//	}
//	
//	public void copCar() {
//		
//		
//		
////		for(int i=0; i<list.size(); i++) {
////		System.out.println(Arrays.toString(list.get(i)));
////		}
//	
//		System.out.println("                ▣ 법인 차량 예약 ▣");
//		System.out.println("================================================");
//		System.out.println("||1. 법인차량 | 2. 법인차량 | 3. 법인차량 | 4. 목록으로 ||");
//		System.out.println("||  예약 신청 |   예약 확인 |   예약 취소 |    돌아가기 ||");
//		System.out.println("================================================");
//		String n = (Util.get("번호를 입력해주세요"));
//		if(n.equals("1")) {
//			createCopCarSchedule();
//		} else if(n.equals("2")) {
//			readCopCarSchedule();
//		} else if(n.equals("3")) {
//			deleteCopCarSchedule();
//		} else if(n.equals("4")) {
//			Main.showReservation();
//		} else {
//			System.out.println("잘못된 입력입니다.");
//			copCar();
//		}
//	}
//	
//	public void createCopCarSchedule() {
//		this.carList = mc.createCopCarReseravtion(carList); // 법인 차량 일정 등록 //리스트 바뀐거 여기서 저장
//		saveCarList();
//		
//		System.out.println("엔터를 누르면 목록으로 돌아갑니다.");
//		scan.nextLine();
//		copCar();
//	}
//	
//	public void readCopCarSchedule() {
//		
//		 ArrayList<int[]> t = new ArrayList<>();
//	      for (int i = 0; i < listCar.size(); i++) {
//	         if (listCar.get(i)[0].equals(this.user.getName())) {
//	            String[] temp = listCar.get(i)[5].split("-");
//	            int year = Util.toInt(temp[0]);
//	            int month = Util.toInt(temp[1]);
//	            int day = Util.toInt(temp[2]);
//	            int[] g = { year, month, day };
//	            t.add(g);
//	         }
//	      }
//	      System.out.println("일정을 선택해주세요");
//	      int[] c = mc.showCanlendar(t);
//	      for (int i = 0; i < listCar.size(); i++) {
//	         if (listCar.get(i)[0].equals(this.user.getName())) {
//	            String[] temp = listCar.get(i)[5].split("-");
//	            int year = Util.toInt(temp[0]);
//	            int month = Util.toInt(temp[1]);
//	            int day = Util.toInt(temp[2]);
//	            if (c[0] == year && c[1] == month && c[2] == day) {
//	               System.out.println();
//	               System.out.println("일정을 출력합니다");
//	               System.out.println("예약자명 : " + listCar.get(i)[0]);
//	               System.out.println("예약 일자 : "+ listCar.get(i)[5]);
//	               System.out.println("예약 목적 : " + listCar.get(i)[6]);
//	            }
//	         }
//	      }
//
//
//		System.out.println("모든 예약을 출력했습니다.");
//		System.out.println("엔터를 누르면 목록으로 돌아갑니다.");
//		scan.nextLine();
//		copCar();
//		
//	}
//	
//	
//	
//	public void deleteCopCarSchedule() {
//		
//		ArrayList<int[]> t = new ArrayList<>();
//	      for (int i = 0; i < listCar.size(); i++) {
//	         if (listCar.get(i)[0].equals(this.user.getName())) {
//	            String[] temp = listCar.get(i)[5].split("-");
//	            int year = Util.toInt(temp[0]);
//	            int month = Util.toInt(temp[1]);
//	            int day = Util.toInt(temp[2]);
//	            int[] g = { year, month, day };
//	            t.add(g);
//
//	         }
//	      } // exit for
//	      System.out.println("삭제할 일정을 선택해주세요");
//	      int[] c = mc.showCanlendar(t);
//	      for (int i = 0; i < listCar.size(); i++) {
//	         if (listCar.get(i)[0].equals(this.user.getName())) {
//	            String[] temp = listCar.get(i)[5].split("-");
//	            int year = Util.toInt(temp[0]);
//	            int month = Util.toInt(temp[1]);
//	            int day = Util.toInt(temp[2]);
//	            if (c[0] == year && c[1] == month && c[2] == day) {
//	               System.out.println();
//	               System.out.println(listCar.get(i)[5] + " 일정을 삭제했습니다.");
//	               for(int j=0; j<carList.size(); j++) {
//						
//						if(listCar.get(i)[3].equals(carList.get(j)[0])) {
//							
//							String[] tempp = {carList.get(j)[0], (Integer.parseInt(carList.get(j)[1]) +1)+""};
//							
//							carList.set(j, tempp);
//							break;
//						}
//					}
//	               listCar.remove(i);
//	            }
//	         }
//	      } // exit for
//		
//		
//		
////		for (int i = 0; i < list.size(); i++) {
////			if (list.get(i)[0].equals(this.user.getName())) {
////				String[] temp = list.get(i)[5].split("-");
////				int year = Util.toInt(temp[0]);
////				int month = Util.toInt(temp[1]);
////				int day = Util.toInt(temp[2]);
////				System.out.println("취소할 차량 예약 일정을 선택해주세요");
////				
////				ArrayList<int[]> toMc = new ArrayList<int[]>();
////				for(int j=0; j<list.size(); j++) {
////					String[]  tmp =  list.get(j)[5].split("-");
////					
////					int[] t = {Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2])};
////					
////					toMc.add(t);
////				}
////				
////				int[] c = mc.showCanlendar(toMc);
////				
////				if (c[0] == year && c[1] == month && c[2] == day) {
////					System.out.println();
////					System.out.println(list.get(i)[5] + " 차량 예약을 삭제했습니다.");
////					for(int j=0; j<listCar.size(); j++) {
////						
////						if(list.get(i)[3].equals(listCar.get(j)[0])) {
////							
////							String[] tempp = {listCar.get(j)[0], (Integer.parseInt(listCar.get(j)[1]) +1)+""};
////							
////							listCar.set(j, tempp);
////							break;
////						}
////					}
////					list.remove(i);
////				}
////			}
////		}
//		
//		
//		saveCarList();
//		saveCarList();
//		//writer > 전체 for문 
//		
//		System.out.println("엔터를 누르면 목록으로 돌아갑니다.");
//		scan.nextLine();
//		copCar();
//		
//	}
//	
//	public void saveCarList() {
//		
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA));
//			
//			for(int i=0; i<listCar.size();i++) {
//				writer.write(String.format("%s,%s,%s,%s,%s,%s,%s\n"
//						                    , listCar.get(i)[0]
//						                    , listCar.get(i)[1]
//						                	, listCar.get(i)[2]
//						                	, listCar.get(i)[3]
//						                	, listCar.get(i)[4]
//						                	, listCar.get(i)[5]
//						                	, listCar.get(i)[6]));
//			}
//			
//			writer.close();
//			
//			BufferedWriter writerCar = new BufferedWriter (new FileWriter(DATA2));
//			
//			for(int i=0; i<carList.size(); i++) {
//				writerCar.write(String.format("%s,%s\n", carList.get(i)[0]
//						                                , carList.get(i)[1]));
//			}
//			
//			writerCar.close();
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//	}
//	
////	public void saveCarList() {
////	
////		try {
////
////			BufferedWriter writerCar = new BufferedWriter (new FileWriter(DATA2));
////			
////			for(int i=0; i<carList.size(); i++) {
////				writerCar.write(String.format("%s,%s\n", carList.get(i)[0]
////						                                , carList.get(i)[1]));
////			}
////			
////			writerCar.close();
////			
////		} catch (Exception e) {
////			System.out.println(e);
////		}
////		
////	}
//	
//	
//	
//	
//}
//
//
//
//
