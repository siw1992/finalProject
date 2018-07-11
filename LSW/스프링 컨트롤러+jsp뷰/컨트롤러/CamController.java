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
	//javaScript���� �����ϴ� �޼���
	//g,p,s,t�������� ��� �������, ���������� ��ȯ�ȴ�
	//��ȯ���� : ���̷μ�����/�ɹڼ�����/���⼾����/�µ�������
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
				//�������� �����̰ų�, �̻��Ұ�� ������ ��ȯ��
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
	
	// CCTV_2�� ���� ����
	@RequestMapping(value = "/camTest_sep")
	public String camTest_sep() {
		//����ؼ� �� ������...
		return "camTest_sep";
	}
	
	@RequestMapping(value="/sensor")
	public String getSensorValues(String name,String sensorName,String status)
	{
		StateNumber.getStateNumber().save(name,sensorName,status);
		System.out.println(name+"/"+sensorName+"/"+status);
		return "getSavedValues";
	}
	
	// ���ڽ�
	@RequestMapping(value = "/bbTest")
	public String bbTest(Model m) {
		//�̰͵� �и� �ؾ���
		String path = "C:\\kosta182\\spring\\workspace\\spring0615_mvc\\BlackBox\\mp4";
		File d = new File(path);
		File fileList[] = d.listFiles();
		StringBuffer sb = new StringBuffer();
		int index = 1;
		for (File t : fileList) {
			if (t.isFile())// �����̶��
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
