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
	
	@RequestMapping(value= {"/index","/"})//다중설정시에는 중괄호 사용. 기본값은 GET.
	//ModelAndView 사용할 필요 없음. index나 form은 단순하게 <뷰만> 보여주므로.
	public String defaultPage() {
		System.out.println("test");
		return "index"; //실제 매핑될 주소
	}
	@RequestMapping(value="/myindex")
	public String myDefaultView() {
		return "index";
	}
	
	@Autowired
	private UserDAO dao; 
	
	// 시작하면 폼창이 뜸
	@GetMapping("loginform")
	public String loginform() {
		return "login2";
	}
	//id.pwd가 맞으면 실행
	@PostMapping("loginProcess")
	public String login(MonitorsUserVO mvo, Model m) {
		//값을 저장하는 곳
		int count = dao.idMonitor(mvo);
		//입력한 아이디가 맞으면
		if(count != 0) {	
			return "main";
			
		}else {
			ModelAndView mav = new ModelAndView();			
			mav.setViewName("erro");
			mav.addObject("msg","다시 로그인 해주세요");			
			return "noresult";
		}
		
	}
	@GetMapping("logout")
	public String logout(HttpSession htp) {
		htp.removeAttribute("userid");
		return "index";
	}
	
	
	
}
