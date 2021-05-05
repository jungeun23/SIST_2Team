
public class MeetingRoom {
    private String[] roomNumber = {"501","502","503","504","505","506","601","602","603","701","702","703","704","815","816","817","819","820","821"};
    private User user;
    private MyCalendar_subin mc;
    
    public MeetingRoom(User user) {
    	this.user = user;
    	this.mc = new MyCalendar_subin(user);
    }
    
    public void createRoomReservation() {
    	mc.createRoomReservation(roomNumber);
    }
}
