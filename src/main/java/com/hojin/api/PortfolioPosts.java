package com.hojin.api;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PortfolioPosts {
	@Id
	@GeneratedValue
	public long id;
	@Column
	public String title;
	@Column
	public String subtitle;
	@Column
	public String content;
	@Column
	public String filename;
	@Column 
	public String filepath;
	
	@Builder
	public PortfolioPosts(String title, String subtitle, String content, String filename, String filepath) {
		this.title = title;
		this.subtitle = subtitle;
		this.content = content;
		this.filename = filename;
		this.filepath = filepath;
	}
}
