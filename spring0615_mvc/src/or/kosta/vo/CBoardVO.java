package or.kosta.vo;

import org.springframework.web.multipart.MultipartFile;

public class CBoardVO {
	private String uname;//�̸�
	private String content;//����Ʈ
	private MultipartFile mfile;//���ε�����
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MultipartFile getMfile() {
		return mfile;
	}
	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}
}
