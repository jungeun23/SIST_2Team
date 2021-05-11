import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static User user = new User();

	public static HR hr = new HR();
	public static ContactList cl = new ContactList();

	public static void main(String[] args) throws Exception {

//		user.regist_user();
//		user.updateDB();
//		System.out.println(user);

		Login login = new Login();
		user = login.loginScreen();

//		Messenger ms = new Messenger(user);
//		ms.load();
//		ms.createMessenger();
//		ms.readMessenger();
//		ms.updateMessenger();
		menu();

//		CoperationCar cc = new CoperationCar(user);
//		cc.createCopCarSchedule();
//<<<<<<< HEAD
//
//		
//		MeetingRoom mr = new MeetingRoom(user);
//		//mr.createRoomReservation();
//
//		mr.MeetingRoomScreen();
//		
//=======
////		mr.MeetingRoomScreen();
//>>>>>>> e33866b78b5dd76a5cad7ab3b0a76296e6d90671
//
////		Board bd = new Board(user);
////		bd.createBoard();
//
////		Board bd = new Board(user);
////		bd.createBoard();
////		bd.readBoard();
////		bd.deleteBoard();
////		bd.updateBoard();
//<<<<<<< HEAD
//		
//
//		
//
////		hr.hrlogin();
//		
//		
//		
//		//at.dummy();
////		at.readWorkingTime2();
////		at.dayWorkingTime2();
////		at.adminSearchWorkingTime();	
//		
//		
//=======
//
//>>>>>>> e33866b78b5dd76a5cad7ab3b0a76296e6d90671
//		Email mail = new Email(user);

//		mail.readMail();
		// menu();

//		MyCalendar c = new MyCalendar();
//		c.output();
		// makeEmailDummy();

	}

	private static void makeEmailDummy() throws Exception {
		String path = "Data/Contract.txt"; // 제목 더미를... 하ㅜㅜ 생각좀해봐야겟다.
		File f = new File(path);
//		System.out.println(f);
		int cnt = 0;
		BufferedReader reader = new BufferedReader(new FileReader("Data/Email/dummy.txt"));

		String line = "";
		String result = "";
		ArrayList<String> title = new ArrayList<>(); // 5, 6
		ArrayList<String> content = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			String[] temp = line.split(",");
//			System.out.println(Arrays.toString(temp));
			title.add(temp[0]);
			content.add(temp[1]);
		}
		// 번호 제목 보낸이메일 받는이메일 보낸이름 받는이름 내용
		ArrayList<String> t1 = new ArrayList<>(); // 이름 + 이메일
//		ArrayList<String> t2 = new ArrayList<>();	//이메일
//		ArrayList<String> t3 = new ArrayList<>();
//		ArrayList<String> t4 = new ArrayList<>();
//		ArrayList<String> t5 = new ArrayList<>();
//		ArrayList<String> t6 = new ArrayList<>();

		BufferedReader reader2 = new BufferedReader(new FileReader("data/Contact.txt"));
