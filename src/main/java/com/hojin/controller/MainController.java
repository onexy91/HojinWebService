package com.hojin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.hojin.api.Posts;
import com.hojin.component.FileUploadUtil;
import com.hojin.dao.PortfolioDto;
import com.hojin.dao.PostsDto;
import com.hojin.service.PortfolioService;
import com.hojin.service.PostsService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Controller
@Slf4j
public class MainController {

	private PostsService postsService;
	private PortfolioService portfolioService;
	private PortfolioDto portfoliodto; 
	private FileUploadUtil uploadutil;
	
	protected String getRemoteIp() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = req.getRemoteAddr();
		}
		log.debug(req.getHeader("user-agent"));

		return ip;
	}

	protected String getClientInfo() {
		return "C:" + getRemoteIp() + ", Rq:";
	}

	@ResponseBody
	@PostMapping("/posts")
	public Long showView(@RequestBody PostsDto dto) {
		log.info(getClientInfo() + "/posts"+ dto.getName()+" "+ dto.getAuthor()+" "+dto.getContent());
		
		return postsService.save(dto);

	}
	
	
	@RequestMapping("/uploadfile")
	public String upload(HttpServletRequest request, @RequestPart MultipartFile files) {
		log.info(getClientInfo()+ "/uploadfile"+" "+files.getOriginalFilename());
		
		  portfoliodto.setTitle(request.getParameter("title"));
		  portfoliodto.setSubtitle(request.getParameter("subtitle"));
		  portfoliodto.setContent(request.getParameter("content"));
		  portfoliodto.setFilename(files.getOriginalFilename());
		  
		  if (!files.isEmpty()) {
			  uploadutil.doWork(request, files);
		  }
		  portfolioService.save(portfoliodto);
		 
		
		return "portfolio";
	}

	@GetMapping("/")
	public String postList(HttpServletResponse response ,Model model,
			@PageableDefault(sort = {"id"}, direction = Direction.DESC, size = 3) Pageable pageable) {
		log.info(getClientInfo() + "/");
		Page<Posts>postList = postsService.findAllDesc(pageable);
		try {
			model.addAttribute("postlist",postList);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@GetMapping("/portfolio")
	public String portfolio() {
		
		return "portfolio";
	}

}
