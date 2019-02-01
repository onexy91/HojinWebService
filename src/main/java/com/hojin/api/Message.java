package com.hojin.api;

import lombok.Data;

@Data
public class Message {
	private MessageType type;
	private String content;
	private String sender;
	private String roomId;
	public enum MessageType {
		CHAT,
		JOIN,
		LEAVE
	}
	
}
