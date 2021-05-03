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

//		Email mail = new Email(user);

//		mail.readMail();
		menu();

//		MyCalendar c = new MyCalendar();
//		c.output();
		//makeEmailDummy();
	}

	private static void makeEmailDummy() throws Exception {
		String path = "Data\\Contract.txt"; //제목 더미를... 하ㅜㅜ 생각좀해봐야겟다.
		File f = new File(path);
//		System.out.println(f);
		int cnt = 0;
		BufferedReader reader = new BufferedReader(new FileReader("Data\\Email\\dummy.txt"));
		
		String line ="";
		String result = "";
		ArrayList<String> title = new ArrayList<>(); //5, 6
		ArrayList<String> content = new ArrayList<>();
		
		while((line = reader.readLine()) != null){
			String[] temp = line.split(",");
//			System.out.println(Arrays.toString(temp));
			title.add(temp[0]);
			content.add(temp[1]);
		}
		// 번호 제목 보낸이메일 받는이메일 보낸이름 받는이름 내용 
		ArrayList<String> t1 = new ArrayList<>();	//이름 + 이메일
//		ArrayList<String> t2 = new ArrayList<>();	//이메일
//		ArrayList<String> t3 = new ArrayList<>();
//		ArrayList<String> t4 = new ArrayList<>();
//		ArrayList<String> t5 = new ArrayList<>();
//		ArrayList<String> t6 = new ArrayList<>();
		
		BufferedReader reader2 = new BufferedReader(new FileReader("data\\Contact.txt"));
//		t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사
		while((line = reader2.readLine())!=null) {
			String[] temp = line.split(",");
			t1.add(temp[2] +","+temp[3]);
		}
		//번호 제목 보낸이메일 받는이메일 보낸이름 받는이름 내용
		//title, content t1(사람)
		int n = t1.size();
		Random rand = new Random();
		int c = rand.nextInt(t1.size()-1);
		
		File f2 = new File("data\\Email\\dummyTest.txt");
		FileWriter fw = new FileWriter(f2);
		cnt=1;
		for(int i=0; i<1000; i++, cnt++) {
			c = rand.nextInt(t1.size()-1);
			String[] temp = t1.get(c).split(",");
			String sendName = temp[0];
			String sendEmail = temp[1];
			c = rand.nextInt(t1.size()-1);
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
			//번호 제목 보낸이메일 받는이메일 보낸이름 받는이름 내용
		}
		fw.close();
		
		
		
			
		for(String s : title) {
			System.out.println(s);
		}
	}

	private static void menu() throws IOException {
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
				return;
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

	private static void showContact() throws IOException {
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
				cl.firstScreen();
			} else if (n == 4) {
				menu();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}

	}

	private static void showApproval() throws IOException {
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

	private static void showNotice() throws IOException {
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

	private static void showReservation() throws IOException {
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

	private static void showHR() throws IOException {
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
				hr.hrScreen();
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

	private static void showAdmin() throws IOException {
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

	private static void showMail() throws IOException {
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
			} else if (n == 0) {
				showContact();
				break;
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

///////////////////////////////////////////////      APPROVAL    ///////////////////////////////////////////////
	private static void showElecApproval() throws IOException {
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
				//ea.addCommnetElecApproval();
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
