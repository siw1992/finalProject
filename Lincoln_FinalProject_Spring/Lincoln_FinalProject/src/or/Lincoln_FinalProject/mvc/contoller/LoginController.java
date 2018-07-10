package or.Lincoln_FinalProject.mvc.contoller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import or.Lincoln_FinalProject.mvc.dao.UserDAO;
import or.Lincoln_FinalProject.vo.MonitorsUserVO;

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
	
	@Autowired
	private UserDAO dao; 
	
	// �����ϸ� ��â�� ��
	@GetMapping("loginform")
	public String loginform() {
		return "login2";
	}
	//id.pwd�� ������ ����
	@PostMapping("loginProcess")
	public String login(MonitorsUserVO mvo, Model m) {
		//���� �����ϴ� ��
		int count = dao.idMonitor(mvo);
		//�Է��� ���̵� ������
		if(count != 0) {	
			return "main";
			
		}else {
			ModelAndView mav = new ModelAndView();			
			mav.setViewName("erro");
			mav.addObject("msg","�ٽ� �α��� ���ּ���");			
			return "noresult";
		}
		
	}
	@GetMapping("logout")
	public String logout(HttpSession htp) {
		htp.removeAttribute("userid");
		return "index";
	}
	
	
	
}
