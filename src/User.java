
public class User {
	private String iD;
	private String passWord;
	
	public User(String iD) {
		super();
		this.iD = iD;
	}

	public User() {
		this.iD = "";
		this.passWord = "";
	}

	public User(User iD) {
		// TODO Auto-generated constructor stub
		this.iD = iD.iD;
		this.passWord = iD.passWord;
	}

	public String getID() {
		return iD;
	}

	public void setID(String id) {
		// TODO Auto-generated method stub
		this.iD = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
