import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

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
			while ((line = reader.readLine()) != null) {

				String[] temp = line.split(",");

				temp[3] = temp[3].substring(0, temp[3].indexOf("@") + 1);

				String add = "sist2.co.kr";

				temp[3] = temp[3].concat(add);

				 
				temp2 += String.format("%s,%s,%s,%s,%s,%s\n", temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);

			}

			BufferedWriter writer = new BufferedWriter (new FileWriter(DATA));
			
			writer.write(temp2);
			
			writer.close();
			
			System.out.println(temp2);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
