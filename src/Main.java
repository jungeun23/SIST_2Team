import java.io.IOException;
import java.util.Scanner;

public class Main {
	Scanner sc = new Scanner(System.in);
	private static User user = new User();

	public static void main(String[] args) throws Exception {

//		user.regist_user();
//		user.updateDB();
//		System.out.println(user);

		Login login = new Login();
		user = login.loginScreen();

		menu();

//		MyCalendar c = new MyCalendar();
//		c.output();

	}

	private static void menu() {
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
			System.out.println("            전자결재, 근태관리, 휴과관리");
			System.out.println();

			System.out.println("            [3. NOTICE] ");
			System.out.println("            게시판");
			System.out.println();

			System.out.println("            [4. RESERVATION] ");
			System.out.println("            일정관리, 예약/대여, 교육센터");
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
	}
/////////////////////////////////////////////////      Lane2      ///////////////////////////////////////////////

	private static void showContact() {
		while (true) {
			cls();
			System.out.println("            [1. CONTACT] ");
			System.out.println("            1.  메일");
			System.out.println("            2.  메신저");
			System.out.println("            3.  주소록");
			System.out.println();
			System.out.println("            4.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();

			if (n == 1) {
				showMail();
			} else if (n == 2) {
//				showMessenger();
			} else if (n == 3) {
//				showContactAddress();
			} else if (n == 4) {
				menu();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}

	}

	private static void showApproval() {
		while (true) {
			cls();
			System.out.println("            [2. APPROVAL] ");
			System.out.println("            1.  전자결재");
			System.out.println("            2.  근태관리");
			System.out.println("            3.  휴가관리");
			System.out.println();
			System.out.println("            4.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			if (n == 1) {
				showElecApproval();
			} else if (n == 2) {
//				showAttendance();
			} else if (n == 3) {
//				showVacation();
			} else if (n == 4) {
				menu();
				break;
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
			System.out.println("            2.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			if (n == 1) {
//				showBoard();
			} else if (n == 2) {
				menu();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	private static void showReservation() {
		while (true) {
			cls();
			System.out.println("            [4. RESERVATION] ");
			System.out.println("            1.  일정관리");
			System.out.println("            2.  예약/대여");
			System.out.println("            3.  교육센터");
			System.out.println();
			System.out.println("            4.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			if (n == 1) {
//				showSchedule();
			} else if (n == 2) {
//				showReservate();
			} else if (n == 3) {
//				showTrainingCenter();
			} else if (n == 4) {
				menu();
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
			System.out.println("            4.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			if (n == 1) {
//				showElecApproval();
			} else if (n == 2) {
//				showAttendance();
			} else if (n == 3) {
//				showVacation();
			} else if (n == 4) {
				menu();
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
			System.out.println("            3.  목차로 돌아가기");

			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();
			if (n == 1) {
//				showSecurity();
			} else if (n == 2) {
//				showEtcetera();
			} else if (n == 3) {
				menu();
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
			System.out.println("            [Mail 업무] ");
			System.out.println("            1.  메일 쓰기");
			System.out.println("            2.  메일 읽기");
			System.out.println("            3.  검색어로 메일 찾기");
			System.out.println("            4.  Spam 확인");
			System.out.println();
			System.out.println("            0.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();

			Email mail = new Email(user);
			if (n == 1) {

				try {
					mail.writeMail();
				} catch (IOException e) {
					System.out.println(e);
				}
			} else if (n == 2) {
				try {
					mail.readMail();
				} catch (IOException e) {
					System.out.println(e);
				}
			} else if (n == 3) {
				try {
					mail.searchMail();
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

///////////////////////////////////////////////      APPROVAL    ///////////////////////////////////////////////
	private static void showElecApproval() {
		while (true) {
			cls();
			System.out.println("            [전자결재 업무] ");
			System.out.println("            1.  문서 작성");
			System.out.println("            2.  문서 읽기");
			System.out.println("            3.  댓글 달기");
			System.out.println("            4.  내가 작성한 문서 확인");
			System.out.println("            5.  문서 삭제");
			System.out.println("            6.  문서 관리(직급별)");
			System.out.println();
			System.out.println("            0.  목차로 돌아가기");
			System.out.println();
			int n = Integer.parseInt(Util.get("번호를 입력해주세요"));
			System.out.println();
			System.out.println();

			ElecApproval ea = new ElecApproval(user);
			if (n == 1) {
				try {
					ea.createElecApproval();
				} catch (Exception e) {
					System.out.println(e);
				}
			} else if (n == 2) {
				ea.readElecApproval();
			} else if (n == 3) {
				ea.addCommnetElecApproval();
			} else if (n == 4) {
				ea.myElecApproval();
			} else if (n == 5) {
				ea.deleteElecApproval();
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

	private static void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}
