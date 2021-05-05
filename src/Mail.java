
public class Mail {
	private static int seq = 0;
	private String title;
	private String content;
	private String receiverName;
	private String receiverEmail;
	private String senderName;
	private String senderEmail;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void Seqplus() {
		this.seq = this.seq + 1;
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
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverEmail() {
		return receiverEmail;
	}
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	@Override
	public String toString() {
		return "Mail [title=" + title + ", content=" + content + ", receiverName=" + receiverName + ", receiverEmail="
				+ receiverEmail + ", senderName=" + senderName + ", senderEmail=" + senderEmail + "]";
	}
}
