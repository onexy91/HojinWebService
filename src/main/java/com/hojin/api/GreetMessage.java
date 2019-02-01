package com.hojin.api;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class GreetMessage {
	private String content;
	
	public GreetMessage(String content) {
		this.content = content;
	}
}