//		t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사
		while ((line = reader2.readLine()) != null) {
			String[] temp = line.split(",");
			t1.add(temp[2] + "," + temp[3]);
		}
		// 번호 제목 보낸이메일 받는이메일 보낸이름 받는이름 내용
		// title, content t1(사람)
		int n = t1.size();
		Random rand = new Random();
		int c = rand.nextInt(t1.size() - 1);

		File f2 = new File("data/Email/dummyTest.txt");
		FileWriter fw = new FileWriter(f2);
		cnt = 1;
		for (int i = 0; i < 1000; i++, cnt++) {
			c = rand.nextInt(t1.size() - 1);
			String[] temp = t1.get(c).split(",");
			String sendName = temp[0];
			String sendEmail = temp[1];
			c = rand.nextInt(t1.size() - 1);
			temp = t1.get(c).split(",");
			String receiveName = temp[0];
			String receiveEmail = temp[1];
			fw.write(cnt + ",");
			fw.write(title.get(i) + ",");
			fw.write(sendEmail + ",");
			fw.write(receiveEmail + ",");
			fw.write(sendName + ",");
			fw.write(receiveName + "\n");
			fw.write(content.get(i) + "\n");
			fw.write("-----\n");
			// 번호 제목 보낸이메일 받는이메일 보낸이름 받는이름 내용
		}
		fw.close();

		for (String s : title) {
			System.out.println(s);
		}
	}

	public static void menu() throws Exception {
		while (true) {
			System.out.println("=============================================");
			System.out.println("               환영합니다.");
			System.out.println("        원하시는 업무를 선택해 주세요        ");
			System.out.println("=============================================");
			System.out.println();

			System.out.println("            [1. CONTACT] ");
			System.out.println("            메일, 메신저, 주소록");
			System.out.println();

			System.out.println("            [2. APPROVAL] ");
			System.out.println("            전자결재");
			System.out.println();

			System.out.println("            [3. NOTICE] ");
			System.out.println("            게시판");
			System.out.println();

			System.out.println("            [4. CALENDAR] ");
			System.out.println("            일정관리, 휴가관리, 예약/대여, 교육센터");
			System.out.println();

			System.out.println("            [5. HR] ");
			System.out.println("            근태관리, 인사관리, 수당관리");
			System.out.println();

			System.out.println("            [6. 관리자 전용] ");
			System.out.println("            보안, 기타");
			System.out.println();

			System.out.println("            [0. 프로그램 종료] ");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();

			if (n == 0) {
				break;
			} else if (n == 1) {
				showContact();
			} else if (n == 2) {
				showApproval();
			} else if (n == 3) {
				showNotice();
			} else if (n == 4) {
				showReservation();
			} else if (n == 5) {
				showHR();
			} else if (n == 6) {
				showAdmin();
			}

		}
		System.out.println("프로그램을 종료합니다.");
	}
