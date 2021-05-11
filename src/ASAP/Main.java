package ASAP;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Approval.ElecApproval;
import Calendar.CoperationCar;
import Calendar.MeetingRoom;
import Calendar.MyCalendar;
import Calendar.PayRoll;
import Calendar.TrainingCenter;
import Calendar.Vacation;
import Contact.ContactList;
import Contact.Email;
import Contact.Messenger;
import HR.Attendance;
import HR.HR;
import Notice.Board;


public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static User user = new User();

	private static HR hr = new HR();
	private static ContactList cl = new ContactList();
	private static Email mail;
	private static Messenger msg;
	private static MyCalendar mc;
	private static CoperationCar cop;
	private static ElecApproval ea;
	private static Vacation v;
	private static TrainingCenter tc;
	private static Attendance at;
	private static PayRoll ep;
	private static Board board;

	public static void main(String[] args) throws Exception {

		Login login = new Login();
		user = login.loginScreen();
		
		mail = new Email(user);
		msg = new Messenger(user);
		mc = new MyCalendar(user);
		cop = new CoperationCar(user);
		ea = new ElecApproval(user);
		v= new Vacation(user);
		tc = new TrainingCenter(user);
		at = new Attendance(user);
		ep = new PayRoll();
		board = new Board(user);
		menu();
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
				
				v.firstScreen();
			} else if (n == 3) {
				showReservate();
			} else if (n == 4) {
				
				tc.trainingScreen();
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
