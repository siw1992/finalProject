package or.Lincoln_FinalProject.mvc.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping(value= {"/index","/"})//���߼����ÿ��� �߰�ȣ ���. �⺻���� GET.
	//ModelAndView ����� �ʿ� ����. index�� form�� �ܼ��ϰ� <�丸> �����ֹǷ�.
	public String defaultPage() {
		System.out.println("test");
		return "index"; //���� ���ε� �ּ�
	}
	@RequestMapping(value="/myindex")
	public String myDefaultView() {
		return "index";
	}
}
