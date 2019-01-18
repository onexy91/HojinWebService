package com.hojin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hojin.api.Posts;
import com.hojin.dao.PostsDto;
import com.hojin.dao.PostsRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PostsService {
	private PostsRepository repository;
	
	public Long save(PostsDto dto) {
		return repository.save(dto.toEntity()).getId();
	}
	
	public Page<Posts> findAllDesc(Pageable pageable) {
		return  repository.findAll(pageable);
	}
	
	public void clean() {
		repository.deleteAll();
		
	}
	
	
	
	
}
