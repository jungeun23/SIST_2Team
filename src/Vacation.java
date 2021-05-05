
public class Vacation {
	User user;
	MyCalendar_subin mc;
//	final String HRDB = "data\\HR.txt";
	
	Vacation(User user){
		this.user = user;
		this.mc = new MyCalendar_subin(user);
//		load();
	}
	
	public void createVacation(){
		mc.createScheduleVacation();
	}
	
	public void deleteVacation() {
		
	}
	
	public void readVacation() {
		
	}
	
	public void updateVacation() {
		
	}
}
