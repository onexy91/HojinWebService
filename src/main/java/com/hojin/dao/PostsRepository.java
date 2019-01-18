package com.hojin.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hojin.api.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long>{
	
	/*
	 * @Query("SELECT m" + "FROM Mmeber m" + "ORDER BY m.id DESC") Stream<Posts>
	 * findAllDesc();
	 */
}
