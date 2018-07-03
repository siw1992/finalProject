package or.kosta.vo;

import org.springframework.web.multipart.MultipartFile;

public class CBoardVO {
	private String uname;//이름
	private String content;//컨텐트
	private MultipartFile mfile;//업로드파일
	
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
