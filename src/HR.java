import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HR {

   private final String DATA;
   private final String DATA2;
//   private String assigned;
//   private int position;
   private Scanner scan;
   ArrayList<String[]> list = new ArrayList<String[]>();
   
   public HR() {

      DATA = "data//HR.txt";
      DATA2 = "data//Contact.txt";
      scan = new Scanner(System.in);
      
   }
   
   public void hrScreen() {
   
      //관리자 전용 비밀번호 -> 권한 있는 사용자에게만 배포
      int pw = 1234; 
      
      System.out.println("관리자 비밀번호를 입력하세요.");
      System.out.println("=======================");
      System.out.print("      PW: ");
      pw = scan.nextInt();
      System.out.println("=======================");
      
      
      if(pw == 1234) {
         
         System.out.println("[로그인 성공]");
         System.out.println();
         System.out.println();
         System.out.println();
         
         
         read();
         //cls();
         
         System.out.println("===============================================");
         System.out.println("|| 1. 부서  || 2. 직급 || 3. 신입사원 || 4. 퇴사직원 ||");
         System.out.println("||    변경  ||    변경 ||    정보추가 ||    정보삭제 ||");
         System.out.println("===============================================");
         System.out.println();
         System.out.print(" 카테고리(번호)를 선택히세요: ");
         //cls();
         
         int num = scan.nextInt();
         
         if(num == 1) {
            buseoAlter();
            
         } else if(num == 2) {
            positionAlter();
            
         } else if(num == 3) {
            add();
            
         } else if(num == 4) {
            delete();
            
         } else {
            
            System.out.println("카테고리(번호)를 다시 입력하세요.");
         }
         
      } else {
         System.out.println("권한이 있는 사용자가 아닙니다.");
         System.out.println("비밀번호를 다시 입력해주세요.");
      }
   
   }//hrScreen()
   
   private void delete() {

      
      
   }
   
   private void add() {
      
   }

   

   private void positionAlter() {
      
      //"인턴","사원","대리","과장","차장","부장" + 상무, 전무, 사장
      //t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사 -> temp[5]
      
      System.out.println("=====================================================================");
      System.out.println("||1.인턴 ||2.사원 ||3.대리 ||4.과장 ||5.차장 ||6.부장 ||7.상무 ||8.전무 ||9.사장||");
      System.out.println("=====================================================================");
      String name = (Util.get("변경할 사용자의 이름"));
      System.out.println();
      
      int index = -1;
      for(int i=0; i<list.size(); i++) {
         
         //list 방 찾기
         if(list.get(i)[2].equals(name)) {
            index=i;
            break;
         }
      }
      
      System.out.printf("[%s님의 정보]", list.get(index)[2]);
      System.out.println(list.get(index));
      System.out.println();
      
      int num = Integer.parseInt(Util.get("변경할 직급에 해당하는 번호를 입력하세요"));
   
      
      
      
      
      //입력된 사원 정보 보여주기
//      String s = String.format("%s,%s,%s,%s,%s,%s,%s"
//                              , list.get(index)[0]
//                              , list.get(index)[1]
//                              , list.get(index)[2]
//                              , list.get(index)[3]
//                              , list.get(index)[4]
//                              , list.get(index)[5]
//                              , list.get(index)[6]);
      
//      System.out.println(s);
      
      if(num == 1) {
         
         
      } else if(num == 2) {
         
      } else if(num == 3) {
         
      } else if(num == 4) {
         
      } else if(num == 5) {
         
      } else if(num == 6) {
         
      } else if(num == 7) {
         
      } else if(num == 8){
         
         
      } else if(num == 9){
         
         
      } else {
         
         System.out.println("잘못된 번호를 입력하셨습니다.");
      }
      
   }

   private void buseoAlter() {
      
      //"회계","재무","인사","영업","마케팅","개발","디자인"
      //t,t,홍길동,a@sis2.co.kr,010-1234-1234,차장,인사 -> temp[6]
      
      System.out.println("=========================================================");
      System.out.println("||1.회계 ||2.재무 ||3.인사 ||4.영업 ||5.마케팅 ||6.개발 ||7.디자인||");
      System.out.println("=========================================================");
      String name = (Util.get("변경할 사용자의 이름"));
      System.out.println();
      int num = Integer.parseInt(Util.get("변경할 부서에 해당하는 번호를 입력하세요"));
      
      
      
      
      
      if(num == 1) {
         
      } else if(num == 2) {
         
      } else if(num == 3) {
         
      } else if(num == 4) {
         
      } else if(num == 5) {
         
      } else if(num == 6) {
         
      } else if(num == 7) {
         
      } else {
         
         System.out.println("잘못된 번호를 입력하셨습니다.");
      }
      
      
   }
   
   private static void cls() {
      
      for(int i=0; i<100; i++) {
         System.out.println();
      }
   }
   
   private void read() {
      
      try {
         
         BufferedReader reader = new BufferedReader(new FileReader(DATA2));
      
         String line = "";
         while((reader.readLine() != null)) {
         
         String[] temp = line.split(",");
         
         this.list.add(temp);
         }
      
      } catch (Exception e) {
         
         System.out.println(e);
      }
   }
      
   
   private void save() {
      //바뀐 내용 저장하는 작업
      
      
      
      
      
      
   }
   
   
   
}//class





