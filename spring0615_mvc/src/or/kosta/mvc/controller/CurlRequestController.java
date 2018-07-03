package or.kosta.mvc.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import or.kosta.vo.CBoardVO;

@Controller
public class CurlRequestController {

	@GetMapping("/curlForm")
	public String curlForm() {
		return "curlForm";
	}

	@PostMapping("/curlDemo")
	public String curlResp(String uname, String content, Model m) {
		System.out.println("post ���...");
		System.out.println("Uname : " + uname);
		System.out.println("Content : " + content);
		StringBuffer sb = new StringBuffer();
		sb.append(uname).append("\n");
		sb.append(content).append("\n");
		m.addAttribute("msg", sb.toString());
		return "curlResp";
	}

	@GetMapping("/curlDemo")
	public String curlResp(String uname, String content, Model m, HttpServletRequest req) {
		System.out.println("get ���..." + req.getRemoteAddr());
		System.out.println("Uname : " + uname);
		System.out.println("Content : " + content);
		StringBuffer sb = new StringBuffer();
		sb.append(uname).append("\n");
		sb.append(content).append("\n");
		m.addAttribute("msg", sb.toString());
		return "curlResp";
	}

	@PostMapping("/upsave")
	public String upsave(CBoardVO vo, HttpServletRequest req, Model m) {
		String uname = vo.getUname();
		String content = vo.getContent();
		MultipartFile mfile = vo.getMfile();

		System.out.println("post ���...");
		System.out.println("Uname : " + uname);
		System.out.println("Content : " + content);
		StringBuffer sb = new StringBuffer();
		sb.append(uname).append("\n");
		sb.append(content).append("\n");
		m.addAttribute("msg", sb.toString());

		// ���Ͼ��ε�-----------------------------//
		HttpSession session = req.getSession();
		// getServletContext()��ü ������ ������
		// ������!(�ý��ۿ� �´� documentroot(�ֻ������) ��θ� �����´�)
		String r_path = session.getServletContext().getRealPath("/");
		System.out.println(r_path);

		// <mvc:resources location="/resources/" mapping="/resources/**"/>
		String img_path = "resources\\img\\";
		System.out.println(img_path);

		// �����ؼ� �̹����� ����� ��ΰ��� ����
		StringBuffer imgSb = new StringBuffer();
		imgSb.append(r_path).append(img_path);

		// upload�� ���� �̸��� path�� ����
		String oriFn = mfile.getOriginalFilename();
		imgSb.append(oriFn);
		System.out.println(oriFn);

		File f = new File(imgSb.toString());
		try {
			mfile.transferTo(f);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "curlResp";
	}
	
	@GetMapping("/isOn")
	public String isOn(String state)
	{
		//�� ����
		StateNumber.getStateNumber().save(state);
		return "onoff";
	}
	
	@GetMapping("/onoff")
	public String onoff(String state,HttpServletRequest req)
	{
		System.out.println("state : " + state);
		//�� ����
		StateNumber.getStateNumber().save(state);
		return "onoff";
	}
	
	@GetMapping("/onoffView")
	public String onoffView(Model m)
	{
		return "onoff";
	}
}
