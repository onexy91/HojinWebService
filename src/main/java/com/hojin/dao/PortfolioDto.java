package com.hojin.dao;

import org.springframework.stereotype.Component;

import com.hojin.api.PortfolioPosts;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Component
public class PortfolioDto {
	public String title;
	public String subtitle;
	public String content;
	public String filename;
	public String filepath;
	
	public PortfolioPosts toEntity() {
		return PortfolioPosts.builder()
				.title(title)
				.subtitle(subtitle)
				.content(content)
				.filename(filename)
				.filepath(filepath)
				.build();
			
				
	}
}
