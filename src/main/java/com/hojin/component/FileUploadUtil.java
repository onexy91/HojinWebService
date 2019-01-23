package com.hojin.component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileUploadUtil {
	private FileOutputStream out;
	private BufferedOutputStream bos;
	private ServletContext context;

	public void doWork(HttpServletRequest request, MultipartFile files) {

		context = request.getSession().getServletContext(); // 웹 컨테이너 저장소 ? 같은
		String fileUploadPath = context.getRealPath(""); // 굳이 context 경로 사용하지 않아도됨.
		System.out.println(fileUploadPath);
		String filename = files.getOriginalFilename();

		try {
			File file = new File(fileUploadPath + File.pathSeparator + filename);
			out = new FileOutputStream(file);
			bos = new BufferedOutputStream(out);

			FileCopyUtils.copy(files.getInputStream(), bos);

			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
