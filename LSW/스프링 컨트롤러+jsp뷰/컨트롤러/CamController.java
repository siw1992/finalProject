package or.kosta.mvc.controller;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CamController {
	
	private String name=null;
	//javaScript에서 접근하는 메서드
	//g,p,s,t센서값이 모두 있을경우, 정상적으로 반환된다
	//반환형식 : 자이로센서값/심박센서값/연기센서값/온도센서값
	@RequestMapping(value = "/getValues")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Object getValues(String name)
	{
		if(name==null || this.name==null || name.equals("0")) return "error";
		
		System.out.println("get values of : "+name);
		StringBuffer sb = new StringBuffer();
		try
		{
			for(int i=0;i<4;i++)
			{
				String s=StateNumber.getSensorValues(name,i);
				//센서값이 공백이거나, 이상할경우 에러를 반환함
				if(s.equals("n")) return "error";
				sb.append(s).append("/");
			}
			return sb.toString();
		}
		catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/camTest")
	public String camTest() {
		// System.out.println("myDefaultView()");
		return "camTest";
	}
	
	// CCTV_2개 따로 관제
	@RequestMapping(value = "/camTest_sep")
	public String camTest_sep() {
		//계속해서 값 보내기...
		return "camTest_sep";
	}
	
	@RequestMapping(value="/sensor")
	public String getSensorValues(String name,String sensorName,String status)
	{
		StateNumber.getStateNumber().save(name,sensorName,status);
		System.out.println(name+"/"+sensorName+"/"+status);
		return "getSavedValues";
	}
	
	// 블랙박스
	@RequestMapping(value = "/bbTest")
	public String bbTest(Model m) {
		//이것도 분리 해야함
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
