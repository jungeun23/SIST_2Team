
public class User {
	private String ID;
	private String passWord;
	
	public User(String iD) {
		super();
		this.ID = iD;
	}

	public User() {
		this.ID = "";
		this.passWord = "";
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		// TODO Auto-generated method stub
		this.ID = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
