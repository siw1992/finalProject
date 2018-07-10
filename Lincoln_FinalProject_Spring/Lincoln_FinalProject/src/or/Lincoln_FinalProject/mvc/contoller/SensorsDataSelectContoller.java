package or.Lincoln_FinalProject.mvc.contoller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorsDataSelectContoller {
	
	@RequestMapping(value = "/jsondata")
	public HashMap<String, Object> sensors(HttpServletRequest req){
		
		HashMap<String, Object> map = new HashMap<>();
		
		
		return map;
	}
}
