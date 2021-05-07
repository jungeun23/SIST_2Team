import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Edit {

	private final static String HR;
	private final static String PATH;
	private final static String CAR;
	
	static {
		HR = "data\\HR.txt";
		CAR = "data\\schedule\\listCar.txt";
		
		PATH = "data\\schedule\\training.txt";
//		PATH = "data\\schedule\\vacation.txt";
//		PATH = "data\\schedule\\car.txt";
//		PATH = "data\\schedule\\meetingRoom.txt";
		
	}

	public static void main(String[] args) {

		try {
	
			BufferedReader hrReader = new BufferedReader(new FileReader(HR));
			BufferedReader listCarReader = new BufferedReader(new FileReader(CAR));
			
			
			
			String line = "";
			String temp2 ="";
			ArrayList<String[]> hr = new ArrayList<String[]>();
			ArrayList<String[]> listCar = new ArrayList<String[]>();

			
			
			while((line = hrReader.readLine())!=null) {
				
				String[] temp = line.split(",");
				
				hr.add(temp);
				
			}
			
			Random rnd = new Random();
			line = "";
			
			//교육센터 더미 만들기
			String[] ment1 = {"신입사원 교육","법정 의무 교육","사내 직무 과정"};
			String[] ment2 = {"신입사원 인성 교육","신입사원 직무 교육","신입사원 멘토링"
								,"산업 안전 보건 교육","직장 내 성희롱 예방 교육","개인정보보호 교육"
								,"엑셀실무","기초 재무회계","데이터 분석과 통계","한 장 보고서 작성"};
			
			
			for(int i=0; i<300; i++) {
				
				int nh = rnd.nextInt(350);
				int nm = rnd.nextInt(12)+1;
				int nd = rnd.nextInt(30)+1;
				
				int nn = rnd.nextInt(3);
				int ne = -1;
				if(nn == 0) {
					ne = rnd.nextInt(3);
				} else if(nn == 1) {
					ne = rnd.nextInt(3)+3;
				} else if(nn == 2) {
					ne = rnd.nextInt(4)+6;
				}
				
				line += String.format("%s,%s,%s,%s,%s,%s\n"
						                    , hr.get(nh)[0]
											, hr.get(nh)[1]
											, hr.get(nh)[2]
											,("2021-"+nm+"-"+nd)
											, ment1[nn]
											, ment2[ne]);
						
			}
			
			System.out.println(line);
			
//			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH,true));
//			
//			writer.write(line);
//			writer.close();
			
			//휴가 더미 만들기
			
//			String[] ment = {"개인 사유","병원 진료","병원 방문","은행 방문","집안 행사", "가족 돌봄","병문안","집 수리","개인 휴가","Refresh","이사","공공기관 방문"};
//			
//			for(int i=0; i<300; i++) {
//				
//				int nh = rnd.nextInt(350);
//				int nv = rnd.nextInt(20) +1;
//				int nm = rnd.nextInt(12)+1;
//				int nd = rnd.nextInt(30)+1;
//				int nn = rnd.nextInt(12);
//				
//				line += String.format("%s,%s,%s,%s,%s,%s\n"
//						                    , hr.get(nh)[0]
//											, hr.get(nh)[1]
//											, hr.get(nh)[2]
//											, nv+""
//											,("2021-"+nm+"-"+nd)
//											,ment[nn]);
//						
//				
//			}
//			
//			System.out.println(line);
			
			

			
			//미팅룸 더미
//			String[] roomNumber = { "501", "502", "503", "504", "505", "506", "601", "602", "603", "701", "702", "703",
//					"704", "815", "816", "817", "819", "820", "821" };
//			String[] ment = {"주간 회의","월간 보고","일간 보고","협력사 미팅","클라이언트 미팅","업무 협의","파견 직원 면접","신입 직원 면접"};
//			
//			
//			for(int i=0; i<300; i++) {
//				int nh = rnd.nextInt(350);
//				int nm = rnd.nextInt(12)+1;
//				int nd = rnd.nextInt(30)+1;
//				int nr = rnd.nextInt(19);
//				int nn = rnd.nextInt(8);
//				
//				line += String.format("%s,%s,%s,%s,%s,%s\n"
//						, hr.get(nh)[0]
//								, hr.get(nh)[1]
//										, hr.get(nh)[2]
//												, roomNumber[nr]
//														,("2021-"+nm+"-"+nd)
//														, ment[nn]);
//				
//				
//			}
			

			
			//Car 차량 대여 더미 만들기
			//			while((line = listCarReader.readLine())!= null) {
//				
//				String[] temp = line.split(",");
//				listCar.add(temp);
//				
//			}
//			
//			
//			
//			Random rnd = new Random();
//			
//			String[] ment = {"외부 미팅", "협력사 미팅", "외부 출장", "외근","지사 출장","클라이언트 미팅","지사 방문"};
//			
//			for(int i=0; i<300; i++) {
//				
//				int nh = rnd.nextInt(350);
//				
//				int nc = rnd.nextInt(110);
//				
//				int nm = rnd.nextInt(12)+1;
//				int nd = rnd.nextInt(30)+1;
//				int nn = rnd.nextInt(7);
//				
//				line += String.format("%s,%s,%s,%s,%s,%s,%s\n"
//											, hr.get(nh)[0]
//											, hr.get(nh)[1]
//											, hr.get(nh)[2]
//											, listCar.get(nc)[0]
//											, listCar.get(nc)[1]
//											, ("2021-"+nm+"-"+nd)
//											, ment[nn]);
//				
//			}
//			
//			System.out.println(line);
			
			
			
			//직급,부서 랜덤 부여
//			BufferedReader reader = new BufferedReader(new FileReader(DATA));
//
//			String line = "";
//			String temp2 ="";
//			
//			while((line = reader.readLine())!= null) {
//				
//				ArrayList<String> temp3 = new ArrayList<String>();
//
//				String[] temp = line.split(",");
//				
//				for(int i=0; i<temp.length; i++) {
//					
//					temp3.add(temp[i]);
//					
//				}
//
//				String[] jikgeop = {"인턴","사원","대리","과장","차장","부장" };
//				String[] buseo = { "회계","재무","인사","영업","마케팅","개발","디자인"};
//				
//				Random rand = new Random(); 
//				
//				temp3.set(5, jikgeop[rand.nextInt(6)]);
//				
//				temp3.add(buseo[rand.nextInt(7)]);
//				
//				
//				temp2 += String.format("%s,%s,%s,%s,%s,%s,%s\n"
//														, temp3.get(0)
//														, temp3.get(1)
//														, temp3.get(2)
//														, temp3.get(3)
//														, temp3.get(4)
//														, temp3.get(5)
//														, temp3.get(6));
//			}//while
//			
//			
//			System.out.println(temp2);
//			
//			
//			
//			
//			
			
			
			
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

			
			
			//버퍼 쓰기는 다 확인하고 마지막에!! 
//			BufferedWriter writer = new BufferedWriter (new FileWriter(DATA));
//			
//			writer.write(temp2);
//			
//			writer.close();
			
//			System.out.println(temp2);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
