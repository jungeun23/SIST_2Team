import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Edit_HR {

	private static ArrayList<String[]> list;
	private static Random rand;
	static {
		list = new ArrayList<String[]>();
		rand = new Random();
	}
	
	//이름,직급,부서,인사고과,근속년수,근무시간 순으로 데이터 더미 만들기
	public static void main(String[] args) {

		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("data\\HR.txt"));
			
			String line = "";
			String result = "";

			
			
			
			
			//월급 데이터 만들기
//			while((line = reader.readLine())!=null) {
//				
//				//이름,직급,부서,인사고과,경력연차
//				String[] temp = line.split(",");
//				
//				int salary = 0;
//				
//				if(temp[1].equals("인턴")) {
//					salary = 2100000;
//					
//				} else if(temp[1].equals("사원")) {
//					salary = 2800000;
//					
//				} else if(temp[1].equals("대리")) {
//					salary = 3200000;
//					
//				} else if(temp[1].equals("과장")) {
//					salary = 4000000;
//					
//				} else if(temp[1].equals("차장")) {
//					salary = 5000000;
//					
//				} else if(temp[1].equals("부장")) {
//					salary = 6000000;
//					
//				} else if(temp[1].equals("상무")) {
//					salary = 7000000;
//					
//				} else if(temp[1].equals("전무")) {
//					salary = 8000000;
//					
//				} else if(temp[1].equals("사장")) {
//					salary = 10000000;
//					
//				}
//				
//				result += line +","+ salary +"\n";
//				
//				
//			}
//			
//			System.out.println(result);
			// 경력 연차 만들기
//			while((line = reader.readLine())!=null) {
//				//이름,직급,부서,인사고과,경력연차
//				String[] temp = line.split(",");
//				
//				String year = "";
//				
//				if(temp[1].equals("인턴")) {
//					year = (rand.nextInt(2)+1) +""; //인턴 1~2년
//				} else if(temp[1].equals("사원")) {
//					year = (rand.nextInt(3)+1) + ""; //사원 1~3년  
//				} else if(temp[1].equals("대리")) {
//					year = (rand.nextInt(4)+4) + ""; //대리 4~7년
//				} else if(temp[1].equals("과장")) {
//					year = (rand.nextInt(4)+8) + ""; //과장 8~11년
//				} else if(temp[1].equals("차장")) {
//					year = (rand.nextInt(5)+12) + ""; //차장 12~16년
//				} else if(temp[1].equals("부장")) { 
//					year = (rand.nextInt(5)+17) + ""; //부장 17~21년
//				} else if(temp[1].equals("상무")) {
//					year = 23 +"";
//				} else if(temp[1].equals("전무")) {
//					year = 25 +"";
//				} else {
//					year = 30 + "";
//				}
//				
//				result += String.format("%s,%s,%s,%s,%s\n", temp[0],temp[1],temp[2],temp[3],year);
//			}
			
//			System.out.println(result);
			
			
//				//인사 고과 데이터 만들기
//			while((line = reader.readLine())!=null) {
//				//아이디,비밀번호,이름,이메일,전화번호,직급,부서
//				String[] temp = line.split(",");
//				
//				String[] goga = {"A", "B", "C", "D", "E"};
//				
//				Random rand = new Random();
//				
//				int rnd = rand.nextInt(5);
//				
//				
//				result += String.format("%s,%s,%s,%s\n", temp[2],temp[5],temp[6],goga[rnd]);
//				
//			}//while
			
//			System.out.println(result);
//			
			
			//Writer
//			BufferedWriter writer = new BufferedWriter (new FileWriter("data\\HR.txt"));
//			
//			writer.write(result);
//			
//			writer.close();
			
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
		
	}

}
