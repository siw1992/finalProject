package or.Lincoln_FinalProject.mvc.component;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionInjection {
	
	@Autowired
	private HttpSession session;

	public String getSession(String key) {
		return this.session.getAttribute(key).toString();
	}

	public void setSession(String key, String value) {
		this.session.setAttribute(key, value);
		System.out.println(session.getAttribute(key).toString());
	}
	
	
}
