
public class ExtraPay {
	private int workHour;
	private String position;
	
	public ExtraPay(int workHour, String position) {
		super();
		this.workHour = workHour;
		this.position = position;
	}
	
	public int getWorkHour() {
		return workHour;
	}
	public void setWorkHour(int workHour) {
		this.workHour = workHour;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
