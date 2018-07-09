package or.kosta.mvc.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CamController {
	// CCTV
	@RequestMapping(value = "/camTest_sep")
	public String camTest_sep() {
		//계속해서 값 보내기...
		return "camTest_sep";
	}
	
	@RequestMapping(value="/sensor")
	public String getSensorValues(String sensorName,String status)
	{
		StateNumber.getStateNumber().save(sensorName,status);
		System.out.println(sensorName + " / " + status);
		return "getSavedValues";
	}
	
	// 블랙박스
	@RequestMapping(value = "/bbTest")
	public String bbTest(Model m) {
		String path = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\BlackBox\\mp4";
		File d = new File(path);
		File fileList[] = d.listFiles();
		StringBuffer sb = new StringBuffer();
		int index = 1;
		for (File t : fileList) {
			if (t.isFile())// 파일이라면
			{
				sb.append(index + "." + t.getName().trim()).append("\n");
				index++;
			}
		}
		System.out.println(sb.toString());
		m.addAttribute("msg", sb.toString());
		return "valueTest";
	}
}
