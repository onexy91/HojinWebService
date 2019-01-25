package com.hojin.component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hojin.dao.PortfolioDto;
import com.hojin.service.PortfolioService;

import javassist.ClassClassPath;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUploadUtil {
	private FileOutputStream out;
	private BufferedOutputStream bos;
	private ServletContext context;
	@Autowired
	private PortfolioDto portfoliodto;
	@Autowired
	private PortfolioService service;

	public void doWork(HttpServletRequest request, MultipartFile files) {

		// context = request.getSession().getServletContext(); // 웹 컨테이너 저장소 ? 같은
		// String fileUploadPath = context.getRealPath(""); // 굳이 context 경로 사용하지 않아도됨.
		File filepath = new File("C://webImage");
		String fileName = files.getOriginalFilename();

		portfoliodto.setTitle(request.getParameter("title"));
		portfoliodto.setSubtitle(request.getParameter("subtitle"));
		portfoliodto.setContent(request.getParameter("content"));
		portfoliodto.setFilename(fileName);
		// portfoliodto.setFilepath(filepath.getAbsolutePath()+File.separator);

		// System.out.println(filepath.getAbsolutePath());
		// System.out.println(filepath.getPath());

		try {
			if (!filepath.exists()) {
				filepath.mkdir();
			}
			File file = new File(filepath.getAbsoluteFile() + File.separator + fileName);
			// System.out.println(file.getCanonicalFile());
			// System.out.println(file.getAbsolutePath());
			out = new FileOutputStream(file);
			bos = new BufferedOutputStream(out);

			FileCopyUtils.copy(files.getInputStream(), bos);

			log.info("File Upload Success");

			service.save(portfoliodto);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
