
public class TrainingCenter {
	User user;
	MyCalendar_junhee mc;
	public TrainingCenter(User user) {
		this.user =user;
		this.mc = new MyCalendar_junhee(user);
	
	
	}
	public void trainingScreen() {
		
		
		
		
		
	}
	
	
	/**
	 * @author 2조
	 * 
	 */
	public void createTraining() {
		mc.createTraining();
	}

	public void deleteTraining() {
		mc.deleteTrainingSchedule();
	}

	public void readTraining() {
		mc.readTrainingSchedule();
	}

	public void updateTraining() {
		mc.updateTrainingSchedule();
	}
	
	
}
