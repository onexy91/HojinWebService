package com.hojin.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private String author;
	@Column
	private String content;
	
	
	
	@Builder
	public Posts(String name, String author, String content) {
		this.name = name;
		this.author = author;
		this.content = content;
	}
}
