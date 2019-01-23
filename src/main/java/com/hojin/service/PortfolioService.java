package com.hojin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hojin.api.PortfolioPosts;
import com.hojin.dao.PortfolioDto;
import com.hojin.dao.PortfolioRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PortfolioService {
	private PortfolioRepository repository;
	
	public void save(PortfolioDto dto) {
		repository.save(dto.toEntity()).getId();
	}
	
	public Page<PortfolioPosts> findAllDesc(Pageable pageable){
		return repository.findAll(pageable);
	}
}
