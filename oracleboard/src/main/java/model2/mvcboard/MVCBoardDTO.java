package model2.mvcboard;

public class MVCBoardDTO {
	// Ŭ���� ��� ����
	private String idx;				// �Ϸù�ȣ
	private String name;			// ����� �̸�
	private String title;			// �Խù� ����
	private String content;			// �Խù� ����
	private java.sql.Date postdate;	// �������
	private String ofile;			// ���� ���ϸ�
	private String sfile;			// ����� ���ϸ�
	private int downcount;			// �ٿ�ε� Ƚ��
	private String pass;			// ��й�ȣ
	private int visitcount;			// ��ȸ��
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	public String getOfile() {
		return ofile;
	}
	public void setOfile(String ofile) {
		this.ofile = ofile;
	}
	public String getSfile() {
		return sfile;
	}
	public void setSfile(String sfile) {
		this.sfile = sfile;
	}
	public int getDowncount() {
		return downcount;
	}
	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}
	
}