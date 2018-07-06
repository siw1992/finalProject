package or.Lincoln_FinalProject.mvc.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
