package or.Lincoln_FinalProject.mvc.contoller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import or.Lincoln_FinalProject.mvc.dao.FireFighterDAO;
import or.Lincoln_FinalProject.vo.FireFighterVO;


@Controller
public class RegisterMemberController {
	@Autowired
	private FireFighterDAO dao;
	
	@RequestMapping(value="/submit")
	public String submit() {
		return "register";
	}
	
	@RequestMapping(value="/checkid")
	   public String checkid(HttpServletRequest request,
	         HttpServletResponse response) throws IOException
	   {
	      request.setCharacterEncoding("utf-8");
	      PrintWriter out = response.getWriter();

	      String fireFighterCode = request.getParameter("fireFighterCode");
	      System.out.println(fireFighterCode);
	      int cnt = 0;
	      if(fireFighterCode  !=  null){
	         cnt = dao.isAlready(Integer.parseInt(fireFighterCode));
	         System.out.println(cnt);
	         if (cnt > 0) {
	            out.println(cnt);
	         } else {
	            out.println(cnt);
	         }
	      }
	     
	      return null;
	   }
	
		@PostMapping(value="/insert_action")
		public String upload(Model model,HttpServletRequest req) {
			FireFighterVO vo = new FireFighterVO();
			vo.setFireFighterCode(Integer.parseInt(req.getParameter("fireFighterCode").toString()));
			vo.setFireStationCode(Integer.parseInt(req.getParameter("fireStationCode").toString()));
			vo.setIP(req.getParameter("IP").toString());
			vo.setFireFighterName(req.getParameter("fireFighterName").toString());
			//fao.isAlready(vo);
			dao.isReady(vo);
			model.addAttribute("vo", vo);
			return "";
		}
}
