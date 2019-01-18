package com.hojin.api;

import lombok.Data;

@Data
public class PostsConfig {
	private int startRow;
	private int endRow;
	
	private int pageIndex = 1;
	private int pageSize = 10;
	private int pageGroupSize = 3;
}
