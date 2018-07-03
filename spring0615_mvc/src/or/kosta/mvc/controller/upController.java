package or.kosta.mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class upController {

	private static final int BUFFER_SIZE = 4096;

	@RequestMapping(value = "/fileDown", method = RequestMethod.GET)
	public void fileDown(@RequestParam("fileName") String fileName,
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ServletContext context = request.getServletContext();
		
		// <mvc:resources location="/resources/" mapping="/resources/**"/>
		String img_path = "resources\\img\\";
		System.out.println(img_path);

		String path = session.getServletContext().getRealPath("resources/img/")
				+ fileName;
		System.out.println("fileDown : " + path);

		File downloadFile = new File(path);
		
		FileInputStream fi = new FileInputStream(downloadFile);
		String mimeType = context.getMimeType(path);
		
		if(mimeType == null)
		{
			mimeType = "application/octet-stream";
		}
		//지정된 마임타입 세팅
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		//다운로드 type을 설정
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while((bytesRead = fi.read(buffer)) != -1)
		{
			outStream.write(buffer,0,bytesRead);
		}
		fi.close();
		outStream.close();
	}
}
