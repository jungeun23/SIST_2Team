import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Edit_HR {

	private static ArrayList<String[]> list;
	
	static {
		list = new ArrayList<String[]>();
	}
	
	
	public static void main(String[] args) {

		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("data\\Contact.txt"));
			
			String line = "";
			String result = "";
			
			while((line = reader.readLine())!=null) {
				//아이디,비밀번호,이름,이메일,전화번호,직급,부서
				String[] temp = line.split(",");
				
				result += String.format("%s,%s,%s\n", temp[2],temp[5],temp[6]);
				
			}
			
//			System.out.println(result);
			
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
