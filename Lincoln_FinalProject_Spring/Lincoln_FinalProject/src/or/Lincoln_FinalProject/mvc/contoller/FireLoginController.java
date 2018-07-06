package or.Lincoln_FinalProject.mvc.contoller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FireLoginController {
	
	@RequestMapping("/flogin")
	public String flogin(Model m ,HttpSession session, String fireFighterCode, String password) {
		
		session.setAttribute("fireFighterCode", fireFighterCode);
		m.addAttribute("fireFighterCode\"",session.getAttribute("fireFighterCode").toString());
		
		return "main";
	}
}