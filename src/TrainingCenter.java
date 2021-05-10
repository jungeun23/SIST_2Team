import java.util.Scanner;

public class TrainingCenter {
	User user;
	MyCalendar_Training mc;
	public static Scanner scan;

	public TrainingCenter(User user) {
		this.user = user;
		this.mc = new MyCalendar_Training(user);
		scan = new Scanner(System.in);

	}

	public void trainingScreen() {
		
		try {
			while(true) {
				System.out.println("                       ▣ 교육 일정 항목 ▣");
				System.out.println("=============================================================================");
				System.out.println("||1.	교육	||2.	교육	||3.	교육	||4.	교육	||5.	뒤로||");
				System.out.println("||  	일정	||  	일정	||  	일정	||  	일정	||  	가기||");
				System.out.println("||  	등록	||  	조회	||  	수정	||  	삭제	||         ||");
				System.out.println("=============================================================================");
				System.out.print(" 카테고리(번호)를 선택하세요: ");
				int num = scan.nextInt();
				
				if (num == 1) {
					createTraining();
					
				} else if (num == 2) {
					readTraining();
					
				} else if (num == 3) {
					updateTraining();
					
				} else if (num == 4) {
					deleteTraining();
					
				} else if (num == 5) {
					
					Main.menu();
					
				} else {
					
					System.out.println("카테고리(번호)를 다시 입력하세요.");
				}
			}//while 
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	public void createTraining() {
		mc.createTraining();
	}

	public void readTraining() {
		mc.readTrainingSchedule();
	}

	public void updateTraining() {
		mc.updateTrainingSchedule();
	}

	public void deleteTraining() {
		mc.deleteTrainingSchedule();
	}
	
	/**
	 * 일시정지 기능의 메소드
	 */
	public static void pause() {
		System.out.println();
		System.out.println("엔터를 누르시면 항목으로 돌아갑니다.");
		scan.nextLine();// Block
		cls();

	}
	
	/**
	 * 100줄을 띄워 새로운 창이 뜨는 효과를 나타내주는 메소드 
	 */
	private static void cls() {

		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}// cls()
	
}
