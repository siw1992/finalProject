package or.Lincoln_FinalProject.mvc.contoller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import or.Lincoln_FinalProject.mvc.component.SessionInjection;

@Controller
public class FireLoginController {
	@Autowired
	SessionInjection injec;
	
	@RequestMapping("/flogin")
	public String flogin(Model m ,HttpSession session, String fireFighterCode, String password) {
		
		session.setAttribute("fireFighterCode", fireFighterCode);
		injec.setSession("fireFighterCode", fireFighterCode);
		System.out.println(session.getAttribute("fireFighterCode").toString());
		m.addAttribute("fireFighterCode",session.getAttribute("fireFighterCode").toString());
		
		return "trans";
	}
}