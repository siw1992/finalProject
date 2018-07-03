package or.kosta.mvc.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	//기본페이지 설정
	//뷰만 필요할때 이런식으로 사용
	@RequestMapping(value = { "/index", "/" })
	public String defaultPage() {
		System.out.println("defaultPage()");
		return "curlForm";//넘겨주면 알아서 viewReserver가 내부처리해줌
	}

	@RequestMapping(value = "/myindex")
	public String myDefaultView() {
		// System.out.println("myDefaultView()");
		return "index";
	}
	
	@RequestMapping(value = "/bbTest")
	public String bbTest(Model m) {
		// System.out.println("myDefaultView()");
		//StateNumber.getStateNumber().save("valueTest");
		//String str=StateNumber.getStateNumber().view();
		String path="C:\\kosta182\\spring\\workspace\\spring0615_mvc\\BlackBox\\mp4";
		File d = new File(path);
		File fileList[] = d.listFiles(); 
		StringBuffer sb = new StringBuffer();
		int index=1;
		for(File t : fileList)
		{
			if(t.isFile())//파일이라면
			{
				sb.append(index+"."+t.getName().trim()).append("\n");
				index++;
			}
		}
		System.out.println(sb.toString());
		m.addAttribute("msg",sb.toString());
		return "valueTest";
	}
	
	@RequestMapping(value = "/camTest")
	public String camTest() {
		// System.out.println("myDefaultView()");
		return "camTest";
	}
	
	@RequestMapping(value = "/camTest_sep")
	public String camTest_sep() {
		// System.out.println("myDefaultView()");
		return "camTest_sep";
	}
}
