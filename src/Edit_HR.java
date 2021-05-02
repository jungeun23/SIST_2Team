import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Edit_HR {

	private static ArrayList<String[]> list;
	
	static {
		list = new ArrayList<String[]>();
	}
	
	//이름,직급,부서,인사고과,근속년수,근무시간 순으로 데이터 더미 만들기
	public static void main(String[] args) {

		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("data\\Contact.txt"));
			
			String line = "";
			String result = "";
			
			while((line = reader.readLine())!=null) {
				//아이디,비밀번호,이름,이메일,전화번호,직급,부서
				String[] temp = line.split(",");
				
				//인사 고가 데이터 만들기
				String[] goga = {"A", "B", "C", "D", "E"};
				
				Random rand = new Random();
				
				int rnd = rand.nextInt(5);
				
				
				result += String.format("%s,%s,%s,%s\n", temp[2],temp[5],temp[6],goga[rnd]);
				
			
				
			}//while
			
//			System.out.println(result);
//			
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
