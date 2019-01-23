package com.hojin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hojin.api.PortfolioPosts;

public interface PortfolioRepository extends JpaRepository<PortfolioPosts, Long>{

}