/////////////////////////////////////////////////      Lane2      ///////////////////////////////////////////////

	private static void showContact() throws IOException {
		while (true) {
			cls();
			System.out.println("            [1. CONTACT] ");
			System.out.println("            1.  메일");
			System.out.println("            2.  메신저");
			System.out.println("            3.  주소록");
			System.out.println();
			System.out.println("            0.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();

			if (n == 1) {
				showMail();
			} else if (n == 2) {
				showMessenger();
			} else if (n == 3) {
				cl.firstScreen();
			} else if (n == 0) {
//				menu();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}

	}

	private static void showApproval() throws Exception {
		while (true) {
			cls();
			System.out.println("            [2. APPROVAL] ");
			System.out.println("            1.  전자결재");
			System.out.println();
			System.out.println("            0.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			if (n == 0) {
				break;
			} else if (n == 1) {
				showElecApproval();
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private static void showNotice() {
		while (true) {
			cls();
			System.out.println("            [3. NOTICE] ");
			System.out.println("            1.  게시판");
			System.out.println();
			System.out.println("            0.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			
			if (n == 1) {
				try {
					showBoard();
				} catch (IOException e) {
					System.out.println(e);
				}
			} else if (n == 0) {
//				menu();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}


	public static void showReservation() throws IOException {
		while (true) {
			cls();
			System.out.println("            [4. CALENDAR] ");
			System.out.println("            1.  일정관리");
			System.out.println("            2.  휴가관리");
			System.out.println("            3.  예약/대여");
			System.out.println("            4.  교육센터");
			System.out.println();
			System.out.println("            0.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			if (n == 1) {
				showSchedule();
			} else if (n == 2) {
				Vacation v = new Vacation(user);
				v.firstScreen();
			}  else if (n == 3) {
				showReservate();
			} else if (n == 4) {
				MyCalendar_Training mt = new MyCalendar_Training(user);
				mt.trainingScreen();
			} else if (n == 0) {
//				menu();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private static void showHR() {
		while (true) {
			cls();
			System.out.println("            [5. HR] ");
			System.out.println("            1.  근태관리");
			System.out.println("            2.  인사관리");
			System.out.println("            3.  수당관리");
			System.out.println();
			System.out.println("            0.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			if (n == 1) {
				Attendance at = new Attendance(user);
				at.attendanceScreen();
			} else if (n == 2) {
				hr.hrScreen();
			} else if (n == 3) {
				showExtraPay();
			} else if (n == 0) {
//				menu();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private static void showAdmin() {
		while (true) {
			cls();
			System.out.println("            [6. 관리자 전용] ");
			System.out.println("            1.  보안");
			System.out.println("            2.  기타");
			System.out.println();
			System.out.println("            0.  목차로 돌아가기");

			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			if (n == 1) {
//				showSecurity();
			} else if (n == 2) {
//				showEtcetera();
			} else if (n == 0) {
//				menu();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

///////////////////////////////////////////////      Lane3      ///////////////////////////////////////////////
///////////////////////////////////////////////      CONTACT    ///////////////////////////////////////////////

	private static void showMail() {
		while (true) {
			cls();
			System.out.println(" ===================================");
			System.out.println(" ||1.메일||2.메일||3.메일||4.스팸");
			System.out.println(" ||  쓰기||  읽기||  검색||  확인");
			System.out.println(" ===================================");
			System.out.println();
			System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요.");
			System.out.println("목차로 돌아가려면 0번을 누르세요.");
			System.out.println();
			System.out.println();
			int n = Integer.parseInt(Util.get("카테고리 번호"));
			System.out.println();

			Email mail = new Email(user);
			if (n == 0) {
				break;
			} else if (n == 1) {
				try {
					mail.writeMail(); // 메일 쓰기 test 완료
					puase("메일 쓰기를 완료했습니다.");
				} catch (IOException e) {
					System.out.println(e);
				}
			} else if (n == 2) {
				try {
					mail.readMail();
					puase("메일 읽기를 완료했습니다.");
				} catch (IOException e) {
					System.out.println(e);
				}
			} else if (n == 3) {
				try {
					mail.searchMail();
					puase("메일 검색을 완료했습니다.");
				} catch (IOException e) {
					System.out.println(e);
				}
			} else if (n == 4) {
				try {
					mail.sapmMailFilter();
				} catch (IOException e) {
					System.out.println(e);
				}
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}
	public static void showMessenger() throws IOException {
		while (true) {
			cls();
			System.out.println(" =========================================");
			System.out.println(" ||1.메세지||2.메세지||3.메세지||4.메세지");
			System.out.println(" ||   쓰기||    읽기||    수정||    삭제");
			System.out.println(" =========================================");
			System.out.println();
			System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요.");
			System.out.println("목차로 돌아가려면 0번을 누르세요.");
			System.out.println();
			System.out.println();
			int n = Integer.parseInt(Util.get("카테고리 번호"));
			System.out.println();

			Messenger msg = new Messenger(user);
			
			if (n == 0) {
				break;
			} else if (n == 1) {
				msg.createMessenger(); 
				puase("");
			} else if (n == 2) {
				msg.readMessenger();
				puase("");
			} else if (n == 3) {
				msg.updateMessenger();
				puase("");
			} else if (n == 4) {
				msg.deleteMessenger();
				puase("");
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}
	public static void showSchedule() throws IOException {
		while (true) {
			cls();
			System.out.println(" ========================================");
			System.out.println(" ||1.일정||2.일정||3.일정||4.일정");
			System.out.println(" ||  쓰기||  읽기||  수정||  삭제");
			System.out.println(" ========================================");
			System.out.println();
			System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요.");
			System.out.println("목차로 돌아가려면 0번을 누르세요.");
			System.out.println();
			System.out.println();
			int n = Integer.parseInt(Util.get("카테고리 번호"));
			System.out.println();

			MyCalendar mc = new MyCalendar(user);
			
			if (n == 0) {
				break;
			} else if (n == 1) {
				mc.createSchedule();
				puase("일정 쓰기를 완료했습니다.");
			} else if (n == 2) {
				//mc.showSchedule();
				puase("일정 읽기를 완료했습니다.");
			} else if (n == 3) {
				mc.updateSchedule();
				puase("일정 수정을 완료했습니다.");
			} else if (n == 4) {
				mc.deleteSchedule();
				puase("일정 삭제를 완료했습니다.");
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}
	   public static void showReservate() throws IOException {
		      System.out.println("            [4. RESERVATION - 2.예약/대여]");
		      System.out.println();
		      System.out.println("            1. 회의실 예약");
		      System.out.println("            2. 차량 대여");
		      System.out.println();
		      System.out.println("            0. 목차로 돌아가기");
		      String n = (Util.get("번호를 입력해주세요"));
		      
		      if(n.equals("1")) {
		         //수빈 - 회의실 예약
		      } else if(n.equals("2")) {
		         
		         CoperationCar cop = new CoperationCar(user);
		         cop.copCarScreen();
		         
		      } else {
		         try {
					menu();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		   }

	
	public static void showExtraPay() {

		PayRoll ep = new PayRoll();

		ep.load();

		System.out.println();
		System.out.println();
		System.out.println("            [5. HR]");
		System.out.println("            ▣ 수당관리 ▣");
		System.out.println("            1. 근로 수당 조회");
		System.out.println("            2. 성과급 조회");
		System.out.println("            3. 인사부 전용");
		System.out.println("            0. 목차로 돌아가기");
		System.out.println();
		System.out.print("선택: ");

		String n = (Util.get("번호를 입력해주세요."));

		if (n.equals("1")) {
			ep.extraWork(Main.user);
		} else if (n.equals("2")) {
			ep.bonus(Main.user);
		} else if (n.equals("3")) {
			ep.HrAccess();
		} else if (n.equals("0")) {
			return;
//			menu();
		} else {
			System.out.println("※ 올바르지 않은 입력입니다 ※");
			System.out.println();
			System.out.println();

			showExtraPay();
		}

	}
	

	

///////////////////////////////////////////////      APPROVAL    ///////////////////////////////////////////////
	private static void showElecApproval() throws Exception {
		while (true) {
			cls();
			System.out.println(" ===================================================================");
			System.out.println(" ||1.전자결재||2.전자결재||3.전자결재||4.전자결재||5.내가쓴 전자결재");
			System.out.println(" ||    쓰기  ||    읽기  ||    수정  ||    삭제  ||        확인");
			System.out.println(" ===================================================================");
			System.out.println();
			System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요.");
			System.out.println("목차로 돌아가려면 0번을 누르세요.");
			System.out.println();
			System.out.println();
			int n = Integer.parseInt(Util.get("카테고리 번호"));
			System.out.println();

			ElecApproval ea = new ElecApproval(user);
			if (n == 1) {
				ea.createElecApproval();
			} else if (n == 2) {
				ea.readElecApproval();
			} else if (n == 3) {
				ea.updateElecApproval();
			} else if (n == 4) {
				ea.deleteElecApproval();
			} else if (n == 5) {
				ea.myElecApproval();
			} else if (n == 6) {
				try {
					ea.setElecApprovalCondition();
				} catch (IOException e) {
					System.out.println(e);
				}
			} else if (n == 0) {
				showContact();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

///////////////////////////////////////////////      NOTICE         ///////////////////////////////////////////////
	public static void showBoard() throws IOException {
		while (true) {
			cls();
			System.out.println(" ========================================");
			System.out.println(" ||1.게시글||2.게시글||3.게시글||4.게시글");
			System.out.println(" ||   쓰기||    읽기||    수정||    삭제");
			System.out.println(" ========================================");
			System.out.println();
			System.out.println("검색 하고자 하는 카테고리(번호)를 선택하세요.");
			System.out.println("목차로 돌아가려면 0번을 누르세요.");
			System.out.println();
			System.out.println();
			int n = Integer.parseInt(Util.get("카테고리 번호"));
			System.out.println();

			Board board = new Board(user);
			if (n == 0) {
				break;
			} else if (n == 1) {
				board.createBoard();
				puase("게시글 쓰기를 완료했습니다.");
			} else if (n == 2) {
				board.readBoard();
				puase("게시글 읽기를 완료했습니다.");
			} else if (n == 3) {
				board.updateBoard();
				puase("메일 검색을 완료했습니다.");
			} else if (n == 4) {
				board.deleteBoard();
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}
///////////////////////////////////////////////      RESERVATION    ///////////////////////////////////////////////
///////////////////////////////////////////////      HR             ///////////////////////////////////////////////
///////////////////////////////////////////////      ADMIN          ///////////////////////////////////////////////
	private static void puase(String string) {
		System.out.println(string);
		System.out.println("계속하시려면 엔터를 입력해주세요.");
		sc.nextLine();

	}

	private static void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}
