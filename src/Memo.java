public class Memo {
	/*
	 * 1,홍길동,2021-03-25 12:30:00,B 메모 테스트입니다.
	 */

	private String id;
	private String pw;
	private String name;
	private String email;
	private String phone;
	private int position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Memo [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", position=" + position + "]";
	}

}
