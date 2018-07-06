package or.Lincoln_FinalProject.mvc.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import or.Lincoln_FinalProject.mvc.dao.ProcessDao;
import or.Lincoln_FinalProject.vo.EmergencyVO;
import or.Lincoln_FinalProject.vo.EventVO;
import or.Lincoln_FinalProject.vo.SensorsVO;

@Controller
@RequestMapping(value="/input")
public class InputCotroller {
	
	@Autowired
	private ProcessDao pao;
	
	@RequestMapping(value="/event")
	public String inputEvent(HttpServletRequest req, Model m) {
		EventVO evo = new EventVO();
		
		//EventCode가 반환 형식이 int로 다르기 때문에 한번에 받지 못하여 각각 값을 주입한다.
		evo.setEventCode(Integer.parseInt(req.getParameter("eventCode").toString()));
		evo.setEventDay(req.getParameter("eventDay").toString());
		evo.setEventHour(req.getParameter("eventHour").toString());
        evo.setEventLocation(req.getParameter("eventLocation").toString());
		evo.setEventMinute(req.getParameter("eventMinute").toString());
		evo.setEventMonth(req.getParameter("eventMonth").toString());
		evo.setEventSecond(req.getParameter("eventSecond").toString());
		evo.setEventYear(req.getParameter("eventYear").toString());
		evo.setGPS(req.getParameter("GPS").toString());
		
		m.addAttribute("event", evo);
		
		pao.eventInsert(evo);
		return "eventView";
	}
	
	@RequestMapping(value = "/emergency")
	public String inputEmergency(HttpServletRequest req ,Model m) {
		EmergencyVO emvo = new EmergencyVO();

		//emvo.setFighterFighterCode((int)cs.session.getAttribute("fireFighterCode"));
		emvo.setFireFighterCode(1);
		emvo.setEmcDay(req.getParameter("emcDay").toString());
		emvo.setEmcGPS(req.getParameter("emcGPS").toString());
		emvo.setEmcGyro(req.getParameter("emcGyro").toString());
		emvo.setEmcHeartRate(req.getParameter("emcHeartRate").toString());
		emvo.setEmcHour(req.getParameter("emcHour").toString());
		emvo.setEmcLocation(req.getParameter("emcLocation").toString());
		emvo.setEmcMinute(req.getParameter("emcMinute").toString());
		emvo.setEmcMonth(req.getParameter("emcMonth").toString());
		emvo.setEmcSmoke(req.getParameter("emcSmoke").toString());
		emvo.setEmcTemperature(req.getParameter("emcTemperature").toString());
		emvo.setEmcYear(req.getParameter("emcYear").toString());
		emvo.setEmergencyCode(req.getParameter("emergencyCode").toString());
		emvo.setEventSecond(req.getParameter("eventSecond").toString());
		
		m.addAttribute("emergency", emvo);
		pao.emergencyInsert(emvo);
		return "emergencyView";
	}
	
	@RequestMapping(value = "/sensors")
	public String inputSensors(HttpServletRequest req, Model m) {
		SensorsVO sevo = new SensorsVO();
		
		sevo.setFireFighterCode(1);
		sevo.setEventCode(1);
		sevo.setVideoCode(req.getParameter("videoCode").toString());
		sevo.setGyroCode(req.getParameter("gyroCode").toString());
		sevo.setTemperatureCode(req.getParameter("temperatureCode").toString());
		sevo.setHeartRateCode(req.getParameter("heartRateCode").toString());
		sevo.setSmokeCode(req.getParameter("smokeCode").toString());
		
		m.addAttribute("sensors", sevo);
		
		pao.sensorsInsert(sevo);
		return "sensorsView";
	}

}
