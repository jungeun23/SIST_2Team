import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Edit {

	private final static String DATA;

	static {
		DATA = "data\\Contact.txt";
	}

	public static void main(String[] args) {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";
			String temp2 ="";
			
			while((line = reader.readLine())!= null) {
				
				ArrayList<String> temp3 = new ArrayList<String>();

				String[] temp = line.split(",");
				
				for(int i=0; i<temp.length; i++) {
					
					temp3.add(temp[i]);
					
				}

				String[] jikgeop = {"인턴","사원","대리","과장","차장","부장" };
				String[] buseo = { "회계","재무","인사","영업","마케팅","개발","디자인"};
				
				Random rand = new Random(); 
				
				temp3.set(5, jikgeop[rand.nextInt(6)]);
				
				temp3.add(buseo[rand.nextInt(7)]);
				
				
				temp2 += String.format("%s,%s,%s,%s,%s,%s,%s\n"
														, temp3.get(0)
														, temp3.get(1)
														, temp3.get(2)
														, temp3.get(3)
														, temp3.get(4)
														, temp3.get(5)
														, temp3.get(6));
			}//while
			
			
			System.out.println(temp2);
			
			
			
			
			
			
			
			
			//이메일 주소 통일
//			while ((line = reader.readLine()) != null) {
//
//				String[] temp = line.split(",");
//
//				temp[3] = temp[3].substring(0, temp[3].indexOf("@") + 1);
//
//				String add = "sist2.co.kr";
//
//				temp[3] = temp[3].concat(add);
//
//				 
//				temp2 += String.format("%s,%s,%s,%s,%s,%s\n", temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
//
//			}

			BufferedWriter writer = new BufferedWriter (new FileWriter(DATA));
			
			writer.write(temp2);
			
			writer.close();
			
//			System.out.println(temp2);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
