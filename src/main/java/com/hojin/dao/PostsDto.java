package com.hojin.dao;

import com.hojin.api.Posts;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class PostsDto {
	private String name;
	private String author;
	private String content;
	
	public Posts toEntity() {
		return Posts.builder()
				.name(name)
				.author(author)
				.content(content)
				.build();
	}
}
