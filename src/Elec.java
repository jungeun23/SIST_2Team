/**
 * 전자결재문서 정보를 담기위한 클래스
 * 이하 getter, setter 메서드로만 구성돼 있음
 * 
 *  @param seq 문서의 번호를 매기기 위한 변수
 *  @param title 문서의 제목을 저장하기 위한 변수
 *  @param content 문서의 내용을 저장하기 위한 변수
 *  @param docuPW 문서의 패스워드를 저장하기 위한 변수
 *  @param position 문서 작성자의 직급을 저장하기 위한 변수
 *  @param name 문서 작성자의 이름을 저장하기 위한 변수
 *
 */
public class Elec {
	private static int seq = 0;
	private String title;
	private String content;
	private String docuPW;
	private String position;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDocuPW() {
		return docuPW;
	}
	public void setDocuPW(String docuPW) {
		this.docuPW = docuPW;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
