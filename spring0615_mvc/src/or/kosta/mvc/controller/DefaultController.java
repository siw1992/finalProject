package or.kosta.mvc.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	//�⺻������ ����
	//�丸 �ʿ��Ҷ� �̷������� ���
	@RequestMapping(value = { "/index", "/" })
	public String defaultPage() {
		System.out.println("defaultPage()");
		return "curlForm";//�Ѱ��ָ� �˾Ƽ� viewReserver�� ����ó������
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
			if(t.isFile())//�����̶��
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
